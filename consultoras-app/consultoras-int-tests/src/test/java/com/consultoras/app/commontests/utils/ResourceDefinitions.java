package com.consultoras.app.commontests.utils;

import org.junit.Ignore;

@Ignore
public enum ResourceDefinitions {
	CLIENTE("clientes");
	
	private String resourceName;
	
	private ResourceDefinitions(final String resourceName){
		this.resourceName = resourceName;
	}
	
	public String getResourceName(){
		return resourceName;
	}

}
