package com.api.project.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum KoreaCity {
    SEOUL("서울", 60, 127),
    BUSAN("부산", 98, 76),
    INCHEON("인천", 55, 124),
    DAEGU("대구", 89, 90),
    DAEJEON("대전", 67, 100),
    GWANGJU("광주", 58, 74),
    ULSAN("울산", 102, 84),
    SUWON("수원", 60, 120),
    CHANGWON("청원", 98, 80),
    CHEONGJU("청주", 69, 103);

    private final String name;
    private final int gridX;
    private final int gridY;

    KoreaCity(String name, int gridX, int gridY) {
        this.name = name;
        this.gridX = gridX;
        this.gridY = gridY;
    }

    public String getName() {
        return name;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public static List<String> getNames() {
        KoreaCity[] cityArray = KoreaCity.values();

        // 스트림을 이용하여 도시 이름들을 리스트로 반환
        return Arrays.stream(cityArray)
                .map(KoreaCity::name)
                .collect(Collectors.toList());
    }
}
