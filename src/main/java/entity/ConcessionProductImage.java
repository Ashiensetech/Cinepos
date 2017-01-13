package entity;

import javax.persistence.*;

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
    private int filePath;

    @Column(name = "is_banner")
    private int isBanner;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @Column(name = "created_by")
    private int createdBy;

    @Column(name = "created_at")
    private int createdAt;

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

    public int getFilePath() {
        return filePath;
    }

    public void setFilePath(int filePath) {
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

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConcessionProductImage that = (ConcessionProductImage) o;

        if (id != that.id) return false;
        if (concessionProductId != that.concessionProductId) return false;
        if (filePath != that.filePath) return false;
        if (isBanner != that.isBanner) return false;
        if (width != that.width) return false;
        if (height != that.height) return false;
        if (createdBy != that.createdBy) return false;
        return createdAt == that.createdAt;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + concessionProductId;
        result = 31 * result + filePath;
        result = 31 * result + isBanner;
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + createdBy;
        result = 31 * result + createdAt;
        return result;
    }

    @Override
    public String toString() {
        return "ConcessionProductImage{" +
                "id=" + id +
                ", concessionProductId=" + concessionProductId +
                ", filePath=" + filePath +
                ", isBanner=" + isBanner +
                ", width=" + width +
                ", height=" + height +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
