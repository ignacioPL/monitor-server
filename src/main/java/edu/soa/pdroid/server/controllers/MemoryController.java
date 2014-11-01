package edu.soa.pdroid.server.controllers;

import edu.soa.pdroid.server.model.ServerMemoryUsage;
import edu.soa.pdroid.server.services.MemoryService;
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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ServerMemoryUsage> getMemoryUsage(){

        try {
            return new ResponseEntity<ServerMemoryUsage>(memoryService.getMemoryStatus(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<ServerMemoryUsage>(HttpStatus.NO_CONTENT);
        }

    }

}
