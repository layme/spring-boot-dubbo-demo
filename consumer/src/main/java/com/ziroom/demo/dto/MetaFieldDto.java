package com.ziroom.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
 * @date Created in 2019年09月29日 13:29
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaFieldDto implements Serializable {
    private static final long serialVersionUID = -9152762793664244153L;

    /**
     * 逻辑标识
     */
    private String fid;

    /**
     * 字段key
     */
    private String fieldKey;

    /**
     * 字段名称
     */
    private String fieldName;
}
