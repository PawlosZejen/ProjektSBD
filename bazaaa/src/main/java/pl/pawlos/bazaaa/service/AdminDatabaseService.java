package pl.pawlos.bazaaa.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class AdminDatabaseService {

    private final JdbcTemplate jdbcTemplate;

    public AdminDatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TableInfo> getTables() {
        return jdbcTemplate.query("""
                        select table_name,
                               (
                                   select count(*)
                                   from information_schema.columns c
                                   where c.table_schema = t.table_schema
                                     and c.table_name = t.table_name
                               ) as column_count
                        from information_schema.tables t
                        where table_schema = 'public'
                          and table_type = 'BASE TABLE'
                        order by table_name
                        """,
                (rs, rowNum) -> new TableInfo(
                        rs.getString("table_name"),
                        rs.getInt("column_count"),
                        countRows(rs.getString("table_name"))
                ));
    }

    public List<String> getColumns(String tableName) {
        ensureTableExists(tableName);
        return jdbcTemplate.queryForList("""
                select column_name
                from information_schema.columns
                where table_schema = 'public'
                  and table_name = ?
                order by ordinal_position
                """, String.class, tableName);
    }

    public List<List<Object>> getRows(String tableName) {
        ensureTableExists(tableName);
        return jdbcTemplate.query("select * from " + quoteIdentifier(tableName), (rs, rowNum) -> {
            ResultSetMetaData metadata = rs.getMetaData();
            List<Object> row = new ArrayList<>();

            for (int columnIndex = 1; columnIndex <= metadata.getColumnCount(); columnIndex++) {
                row.add(rs.getObject(columnIndex));
            }

            return row;
        });
    }

    private int countRows(String tableName) {
        Number count = jdbcTemplate.queryForObject("select count(*) from " + quoteIdentifier(tableName), Number.class);
        return count == null ? 0 : count.intValue();
    }

    private void ensureTableExists(String tableName) {
        Integer count = jdbcTemplate.queryForObject("""
                select count(*)
                from information_schema.tables
                where table_schema = 'public'
                  and table_type = 'BASE TABLE'
                  and table_name = ?
                """, Integer.class, tableName);

        if (count == null || count == 0) {
            throw new IllegalArgumentException("Nie znaleziono tabeli: " + tableName);
        }
    }

    private String quoteIdentifier(String identifier) {
        return "\"" + identifier.replace("\"", "\"\"") + "\"";
    }

    public record TableInfo(String name, int columnCount, int rowCount) {
    }
}
