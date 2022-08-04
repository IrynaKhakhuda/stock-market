package com.stock.market.services;

import com.stock.market.aspects.ExecutionTimeTracked;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Component
@Log4j2
public class FileLoader {

    @Autowired
    private ExistedFiles existedFiles;

    @ExecutionTimeTracked
    public List<LocalDate> readFile(String token) {
        if (existedFiles.isFileExist(token)) {
            try {
                return Files.readAllLines(existedFiles.getPath(token)).stream().skip(1).map(s -> s.substring(0, 10))
                        .map(LocalDate::parse).toList();
            } catch (IOException e) {
                log.error("Fail while reading a CSV file, token: [{{}}]", token);
                e.printStackTrace();
            }
        } else {
            log.error("There is no exist such token [{{}}]", token);
        }
        return Collections.emptyList();
    }

}
