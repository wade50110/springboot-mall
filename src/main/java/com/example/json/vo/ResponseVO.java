package com.example.json.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO {
    // 回傳狀態代碼(自定義)
    private int code;
    // 回傳狀態訊息(自定義)
    private String msg;
    // 回傳資料，型別為Object，讓回傳的資料型別可以更多樣化
    private Object data;

    public static ResponseVO buildSuccessResult(Object data) {
        return buildResult(HttpStatus.OK.value(), "Success", data);
    }

    public static ResponseVO buildSuccessResultWithMessage(String message) {
        return buildResult(HttpStatus.OK.value(), message, null);
    }

    public static ResponseVO buildSuccessResult() {
        return buildResult(HttpStatus.OK.value(), "Success", null);
    }

    public static ResponseVO buildFailResult(String message) {
        return buildResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
    }

    public static ResponseVO buildResult(HttpStatus httpStatus, String message, Object data) {
        return buildResult(httpStatus.value(), message, data);
    }

    public static ResponseVO buildResult(int resultCode, String message, Object data) {
        return new ResponseVO(resultCode, message, data);
    }

}
