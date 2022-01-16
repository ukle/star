package me.star.system.mapstruct;

import me.star.base.BaseMapper;
import me.star.system.domain.Dict;
import me.star.system.dto.DictSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/14
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictSmallMapper extends BaseMapper<DictSmallDto, Dict> {

}
