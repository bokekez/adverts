package codeVibe.task.repository;

import codeVibe.task.model.Adverts;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdvertsDAO {

    private final Connection connection;

    public AdvertsDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Adverts> getAllAdverts() {
        List<Adverts> advertsList = new ArrayList<>();
        String query = "SELECT * FROM adverts";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Adverts advert = new Adverts();
                advert.setId(rs.getInt("id"));
                advert.setTitle(rs.getString("title"));
                advert.setFuelType(rs.getString("fuel_type"));
                advert.setPrice(rs.getInt("price"));
                advert.setIsNew(rs.getBoolean("is_new"));
                advert.setMileage(rs.getInt("mileage"));
                advert.setFirstRegistration(rs.getDate("first_registration"));
                advert.setCreatedAt(rs.getTimestamp("created_at"));
                advert.setUpdatedAt(rs.getTimestamp("updated_at"));
                advertsList.add(advert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch adverts.", e);
        }
        return advertsList;
    }

    public Adverts getAdvertById(int id) {
        String query = "SELECT * FROM adverts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Adverts advert = new Adverts();
                    advert.setId(rs.getInt("id"));
                    advert.setTitle(rs.getString("title"));
                    advert.setFuelType(rs.getString("fuel_type"));
                    advert.setPrice(rs.getInt("price"));
                    advert.setIsNew(rs.getBoolean("is_new"));
                    advert.setMileage(rs.getInt("mileage"));
                    advert.setFirstRegistration(rs.getDate("first_registration"));
                    advert.setCreatedAt(rs.getTimestamp("created_at"));
                    advert.setUpdatedAt(rs.getTimestamp("updated_at"));
                    return advert;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch advert by ID.", e);
        }
        return null;
    }
}
