package cn.sst.mapper;

import cn.sst.entity.SystemOperateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author shengtengsun
 * @Description 操作日志记录
 * @Date 2020/9/25 4:13 下午
 * @Version 1.1.0
 **/
@Repository
public interface SystemOperateLogRepository extends JpaRepository<SystemOperateLog, Long> {

}
