package me.star.system.mapstruct;

import me.star.base.BaseMapper;
import me.star.system.domain.LocalStorage;
import me.star.system.dto.LocalStorageDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/15
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocalStorageMapper extends BaseMapper<LocalStorageDto, LocalStorage> {

}
