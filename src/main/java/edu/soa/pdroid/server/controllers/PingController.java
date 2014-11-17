package edu.soa.pdroid.server.controllers;

import edu.soa.pdroid.server.services.PingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ignacio on 13/11/14.
 */
@RestController
@RequestMapping("/ping")
public class PingController {

    private Logger log = LoggerFactory.getLogger(PingController.class);

    @Autowired
    private PingService pingService;

    @RequestMapping(value="/{ip:.+}" , method= RequestMethod.GET)
    public ResponseEntity<Object> doPing(@PathVariable("ip") String url) {

        log.info("Ping requested to: "+url);

        if(!pingService.isAlive(url)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
