package bookstore.services;

import bookstore.entities.ProductEntity;
import bookstore.repositories.IRepository;

public class NailProductService {
    private final IRepository<ProductEntity> repository;

    public NailProductService(IRepository<ProductEntity> repository) {
        this.repository = repository;
    }

//    Idea: vegan and cruelty-free polish
//    one of those has to pass to sell

//    part 1 accessory plan:
//    make a nail kit entity

//    part 2 is easy - environmental charge
//    based on how many products you buy
//    bulk is per-item cheaper, less
//    carbon emissions for shipments

}
