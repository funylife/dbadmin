package org.funnylife.vo;

/**
 * Created by cheng on 2017/7/2.
 */
public class TableColumnsVO {
    private String name;
    private String typeName;
    private int length;

    public TableColumnsVO() {}
    public TableColumnsVO(String name, String typeName, int length) {
        this.name = name;
        this.typeName = typeName;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
