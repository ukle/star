package ${package}.service.mapstruct;

import ${package}.repository.entity.${className};
import ${package}.domain.dto.${className}Dto;
import cn.hf.fuda.common.web.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author ${author}
* @date ${date}
**/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ${className}Mapper extends BaseMapper<${className}Dto, ${className}> {

}
