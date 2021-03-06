package com.ziroom.demo.api;

import com.ziroom.demo.dto.MetaFieldDto;
import com.ziroom.demo.dto.PageResultDto;

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
 * @date Created in 2019年09月26日 15:44
 * @since 1.0
 */
public interface DemoService {
    /**
     * test
     * @param name
     * @return
     */
    String sayHello(String name);

    List<MetaFieldDto> selectAll();

    List<MetaFieldDto> selectAllFromMaster();

    List<MetaFieldDto> selectAllFromSlave();

    PageResultDto<MetaFieldDto> selectByPage(Integer page, Integer limit);
}
