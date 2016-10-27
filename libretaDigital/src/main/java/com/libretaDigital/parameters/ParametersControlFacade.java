package com.libretaDigital.parameters;

import java.util.List;

public interface ParametersControlFacade {
	
	public void saveParameter(ParameterControl parameter) throws Throwable;
	public void editParameter(ParameterControl parameter) throws Throwable;
	public ParameterControl getParameterByName(String name);
	public List<ParameterControl> getAllParameters() throws Throwable;

}