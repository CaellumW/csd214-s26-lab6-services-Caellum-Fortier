package bookstore.services;

import bookstore.entities.NailProductEntity;
import bookstore.entities.ProductEntity;
import bookstore.entities.PublicationEntity;
import bookstore.entities.VehiclePartEntity;
import bookstore.pojos.SaleableItem;
import bookstore.repositories.IRepository;

public class StorefrontTransactionService {
    private final IRepository<ProductEntity> repository;

    public StorefrontTransactionService(IRepository<ProductEntity> repository) {
        this.repository = repository;
    }

    public String processFulfillment(Long id, int quantity) {
        ProductEntity item = repository.findById(id);

        if (item instanceof PublicationEntity) {
            quantity = ((PublicationEntity) item).getCopies();
            if (quantity == 0) {
                throw new IllegalStateException("Insufficient inventory to fulfill order.");
            } else if (quantity > 0) {
                ((PublicationEntity) item).setCopies(quantity - 1);
                String success = "Successfully sold a copy";
                return success;
            }
        } else if (item instanceof NailProductEntity) {
            quantity = ((NailProductEntity) item).getStock();
            if (quantity == 0) {
                throw new IllegalStateException("Insufficient inventory to fulfill order.");
            } else if (quantity > 0) {
                ((NailProductEntity) item).setStock(quantity - 1);
                String success = "Successfully sold a copy";
                return success;
            }
        }
        return "Sale successful";
    }

    public double calculateBulkDiscount(Long id, int orderQuantity) {
        ProductEntity item = repository.findById(id);
        double price;

        if (orderQuantity >= 10) {
            double discountPercent = 0.2;
            if (item instanceof PublicationEntity) {
                price = ((PublicationEntity) item).getPrice() * orderQuantity;
                double discount = price * discountPercent;
                double total = price - discount;
                return total;
            } else if (item instanceof NailProductEntity) {
                price = ((NailProductEntity) item).getPrice() * orderQuantity;
                double discount = price * discountPercent;
                double total = price - discount;
                return total;
            } else if (item instanceof VehiclePartEntity) {
                price = ((VehiclePartEntity) item).getPrice() * orderQuantity;
                double discount = price * discountPercent;
                double total = price - discount;
                return total;
            }
        } else if (orderQuantity >= 5) {
            double discountPercent = 0.1;
            if (item instanceof PublicationEntity) {
                price = ((PublicationEntity) item).getPrice() * orderQuantity;
                double discount = price * discountPercent;
                double total = price - discount;
                return total;
            } else if (item instanceof NailProductEntity) {
                price = ((NailProductEntity) item).getPrice() * orderQuantity;
                double discount = price * discountPercent;
                double total = price - discount;
                return total;
            } else if (item instanceof VehiclePartEntity) {
                price = ((VehiclePartEntity) item).getPrice() * orderQuantity;
                double discount = price * discountPercent;
                double total = price - discount;
                return total;
            }
        }
        return 0.0;
    }



}
