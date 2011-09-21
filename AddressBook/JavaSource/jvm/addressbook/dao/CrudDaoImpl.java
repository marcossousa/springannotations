package jvm.addressbook.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CrudDaoImpl<T extends Serializable, PK extends Serializable> extends HibernateDaoSupport implements CrudDao<T, PK> {

	private final Class<T> entityClass;

    public CrudDaoImpl(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<T> getEntityClass() {
    	return this.entityClass;
    }
    
    public T save(T object) {
    	try {
            this.getSession().save(object);
            this.getSession().flush();
            return object;
        } catch (final HibernateException e) {
            throw convertHibernateAccessException(e);
        }
    }
    
    public void update(T object) {
    	try {
            this.getSession().update(object);
            this.getSession().flush();
        } catch (final HibernateException e) {
            throw convertHibernateAccessException(e);
        }
    }
    
    public void delete(T object) {
    	try {
            this.getSession().delete(object);
            this.getSession().flush();
        } catch (final HibernateException e) {
            throw convertHibernateAccessException(e);
        }	
    }
    
    @SuppressWarnings("unchecked")
	public T getByPk(PK primaryKey) {
    	 try {
             return (T) this.getSession().get(this.entityClass, primaryKey);
         } catch (final HibernateException e) {
             throw convertHibernateAccessException(e);
         }
    }
    
    @SuppressWarnings("unchecked")
    public List<T> listAll() {
        try {
            Criteria c = this.getSession().createCriteria(this.entityClass);
            return c.list();
        } catch (final HibernateException e) {
            throw convertHibernateAccessException(e);
        }
    }
}
