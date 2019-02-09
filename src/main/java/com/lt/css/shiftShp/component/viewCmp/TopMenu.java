package com.lt.css.shiftShp.component.viewCmp;

import com.lt.css.shiftShp.component.AbstractTopMenu;
import com.vaadin.server.ThemeResource;
import org.springframework.stereotype.Component;

/**
 * @program: TopMenu
 * @description: 继承自Design设计的页面，添加其他东西
 * @author: ben.zhang.b.q
 * @create: 2019-01-25 16:58
 **/
@Component
public class TopMenu extends AbstractTopMenu{

    public TopMenu(){
        ThemeResource themeResource = new ThemeResource("img/logo.png");
        getCssLogo().setSource(themeResource);
    }
}
