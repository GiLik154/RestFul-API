package com.api.project.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_FOUND_DEPARTMENT("부서의 아이디를 확인해주세요"),
    NOT_FOUND_EMPLOYEE("직원의 아이디를 확인해주세요"),
    INVALID_PERCENTAGE("퍼센테이지 입력을 확인해주세요"),
    METHOD_EXCEPTION("입력에 오류가 있습니다. 다시 확인해주세요.");

    private final String msg;

    ErrorCode(String msg) {
        this.msg = msg;
    }
}
