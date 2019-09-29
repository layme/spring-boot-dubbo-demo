package com.ziroom.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询返回对象
 * @author
 * @create 2018-12-15 20:07
 * Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResultDto<T> implements Serializable {
    private static final long serialVersionUID = -7151930751313982168L;

    /**
     * 总量
     */
    private Long total;

    /**
     * 当页数据
     */
    private List<T> rows;
}
