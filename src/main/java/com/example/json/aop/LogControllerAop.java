package com.example.json.aop;



import com.example.json.util.Constants;
import com.example.json.util.IpUtil;
import com.example.json.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LogControllerAop {
    public static final String CONTROLLER_POINT_CUT = "getPointCut()||postPointCut()||deletePointCut()||patchPointCut()";


    // 配置織入點
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postPointCut() {
    }

    // 配置織入點
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getPointCut() {
    }

    // 配置織入點
    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deletePointCut() {
    }

    // 配置織入點
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void patchPointCut() {
    }


    /**
     * 前置通知，方法调用前被调用
     *
     * @param joinPoint
     */
    @Before(CONTROLLER_POINT_CUT)
    public void doBefore(JoinPoint joinPoint) {
        try {
            String traceId = RandomStringUtils.randomAlphanumeric(5);
            MDC.put(Constants.LOG_TRACE_ID, traceId);
            //获取目标方法的参数信息
            Signature signature = joinPoint.getSignature();

            //AOP代理类的类（class）信息
            signature.getDeclaringType();
            MethodSignature methodSignature = (MethodSignature) signature;
            String[] strings = methodSignature.getParameterNames();
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest req = attributes.getRequest();
            // 打印请求内容
            // 记录下请求内容
            Enumeration<String> headerNames = req.getHeaderNames();
            StringBuffer header = new StringBuffer("[");
            while (headerNames.hasMoreElements()) {
                String hk = headerNames.nextElement();
                String hv = req.getHeader(hk);
                header.append(hk + "=" + hv + ",");
            }
            if (',' == (header.charAt(header.length() - 1))) {
                header = header.deleteCharAt(header.length() - 1);
            }
            header.append("]");


            StringBuffer youAreSb = new StringBuffer();
            youAreSb.append("\n===============SERVICE-REQ-START===============").
                    append("\nURL : " + req.getRequestURL().toString()).
//                    append("\nHEADER : ").append(header).
                    append("\nHTTP_METHOD : ").append(req.getMethod()).
                    append("\nIP : ").append(IpUtil.getIpAddress(req)).
                    append("\nIP_LAST : ").append(IpUtil.getIpFromRequest(req)).
                    append("\nPACKAGE : ").append(signature.getDeclaringTypeName()).
                    append("\nCLASS_METHOD : ").append(joinPoint.getSignature().getDeclaringTypeName()).append(".").append(joinPoint.getSignature().getName()).
                    append("\nMETHOD_NAME : ").append(signature.getName()).
                    append("\nMETHOD_PARAM_NAME : ").append(Arrays.toString(strings)).
                    append("\nMETHOD_PARAM_NAME_AND_VALUE : ").append(Arrays.toString(joinPoint.getArgs())).
//                    append("\n請求類方法參數名稱和值 : ").append(sb).
                    append("\n===============SERVICE-REQ-END===============");
            log.info(youAreSb.toString());
        } catch (Exception e) {
            log.warn("=====>@Before：Parameter conversion exception requested.", e);
        }
    }

    /**
     * Response
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = CONTROLLER_POINT_CUT)
    public void doAfterReturning(JoinPoint joinPoint, Object ret) {
        try {
            log.info("\n===============SERVICE-RES-START===============");
            log.info("\nResponseBody : " + JsonUtil.toJson(ret));
            log.info("\n===============SERVICE-RES-END===============");
        } catch (Exception e) {
            log.error("json轉換錯誤" + ret);
        }
    }

    /**
     * 異常通知
     *
     * @param jp
     */
    @AfterThrowing(CONTROLLER_POINT_CUT)
    public void throwss(JoinPoint jp) {
        log.error("Service=====>Execution method is abnormal.......");
        MDC.clear();
    }


    /**
     * 增強通知，MethodInterceptor
     *
     * @param pjp
     * @return
     */
    @Around(CONTROLLER_POINT_CUT)
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object o = null;
        try {
            //获取开始执行的时间
            log.info("\n===============SERVICE-RES-START===============");
            long startTime = System.currentTimeMillis();
            o = pjp.proceed();
            // 获取执行结束的时间
            long endTime = System.currentTimeMillis();
            log.info("\n===============SERVICE-RES-END===============");
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
            // 打印耗时的信息
            if (endTime - startTime >2000){
                log.error("Service=====>method {} :Processing time is too long, took:{} ms", methodName, endTime - startTime);
            } else {
                log.info("Service=====>method {} :Processing time is normal", methodName);
            }
        } catch (Exception e) {
            log.error("=====>@Before：Parameter conversion exception requested.", e);
        } finally {
            MDC.clear();
        }
        return o;
    }

}
