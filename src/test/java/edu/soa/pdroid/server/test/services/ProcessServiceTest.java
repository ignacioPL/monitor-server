package edu.soa.pdroid.server.test.services;

import edu.soa.pdroid.server.Application;
import edu.soa.pdroid.server.model.OsProcess;
import edu.soa.pdroid.server.services.ProcessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by ignacio on 31/10/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ProcessServiceTest {

    @Autowired
    private ProcessService processService;

    @Test
    public void listOfProcessTest() throws IOException {

        List<OsProcess> listOfProcess = processService.getProcess();

        assertTrue(listOfProcess.size() > 0);
    }


}
