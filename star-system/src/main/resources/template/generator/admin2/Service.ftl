package ${package}.service;

import ${package}.domain.dto.*;
import cn.hf.boot.web.PageResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* @description 服务接口
* @author ${author}
* @date ${date}
**/
public interface ${className}Service {

    /**
    * 分页查询数据
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Page
    */
    PageResult<${className}Dto> queryAll(${className}QueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<${className}Dto>
    */
    List<${className}Dto> queryAll(${className}QueryCriteria criteria);

    /**
     * 根据ID查询
     * @param ${pkChangeColName} ID
     * @return ${className}Dto
     */
    ${className}Dto findById(${pkColumnType} ${pkChangeColName});

    /**
    * 创建
    * @param forCreate /
    * @return ${className}Dto
    */
    ${className}Dto create(${className}ForCreate forCreate);

    /**
    * 编辑
    * @param forUpdate /
    */
    void update(${className}ForUpdate forUpdate);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(${pkColumnType}[] ids);

}
