package com.example.json.exception.vo;


import com.example.json.exception.ErrorMessage;
import com.example.json.util.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public final class A10002UnknownErrorVo extends BusinessExceptionVo {
  @Schema(description = "錯誤狀態碼", example = "10002")
  private final Integer code = 10002;

  @Schema(description = "回應訊息", example = ErrorMessage.A10002)
  private  String message = ErrorMessage.A10002;

  @Schema(
      description = "traceId",
      example = "dad34547-3117-4c3b-8ea4-59ea91de9dee")
  private final UUID traceId = UUID.randomUUID();

  @Schema(description = "錯誤來源", example = "")
  private final String source = Constants.SERVICE_NAME;
}
