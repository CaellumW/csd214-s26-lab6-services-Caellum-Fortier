package bookstore.jpa;

import bookstore.entities.BookEntity;
import bookstore.entities.GelPolishEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaGelPolishApp {
    public static void main(String[] args) {
        // 1. Start Hibernate (Loads persistence.xml)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore-pu");
        EntityManager em = emf.createEntityManager();

        // 2. Create Data
        em.getTransaction().begin();
        GelPolishEntity polish = new GelPolishEntity();
        polish.setBrand("Nyx");
        polish.setStock(30);
        polish.setPrice(20.75);
        polish.setColourShade("Blue");
        polish.setTexture("Gloss");
        em.persist(polish);
        em.getTransaction().commit();

        // 3. Read Data (Polymorphic check)
        // Notice we can query specifically for Widgets
        List<GelPolishEntity> polishes = em.createQuery(
                "SELECT w FROM GelPolishEntity w", GelPolishEntity.class).getResultList();

        // 3.5 - printing the results of the SQL query
        for (GelPolishEntity p : polishes) {
            System.out.println("Found: " + p);
        }

        // 4. Find the item, and update it (idk consistency check or something like that)
        GelPolishEntity foundItem = em.find(GelPolishEntity.class, polish.getId());
        foundItem.setTexture("Matte");
        em.persist(foundItem);
        em.getTransaction().commit();

        // 5. Now delete all my hard work :(
        GelPolishEntity itemToDelete = em.find(GelPolishEntity.class, polish.getId());
        em.remove(itemToDelete);
        em.getTransaction().commit();

        em.close();


    }
}
