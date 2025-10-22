package Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerAdmin {

    private static final String UNIDAD_PERSISTENCE = "PUBLICACIONJPA";

    private static EntityManager instance;

    public static EntityManager getInstance() {
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory(UNIDAD_PERSISTENCE);
        return emf.createEntityManager();
    }

}