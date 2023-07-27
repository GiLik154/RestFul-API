package com.api.project.controller;

import com.api.project.controller.res.apires.ApiRes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class ApiController {
    private static final String API_BASE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst";

    private String serviceKey;

    @Value("${SERVICE_KEY}")
    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    @GetMapping("/api")
    public ApiRes api(@Param("nx") int nx, @Param("ny") int ny) throws IOException {
        String buildApiUrl = buildApiUrl(nx, ny);

        URL url = new URL(buildApiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        ApiRes apiRes = convertToDto(conn);

        conn.disconnect();

        return apiRes;
    }

    private String buildApiUrl(int nx, int ny) {
        StringBuilder urlBuilder = new StringBuilder(API_BASE_URL);
        urlBuilder.append("?").append(URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)).append("=").append(serviceKey);
        urlBuilder.append("&").append(URLEncoder.encode("pageNo", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode("1", StandardCharsets.UTF_8));
        urlBuilder.append("&").append(URLEncoder.encode("numOfRows", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode("1000", StandardCharsets.UTF_8));
        urlBuilder.append("&").append(URLEncoder.encode("dataType", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode("JSON", StandardCharsets.UTF_8));
        urlBuilder.append("&").append(URLEncoder.encode("base_date", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(getToday(), StandardCharsets.UTF_8));
        urlBuilder.append("&").append(URLEncoder.encode("base_time", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode("0600", StandardCharsets.UTF_8));
        urlBuilder.append("&").append(URLEncoder.encode("nx", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(String.valueOf(nx), StandardCharsets.UTF_8));
        urlBuilder.append("&").append(URLEncoder.encode("ny", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(String.valueOf(ny), StandardCharsets.UTF_8));
        return urlBuilder.toString();
    }

    private String getToday() {
        LocalDate today = LocalDate.now();

        return today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    private ApiRes convertToDto(HttpURLConnection conn) throws IOException {
        if (!validApiServer(conn)) {
            throw new ConnectException("API server is not responding.");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(conn.getInputStream(), ApiRes.class);
    }

    private boolean validApiServer(HttpURLConnection conn) throws IOException {
        return conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300;
    }
}