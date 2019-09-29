package com.ziroom.demo.configuration.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author renhy
 * @version 1.0
 * @date Created in 2019年09月27日 17:38
 * @since 1.0
 */
public class DataSourceContextHolder extends AbstractRoutingDataSource {

    private static final ThreadLocal<DatabaseType> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 获取当前要使用的数据源
     * @author renhy
     * @date 2019-09-29 16:41:13
     * @param
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return CONTEXT_HOLDER.get();
    }

    public enum DatabaseType {
        /**
         * 主，从
         */
        Master, Slave
    }

    public static void master() {
        CONTEXT_HOLDER.set(DatabaseType.Master);
    }


    public static void slave() {
        CONTEXT_HOLDER.set(DatabaseType.Slave);
    }

    public static void setDatabaseType(DatabaseType type) {
        CONTEXT_HOLDER.set(type);
    }

    public static DatabaseType getType() {
        return CONTEXT_HOLDER.get();
    }

    public static void cleanAll(){
        CONTEXT_HOLDER.remove();
    }
}
