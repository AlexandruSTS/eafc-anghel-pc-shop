package eafcanghel.pcshop.security.controller;

import eafcanghel.pcshop.service.MyDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    private MyDbService myDbService;

    public TestController(MyDbService myDbService) {
        this.myDbService = myDbService;
    }


    @GetMapping(value = "/save")
    public ResponseEntity save() {
        try {
            myDbService.saveData();
            return ResponseEntity.accepted().body("OK");
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().body("ERROR :-)");
        }
    }


}
