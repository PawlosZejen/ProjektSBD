package pl.pawlos.bazaaa.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Stream;

@Service
public class AdminService {

    private final JdbcTemplate jdbcTemplate;

    public AdminService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getAllTables() {
        return jdbcTemplate.queryForList("SELECT table_name FROM information_schema.tables WHERE table_schema='public'", String.class);
    }

    public List<String> getColumns(String table) {
        return jdbcTemplate.queryForList("SELECT column_name FROM information_schema.columns WHERE table_name=?", String.class, table);
    }

    public List<Map<String, Object>> getRows(String table) {
        return jdbcTemplate.queryForList("SELECT * FROM " + table);
    }

    public void insertRow(String table, Map<String, String> dane) {
        String columns = String.join(", ", dane.keySet());
        String values = String.join(", ", Collections.nCopies(dane.size(), "?"));
        jdbcTemplate.update("INSERT INTO " + table + " (" + columns + ") VALUES (" + values + ")", dane.values().toArray());
    }

    public void updateRow(String table, Long id, Map<String, String> dane) {
        String setClause = String.join(", ", dane.keySet().stream().map(k -> k + "=?").toList());
        jdbcTemplate.update("UPDATE " + table + " SET " + setClause + " WHERE id=?", Stream.concat(dane.values().stream(), Stream.of(id)).toArray());
    }

    public void deleteRow(String table, Long id) {
        jdbcTemplate.update("DELETE FROM " + table + " WHERE id=?", id);
    }
}
