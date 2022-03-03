package dbgateway.dao;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class DBRepository<S, T> {

    protected final EntityManager entityManager;
    protected final Session session;

    public DBRepository(Session session) {
        this.entityManager = session.getEntityManagerFactory().createEntityManager();
        this.session = session;
    }

    public S save(S s){
        boolean hasTransaction = true;
        if (!session.getTransaction().isActive()){
            session.getTransaction().begin();
            hasTransaction = false;
        }
        session.saveOrUpdate(s);
        session.refresh(s);
        if (!hasTransaction){
            session.getTransaction().commit();
        }
        return s;
    }

    public void delete(S s){
        boolean hasTransaction = true;
        if (!session.getTransaction().isActive()){
            session.getTransaction().begin();
            hasTransaction = false;
        }
        session.remove(s);
        session.detach(s);
        if (!hasTransaction){
            session.getTransaction().commit();
        }
    }

    public void deleteById(T id){
        boolean hasTransaction = true;
        if (!session.getTransaction().isActive()){
            session.getTransaction().begin();
            hasTransaction = false;
        }
        Class<S> sClass = getGenericClass();
        S object = session.find(sClass, id);
        session.remove(object);
        if (!hasTransaction){
            session.getTransaction().commit();
        }
    }

    public S findOneById(T id){
        Class<S> sClass = getGenericClass();
        return session.find(sClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<S> findAll(){
        Class<S> sClass = getGenericClass();
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaQuery<S> criteriaQuery = criteria.createQuery(sClass);
        Root<S> root = criteriaQuery.from(sClass);
        CriteriaQuery<S> all = criteriaQuery.select(root);
        TypedQuery<S> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public long countAll(){
        return findAll().size();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public CriteriaBuilder getcriteriaBuilder(){
        return this.session.getCriteriaBuilder();
    }

    @SuppressWarnings("unchecked")
    private Class<S> getGenericClass(){
        try {
            String genericClassName = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
            Class<?> generic = Class.forName(genericClassName);
            return (Class<S>) generic;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
