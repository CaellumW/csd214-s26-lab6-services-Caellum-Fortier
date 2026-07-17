package bookstore.pojos;

import bookstore.entities.NailKitEntity;
import bookstore.entities.NailProductEntity;

import java.util.ArrayList;
import java.util.Objects;

public class NailKit extends NailProduct {
    private String kitType;
    private String kitLevel;
    private ArrayList<NailProductEntity> items;
    private double price;
    private int stock;

    public String getKitType() {
        return kitType;
    }

    public void setKitType(String kitType) {
        this.kitType = kitType;
    }

    public String getKitLevel() {
        return kitLevel;
    }

    public void setKitLevel(String kitLevel) {
        this.kitLevel = kitLevel;
    }

    public ArrayList<NailProductEntity> getItems() {
        return items;
    }

    public void setItems(ArrayList<NailProductEntity> items) {
        this.items = items;
    }

    @Override
    public void sellItem() {
        stock = stock - 1;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getStock() {
        return stock;
    }

    @Override
    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NailKit nailKit = (NailKit) o;
        return price == nailKit.price && stock == nailKit.stock && Objects.equals(kitType, nailKit.kitType) && Objects.equals(kitLevel, nailKit.kitLevel) && Objects.equals(items, nailKit.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), kitType, kitLevel, items, price, stock);
    }

    @Override
    public String toString() {
        return "NailKit{" +
                "kitType='" + kitType + '\'' +
                ", kitLevel='" + kitLevel + '\'' +
                ", items=" + items +
                ", price=" + price +
                ", stock=" + stock +
                "} " + super.toString();
    }

    public NailKit() {
    }

    public NailKit(String kitType, String kitLevel, ArrayList<NailProductEntity> items) {
        this.kitType = kitType;
        this.kitLevel = kitLevel;
        this.items = items;
    }

    public NailKit(int stock, String brand, double price, String kitType, String kitLevel, ArrayList<NailProductEntity> items) {
        super(stock, brand, price);
        this.kitType = kitType;
        this.kitLevel = kitLevel;
        this.items = items;
    }

    public NailKitEntity toEntity() {
        NailKitEntity entity = new NailKitEntity();
        entity.setId(this.getDbId());
        entity.setProductId(this.getProductId());
        entity.setBrand(this.getBrand());
        entity.setPrice(this.getPrice());
        entity.setStock(this.getStock());
        entity.setKitType(this.getKitType());
        entity.setKitLevel(this.getKitLevel());
        entity.setItems(this.getItems());
        return entity;
    }

    public static NailKit fromEntity(NailKitEntity entity) {
        NailKit kit = new NailKit(
                entity.getStock(),
                entity.getBrand(),
                entity.getPrice(),
                entity.getKitType(),
                entity.getKitLevel(),
                entity.getItems()
        );
        kit.setDbId(entity.getId());
        kit.setProductId(entity.getProductId());
        return kit;
    }
}
