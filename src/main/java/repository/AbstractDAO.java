package repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by romm on 01.02.17.
 */
public abstract class AbstractDAO<T, PK extends Serializable> implements DAO<T, PK> {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public Session getSession() throws HibernateException {
        return sessionFactory.getCurrentSession();
    }

//    static final Logger logger = Logger.getLogger(AbstractDAO.class);

    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        Session session = getSessionFactory().getCurrentSession();
        Query<T> query = session.createQuery(
                "from " + getEntityClass().getSimpleName() + " t",
                getEntityClass()
        );
        return query.list();
    }

    @Override
    @Transactional(readOnly = true)
    public T get(PK key) {
        T result = null;
        Session session = getSessionFactory().getCurrentSession();
        Query<T> query = session.createQuery(
                "from " + getEntityClass().getSimpleName() + " t where t.id = :id",
                getEntityClass()
        );
        query.setParameter("id", key);
        List<T> resultList = query.list();
        if (resultList != null && !resultList.isEmpty()) {
            result = resultList.get(0);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PK create(T entity) {
        return (PK) getSessionFactory().getCurrentSession().save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void update(T entity) {
        getSessionFactory().getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
       getSessionFactory().getCurrentSession().remove(entity);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
