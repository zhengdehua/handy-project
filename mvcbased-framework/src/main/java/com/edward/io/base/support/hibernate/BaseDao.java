package com.edward.io.base.support.hibernate;

import java.io.Serializable;
import java.util.List;

public interface BaseDao {

	<T> void batchSaveOrUpdate(List<T> list);

	<T> List<T> findBy(String propertyName, Object value);

	<T> List<T> findBy(String propertyName, Object value, String orderBy, boolean isAsc);

	<T> T findUniqueBy(String propertyName, Object value);

	<T> List<T> getAll();

	<T> List<T> getAll(String orderBy, boolean isAsc);

	<T> boolean isUnique(Object entity, String uniquePropertyNames);

	<T> T load(Serializable id);

	<T> void remove(T entity);

	<T> void removeById(Serializable id);

	<T> void save(T entity);

	<T> void saveOrUpdate(T entity);

}
