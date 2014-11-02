package edu.soa.pdroid.server.controllers;

import java.io.IOException;
import java.util.List;

import edu.soa.pdroid.server.model.OsProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.soa.pdroid.server.services.ProcessService;

@RestController
@RequestMapping("/process")
public class ProcessController {

	@Autowired
	private ProcessService procesoService;

    private Logger log = LoggerFactory.getLogger(ProcessController.class);

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<OsProcess>> showProcess() {

         log.info("showProcess Required");

    	 try {
             return new ResponseEntity<>(procesoService.getProcesos(),HttpStatus.OK);
         } catch (IOException e) {
             log.error(e.getMessage());
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
    }
    
    @RequestMapping(value="/{pid}", method=RequestMethod.DELETE)
    public ResponseEntity<Object> killProceso(@PathVariable("pid") String pid){

        log.info("kill process invoked with pid: "+pid);

        try {
            Integer.parseInt(pid);
        }catch(Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            procesoService.killProceso(pid);
        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("process "+pid+" killed");

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
