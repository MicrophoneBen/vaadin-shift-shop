package com.lt.css.shiftShp.view;

import com.css.idm.ldapService.GroupOrg;
import com.css.idm.ldapService.ManagedUser;
import com.lt.css.shiftShp.component.viewCmp.EmpTabel;
import com.lt.css.shiftShp.component.viewCmp.TopMenu;
import com.lt.css.shiftShp.entity.DepartmentTreeDto;
import com.lt.css.shiftShp.entity.EmployeeDto;
import com.lt.css.shiftShp.service.DgAndDpFromGrid;
import com.vaadin.data.TreeData;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.sliderpanel.SliderPanel;
import org.vaadin.sliderpanel.SliderPanelBuilder;
import org.vaadin.sliderpanel.SliderPanelStyles;
import org.vaadin.sliderpanel.client.SliderMode;
import org.vaadin.sliderpanel.client.SliderTabPosition;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @program: ShiftShpView
 * @description: 调动班次，在两个店面之间操作
 * @author: ben.zhang.b.q
 * @create: 2019-01-25 17:23
 **/
@UIScope
@SpringView(name = ShiftShpView.VIEW_NAME)
public class ShiftShpView extends VerticalLayout implements View {
    private static final Logger logger = LoggerFactory.getLogger(ShiftShpView.class);
    public static final String VIEW_NAME = "shiftshop";
    private String outChoosed = "";
    List<EmployeeDto> toList = new ArrayList<>();
    List<EmployeeDto> fromList = new ArrayList<>();
    private String inChoosed = "";

    @Autowired
    TopMenu topMenu;

    @Autowired
    private EmpTabel empTabel;

    @Autowired
    private DgAndDpFromGrid dgAndDpFromGrid;

    @PostConstruct
    public void init() {
        setId("调班部门界面");
        setSizeFull();
        setMargin(false);
        setSpacing(false);
        addComponent(buildView());
    }

    public Component buildView() {
        ManagedUser ldap = getManagedUser();
        final VerticalLayout mainLayout = new VerticalLayout();

        topMenu.setHeight(50, Sizeable.Unit.PIXELS);
        mainLayout.addComponent(topMenu);

        mainLayout.setExpandRatio(topMenu, 1);
        //设置一个布局的三个基本元素
        mainLayout.setSizeFull();
        mainLayout.setMargin(false);
        mainLayout.setSpacing(false);

        //页面的水平布局，用来放置在界面中左右两边的滑动窗口
        HorizontalLayout contentLayout = new HorizontalLayout();
        //设置基本属性
        contentLayout.setSpacing(false);
        contentLayout.setSizeFull();
//        TreeGrid<DepartmentTreeDto> treeGrid = departmentTree.getTreeGrid();

        // 组织树
        List<String> cns = new ArrayList<String>();
        cns.add("015A");
        cns.add("015B");
        Collection<DepartmentTreeDto> treeData = getTreeData(ldap,cns);
        Tree<DepartmentTreeDto> tree = buildTree(ldap, treeData);

        VerticalLayout topLeftSliderContent = new VerticalLayout(tree);
        topLeftSliderContent.setMargin(true);
        topLeftSliderContent.setSpacing(true);
        topLeftSliderContent.setExpandRatio(tree, 1);
        topLeftSliderContent.setWidth(450, Sizeable.Unit.PIXELS);
        topLeftSliderContent.setHeight(-1,Sizeable.Unit.PIXELS );

        //开始制作一个左边栏滑动窗口
        SliderPanel leftSlider =
                new SliderPanelBuilder(topLeftSliderContent, "部門人員樹")
                        .mode(SliderMode.LEFT)
                        .autoCollapseSlider(true)
                        .tabPosition(SliderTabPosition.BEGINNING)
                        .style(SliderPanelStyles.COLOR_GRAY)
                        .flowInContent(true)
                        .build();


        //在内容显示页面添加左侧栏滑动组件
        contentLayout.addComponent(leftSlider);
//        contentLayout.addComponent(topLeftSliderContent);

        //把内容组件添加到我们的页面内容显示组件上面
        contentLayout.addComponent(empTabel);

        //设置显示的位置
        contentLayout.setComponentAlignment(empTabel, Alignment.MIDDLE_CENTER);
        //设置显示的扩展
        contentLayout.setExpandRatio(empTabel, 1.0f);

        // 组织树点击事件
        tree.addItemClickListener(event -> {
            DepartmentTreeDto item = event.getItem();

            if(outChoosed.equals(item.getId())) {
                // 取消调出单位
                empTabel.getFromShiftShp().setCaption("");
                outChoosed = "";
                empTabel.getFromShiftShp().setItems(new ArrayList<EmployeeDto>(1));
                //employeeSet.setItems(null);
            }else if(inChoosed.equals(item.getId())) {
                // 取消调入单位
                 empTabel.getToShiftShp().setCaption("");
                 inChoosed = "";
                 empTabel.getToShiftShp().setItems(new ArrayList<EmployeeDto>(1));
            }else if(StringUtils.isEmpty(outChoosed)) {
                // 选择调出单位
                String dn = GroupOrg.toDn(ldap, item.getId());
                String businessTime = getBusinessTime(ldap, dn);
                empTabel.getFromShiftShp().setCaption(item.getName() + "  营业时间 ： "+ businessTime);
                System.out.println(dn);
                logger.info("调出员工分店Dn\n" + dn + "\n" + item.getName());
//                logger.error(dn);
                Set<String> users = GroupOrg.getMembers(ldap, dn);
                fromList.clear();
                loadEmpData(ldap, users, fromList);
                empTabel.setFromlist(fromList);
//                empTabel.fromProvider = new ListDataProvider<>(fromList);
//                empTabel.getFromShiftShp().setDataProvider(empTabel.fromProvider);
//                empTabel.fromProvider.refreshAll();
                empTabel.setEmployee(true, empTabel.getFromShiftShp(), fromList);
                outChoosed = item.getId();
            } else if(StringUtils.isEmpty(inChoosed)) {
                // 选择调入单位
                String dn = GroupOrg.toDn(ldap, item.getId());
                String businessTime = getBusinessTime(ldap, dn);
                empTabel.getToShiftShp().setCaption(item.getName() + "  营业时间 ： " +businessTime);
                System.out.println(dn);
                logger.info("调入员工分店Dn\n" + dn  + "\n" + item.getName());
//                logger.error(dn);
                Set<String> users = GroupOrg.getMembers(ldap, dn);
                toList.clear();
                loadEmpData(ldap, users, toList);
                empTabel.setTolist(toList);
//                empTabel.toProvider = new ListDataProvider<>(toList);
//                empTabel.getToShiftShp().setDataProvider(empTabel.toProvider);
//                empTabel.toProvider.refreshAll();
                empTabel.setEmployee(true, empTabel.getToShiftShp(), toList);
                inChoosed = item.getId();
            }
        });

        //允许人员行拖动
//        new GridRowDragger<>(empTabel.getFromShiftShp());
//        new GridRowDragger<>(empTabel.getToShiftShp());
        empTabel.getFromShiftShp().getColumns().stream().forEach(col -> col.setSortable(false));
        empTabel.getToShiftShp().getColumns().stream().forEach(col -> col.setSortable(false));
        //Grid 内部拖动允许设置 ends

        dgAndDpFromGrid.dragAndDrop(empTabel.getFromShiftShp(), empTabel.getToShiftShp());
        //组织树点击事件取出人员 end

        // fit full screen
        //在主页面中添加显示的布局管理器
        mainLayout.addComponent(contentLayout);
        //设置布局管理器的扩展显示
        mainLayout.setExpandRatio(contentLayout, 18.0f);
        return mainLayout;
    }

    //获取当前店铺营业时间
    private String getBusinessTime(ManagedUser ldap, String dn){
        String group = "";
        String [] cnList = dn.split("=");
        group = cnList[1].substring(0, 3);
        logger.warn(group);
        StringBuilder shpTime = new StringBuilder();
        GroupOrg org = new GroupOrg(ldap, group);
        for (String oh : org.getBusinessCategory("OH")) {
            oh = oh.replaceAll(";", "——");
            shpTime.append(oh);
        }
//        System.out.println(shpTime.toString());
        return shpTime.toString();
    }

    //加载当前部门的员工
    private void loadEmpData(ManagedUser ldap, Set<String> users, List<EmployeeDto> toList) {
        for (String userDn : users) {
            if (StringUtils.isNotEmpty(userDn) && userDn.contains("uid")) {
                ManagedUser userInfo = new ManagedUser(ldap, userDn);
                EmployeeDto employee = new EmployeeDto();
                employee.setUid(Integer.parseInt(userInfo.getUid()));
                employee.setName(userInfo.getGivenName());
                //设置工作时长，设置背景颜色
                employee.setDay2WorkLength("6");
                employee.setDay2Backgroud("2");
                employee.setDay3Backgroud("4");
                //设置ends
                employee.setTitle(userInfo.getTitle());
                toList.add(employee);
            }
        }
    }

    public ManagedUser getManagedUser() {
        return new ManagedUser("27188","123456");
    }
    public Collection<DepartmentTreeDto> getTreeData(ManagedUser ldap,Collection<String> cns){
        List<DepartmentTreeDto> collection = new ArrayList<DepartmentTreeDto>();
        for(String cn : cns) {
            GroupOrg root = new GroupOrg(ldap,cn);
            DepartmentTreeDto dpt = new DepartmentTreeDto();
            dpt.setId(cn);
            dpt.setName(root.getDescription());
            collection.add(dpt);
        }
        return collection;
    }

    /**
     *  	构造组织单位树Component
     * @param roots
     * @return
     */
    public Tree<DepartmentTreeDto> buildTree(ManagedUser ldap,Collection<DepartmentTreeDto> roots){
        Tree<DepartmentTreeDto> tree = new Tree<DepartmentTreeDto>();
        TreeData<DepartmentTreeDto> treeData = new TreeData<DepartmentTreeDto>();
        // 设置节点数据和子类提供器
        treeData.addItems(roots, (ValueProvider<DepartmentTreeDto, Collection<DepartmentTreeDto>>) source -> {
            List<DepartmentTreeDto> collection = new ArrayList<DepartmentTreeDto>();
            String cn = source.getId();
            GroupOrg root = new GroupOrg(ldap, cn);
            Map<String,String> map = root.getChildrenOrg(GroupOrg.FN);
            for(String dn : map.keySet()) {
                DepartmentTreeDto dept = new DepartmentTreeDto();
                //String[] value = dn.split(",");
                //dept.setId(value[0].substring(3));
                dept.setId(dn);
                dept.setName(map.get(dn));
                collection.add(dept);
            }
            return collection;
        });
        TreeDataProvider<DepartmentTreeDto> inMemoryDataProvider = new TreeDataProvider<DepartmentTreeDto>(treeData);
        tree.setDataProvider(inMemoryDataProvider);
        // 设置节点名称提供器
        tree.setItemCaptionGenerator((ItemCaptionGenerator<DepartmentTreeDto>) item -> item.getName());
        return tree;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
