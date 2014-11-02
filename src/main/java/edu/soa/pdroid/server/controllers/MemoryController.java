package edu.soa.pdroid.server.controllers;

import edu.soa.pdroid.server.model.ServerMemoryUsage;
import edu.soa.pdroid.server.services.MemoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by ignacio on 19/10/14.
 */
@RestController
@RequestMapping("/memory")
public class MemoryController {

    @Autowired
    private MemoryService memoryService;

    private Logger log = LoggerFactory.getLogger(MemoryController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ServerMemoryUsage> getMemoryUsage(){

        log.info("memory usage requested");

        try {
            return new ResponseEntity<ServerMemoryUsage>(memoryService.getMemoryStatus(), HttpStatus.OK);
        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<ServerMemoryUsage>(HttpStatus.NO_CONTENT);
        }

    }

}
