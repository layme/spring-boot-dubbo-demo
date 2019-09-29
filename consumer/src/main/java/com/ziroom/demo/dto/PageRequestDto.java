package com.ziroom.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询对象
 * @author
 * @create 2018-12-15 20:07
 * Description:
 */
@Data
public class PageRequestDto implements Serializable {
    private static final long serialVersionUID = -2086857838405077930L;

    /**
     * 页码
     */
    private Integer page;

    /**
     * 页大小
     */
    private Integer limit;
}
