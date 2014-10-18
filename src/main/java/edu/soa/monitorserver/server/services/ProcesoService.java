package edu.soa.monitorserver.server.services;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.soa.monitorserver.server.model.Proceso;

/**
 * Created by ignacio on 18/08/14.
 */
@Service
public class ProcesoService {

	@Autowired
	private SoConfig soConfig;
	
    public void killProceso(String pid) throws IOException {
        
    	String killProcess = "taskkill -PID";
    	
    	if("linux".equals(soConfig.getOsName())){
    		killProcess = "kill"; 
    	}
    	
    	CommandLine cmd = new CommandLine(killProcess);

        cmd.addArgument(pid);

        DefaultExecutor de = new DefaultExecutor();

        de.execute(cmd);
    }

    public List<Proceso> getProcesos() throws IOException {

        List<Proceso> lp = new ArrayList<Proceso>();

        String comando = "tasklist /nh /v";
        
        if("linux".equals(soConfig.getOsName())){
        	comando = "ps -e -o pcpu,pid,state,pmem,fname --sort pcpu";
        }
        
        CommandLine cmd1 = CommandLine.parse(comando);

        OutputStream output1 = new ByteArrayOutputStream();

        DefaultExecutor de1 = new DefaultExecutor();
        de1.setStreamHandler( new PumpStreamHandler(output1));

        de1.execute(cmd1);

        InputStream is1 = new ByteArrayInputStream( ((ByteArrayOutputStream)output1).toByteArray() );

        BufferedReader reader = new BufferedReader( new InputStreamReader(is1));

        String line="";

        List<String> listaProcesos = new ArrayList<String>();

        while( (line=reader.readLine()) != null ){
            listaProcesos.add(line);
        }

        listaProcesos.remove(0);

        Collections.reverse(listaProcesos);

        int count=0;
        for(String s : listaProcesos){
            if( s.startsWith(" 0.0") ){
                if(count<10){
                    count++;
                }else {
                    break;
                }
            }
            lp.add(getProceso(s));
        }

        return lp;
    }

    private Proceso getProceso(String line) {
        if(line.contains("%CPU")){
            return null;
        }

        if( line.startsWith(" ") ){
            line = line.replaceFirst(" ","");
        }

        String[] arrayLine = line.split("\\s+");

        Proceso p = new Proceso();
        
        if("linux".equals(soConfig.getOsName())){
	        p.setPcpu(arrayLine[0]);
	        p.setPid(arrayLine[1]);
	        p.setState(arrayLine[2]);
	        p.setPmem(arrayLine[3]);
	        p.setName(arrayLine[4]);
        }else{
        	p.setName(arrayLine[0]);
        	p.setPid(arrayLine[1]);
        	p.setPmem(arrayLine[4]+arrayLine[5]);
        	p.setState(arrayLine[6]);
        	p.setPcpu("N/D");
        }
        return p;
    }

}
