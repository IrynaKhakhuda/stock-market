package com.stock.market.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

@Service
public class ExistedFiles {

    private Map<String, Path> allExistedFiles = Collections.emptyMap();

    private static final String COMMON_CSV_FILE_SUFFIX = ".csv";

    public ExistedFiles(@Value("${files.path}") String path) {
        try {
            allExistedFiles = Files.walk(Path.of(path)).filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(COMMON_CSV_FILE_SUFFIX))
                    .collect(toMap(f -> f.getFileName().toString().replace(COMMON_CSV_FILE_SUFFIX, ""), f -> f));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isFileExist(String symbol) {
        return allExistedFiles.containsKey(symbol);
    }

    public Path getPath(String token) {
        return allExistedFiles.get(token);
    }

    public Set<String> getAllTokens() {
        return allExistedFiles.keySet();
    }
}
