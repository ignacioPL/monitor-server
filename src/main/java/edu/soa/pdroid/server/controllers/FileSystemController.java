package edu.soa.pdroid.server.controllers;

import edu.soa.pdroid.server.model.FileSystemUsage;
import edu.soa.pdroid.server.services.FileSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger log = LoggerFactory.getLogger(FileSystemController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FileSystemUsage>> getFileSystemsUsage() {

        log.info("FileSystem Usage required");

        try {
            return new ResponseEntity<List<FileSystemUsage>>(fileSystemService.getFileSystems(), HttpStatus.OK);
        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<List<FileSystemUsage>>(HttpStatus.NO_CONTENT);
        }
    }
}
