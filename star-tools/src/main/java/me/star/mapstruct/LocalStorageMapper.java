package me.star.mapstruct;

import me.star.base.BaseMapper;
import me.star.domain.LocalStorageDto;
import me.star.entity.LocalStorage;
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
