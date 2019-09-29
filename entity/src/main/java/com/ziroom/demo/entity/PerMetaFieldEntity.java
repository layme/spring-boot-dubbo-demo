package com.ziroom.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * t_per_meta_field
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerMetaFieldEntity implements Serializable {
    private static final long serialVersionUID = 4236694048639270027L;

    /**
     * 主键
     */
    private Integer id;

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

    /**
     * 字段类型 1-业绩公式字段，2-分摊人员字段
     */
    private Byte fieldType;

    /**
     * 值类型 1-数字，2-字符串
     */
    private Byte valueType;

    /**
     * 枚举类型 0-非枚举，1-单值，2-范围值
     */
    private Byte enumType;

    /**
     * 元数据类型 1-成本，2-收入，3-管家计件，4-目标
     */
    private Byte metaType;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 删除状态;0：未删除；1已删除
     */
    private Boolean isDel;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人
     */
    private String lastModifyBy;

    /**
     * 最后修改时间
     */
    private Date lastModifyTime;
}