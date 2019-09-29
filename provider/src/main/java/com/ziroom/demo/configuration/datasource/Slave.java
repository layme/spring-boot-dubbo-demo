package com.ziroom.demo.configuration.datasource;

import java.lang.annotation.*;

/**
 * <p>仅应用在service层有效</p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author renhy
 * @version 1.0
 * @date Created in 2019年09月27日 17:36
 * @since 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Slave {
    // 仅应用在service层有效，操作从库
}
