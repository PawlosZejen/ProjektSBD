package pl.pawlos.bazaaa.dao;

import pl.pawlos.bazaaa.db.DatabaseConnection;
import pl.pawlos.bazaaa.model.Uczen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UczenDAO {

    public List<Uczen> findAll() {
        List<Uczen> uczniowie = new ArrayList<>();

        String sql = "SELECT pesel, imie, nazwisko FROM uczniowie";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                uczniowie.add(new Uczen(
                        rs.getLong("pesel"),
                        rs.getString("imie"),
                        rs.getString("nazwisko")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return uczniowie;
    }
}
