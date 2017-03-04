package repository;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by romm on 01.02.17.
 */
public interface DAO<T, PK extends Serializable> {

    Class<T> getEntityClass();

    List<T> getAll();

    PK create(T entity);

    T get(PK key);

    void update(T entity);

    void delete(T entity);

    Session getSession();

}
