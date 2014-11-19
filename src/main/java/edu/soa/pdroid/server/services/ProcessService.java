package edu.soa.pdroid.server.services;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import edu.soa.pdroid.server.model.OsProcess;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ignacio on 18/08/14.
 */
@Service
public class ProcessService {

	@Autowired
	private OsConfig osConfig;
	
    public void killProcess(String pid) throws IOException {
        
    	String killProcess = "taskkill -PID";
    	
    	if("linux".equals(osConfig.getOsName())){
    		killProcess = "kill"; 
    	}
    	
    	CommandLine cmd = new CommandLine(killProcess);

        cmd.addArgument(pid);

        DefaultExecutor de = new DefaultExecutor();

        de.execute(cmd);
    }

    public List<OsProcess> getProcess() throws IOException {

        String command = "tasklist /nh /v";
        
        if("linux".equals(osConfig.getOsName())){
        	command = "ps -e -o pcpu,pid,state,pmem,fname --sort pcpu";
        }
        
        CommandLine cmd = CommandLine.parse(command);

        OutputStream output = new ByteArrayOutputStream();

        DefaultExecutor de = new DefaultExecutor();
        de.setStreamHandler(new PumpStreamHandler(output));

        de.execute(cmd);

        InputStream inputStream = new ByteArrayInputStream( ((ByteArrayOutputStream)output).toByteArray() );

        BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream));

        List<String> listOfProcess = reader.lines().collect(Collectors.toList());

        reader.close();

        return listOfProcess.stream()
                            .skip(1)
                            .sorted(Comparator.reverseOrder())
                            .filter(s -> !s.startsWith(" 0.0"))
                            .map(s -> mapProcess(s))
                            .collect(Collectors.toList());
    }

    private OsProcess mapProcess(String line) {

        if( line.startsWith(" ") ){
            line = line.replaceFirst(" ","");
        }

        String[] arrayLine = line.split("\\s+");

        OsProcess p = new OsProcess();
        
        if("linux".equals(osConfig.getOsName())){
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
