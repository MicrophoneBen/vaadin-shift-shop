package com.lt.css.shiftShp.service;

import com.lt.css.shiftShp.entity.item.ShiftItem;
import org.springframework.stereotype.Service;
import org.vaadin.addon.calendar.item.BasicItemProvider;

/**
 * @program: ShiftDataProvider
 * @description: 页面中事件时间的数据操作类，删除与添加
 * @author: ben.zhang.b.q
 * @create: 2019-01-29 20:37
 **/
@Service
public class ShiftDataProvider extends BasicItemProvider<ShiftItem> {

    @Override
    public void removeItem(ShiftItem item) {
        super.removeItem(item);

        this.itemList.clear();
        fireItemSetChanged();
    }

}
