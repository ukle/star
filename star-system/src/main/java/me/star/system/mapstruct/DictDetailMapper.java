package me.star.system.mapstruct;

import me.star.base.BaseMapper;
import me.star.system.domain.DictDetail;
import me.star.system.dto.DictDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/14
 */
@Mapper(componentModel = "spring", uses = {DictSmallMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictDetailMapper extends BaseMapper<DictDetailDto, DictDetail> {

}
