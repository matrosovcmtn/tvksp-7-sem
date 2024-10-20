package ru.matrosov.TVKSP_3prac.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.matrosov.TVKSP_3prac.service.GraylogService;

@Slf4j
@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class GraylogController {
    private final GraylogService graylogService;

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportLogs() {
        log.info("Start method executing");
        byte[] csvData = graylogService.exportLogs();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=logs.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csvData);
    }
}
