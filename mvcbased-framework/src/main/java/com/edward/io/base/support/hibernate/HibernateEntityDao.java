package com.edward.io.base.support.hibernate;

import com.edward.io.base.support.Page;
import com.edward.io.base.util.ReflectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class HibernateEntityDao<T> extends HibernateGenericDao {

	protected Class<T> entityClass;

	public HibernateEntityDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	protected Class<T> getEntityClass() {
		return this.entityClass;
	}

	public T load(Serializable id) {
		return load(getEntityClass(), id);
	}

	public List<T> getAll() {
		return getAll(getEntityClass());
	}

	public List<T> getAll(String orderBy, boolean isAsc) {
		return getAll(getEntityClass(), orderBy, isAsc);
	}

	public void removeById(Serializable id) {
		removeById(getEntityClass(), id);
	}

	public Criteria createCriteria(Criterion[] criterions) {
		return createCriteria(getEntityClass(), criterions);
	}

	public Criteria createCriteria(String orderBy, boolean isAsc,
			Criterion[] criterions) {
		return createCriteria(getEntityClass(), orderBy, isAsc, criterions);
	}

	public List<T> findBy(String propertyName, Object value) {
		return findBy(getEntityClass(), propertyName, value);
	}

	public List<T> findBy(String propertyName, Object value, String orderBy,
			boolean isAsc) {
		return findBy(getEntityClass(), propertyName, value, orderBy, isAsc);
	}

	public Page<T> findPage(Page<T> page, String hql, Object[] values) {
		Assert.notNull(page, "page can not be null");
		Query q = createQuery(hql, values);
		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}
		setPageParameter(q, page);
		List<T> result = q.list();
		page.setResult(result);
		return page;
	}

	public Page<T> findPage(Page<T> page, Criterion[] criterions) {
		Assert.notNull(page, "page can not be null");
		Criteria c = createCriteria(getEntityClass(), criterions);
		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}
		setPageParameter(c, page);
		List<T> result = c.list();
		page.setResult(result);
		return page;
	}

	public Page<T> findPage(Page<T> page, String hql, Map<String, Object> values) {
		Assert.notNull(page, "page can not be null");
		Query q = createQuery(hql, values);
		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}
		setPageParameter(q, page);
		List<T> result = q.list();
		page.setResult(result);
		return page;
	}

	public T findUniqueBy(String propertyName, Object value) {
		return findUnique(getEntityClass(), propertyName, value);
	}

	public boolean isUnique(Object entity, String uniquePropertyNames) {
		return isUnique(getEntityClass(), entity, uniquePropertyNames);
	}

	public List<T> getTopQuery(String hql, int begin, int size, Object[] values) {
		Query query = createQuery(hql, values);
		query.setFirstResult(begin);
		query.setMaxResults(size);
		return query.list();
	}

	public List<T> getTopQuery(String hql, int begin, int size,
			Map<String, Object> map) {
		Query query = createQuery(hql, map);
		query.setFirstResult(begin);
		query.setMaxResults(size);
		return query.list();
	}
}