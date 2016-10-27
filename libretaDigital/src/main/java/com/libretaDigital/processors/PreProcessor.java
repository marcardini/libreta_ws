package com.libretaDigital.processors;

public interface PreProcessor<E> {

	E process(E object);
}
