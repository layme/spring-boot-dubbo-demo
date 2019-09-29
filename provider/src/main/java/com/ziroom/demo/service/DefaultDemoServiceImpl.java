package com.ziroom.demo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziroom.demo.api.DemoService;
import com.ziroom.demo.configuration.datasource.Master;
import com.ziroom.demo.configuration.datasource.Slave;
import com.ziroom.demo.dto.MetaFieldDto;
import com.ziroom.demo.dto.PageResultDto;
import com.ziroom.demo.mapper.PerMetaFieldMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

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
 * @date Created in 2019年09月26日 15:45
 * @since 1.0
 */
@Service(version = "1.0.0")
public class DefaultDemoServiceImpl implements DemoService {
    @Autowired
    private PerMetaFieldMapper perMetaFieldMapper;

    /**
     * The default value of ${dubbo.application.name} is ${spring.application.name}
     */
    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String sayHello(String name) {
        return String.format("[%s] : Hello, %s", serviceName, name);
    }

    @Override
    public List<MetaFieldDto> selectAll() {
        return perMetaFieldMapper.selectAll();
    }

    @Master
    @Override
    public List<MetaFieldDto> selectAllFromMaster() {
        return perMetaFieldMapper.selectAll();
    }

    @Slave
    @Override
    public List<MetaFieldDto> selectAllFromSlave() {
        return perMetaFieldMapper.selectAll();
    }

    @Override
    public PageResultDto<MetaFieldDto> selectByPage(Integer page, Integer limit) {
        // 分页设置
        PageHelper.startPage(page, limit);
        List<MetaFieldDto> list = perMetaFieldMapper.selectAll();

        // 获取分页结果
        PageInfo<MetaFieldDto> pageInfo = new PageInfo<>(list);

        return PageResultDto.<MetaFieldDto>builder().rows(pageInfo.getList()).total(pageInfo.getTotal()).build();
    }
}
