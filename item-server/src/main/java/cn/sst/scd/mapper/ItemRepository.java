package cn.sst.scd.mapper;

import cn.sst.scd.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author shengtengsun
 * @Description
 * @Date 2021/2/3 下午3:58
 * @Version 1.1.0
 **/
@Repository
public interface ItemRepository extends JpaRepository<Item,String> {
}
