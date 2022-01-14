package me.star.deploy.service.mapstruct;

import me.star.base.BaseMapper;
import me.star.deploy.domain.dto.AppDto;
import me.star.deploy.domain.entity.App;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/5
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppMapper extends BaseMapper<AppDto, App> {

}

