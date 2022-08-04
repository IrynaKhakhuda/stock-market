package com.stock.market.api;

import com.stock.market.services.ExistedFiles;
import com.stock.market.services.ExtractHolidaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    ExistedFiles existedFiles;

    @Autowired
    ExtractHolidaysService extractHolidaysService;


    @RequestMapping("/get-holidays")
    public ResponseEntity<?> getHolidays(@RequestParam(defaultValue = "SAMPLE") String token) {
        List<LocalDate> holidays = extractHolidaysService.getHolidays(token);
        return new ResponseEntity<>(holidays, HttpStatus.OK);
    }

    @RequestMapping("all-tokens")
    public ResponseEntity<?> getAllTokens() {
        return new ResponseEntity<>(existedFiles.getAllTokens(), HttpStatus.OK);
    }


}
