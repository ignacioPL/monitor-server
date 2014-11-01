package edu.soa.pdroid.server.services;

import edu.soa.pdroid.server.model.FileSystemUsage;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ignacio on 19/10/14.
 */
@Service
public class FileSystemService {

    public List<FileSystemUsage> getFileSystems() throws IOException {

        String command = "df -h -x tmpfs";

        CommandLine cmd = CommandLine.parse(command);

        OutputStream output = new ByteArrayOutputStream();

        DefaultExecutor de = new DefaultExecutor();
        de.setStreamHandler(new PumpStreamHandler(output));

        de.execute(cmd);

        InputStream inputStream = new ByteArrayInputStream( ((ByteArrayOutputStream)output).toByteArray() );

        BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream));

        String line;

        boolean first = false;

        List<FileSystemUsage> lista = new ArrayList<FileSystemUsage>();

        while( ( line=reader.readLine()) != null ){

            if(!first){
                first = true;
            }else{
                lista.add(parseLine(line));
            }
        }

        return lista;
    }

    private FileSystemUsage parseLine(String line) {

        FileSystemUsage fsu = new FileSystemUsage();

        String[] arrayLine = line.split("\\s+");

        fsu.setFilesystem(arrayLine[0]);
        fsu.setSize(arrayLine[1]);
        fsu.setUsed(arrayLine[2]);
        fsu.setAvailable(arrayLine[3]);
        fsu.setUsePercen(arrayLine[4]);
        fsu.setMountedOn(arrayLine[5]);

        return fsu;
    }
}
