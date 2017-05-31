package ch.bfh.bti7081.s2017.grey.database.dao.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.GenericDao;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by gabor on 29/05/17.
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    protected EntityManager em;

    private Class<T> type;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
        em = EntityManagerSingleton.getInstance();
    }

    @Override
    public T create(T t) {
        this.em.getTransaction().begin();
        this.em.persist(t);
        this.em.getTransaction().commit();
        return t;
    }

    @Override
    public void delete(Object id) {
        this.em.getTransaction().begin();
        this.em.remove(this.em.getReference(type, id));
        this.em.getTransaction().commit();
    }

    @Override
    public T find(Object id) {
        return this.em.find(type, id);
    }

    @Override
    public T update(T t) {
        this.em.getTransaction().begin();
        t = this.em.merge(t);
        this.em.getTransaction().commit();
        return t;
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> c = criteriaBuilder.createQuery(type);
        Root<T> t = c.from(type);
        c.select(t);

        TypedQuery<T> query = em.createQuery(c);
        return query.getResultList();
    }
}
