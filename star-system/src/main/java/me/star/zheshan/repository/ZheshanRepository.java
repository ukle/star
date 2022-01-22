package me.star.zheshan.repository;

import me.star.zheshan.domain.Zheshan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
* @author Star Chou
* @date 2022-01-15
**/
public interface ZheshanRepository extends JpaRepository<Zheshan, Long>, JpaSpecificationExecutor<Zheshan> {

    int countBySaleStatusIn(List<String> saleStatus);

    @Query(value = "SELECT sum(zs_sale_in) FROM zheshan", nativeQuery = true)
    int countByZsSaleIn();

    @Query(value = "SELECT sum(zs_sale_out) FROM zheshan", nativeQuery = true)
    int countByZsSaleOut();
}
