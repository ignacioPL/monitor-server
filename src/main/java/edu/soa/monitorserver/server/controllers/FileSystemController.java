package edu.soa.monitorserver.server.controllers;

import edu.soa.monitorserver.server.model.FileSystemUsage;
import edu.soa.monitorserver.server.services.FileSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by ignacio on 19/10/14.
 */
@RestController
@RequestMapping("/filesystem")
public class FileSystemController {

    @Autowired
    private FileSystemService fileSystemService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FileSystemUsage>> getFileSystemsUsage() {

        try {
            return new ResponseEntity<List<FileSystemUsage>>(fileSystemService.getFileSystems(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<List<FileSystemUsage>>(HttpStatus.NO_CONTENT);
        }
    }
}
