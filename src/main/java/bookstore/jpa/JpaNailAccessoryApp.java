package bookstore.jpa;

import bookstore.entities.GelPolishEntity;
import bookstore.entities.NailAccessoryEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaNailAccessoryApp {
    public static void main(String[] args) {
        // 1. Start Hibernate (Loads persistence.xml)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore-pu");
        EntityManager em = emf.createEntityManager();
        // 2. Create Data
        em.getTransaction().begin();
        NailAccessoryEntity accessoryItem = new NailAccessoryEntity();
        accessoryItem.setBrand("Nyx");
        accessoryItem.setStock(30);
        accessoryItem.setPrice(50.99);
        accessoryItem.setAccessoryType("Nail Drill");
        em.persist(accessoryItem);
        em.getTransaction().commit();

        // 3. Read Data (Polymorphic check)
        // Notice we can query specifically for Widgets
        List<NailAccessoryEntity> accessoryList = em.createQuery(
                "SELECT w FROM NailAccessoryEntity w", NailAccessoryEntity.class).getResultList();

        for (NailAccessoryEntity a : accessoryList) {
            System.out.println("Found: " + a);
        }

        // 4. Find the item, and update it (idk consistency check or something like that)
        NailAccessoryEntity foundItem = em.find(NailAccessoryEntity.class, accessoryItem.getId());
        foundItem.setBrand("Mattel");
        em.persist(foundItem);
        em.getTransaction().commit();

        // 5. Now delete all my hard work :(
        NailAccessoryEntity itemToDelete = em.find(NailAccessoryEntity.class, foundItem.getId());
        em.remove(itemToDelete);
        em.getTransaction().commit();

        em.close();


    }
}
