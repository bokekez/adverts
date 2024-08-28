package codeVibe.task.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Adverts {

    private int id;
    private String title;
    private String fuelType;
    private int price;
    private boolean isNew;
    private int mileage;
    private Date firstRegistration;
    private Date createdAt;
    private Date updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Date getFirstRegistration() {
        return firstRegistration;
    }

    public void setFirstRegistration(Date firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    String sqlScript = """
            CREATE TABLE adverts (
                id SERIAL PRIMARY KEY,
                title VARCHAR(255) NOT NULL,
                fuel_type VARCHAR(255),
                price INTEGER,
                is_new BOOLEAN,
                mileage INTEGER,
                first_registration DATE,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
            );

            CREATE OR REPLACE FUNCTION update_updated_at_column()
            RETURNS TRIGGER AS $$
            BEGIN
               NEW.updated_at = CURRENT_TIMESTAMP;
               RETURN NEW;
            END;
            $$ LANGUAGE plpgsql;

            CREATE TRIGGER update_adverts_updated_at
            BEFORE UPDATE ON adverts
            FOR EACH ROW
            EXECUTE FUNCTION update_updated_at_column();
        """;
}
