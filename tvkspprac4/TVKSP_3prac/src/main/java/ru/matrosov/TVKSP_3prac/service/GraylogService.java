package ru.matrosov.TVKSP_3prac.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class GraylogService {
    @Value("${graylog.api.url}")
    private String graylogApiUrl;

    private final RestTemplate restTemplate;

    @Value("${graylog.api.username}")
    private String graylogUsername;

    @Value("${graylog.api.password}")
    private String graylogPassword;
    public byte[] exportLogs() {
        var searchUrl = graylogApiUrl + "/api/search/universal/absolute/export";

        var query = "*"; // Поиск всех логов
        var from = Instant.now().minusSeconds(86400).toString(); // Логи за последние 24 часа
        var to = Instant.now().toString();
        var fields = "timestamp,source,message"; // Поля для экспорта

        var url = String.format("%s?query=%s&from=%s&to=%s&fields=%s",
                searchUrl, query, from, to, fields);

        // Настройка заголовков
        var headers = new HttpHeaders();
        String auth = graylogUsername + ":" + graylogPassword;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        headers.set("Accept", "text/csv");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
        System.out.println(response);

        if (response.getBody() == null) {
            return new byte[]{0};
        }
        return response.getBody();
    }
}
