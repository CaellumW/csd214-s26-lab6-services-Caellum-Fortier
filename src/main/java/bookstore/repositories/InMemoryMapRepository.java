package bookstore.repositories;

import bookstore.entities.ProductEntity;

import java.util.*;

public class InMemoryMapRepository implements IRepository<ProductEntity> {
    private Long idCounter = 1L;
    private final Map<Long, ProductEntity> list = new HashMap<>();

    @Override
    public ProductEntity save(ProductEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot save a null entity.");
        }

        // 1. Assign ID if it's a new entity (mimicking MySQL AUTO_INCREMENT)
        if (entity.getId() == null) {
            entity.setId(idCounter++);
            list.put(entity.getId(), entity);
        } else {
            // 2. Upsert Strategy: If the entity has an ID, find and replace it
            int foundIndex = -1;
            for (int i = 0; i < list.size(); i++) {
                if (Objects.equals(list.get(i).getId(), entity.getId())) {
                    foundIndex = i;
                    break;
                }
            }

            if (foundIndex != -1) {
                list.put((long) foundIndex, entity); // Update existing
            } else {
                list.put((long) foundIndex, entity); // Insert if ID was manually provided but missing from collection
            }
        }
        return entity;
    }

    @Override
    public ProductEntity findById(Long id) {
        if (id == null) {
            return null;
        }

        // Linear O(n) search: Evaluates every element sequentially until a match is found
        for (Long ID : list.keySet()) {
            if (ID == id) {
                return list.get(ID);
            }
        }
        return null; // ID not present in list
    }

    @Override
    public ProductEntity findByProductId(String productId) {
        if (productId == null) {
            return null;
        }
        for (Long ID : list.keySet()) {
            if (list.get(ID).getProductId() == productId) {
                return list.get(ID);
            }
        }
        return null;
    }

    @Override
    public List<ProductEntity> findAll() {
        List<ProductEntity> allList = new ArrayList<>();
        for (ProductEntity P : list.values()) {
            allList.add(P);
        }
        return allList;
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            return;
        }
        for (Long IDnum : list.keySet()) {
            if (list.get(IDnum).getId() == id) {
                list.remove(IDnum);
                break;
            }
        }
    }

    @Override
    public long count() {
        return list.size();
    }

    @Override
    public int deleteAll() {
        int itemsDeleted = list.size();
        list.clear();
        return itemsDeleted;
    }

    @Override
    public String getDataSourceType() {
        return "VOLATILE RAM (HashMap - Indexed Search)";
    }

    @Override
    public void close() {
        System.out.println("Volatile memory cleared (InMemoryListRepository).");
    }
}
