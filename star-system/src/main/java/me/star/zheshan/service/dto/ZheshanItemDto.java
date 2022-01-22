package me.star.zheshan.service.dto;

import lombok.Data;
import me.star.utils.StringUtils;
import me.star.zheshan.domain.Zheshan;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/15
 */
@Data
public class ZheshanItemDto extends ZheshanDto implements Serializable {

    /**
     * 规格：包括尺寸、方数、头型、排口、肩部、烫钉
     */
    private String zsGuige;

    public static ZheshanItemDto toDto(Zheshan entity) {
        if (entity == null) {
            return null;
        }

        ZheshanItemDto itemDto = new ZheshanItemDto();
        BeanUtils.copyProperties(entity, itemDto);
        List<String> guigeList = new ArrayList<>(8);
        String zsLength = entity.getZsLength();
        if (StringUtils.isNotBlank(zsLength)) {
            guigeList.add(String.format("%s寸", zsLength));
        }
        Integer fangshu = entity.getZsFangshu();
        if (fangshu != null && fangshu != 0) {
            guigeList.add(String.format("%s方", fangshu));
        }
        String zsTouxing = entity.getZsTouxing();
        if (StringUtils.isNotBlank(zsTouxing)) {
            guigeList.add(String.format("%s", zsTouxing));
        }
        String zsPaikou = entity.getZsPaikou();
        if (StringUtils.isNotBlank(zsPaikou)) {
            guigeList.add(String.format("%s排口", zsPaikou));
        }
        String zsJianbu = entity.getZsJianbu();
        if (StringUtils.isNotBlank(zsJianbu)) {
            guigeList.add(String.format("%s", zsJianbu));
        }
        String zsTangding = entity.getZsTangding();
        if (StringUtils.isNotBlank(zsTangding)) {
            guigeList.add(String.format("%s烫钉", zsTangding));
        }
        if (!CollectionUtils.isEmpty(guigeList)) {
            itemDto.setZsGuige(String.join("、", guigeList));
        }
        return itemDto;
    }
}
