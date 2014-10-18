package edu.soa.monitorserver.server.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.soa.monitorserver.server.model.Proceso;
import edu.soa.monitorserver.server.services.ProcesoService;

@RestController
@RequestMapping("/proceso")
public class ProcesoController {

	@Autowired
	private ProcesoService procesoService;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Proceso>> showProcess() {
        
    	 try {
             return new ResponseEntity<List<Proceso>>(procesoService.getProcesos(),HttpStatus.OK);
         } catch (IOException e) {
             e.printStackTrace();
             return new ResponseEntity<List<Proceso>>(HttpStatus.NO_CONTENT);
         }
    }
    
    @RequestMapping(value="/{pid}", method=RequestMethod.DELETE)
    public ResponseEntity<Object> killProceso(@PathVariable("pid") String pid){

        try {
            Integer.parseInt(pid);
        }catch(Exception e){
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }

        try {
            procesoService.killProceso(pid);
        } catch (IOException e) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
