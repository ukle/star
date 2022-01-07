package me.star.deploy.service.mapstruct;

import me.star.base.BaseMapper;
import me.star.deploy.domain.entity.DeployHistory;
import me.star.deploy.service.dto.DeployHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/7
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeployHistoryMapper extends BaseMapper<DeployHistoryDto, DeployHistory> {

}
