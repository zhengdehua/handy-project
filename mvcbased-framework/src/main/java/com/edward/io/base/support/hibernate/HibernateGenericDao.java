package com.edward.io.base.support.hibernate;

import com.edward.io.base.support.Page;
import com.edward.io.base.util.ReflectionUtils;
import com.edward.io.base.util.StringUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HibernateGenericDao extends HibernateDaoSupport {

	private static Logger logger = LoggerFactory
			.getLogger(HibernateGenericDao.class);

	@Autowired
	protected SessionFactory sessionFactory;

	public <T> T load(Class<T> entityClass, Serializable id) {
		Assert.notNull(id);

		try {
			return (T) getSession().load(entityClass, id);

		} catch (Exception e) {
			logger.error("load entity fail", e);
			throw new RuntimeException(e);
		}
	}

	public <T> List<T> getAll(Class<T> entityClass) {
		return createCriteria(entityClass, new Criterion[0]).list();
	}

	public <T> List<T> getAll(Class<T> entityClass, String orderBy,
			boolean isAsc) {
		Assert.hasText(orderBy);

		return createCriteria(entityClass, orderBy, isAsc, new Criterion[0])
				.list();
	}

	@Transactional
	public <T> void save(T entity) {
		Assert.notNull(entity);
		getSession().save(entity);
		logger.debug("save entity: {}", entity);
	}

	@Transactional
	public <T> void saveOrUpdate(T entity) {
		Assert.notNull(entity);
		getSession().saveOrUpdate(entity);
		logger.debug("save entity: {}", entity);
	}

	@Transactional
	public <T> void batchSaveOrUpdate(List<T> list) {
		Assert.notNull(list);
		Session session = getSession();
		int flushCount = 20;
		for (int i = 0, j = list.size(); i < j; i++) {
			session.saveOrUpdate(list.get(i));
			if (i != 0 && i % flushCount == 0) {
				session.flush();
				session.clear();
			}
		}
	}

	@Transactional
	public <T> void remove(T entity) {
		Assert.notNull(entity);

		try {
			Class entityClass = entity.getClass();

			Serializable id = getId(entityClass, entity);

			removeById(entityClass, id);

			logger.debug("delete entity: {}", entity);

		} catch (Exception e) {
			logger.error("delete entity fail", e);
			throw new RuntimeException(e);
		}

	}

	@Transactional
	public <T> void removeById(Class<T> entityClass, Serializable id) {
		Assert.notNull(id);

		try {

			String idName = getIdName(entityClass);

			String hql = new StringBuilder("delete from ")
					.append(entityClass.getName()).append(" where ")
					.append(idName).append(" = ?").toString();

			createQuery(hql, new Object[] { id }).executeUpdate();

			logger.debug("delete entity {},id is {}",
					entityClass.getSimpleName(), id);

		} catch (Exception e) {
			logger.error("delete entity fail", e);
			throw new RuntimeException(e);
		}

	}

	public <T> List<T> findBy(Class<T> entityClass, String propertyName,
			Object value) {
		Assert.hasText(propertyName, "propertyName can not be empty");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return createCriteria(entityClass, new Criterion[] { criterion })
				.list();
	}

	public <T> List<T> findBy(Class<T> entityClass, String propertyName,
			Object value, String orderBy, boolean isAsc) {
		Assert.hasText(propertyName, "propertyName can not be empty");
		Assert.hasText(orderBy);
		Criterion criterion = Restrictions.eq(propertyName, value);

		return createCriteria(entityClass, orderBy, isAsc,
				new Criterion[] { criterion }).list();
	}

	public SQLQuery createSqlQuery(String queryString, Object[] values) {
		Assert.hasText(queryString, "queryString can not be empty");
		SQLQuery query = getSession().createSQLQuery(queryString);
		if (values != null)
			for (int i = 0; i < values.length; ++i)
				query.setParameter(i, values[i]);

		return query;
	}

	public <T> SQLQuery createSqlQuery(String queryString,
			Map<String, Object> values) {
		Assert.hasText(queryString, "queryString can not be empty");
		SQLQuery query = getSession().createSQLQuery(queryString);

		if (values != null)
			query.setProperties(values);

		return query;
	}

	public long countSqlResult(String sql, Object[] values) {

		String countSql = makeHqlShortForCount(sql);

		try {

			BigDecimal count = (BigDecimal) createSqlQuery(countSql, values)
					.uniqueResult();

			return count.longValue();

		} catch (RuntimeException e) {
			throw new RuntimeException("sql can't be auto count, sql is:"
					+ countSql, e);
		}
	}

	public long countSqlResult(String sql, Map<String, Object> values) {

		String countSql = makeHqlShortForCount(sql);

		try {

			BigDecimal count = (BigDecimal) createSqlQuery(countSql, values)
					.uniqueResult();

			return count.longValue();

		} catch (RuntimeException e) {
			throw new RuntimeException("sql can't be auto count, sql is:"
					+ countSql, e);
		}
	}

	protected <T> Query setPageParameter(Query q, Page<T> page) {
		q.setFirstResult(page.getFirstOfPage() - 1);
		q.setMaxResults(page.getPageSize());
		return q;
	}

	protected <T> Criteria setPageParameter(Criteria c, Page<T> page) {
		c.setFirstResult(page.getFirstOfPage() - 1);
		c.setMaxResults(page.getPageSize());

		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ",");
			String[] orderArray = StringUtils.split(page.getOrder(), ",");

			for (int i = 0; i < orderByArray.length; ++i)
				if ("asc".equals(orderArray[i]))
					c.addOrder(Order.asc(orderByArray[i]));
				else
					c.addOrder(Order.desc(orderByArray[i]));
		}

		return c;
	}

	public Query createQuery(String queryString, Object[] values) {
		Assert.hasText(queryString, "queryString can not be empty");
		Query query = getSession().createQuery(queryString);
		if (values != null)
			for (int i = 0; i < values.length; ++i)
				query.setParameter(i, values[i]);

		return query;
	}

	public Query createQuery(String queryString, Map<String, Object> values) {
		Assert.hasText(queryString, "queryString can not be empty");
		Query query = getSession().createQuery(queryString);
		if (values != null)
			query.setProperties(values);

		return query;
	}

	public <T> T findUnique(String hql, Object[] values) {
		return (T) createQuery(hql, values).uniqueResult();
	}

	public <T> T findUnique(String hql, Map<String, Object> values) {
		return (T) createQuery(hql, values).uniqueResult();
	}

	public <T> T findUnique(Class<T> entityClass, Criterion[] criterions) {
		return (T) createCriteria(entityClass, criterions).uniqueResult();
	}

	public <T> T findUnique(Class<T> entityClass, String propertyName,
			Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(entityClass,
				new Criterion[] { Restrictions.eq(propertyName, value) })
				.uniqueResult();
	}

	public <T> Criteria createCriteria(Class<T> entityClass,
			Criterion[] criterions) {
		Criterion[] arrayOfCriterion;
		Criteria criteria = getSession().createCriteria(entityClass);
		int j = (arrayOfCriterion = criterions).length;
		for (int i = 0; i < j; ++i) {
			Criterion c = arrayOfCriterion[i];
			criteria.add(c);
		}
		return criteria;
	}

	public <T> Criteria createCriteria(Class<T> entityClass, String orderBy,
			boolean isAsc, Criterion[] criterions) {
		Assert.hasText(orderBy);
		Criteria criteria = createCriteria(entityClass, criterions);
		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));

		return criteria;
	}

	private String makeHqlShortForCount(String hql) {
		Assert.notNull(hql);

		String fromHql = hql;

		int from_pos = fromHql.toLowerCase().indexOf("from") + "from".length();

		fromHql = "from " + fromHql.substring(from_pos);

		int order_by_pos = fromHql.toLowerCase().indexOf("order by");

		fromHql = fromHql.substring(0, order_by_pos);

		String countHql = "select count(*) " + fromHql;

		return countHql;

	}

	public long countHqlResult(String hql, Object[] values) {

		String countHql = makeHqlShortForCount(hql);

		try {
			Long count = (Long) findUnique(countHql, values);

			return count.longValue();

		} catch (RuntimeException e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}

	public long countHqlResult(String hql, Map<String, Object> values) {

		String countHql = makeHqlShortForCount(hql);

		try {
			Long count = (Long) findUnique(countHql, values);

			return count.longValue();

		} catch (RuntimeException e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}

	}

	protected long countCriteriaResult(Criteria c) {

		CriteriaImpl impl = (CriteriaImpl) c;

		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List orderEntries = (List) ReflectionUtils.getFieldValue(impl,
				"orderEntries");
		ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());

		long totalCount = ((Long) c.setProjection(Projections.rowCount())
				.uniqueResult()).longValue();

		c.setProjection(projection);

		if (projection == null)
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

		if (transformer != null)
			c.setResultTransformer(transformer);

		ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);

		return totalCount;
	}

	public <T> boolean isUnique(Class<T> entityClass, Object entity,
			String uniquePropertyNames) {
		Assert.hasText(uniquePropertyNames);
		Criteria criteria = createCriteria(entityClass, new Criterion[0])
				.setProjection(Projections.rowCount());
		String[] nameList = uniquePropertyNames.split(",");
		try {
			int j = nameList.length;

			for (int i = 0; i < j; ++i) {
				String name = nameList[i];
				criteria.add(Restrictions.eq(name,
						PropertyUtils.getProperty(entity, name)));
			}

			String idName = getIdName(entityClass);

			Serializable id = getId(entityClass, entity);

			if (id == null)
				return (((Long) criteria.uniqueResult()).longValue() == 0);

			criteria.add(Restrictions.not(Restrictions.eq(idName, id)));

		} catch (IllegalAccessException e) {
			logger.error("exception:{}", e.getMessage());
		} catch (InvocationTargetException e) {
			logger.error("exception:{}", e.getMessage());
		} catch (NoSuchMethodException e) {
			logger.error("exception:{}", e.getMessage());
		}

		return (((Long) criteria.uniqueResult()).longValue() == 0);

	}

	protected Serializable getId(Class entityClass, Object entity) {

		Assert.notNull(entity);
		Assert.notNull(entityClass);

		try {
			return ((Serializable) PropertyUtils.getProperty(entity,
					getIdName(entityClass)));

		} catch (Exception e) {
			logger.error("get primary key fail", e);
			throw new RuntimeException(e);
		}

	}

	protected String getIdName(Class clazz) {
		Assert.notNull(clazz);
		ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
		Assert.notNull(meta, "Class " + clazz
				+ " not define in hibernate session factory.");
		String idName = meta.getIdentifierPropertyName();
		Assert.hasText(idName, clazz.getSimpleName()
				+ " has no identifier property define.");
		return idName;
	}

}