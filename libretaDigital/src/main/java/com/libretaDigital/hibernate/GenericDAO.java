package com.libretaDigital.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.libretaDigital.exceptions.SystemErrorException;
import com.libretaDigital.interfaces.IGenericDAO;
import com.libretaDigital.processors.PreProcessor;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class GenericDAO<E> extends HibernateDaoSupport implements IGenericDAO<E> {

	private static Logger logger = Logger.getLogger(GenericDAO.class);

	protected List<PreProcessor<E>> processors;
	
	public Serializable save(E inst) throws SystemErrorException {
		getLogger().debug("save");

		if (processors != null)
			for (PreProcessor<E> pp : processors)
				inst = pp.process(inst);

		try {
			getHibernateTemplate().setCheckWriteOperations(false);
			long timerOn = System.currentTimeMillis();
			Serializable id = getHibernateTemplate().save(inst);
			long timerOff = System.currentTimeMillis();

			getLogger().info("time elapsed during database interaction (ms): " + (timerOff - timerOn));
			return id;
		} catch (DataAccessException e) {
			getLogger().error(e.getLocalizedMessage(), e);
			throw new SystemErrorException(e.getLocalizedMessage());
		}
	}
	
	public void update(E inst) throws SystemErrorException {
		getLogger().debug("update");

		if (processors != null)
			for (PreProcessor<E> pp : processors)
				inst = pp.process(inst);

		try {
			
			getHibernateTemplate().setCheckWriteOperations(false);
			long timerOn = System.currentTimeMillis();
			Session session = getHibernateTemplate().getSessionFactory().openSession();			
			session.saveOrUpdate(inst);
			session.flush();
			long timerOff = System.currentTimeMillis();

			getLogger().info("time elapsed during database interaction (ms): " + (timerOff - timerOn));
		} catch (DataAccessException e) {
			getLogger().error(e.getLocalizedMessage(), e);
			throw new SystemErrorException(e.getLocalizedMessage());
		}
	}


	public void saveOrUpdate(E inst) throws SystemErrorException {
		getLogger().debug("saveOrUpdate");

		if (processors != null)
			for (PreProcessor<E> pp : processors)
				inst = pp.process(inst);

		try {
			
			getHibernateTemplate().setCheckWriteOperations(false);
			long timerOn = System.currentTimeMillis();
			getHibernateTemplate().saveOrUpdate(inst);
			long timerOff = System.currentTimeMillis();

			getLogger().info("time elapsed during database interaction (ms): " + (timerOff - timerOn));
		} catch (DataAccessException e) {
			getLogger().error(e.getLocalizedMessage(), e);
			throw new SystemErrorException(e.getLocalizedMessage());
		}
	}

	public E getById(Long id) throws SystemErrorException {
		getLogger().debug("getById");

		try {
			long timerOn = System.currentTimeMillis();
			E obj = (E) getHibernateTemplate().get(getGenericClass(), id);
			long timerOff = System.currentTimeMillis();

			getLogger().info(
					"time elapsed during database interaction (ms): "
							+ (timerOff - timerOn));
			return obj;
		} catch (DataAccessException e) {
			getLogger().error(e.getLocalizedMessage(), e);
			throw new SystemErrorException(e.getLocalizedMessage());
		}
	}

	public void delete(E inst) throws SystemErrorException {
		getLogger().debug("delete");

		try {
			long timerOn = System.currentTimeMillis();
			getHibernateTemplate().delete(inst);
			long timerOff = System.currentTimeMillis();

			getLogger().info(
					"time elapsed during database interaction (ms): "
							+ (timerOff - timerOn));
		} catch (DataAccessException e) {
			getLogger().error(e.getLocalizedMessage(), e);
			throw new SystemErrorException(e.getLocalizedMessage());
		}
	}

	public E merge(E inst) throws SystemErrorException {
		getLogger().debug("merge");

		try {
			long timerOn = System.currentTimeMillis();
			E obj = (E) getHibernateTemplate().merge(inst);
			long timerOff = System.currentTimeMillis();

			getLogger().info(
					"time elapsed during database interaction (ms): "
							+ (timerOff - timerOn));
			return obj;
		} catch (DataAccessException e) {
			getLogger().error(e.getLocalizedMessage(), e);
			throw new SystemErrorException(e.getLocalizedMessage());
		}
	}

	private Class getGenericClass() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass()
				.getGenericSuperclass();
		return (Class) parameterizedType.getActualTypeArguments()[0];
	}

	public void setProcessors(List<PreProcessor<E>> processors) {
		this.processors = processors;
	}

	public static Logger getLogger() {
		return logger;
	}
	
}
