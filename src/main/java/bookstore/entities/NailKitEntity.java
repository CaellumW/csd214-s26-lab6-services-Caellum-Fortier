package bookstore.entities;

import bookstore.pojos.NailProduct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
public class NailKitEntity extends NailProductEntity {
    @Column(name = "kit_type")
    private String kitType;

    public String getKitType() {
        return kitType;
    }

    public void setKitType(String kitType) {
        this.kitType = kitType;
    }

    @Column(name = "kit_level")
    private String kitLevel;

    public String getKitLevel() {
        return kitLevel;
    }

    public void setKitLevel(String kitLevel) {
        this.kitLevel = kitLevel;
    }

    @Column(name = "items")
    private ArrayList<NailProduct> items;

    public ArrayList<NailProduct> getItems() {
        return items;
    }
    public void setItems(ArrayList<NailProduct>) {
        this.items = items;
    }

    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //    no param constructor
    public NailKitEntity() {
    }

    //    param constructor
    public NailKitEntity(String kitType, String kitLevel, ArrayList<NailProduct> items) {
        this.kitType = kitType;
        this.kitLevel = kitLevel;
        this.items = items;
    }

    //    super constructor
    public NailKitEntity(String id, String brand, int stock, double price, String kitType, String kitLevel, ArrayList<NailProduct> items) {
        super(id, brand, stock, price);
        this.kitType = kitType;
        this.kitLevel = kitLevel;
        this.items = items;
    }
}
