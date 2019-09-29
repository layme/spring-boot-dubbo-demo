package com.ziroom.demo.configuration.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author renhy
 * @version 1.0
 * @date Created in 2019年09月27日 17:47
 * @since 1.0
 */
@Slf4j
@Aspect
@Component
public class DataSourceAop {
    /**
     * 切点，注意这里是在service层，避免同一事务操作不同数据源
     * @author renhy
     * @date 2019-09-29 16:20:18
     * @param
     * @return
     */
    @Pointcut("execution(* com.ziroom.demo.service..*.*(..)))")
    public void aspect() {
    }

    @Before("aspect()")
    public void setDatasource(JoinPoint point) {
        Class<?> clz = point.getTarget().getClass();
        String method = point.getSignature().getName();

        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = clz.getDeclaredMethod(method, parameterTypes);
            if (m != null) {
                if (AnnotationUtils.findAnnotation(m, Master.class) != null) {
                    // 方法上有Master注解，使用主库
                    DataSourceContextHolder.master();
                    log.info("dataSource switch to master");

                } else if (AnnotationUtils.findAnnotation(m, Slave.class) != null) {
                    // 方法上有Slave注解，使用从库
                    DataSourceContextHolder.slave();
                    log.info("dataSource switch to slave");

                } else {
                    // 方法上无注解，类上有Master注解，使用主库
                    Master annotationMaster = AnnotationUtils.findAnnotation(clz, Master.class);
                    if (annotationMaster != null) {
                        DataSourceContextHolder.master();
                        log.info("dataSource switch to master");
                        return;
                    }

                    // 方法上无注解，类上有Slave注解，使用从库
                    Slave annotationSlave = AnnotationUtils.findAnnotation(clz, Slave.class);
                    if (annotationSlave != null) {
                        DataSourceContextHolder.slave();
                        log.info("dataSource switch to slave");
                        return;
                    }

                    // 方法和类均无注解，默认使用主库
                    DataSourceContextHolder.master();
                    log.info("dataSource switch to master");
                }
            }
        } catch (Exception e) {
            log.error("设置数据源失败", e);
        }
    }

    @After("aspect()")
    public void after() {
        DataSourceContextHolder.cleanAll();
        log.info("dataSource cleanAll");
    }
}
