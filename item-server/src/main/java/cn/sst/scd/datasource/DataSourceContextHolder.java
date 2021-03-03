package cn.sst.scd.datasource;

/**
 * @author shengtengsun
 * @Description 动态数据源上下文管理：设置数据源，获取数据源，清除数据源
 * @Date 2020/8/19 4:04 下午
 * @Version 1.1.0
 **/
public class DataSourceContextHolder {
    /**
     * 存储当前线程使用的数据源类型
     **/
    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    /**
     * 获取当前数据源
     *
     * @return cn.sst.datasource.DataSourceType
     * @author shengtengsun
     * @Date 2020/8/19 4:09 下午
     * @Param []
     **/
    public static DataSourceType getDataSource() {
        return contextHolder.get();
    }

    /**
     * 设置当前数据源
     *
     * @return void
     * @author shengtengsun
     * @Date 2020/8/19 4:09 下午
     * @Param [dataSource]
     **/
    public static void setDataSource(DataSourceType dataSource) {
        contextHolder.set(dataSource);
    }

    /**
     * 重置数据源
     *
     * @return void
     * @author shengtengsun
     * @Date 2020/8/19 4:10 下午
     * @Param []
     **/
    public static void resetDataSource() {
        contextHolder.remove();
    }
}
