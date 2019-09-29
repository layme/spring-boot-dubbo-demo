package com.ziroom.demo.mapper;


import com.ziroom.demo.dto.MetaFieldDto;
import com.ziroom.demo.entity.PerMetaFieldEntity;

import java.util.List;

public interface PerMetaFieldMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PerMetaFieldEntity record);

    int insertSelective(PerMetaFieldEntity record);

    PerMetaFieldEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PerMetaFieldEntity record);

    int updateByPrimaryKey(PerMetaFieldEntity record);

    List<MetaFieldDto> selectAll();
}