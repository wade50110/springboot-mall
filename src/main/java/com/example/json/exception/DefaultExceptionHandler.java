 package com.example.json.exception;


 import com.example.json.exception.vo.A10001FieldErrorVo;
 import com.example.json.exception.vo.A10002UnknownErrorVo;
 import lombok.RequiredArgsConstructor;
 import lombok.extern.slf4j.Slf4j;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.validation.BindException;
 import org.springframework.validation.FieldError;
 import org.springframework.web.bind.annotation.ExceptionHandler;
 import org.springframework.web.bind.annotation.ResponseStatus;
 import org.springframework.web.bind.annotation.RestControllerAdvice;

 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import java.util.List;


 /**
 * Global exception handler.
 *
 * @author Evan
 * @date 2019/11
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class DefaultExceptionHandler {
    @Resource
    public HttpServletRequest request;

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        saveExceptionLog(e);


        e.printStackTrace();
        return ResponseEntity.badRequest().body(new A10002UnknownErrorVo());
    }

     /** 參數錯誤 */
     @ExceptionHandler(BindException.class)
     @ResponseStatus(HttpStatus.BAD_REQUEST)
     public ResponseEntity<?> handleBindException(final BindException e) {
         List<FieldError> fe = e.getFieldErrors();
         StringBuffer sb = new StringBuffer();
         for (FieldError fieldError : fe) {
             sb.append(fieldError.getField())
                     .append(":")
                     .append(fieldError.getDefaultMessage())
                     .append(";");
         }
         A10001FieldErrorVo a10001FieldErrorVo = new A10001FieldErrorVo();
         a10001FieldErrorVo.setMessage(sb.toString());

         return ResponseEntity.badRequest().body(a10001FieldErrorVo);
     }

     /** 錯誤log起來 */
     private void saveExceptionLog(final Exception e) {
         StackTraceElement[] stackTraceArray = e.getStackTrace();
         StringBuffer stringBuffer = new StringBuffer();
         for (StackTraceElement stackTraceElement : stackTraceArray) {
             stringBuffer.append(stackTraceElement).append("\n");
         }
         log.error("URL = " + getErrorLogUrl());
         log.error("saveExceptionLog = " + stringBuffer);
     }

     private String getErrorLogUrl() {
         return "URL:" + request.getRequestURL() + "\t";
     }
}