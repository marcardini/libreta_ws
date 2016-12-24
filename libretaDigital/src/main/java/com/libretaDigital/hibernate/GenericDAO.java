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
		Session session = null;
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
		}finally{
			if(session != null){
				session.close();
			}
		}
	}
	
	public void update(E inst) throws SystemErrorException {
		getLogger().debug("update");
		Session session = null;
		if (processors != null)
			for (PreProcessor<E> pp : processors)
				inst = pp.process(inst);

		try {
			
			getHibernateTemplate().setCheckWriteOperations(false);
			long timerOn = System.currentTimeMillis();
			session = getHibernateTemplate().getSessionFactory().openSession();			
			session.saveOrUpdate(inst);
			session.flush();
			long timerOff = System.currentTimeMillis();

			getLogger().info("time elapsed during database interaction (ms): " + (timerOff - timerOn));
		} catch (DataAccessException e) {
			getLogger().error(e.getLocalizedMessage(), e);
			throw new SystemErrorException(e.getLocalizedMessage());
		}finally{
			if(session != null){
				session.close();
			}
		}
	}


	public void saveOrUpdate(E inst) throws SystemErrorException {
		getLogger().debug("saveOrUpdate");
		Session session = null;
		if (processors != null)
			for (PreProcessor<E> pp : processors)
				inst = pp.process(inst);

		try {
			
			getHibernateTemplate().setCheckWriteOperations(false);
			long timerOn = System.currentTimeMillis();
			session = getHibernateTemplate().getSessionFactory().openSession();			
			session.saveOrUpdate(inst);
			session.flush();
			long timerOff = System.currentTimeMillis();

			getLogger().info("time elapsed during database interaction (ms): " + (timerOff - timerOn));
		} catch (DataAccessException e) {
			getLogger().error(e.getLocalizedMessage(), e);
			throw new SystemErrorException(e.getLocalizedMessage());
		}finally{
			if(session != null){
				session.close();
			}
		}
	}

	public E getById(Long id) throws SystemErrorException {
		getLogger().debug("getById");
		Session session = null;
		try {
			long timerOn = System.currentTimeMillis();
//			E obj = (E) getHibernateTemplate().get(getGenericClass(), id);
			session = getHibernateTemplate().getSessionFactory().openSession();
			E obj = (E) session.get(getGenericClass(), id);
			long timerOff = System.currentTimeMillis();

			getLogger().info(
					"time elapsed during database interaction (ms): "
							+ (timerOff - timerOn));
			return obj;
		} catch (DataAccessException e) {
			getLogger().error(e.getLocalizedMessage(), e);
			throw new SystemErrorException(e.getLocalizedMessage());
		}finally{
			if(session != null){
				session.close();
			}
		}
	}

	public void delete(E inst) throws SystemErrorException {
		getLogger().debug("delete");
		Session session = null;
		try {
			getHibernateTemplate().setCheckWriteOperations(false);
			long timerOn = System.currentTimeMillis();
			session = getHibernateTemplate().getSessionFactory().openSession();			
			session.delete(inst);
			session.flush();
			long timerOff = System.currentTimeMillis();

			getLogger().info(
					"time elapsed during database interaction (ms): "
							+ (timerOff - timerOn));
		} catch (DataAccessException e) {
			getLogger().error(e.getLocalizedMessage(), e);
			throw new SystemErrorException(e.getLocalizedMessage());
		}finally{
			if(session != null){
				session.close();
			}
		}
	}

	public E merge(E inst) throws SystemErrorException {
		getLogger().debug("merge");
		Session session = null;
		try {
			getHibernateTemplate().setCheckWriteOperations(false);
			long timerOn = System.currentTimeMillis();
//			E obj = (E) getHibernateTemplate().merge(inst);
			session = getHibernateTemplate().getSessionFactory().openSession();
			E obj = (E) session.merge(inst);
			long timerOff = System.currentTimeMillis();

			getLogger().info(
					"time elapsed during database interaction (ms): "
							+ (timerOff - timerOn));
			return obj;
		} catch (DataAccessException e) {
			getLogger().error(e.getLocalizedMessage(), e);
			throw new SystemErrorException(e.getLocalizedMessage());
		}finally{
			if(session != null){
				session.close();
			}
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
