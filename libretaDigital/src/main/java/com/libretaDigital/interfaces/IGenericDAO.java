package com.libretaDigital.interfaces;

import java.io.Serializable;
import com.libretaDigital.exceptions.SystemErrorException;

public interface IGenericDAO<E> {

	abstract Serializable save(E inst) throws SystemErrorException;

	abstract void saveOrUpdate(E inst) throws SystemErrorException;

	abstract E getById(Long id) throws SystemErrorException;

	void delete(E inst) throws SystemErrorException;

	E merge(E inst) throws SystemErrorException;

}