package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 1/11/17.
 */
@Entity
@Table(name = "temp_file", schema = "", catalog = "cinepos")
public class TempFile {
    private int id;
    private int token;
    private String path;
    private Timestamp createdDate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "token")
    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TempFile tempFile = (TempFile) o;

        if (id != tempFile.id) return false;
        if (token != tempFile.token) return false;
        if (path != null ? !path.equals(tempFile.path) : tempFile.path != null) return false;
        if (createdDate != null ? !createdDate.equals(tempFile.createdDate) : tempFile.createdDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + token;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
