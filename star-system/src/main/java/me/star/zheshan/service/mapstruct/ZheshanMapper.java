package me.star.zheshan.service.mapstruct;

import me.star.base.BaseMapper;
import me.star.zheshan.domain.Zheshan;
import me.star.zheshan.service.dto.ZheshanDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Star Chou
* @date 2022-01-15
**/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ZheshanMapper extends BaseMapper<ZheshanDto, Zheshan> {

}
