package com.lt.css.shiftShp.entity;

import java.time.ZonedDateTime;

import static com.lt.css.shiftShp.entity.ShiftTimeDto.State.empty;

/**
 * @program: ShiftTimeDto
 * @description: 移动员工到分店的时间
 * @author: ben.zhang.b.q
 * @create: 2019-01-29 20:07
 **/
public class ShiftTimeDto {

    public enum State {
        empty,              //空事件
        planned,            //计划事件
        confirmed,           //确认事件
        origin,               //源事件,相当于X事件
        dayoff                 //请假事件
    }

    private ZonedDateTime start;

    private ZonedDateTime end;

    private String name;

    private String details;

    private State state = empty;

    private boolean longTime;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public ShiftTimeDto(boolean longTime) {
        this.longTime = longTime;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isEditable() {
        return state != State.confirmed;
    }

    public boolean isLongTimeEvent() {
        return longTime;
    }

}
