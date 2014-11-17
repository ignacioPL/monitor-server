package edu.soa.pdroid.server.services;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * Created by ignacio on 13/11/14.
 */
@Service
public class PingService {

    public boolean isAlive(String url) {

        String ping = "ping -c 4 "+url;

        CommandLine cmd = CommandLine.parse(ping);

        DefaultExecutor de = new DefaultExecutor();

        try {
            de.execute(cmd);
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}
