package com.example.json.exception.vo;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class BusinessExceptionVo {
  Integer code;
  String message;
  UUID traceId;
  String source;
}
