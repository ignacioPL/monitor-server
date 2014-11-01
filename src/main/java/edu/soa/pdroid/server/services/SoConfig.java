package edu.soa.pdroid.server.services;

import org.springframework.stereotype.Service;


@Service
public class SoConfig {

	private String osName;
	
    public SoConfig(){
		this.osName = System.getProperty("os.name").toLowerCase();
    }
	
	public String getOsName(){
		return this.osName;
	}
	
}
