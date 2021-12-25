package me.star.service.impl;

import cn.hutool.core.util.ObjectUtil;
import me.star.domain.vo.TableInfo;
import me.star.service.GeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.star.utils.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sida_zhou
 * @description
 * @date 2021/12/24
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GeneratorServiceImpl implements GeneratorService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Object getTables(String name, int[] startEnd) {
        // 使用预编译防止sql注入
        String sql = "select table_name ,create_time , engine, table_collation, table_comment " +
                "from information_schema.tables " +
                "where table_schema = (select database()) " +
                "and table_name like :table order by create_time desc";
        Query query = em.createNativeQuery(sql);
        query.setFirstResult(startEnd[0]);
        query.setMaxResults(startEnd[1] - startEnd[0]);
        query.setParameter("table", StringUtils.isNotBlank(name) ? ("%" + name + "%") : "%%");
        List result = query.getResultList();
        List<TableInfo> tableInfos = new ArrayList<>();
        for (Object obj : result) {
            Object[] arr = (Object[]) obj;
            tableInfos.add(new TableInfo(arr[0], arr[1], arr[2], arr[3], ObjectUtil.isNotEmpty(arr[4]) ? arr[4] : "-"));
        }
        String countSql = "select count(1) from information_schema.tables " +
                "where table_schema = (select database()) and table_name like :table";
        Query queryCount = em.createNativeQuery(countSql);
        queryCount.setParameter("table", StringUtils.isNotBlank(name) ? ("%" + name + "%") : "%%");
        Object totalElements = queryCount.getSingleResult();
        return PageUtil.toPage(tableInfos, totalElements);
    }
}
