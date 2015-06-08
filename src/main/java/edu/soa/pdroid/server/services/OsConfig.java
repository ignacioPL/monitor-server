package edu.soa.pdroid.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OsConfig {

    private String osName;

    private Logger log = LoggerFactory.getLogger(OsConfig.class);

    public OsConfig(){
        this.osName = System.getProperty("os.name").toLowerCase();
        log.info("OS Name: "+this.osName);
    }
	
	public String getOsName(){
		return this.osName;
	}

    public String getProcessListCommand(){
        return "linux".equals(this.osName) ? "ps -e -o pcpu,pid,state,pmem,fname --sort pcpu" : "tasklist /nh /v"  ;
    }

    public String getKillCommand(){
        return "linux".equals(this.osName) ? "kill" : "taskkill -PID";
    }

}
