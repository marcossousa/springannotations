package jvm.addressbook.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface CrudDao<T extends Serializable, PK extends Serializable> {
	Class<T> getEntityClass();
	@Transactional(propagation=Propagation.REQUIRED)
	T save(final T object);
	@Transactional(propagation=Propagation.REQUIRED)
	void update(final T object);
	@Transactional(propagation=Propagation.REQUIRED)
	void delete(final T object);
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	T getByPk(final PK pk);
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	List<T> listAll();

}
