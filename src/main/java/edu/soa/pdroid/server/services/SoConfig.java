package edu.soa.pdroid.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class SoConfig {

	private String osName;

    private Logger log = LoggerFactory.getLogger(SoConfig.class);

    public SoConfig(){
        this.osName = System.getProperty("os.name").toLowerCase();
        log.info("OS Name: "+this.osName);
    }
	
	public String getOsName(){
		return this.osName;
	}
	
}
