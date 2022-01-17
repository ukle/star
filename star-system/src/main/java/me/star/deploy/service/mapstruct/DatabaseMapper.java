package me.star.deploy.service.mapstruct;

import me.star.base.BaseMapper;
import me.star.deploy.domain.entity.Database;
import me.star.deploy.service.dto.DatabaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/17
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DatabaseMapper extends BaseMapper<DatabaseDto, Database> {

}
