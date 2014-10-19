package edu.soa.monitorserver.server.services;

import edu.soa.monitorserver.server.model.ServerMemoryUsage;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by ignacio on 19/10/14.
 */
@Service
public class MemoryService {

    public ServerMemoryUsage getMemoryStatus() throws IOException {

        String command = "free -m";

        CommandLine cmd = CommandLine.parse(command);

        OutputStream output = new ByteArrayOutputStream();

        DefaultExecutor de = new DefaultExecutor();
        de.setStreamHandler(new PumpStreamHandler(output));

        de.execute(cmd);

        InputStream inputStream = new ByteArrayInputStream( ((ByteArrayOutputStream)output).toByteArray() );

        BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream));

        String line;

        ServerMemoryUsage smu = new ServerMemoryUsage();

        boolean first = false;
        boolean second = false;
        boolean third = false;

        while( ( line=reader.readLine()) != null ){

            if(!first){
                first = true;
            }else if(!second){

                String[] arrayLine = line.split("\\s+");

                smu.setTotalMemory(arrayLine[1]);

                second = true;
            }else if (!third) {

                String[] arrayLine = line.split("\\s+");

                smu.setUsedMemory(arrayLine[2]);
                smu.setFreeMemory(arrayLine[3]);
                third = true;
            }
        }

        return smu;
    }

}
