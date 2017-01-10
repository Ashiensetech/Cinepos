package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "circuit")
public class VatSetting {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "rating")
    private String rating;

    @Basic
    @Column(name = "status")
    private int status;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "created_by",nullable = true)
    private int createdBy;

    @Basic
    @Column(name = "updated_by",nullable = true)
    private int updatedBy;

    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at",nullable = true)
    private Timestamp updatedAt;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VatSetting that = (VatSetting) o;

        if (Id != that.Id) return false;
        if (status != that.status) return false;
        if (createdBy != that.createdBy) return false;
        if (updatedBy != that.updatedBy) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        return updatedAt != null ? updatedAt.equals(that.updatedAt) : that.updatedAt == null;
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + createdBy;
        result = 31 * result + updatedBy;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "vatSetting{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
