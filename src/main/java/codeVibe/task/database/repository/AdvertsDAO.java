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

    public List<Adverts> getAllAdverts(String sortBy) {
        List<Adverts> advertsList = new ArrayList<>();
        String query = "SELECT * FROM adverts";
        if (sortBy != null) {
            switch (sortBy) {
                case "price":
                    query += " ORDER BY price";
                    break;
                case "title":
                    query += " ORDER BY title";
                    break;
                case "createdAt":
                    query += " ORDER BY created_at";
                    break;
                default:
                    query += " ORDER BY id";
            }
        }

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

    public Adverts save(Adverts advert) {
        String query = advert.getId() > 0 
            ? "UPDATE adverts SET title = ?, fuel_type = ?, price = ?, is_new = ?, mileage = ?, first_registration = ?, updated_at = ? WHERE id = ?"
            : "INSERT INTO adverts (title, fuel_type, price, is_new, mileage, first_registration, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, advert.getTitle());
            stmt.setString(2, advert.getFuelType());
            stmt.setInt(3, advert.getPrice());
            stmt.setBoolean(4, advert.isNew());
            stmt.setInt(5, advert.getMileage());
            stmt.setDate(6, new java.sql.Date(advert.getFirstRegistration().getTime()));
            if (advert.getId() > 0) {
                stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
                stmt.setInt(8, advert.getId());
            } else {
                stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            }
            stmt.executeUpdate();

            if (advert.getId() == 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        advert.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save advert.", e);
        }
        return advert;
    }

    public Adverts updateAdvert(Adverts advert) {
        String query = "UPDATE adverts SET title = ?, fuel_type = ?, price = ?, is_new = ?, mileage = ?, first_registration = ?, updated_at = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, advert.getTitle());
            stmt.setString(2, advert.getFuelType());
            stmt.setInt(3, advert.getPrice());
            stmt.setBoolean(4, advert.isNew());
            stmt.setInt(5, advert.getMileage());
            stmt.setDate(6, new java.sql.Date(advert.getFirstRegistration().getTime()));
            stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(8, advert.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update advert.", e);
        }
        return advert;
    }

    public boolean delete(Adverts advert) {
        String query = "DELETE FROM adverts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, advert.getId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete advert.", e);
        }
    }
}
