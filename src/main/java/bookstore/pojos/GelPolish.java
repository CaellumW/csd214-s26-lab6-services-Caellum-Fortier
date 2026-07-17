package bookstore.pojos;

import bookstore.entities.GelPolishEntity;

import java.util.Objects;
import java.util.Scanner;

public class GelPolish extends NailProduct{
    private String colourShade;
    private String finish;


    @Override
    public String toString() {
        return "GelPolish{colourShade='" + colourShade + "', " + "finish='" + finish + "', " + super.toString() + "}";
    }

    public GelPolish() {
        super();
    }

    public GelPolish(String colourShade, String finish, String brand, double price, int stock) {
        super(stock, brand, price);
        this.colourShade = colourShade;
        this.finish = finish;
    }

    @Override
    public void edit(Scanner input) {
        super.edit(input);
        System.out.println("Edit Shade [" + this.colourShade + "]:");
        this.colourShade = getInput(input, this.colourShade);
        System.out.println("Edit Finish [" + this.finish + "]:");
        this.finish = getInput(input, this.finish);
    }

    @Override
    public void initialize(Scanner input) {
        // Pass scanner up to parent
        super.initialize(input);

        System.out.println("Enter Shade:");
        this.colourShade = getInput(input, "Unknown Shade");

        System.out.println("Enter Finish:");
        this.finish = getInput(input, "Shiny");

    }

    @Override
    public void sellItem() {
        System.out.println("Enjoy your polish! That colour will look amazing on you");
        setStock(getStock()-1);
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    public String getColourShade() {
        return colourShade;
    }

    public void setColourShade(String colourShade) {
        this.colourShade = colourShade;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public void setPrice(int price) {
        super.setPrice(price);
    }

    @Override
    public int getStock() {
        return super.getStock();
    }

    @Override
    public void setStock(int stock) {
        super.setStock(stock);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GelPolish gelPolish = (GelPolish) o;
        return Objects.equals(colourShade, gelPolish.colourShade) && Objects.equals(finish, gelPolish.finish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colourShade, finish);
    }

    public GelPolishEntity toEntity() {
        GelPolishEntity entity = new GelPolishEntity();
        entity.setId(this.getDbId());
        entity.setProductId(this.getProductId());
        entity.setBrand(this.getBrand());
        entity.setPrice(this.getPrice());
        entity.setStock(this.getStock());
        entity.setColourShade(this.getColourShade());
        entity.setTexture(this.getFinish());
        return entity;
    }

    public static GelPolish fromEntity(GelPolishEntity entity) {
        GelPolish polish = new GelPolish(
                entity.getColourShade(),
                entity.getTexture(),
                entity.getBrand(),
                entity.getPrice(),
                entity.getStock()
        );
        polish.setDbId(entity.getId());
        polish.setProductId(entity.getProductId());
        return polish;
    }

}
