package me.star.deploy.service.mapstruct;

import me.star.base.BaseMapper;
import me.star.deploy.domain.dto.DeployDto;
import me.star.deploy.domain.entity.Deploy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/5
 */
@Mapper(componentModel = "spring", uses = {AppMapper.class, ServerDeployMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeployMapper extends BaseMapper<DeployDto, Deploy> {

}
