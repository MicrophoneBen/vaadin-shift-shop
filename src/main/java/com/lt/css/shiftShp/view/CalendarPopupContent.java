package com.lt.css.shiftShp.view;

import com.vaadin.navigator.View;
import com.vaadin.shared.Registration;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;

/**
 * @program: MyPopupContent
 * @description: 增加一个页面弹出控制排班时间添加
 * @author: ben.zhang.b.q
 * @create: 2019-01-31 02:49
 **/
@UIScope
@SpringView(name = CalendarPopupContent.VIEW_NAME)
public class CalendarPopupContent extends MVerticalLayout implements View {
    public static final String VIEW_NAME="popupcalendar";
    MButton button = new MButton("打开新窗口");
    Registration openerClickRegistration;
    Button.ClickListener clickListener;
    @Autowired
    private ShiftCalendarView shiftCalendarView;

   @PostConstruct
    public void init

           () {
//        add(new MLabel("Open from popup")
//                        .withStyleName(ValoTheme.LABEL_COLORED, ValoTheme.LABEL_H1),
//                button
//        );
        add(shiftCalendarView, Alignment.MIDDLE_CENTER);

    }
}
