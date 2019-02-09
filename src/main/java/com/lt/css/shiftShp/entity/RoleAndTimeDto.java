package com.lt.css.shiftShp.entity;

/**
 * @program: RoleAndTimeDto
 * @description: 传输出去修改在新分店的Role的类
 * @author: ben.zhang.b.q
 * @create: 2019-01-31 17:18
 **/
public class RoleAndTimeDto {
    /**
     * @author ben.zhang.b.q
     * @date 2019/1/31 17:20
     *  员工在新店的Role
     **/
    private String role;

    private String startDate;
    private String endDate;

    private String id;
    private String editorId;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEditorId() {
        return editorId;
    }

    public void setEditorId(String editorId) {
        this.editorId = editorId;
    }

    @Override
    public String toString() {
        return "RoleAndTimeDto{" +
                "role='" + role + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", id='" + id + '\'' +
                ", editorId='" + editorId + '\'' +
                '}';
    }
}
