package com.example.json.exception;

import com.example.json.exception.vo.BusinessExceptionVo;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class BusinessException extends RuntimeException {

  private final BusinessExceptionVo businessExceptionVo;

  public BusinessException(BusinessExceptionVo businessExceptionVo) {
    this.businessExceptionVo = businessExceptionVo;
  }
}
