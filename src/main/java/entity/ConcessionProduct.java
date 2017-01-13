package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sarwar on 1/13/2017.
 */
@Entity
@Table(name = "concession_product")
public class ConcessionProduct {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "annotation")
    private String annotation;

    @Basic
    @Column(name = "category_id")
    private int category_id;

    @Basic
    @Column(name = "unit")
    private int unit;

    @Basic
    @Column(name = "remote_print")
    private int remotePrint;

    @Basic
    @Column(name = "is_combo")
    private int isCombo;

    @Basic
    @Column(name = "status")
    private int status;

    @Basic
    @Column(name = "buying_price")
    private float buying_price;

    @Basic
    @Column(name = "is_price_shift")
    private int isPriceShift;

    @Basic
    @Column(name = "created_by")
    private int createdBy;

    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;



}
