package Services;

import Services.IDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ImplDAO implements IDAO {
    @Override
    public <T> List<T> getAll(String nameQuery, Class<T> clazz) {
        EntityManager entityManager = EntityManagerAdmin.getInstance();
        try {
            TypedQuery<T> query =  entityManager.createNamedQuery(nameQuery,clazz);
            return query.getResultList();
        }
        catch (Exception e) {e.printStackTrace();}
        finally{ entityManager.close();}
        return null;
    }

    @Override
    public <T> void insert(T entity) {
        EntityManager entityManager = EntityManagerAdmin.getInstance();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        finally{ entityManager.close();}
    }

    @Override
    public <T> void update(T entity) {
        EntityManager entityManager = EntityManagerAdmin.getInstance();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        finally{ entityManager.close();}
    }

    @Override
    public <T> void delete(T entity) {
        EntityManager entityManager = EntityManagerAdmin.getInstance();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(entity));
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        finally{ entityManager.close();}
    }

    @Override
    public <T> T findById(Object id, Class<T> clazz) {
        EntityManager entityManager = EntityManagerAdmin.getInstance();
        try {
            return entityManager.find(clazz, id);
        }
        catch (Exception e) {e.printStackTrace();}
        finally{ entityManager.close();}
        return null;
    }
}
