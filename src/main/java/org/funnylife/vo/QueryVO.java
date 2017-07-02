package org.funnylife.vo;

/**
 * Created by cheng on 2017/7/2.
 */
public class QueryVO {
    private String sql;
    private String execTtype;
    private String dbName;
    private String tableName;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getExecTtype() {
        return execTtype;
    }

    public void setExecTtype(String execTtype) {
        this.execTtype = execTtype;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
