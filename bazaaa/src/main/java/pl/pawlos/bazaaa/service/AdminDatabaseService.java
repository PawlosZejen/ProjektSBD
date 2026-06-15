package pl.pawlos.bazaaa.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AdminDatabaseService {

    private final JdbcTemplate jdbcTemplate;

    private static final Map<String, String> PRIMARY_KEYS = Map.ofEntries(
            Map.entry("egzamin", "id_egzaminu"),
            Map.entry("instruktor", "id_instruktora"),
            Map.entry("jazda", "id_jazdy"),
            Map.entry("kategoria_prawa_jazdy", "id_kategorii"),
            Map.entry("kategoriaprawajazdy", "id_kategorii"),
            Map.entry("kurs", "id_kursu"),
            Map.entry("kursant", "pesel"),
            Map.entry("obecnosc_wyklady", "id_obecnosci"),
            Map.entry("platnosc", "id_platnosci"),
            Map.entry("pojazd", "nr_rejestracyjny"),
            Map.entry("rola", "id"),
            Map.entry("uzytkownik", "id"),
            Map.entry("wyklady", "id_wykladu")
    );

    // tabele z auto-increment PK (bigint + sequence)
    private static final Set<String> AUTO_PK_TABLES = Set.of(
            "egzamin",
            "instruktor",
            "jazda",
            "kurs",
            "obecnosc_wyklady",
            "platnosc",
            "rola",
            "uzytkownik",
            "wyklady"
    );

    public AdminDatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getTables() {
        return jdbcTemplate.queryForList(
                "SELECT table_name FROM information_schema.tables " +
                        "WHERE table_schema = 'public' ORDER BY table_name",
                String.class
        );
    }

    public List<String> getColumns(String tableName) {
        return jdbcTemplate.queryForList("""
                SELECT column_name
                FROM information_schema.columns
                WHERE table_schema = 'public'
                  AND table_name = ?
                ORDER BY ordinal_position
                """, String.class, tableName);
    }

    public List<List<Object>> getRows(String tableName) {
        return jdbcTemplate.query("SELECT * FROM \"" + tableName + "\"", (rs, rowNum) -> {
            ResultSetMetaData meta = rs.getMetaData();
            List<Object> row = new ArrayList<>();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                row.add(rs.getObject(i));
            }
            return row;
        });
    }

    public String getPrimaryKey(String tableName) {
        return PRIMARY_KEYS.get(tableName);
    }

    private Map<String, Object> convertTypes(Map<String, String> data) {
        Map<String, Object> converted = new LinkedHashMap<>();

        data.forEach((k, v) -> {
            if (v == null || v.isBlank()) {
                converted.put(k, null);
                return;
            }

            // integer / bigint
            if (v.matches("^-?\\d+$")) {
                // użyj Long dla bezpieczeństwa (bigint)
                converted.put(k, Long.valueOf(v));
                return;
            }

            // double precision
            if (v.matches("^-?\\d+[\\.,]\\d+$")) {
                converted.put(k, Double.valueOf(v.replace(',', '.')));
                return;
            }

            // date YYYY-MM-DD
            if (v.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                converted.put(k, java.sql.Date.valueOf(LocalDate.parse(v)));
                return;
            }

            // timestamp YYYY-MM-DD HH:mm[:ss] lub z T
            if (v.matches("^\\d{4}-\\d{2}-\\d{2}[ T]\\d{2}:\\d{2}(:\\d{2})?$")) {
                String norm = v.replace(" ", "T");
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm[:ss]");
                LocalDateTime dt = LocalDateTime.parse(norm, fmt);
                converted.put(k, Timestamp.valueOf(dt));
                return;
            }

            // tekst
            converted.put(k, v);
        });

        return converted;
    }

    public void insert(String tableName, Map<String, String> data) {
        String pk = getPrimaryKey(tableName);

        data.remove("_csrf");

        // usuwamy PK tylko dla tabel z auto-increment
        if (AUTO_PK_TABLES.contains(tableName)) {
            data.remove(pk);
        }

        Map<String, Object> converted = convertTypes(data);

        String columns = String.join(", ", converted.keySet());
        String values = String.join(", ", Collections.nCopies(converted.size(), "?"));

        jdbcTemplate.update(
                "INSERT INTO \"" + tableName + "\" (" + columns + ") VALUES (" + values + ")",
                converted.values().toArray()
        );
    }

    public void update(String tableName, String pkValue, Map<String, String> data) {
        String pk = getPrimaryKey(tableName);

        data.remove("_csrf");
        data.remove(pk);

        Map<String, Object> converted = convertTypes(data);

        String setClause = String.join(", ",
                converted.keySet().stream().map(c -> c + "=?").toList()
        );

        List<Object> params = new ArrayList<>(converted.values());
        params.add(pkValue);

        jdbcTemplate.update(
                "UPDATE \"" + tableName + "\" SET " + setClause + " WHERE \"" + pk + "\"=?",
                params.toArray()
        );
    }

    public void delete(String tableName, String pkValue) {
        String pk = getPrimaryKey(tableName);
        jdbcTemplate.update(
                "DELETE FROM \"" + tableName + "\" WHERE \"" + pk + "\"=?",
                pkValue
        );
    }
}
