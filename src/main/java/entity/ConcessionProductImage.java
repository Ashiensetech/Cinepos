package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sarwar on 1/13/2017.
 */
@Entity
@Table(name = "concession_product_image")
public class ConcessionProductImage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "concession_product_id")
    private int concessionProductId;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "is_banner")
    private int isBanner;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @Column(name = "created_by")
    private int createdBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConcessionProductId() {
        return concessionProductId;
    }

    public void setConcessionProductId(int concessionProductId) {
        this.concessionProductId = concessionProductId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getIsBanner() {
        return isBanner;
    }

    public void setIsBanner(int isBanner) {
        this.isBanner = isBanner;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConcessionProductImage that = (ConcessionProductImage) o;

        if (id != that.id) return false;
        if (concessionProductId != that.concessionProductId) return false;
        if (isBanner != that.isBanner) return false;
        if (width != that.width) return false;
        if (height != that.height) return false;
        if (createdBy != that.createdBy) return false;
        if (filePath != null ? !filePath.equals(that.filePath) : that.filePath != null) return false;
        return createdAt != null ? createdAt.equals(that.createdAt) : that.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + concessionProductId;
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + isBanner;
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + createdBy;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ConcessionProductImage{" +
                "id=" + id +
                ", concessionProductId=" + concessionProductId +
                ", filePath='" + filePath + '\'' +
                ", isBanner=" + isBanner +
                ", width=" + width +
                ", height=" + height +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
