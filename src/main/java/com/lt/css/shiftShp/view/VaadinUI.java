package com.lt.css.shiftShp.view;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: VaadinUI
 * @description: 前台页面
 * @author: ben.zhang.b.q
 * @create: 2019-01-25 14:53
 **/
@Theme("mytheme")
@SpringUI(path = "")
@SpringViewDisplay
public class VaadinUI extends UI implements ViewDisplay {
    private static final Logger logger = LoggerFactory.getLogger(VaadinUI.class);
    private VerticalLayout contentViewDispaly;
//    public Navigator urlNavigator;

/*
    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        Button btn = new Button("点击");
        Label label = new Label(myService.sayHi());
        layout.addComponents(btn, label);

        urlNavigator = new Navigator(this, this);
//        getUI().getNavigator().navigateTo(ShiftShpView.VIEW_NAME);
        getUI().getNavigator().navigateTo(ShiftCalendarView.VIEW_NAME);
//        urlNavigator.addView(ShiftCalendarView.VIEW_NAME, new ShiftCalendarView());
//        getNavigator().navigateTo(ShiftShpView.VIEW_NAME);
//        setContent(shiftShpView);
    }
*/


    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(false);
        root.setSpacing(false);
        setContent(root);

//        final CssLayout navigationBar = new CssLayout();
//        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
//        navigationBar.addComponent(createNavigationButton("shift-calendar",
//                ShiftCalendarView.VIEW_NAME));

        contentViewDispaly = new VerticalLayout();
        contentViewDispaly.setSizeFull();
        contentViewDispaly.setMargin(false);
        contentViewDispaly.setSpacing(false);
        root.addComponent(contentViewDispaly);
//        root.setExpandRatio(contentViewDispaly, 1.0f);

    }

    private Button createNavigationButton(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        // If you didn't choose Java 8 when creating the project, convert this
        // to an anonymous listener class
        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }

    @Override
    public void showView(View view) {
//        springViewDisplay.setContent((Component) view);
        contentViewDispaly.addComponent((Component) view);
    }
}
