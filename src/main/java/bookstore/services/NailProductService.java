package bookstore.services;

import bookstore.entities.*;
import bookstore.pojos.NailProduct;
import bookstore.repositories.IRepository;

import java.util.ArrayList;
import java.util.List;

public class NailProductService {
    private final IRepository<ProductEntity> repository;

    public NailProductService(IRepository<ProductEntity> repository) {
        this.repository = repository;
    }

    public boolean SafetyCheck(NailProductEntity product) {
        List<String> isVegan = List.of("100% Pure", "A.Dorn", "Aila", "Barry M", "Beauty Without Cruelty", "Benecos", "Color Club", "Colourpop", "Deco Miami", "e.l.f.", "Ella + Mila", "Fairypants", "Furless Cosmetics", "Gabriel Cosmetics", "GOSH", "Habit", "Hard Candy", "Illamasqua", "Jinsoon", "Jolie Vegan", "Jordana", "Kester Black", "Kokie", "L.A. Colors", "Lauren B. Beauty", "LOOK Nail Color", "LVX", "Manic Panic", "Mented Cosmetics", "Milani Cosmetics", "Milk Makeup", "Mineral Fusion", "NCLA", "OOO Polish", "Obsessive Compulsive Cosmetics", "OH TIFF!", "Orly", "Oribe", "Palate Polish", "Piggy Paint", "Pixi", "Play Love Laugh (Hugo Naturals)", "Prestige Cosmetics", "Pretty Serious", "PRITI NYC", "Pure Anada", "RMS Beauty", "Sonia Kashuk", "SOPHI", "Smith & Cult", "SpaRitual", "Stylush", "Sugarpill", "theBalm", "Trust Fund Beauty", "Urban Decay", "Wet N wild");
        List<String> isCrueltyFree = List.of("A.Dorn", "Beauty Without Cruelty", "Deco Miami", "Furless Cosmetics", "Gabriel Cosmetics", "Jolie Vegan", "LVX", "Mented Cosmetics", "NCLA", "OOO Polish", "Obsessive Compulsive Cosmetics", "OH TIFF!", "Oribe", "Play Love Laught (Hugo Naturals)", "PRITI NYC", "SOPHI", "SpaRitual", "Stylush", "Trust Fund Beauty", "Urban Decay");
//    Idea: vegan and cruelty-free polish
//    one of those has to pass to sell
//    part 1 accessory plan:
//    nail kit items must all be same brand
        if (product instanceof GelPolishEntity) {
            if (isVegan.contains(product)){
                return true;
            } else if (isCrueltyFree.contains(product)) {
                return true;
            } else {
                return false;
            }
        } else if (product instanceof NailKitEntity) {
            ArrayList<NailProductEntity> products = ((NailKitEntity) product).getItems();
            boolean allSame = products.stream().map(NailProductEntity::getBrand).distinct().count()==1;
            return allSame;
        }
        return false;
    }



//    part 2 is easy - environmental charge
//    based on item
    public NailProductEntity EnvironmentalCharge(NailProductEntity product) {
        if (product instanceof GelPolishEntity) {
            double currentPrice = product.getPrice();
            double CO2Price = currentPrice + 1.55;
            product.setPrice(CO2Price);
            return product;
        } else if (product instanceof NailAccessoryEntity) {
            double currentPrice = product.getPrice();
            double CO2Price = currentPrice + 3.55;
            product.setPrice(CO2Price);
            return product;
        } else if (product instanceof NailKitEntity) {
            double currentPrice = product.getPrice();
            double CO2Price = currentPrice + 5.55;
            product.setPrice(CO2Price);
            return product;
        }
        return product;
    }

}
