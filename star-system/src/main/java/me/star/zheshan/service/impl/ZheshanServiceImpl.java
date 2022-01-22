package me.star.zheshan.service.impl;

import lombok.RequiredArgsConstructor;
import me.star.utils.PageUtil;
import me.star.utils.QueryHelp;
import me.star.utils.ValidationUtil;
import me.star.zheshan.domain.Zheshan;
import me.star.zheshan.repository.ZheshanRepository;
import me.star.zheshan.service.ZheshanService;
import me.star.zheshan.service.dto.ZheshanDto;
import me.star.zheshan.service.dto.ZheshanItemDto;
import me.star.zheshan.service.dto.ZheshanQueryCriteria;
import me.star.zheshan.service.mapstruct.ZheshanMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* @description 服务实现
* @author Star Chou
* @date 2022-01-15
**/
@Service
@RequiredArgsConstructor
public class ZheshanServiceImpl implements ZheshanService {

    private final ZheshanRepository zheshanRepository;
    private final ZheshanMapper zheshanMapper;

    @Override
    public Map<String,Object> queryAll(ZheshanQueryCriteria criteria, Pageable pageable){
        Page<Zheshan> page = zheshanRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(ZheshanItemDto::toDto));
    }

    @Override
    public List<ZheshanDto> queryAll(ZheshanQueryCriteria criteria){
        return zheshanMapper.toDto(zheshanRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ZheshanDto findById(Long zsId) {
        Zheshan zheshan = zheshanRepository.findById(zsId).orElseGet(Zheshan::new);
        ValidationUtil.isNull(zheshan.getZsId(),"Zheshan","zsId",zsId);
        return zheshanMapper.toDto(zheshan);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ZheshanDto create(Zheshan resources) {
//        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
//        resources.setZsId(snowflake.nextId());
        return zheshanMapper.toDto(zheshanRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Zheshan resources) {
        Zheshan zheshan = zheshanRepository.findById(resources.getZsId()).orElseGet(Zheshan::new);
        ValidationUtil.isNull( zheshan.getZsId(),"Zheshan","id",resources.getZsId());
        zheshan.copy(resources);
        zheshanRepository.save(zheshan);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long zsId : ids) {
            zheshanRepository.deleteById(zsId);
        }
    }

}
