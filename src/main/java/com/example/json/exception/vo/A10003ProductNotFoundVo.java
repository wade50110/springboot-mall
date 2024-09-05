package com.example.json.exception.vo;

import com.example.json.exception.ErrorMessage;
import com.example.json.util.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class A10003ProductNotFoundVo extends BusinessExceptionVo{
    @Schema(description = "錯誤狀態碼", example = "10003")
    private final Integer code = 10003;

    @Schema(description = "回應訊息", example = ErrorMessage.A10003)
    private  String message = ErrorMessage.A10003;

    @Schema(
            description = "traceId",
            example = "dad34547-3117-4c3b-8ea4-59ea91de9dee")
    private final UUID traceId = UUID.randomUUID();

    @Schema(description = "錯誤來源", example = "")
    private final String source = Constants.SERVICE_NAME;
}
