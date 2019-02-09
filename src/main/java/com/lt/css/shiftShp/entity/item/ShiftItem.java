package com.lt.css.shiftShp.entity.item;

import com.lt.css.shiftShp.entity.ShiftTimeDto;
import com.vaadin.icons.VaadinIcons;
import org.vaadin.addon.calendar.item.BasicItem;

import java.time.ZonedDateTime;

/**
 * @program: ShiftItem
 * @description: 继承Calendar的基本事件，实现我们添加在Calendar面板中的Item
 * @author: ben.zhang.b.q
 * @create: 2019-01-29 20:12
 **/
public class ShiftItem extends BasicItem {
    private final ShiftTimeDto shiftTimeDto;

    /**
     * constructor
     *
     * @param shiftTimeDto A meeting
     */

    public ShiftItem(ShiftTimeDto shiftTimeDto) {
        //标题， 详细介绍。开始时间，结束时间
        super(shiftTimeDto.getDetails(), "员工调班", shiftTimeDto.getStart(), shiftTimeDto.getEnd());
        this.shiftTimeDto = shiftTimeDto;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShiftItem)) {
            return false;
        }
        ShiftItem that = (ShiftItem) obj;
        return getShiftDto().equals(that.getShiftDto());
    }

    public ShiftTimeDto getShiftDto() {
        return shiftTimeDto;
    }

    //获取是事件配置方案
    @Override
    public String getStyleName() {
        return "state-" + shiftTimeDto.getState().name().toLowerCase();
    }

    //获取存储位置
    @Override
    public int hashCode() {
        return getShiftDto().hashCode();
    }

    //是否长时间时间
    @Override
    public boolean isAllDay() {
        return shiftTimeDto.isLongTimeEvent();
    }

    //能否被移动
    @Override
    public boolean isMoveable() {
        return shiftTimeDto.isEditable();
    }

    //能否被重新设置时间范围
    @Override
    public boolean isResizeable() {
        return shiftTimeDto.isEditable();
    }

    //单击后是否允许修改
    @Override
    public boolean isClickable() {
        return shiftTimeDto.isEditable();
    }

    //设置结束时间
    @Override
    public void setEnd(ZonedDateTime end) {
        shiftTimeDto.setEnd(end);
        super.setEnd(end);
    }

    //设置开始时间
    @Override
    public void setStart(ZonedDateTime start) {
        shiftTimeDto.setStart(start);
        super.setStart(start);
    }

    @Override
    public String getDateCaptionFormat() {
        //return CalendarItem.RANGE_TIME;
        return VaadinIcons.CLOCK.getHtml()+" %s<br>" +
                VaadinIcons.ARROW_CIRCLE_RIGHT_O.getHtml()+" %s";
    }
}
