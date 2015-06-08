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
        String command = "tasklist /nh /v";

        if("linux".equals(this.osName)){
            command = "ps -e -o pcpu,pid,state,pmem,fname --sort pcpu";
        }

        return command;
    }

    public String getKillCommand(){
        String killProcess = "taskkill -PID";

        if("linux".equals(this.osName)){
            killProcess = "kill";
        }

        return killProcess;
    }

}
