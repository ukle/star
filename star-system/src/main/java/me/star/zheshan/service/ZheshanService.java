package me.star.zheshan.service;

import me.star.zheshan.domain.Zheshan;
import me.star.zheshan.service.dto.ZheshanDto;
import me.star.zheshan.service.dto.ZheshanQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;

/**
* @description 服务接口
* @author Star Chou
* @date 2022-01-15
**/
public interface ZheshanService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZheshanQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZheshanDto>
    */
    List<ZheshanDto> queryAll(ZheshanQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param zsId ID
     * @return ZheshanDto
     */
    ZheshanDto findById(Long zsId);

    /**
    * 创建
    * @param resources /
    * @return ZheshanDto
    */
    ZheshanDto create(Zheshan resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(Zheshan resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);

    /**
     * 根据销售状态统计折扇数量
     * @param saleStatus
     * @return
     */
    int countBySaleStatusIn(List<String> saleStatus);

    /**
     * 总成本价
     * @return
     */
    int sumBySaleIn();
    /**
     * 总销售价
     * @return
     */
    int sumBySaleOut();
}
