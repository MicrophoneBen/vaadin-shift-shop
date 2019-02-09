package com.lt.css.shiftShp.service;

import com.lt.css.shiftShp.entity.EmployeeDto;
import com.lt.css.shiftShp.entity.ShiftEmpInfo;
import com.lt.css.shiftShp.view.ShiftCalendarView;
import com.vaadin.shared.ui.dnd.DropEffect;
import com.vaadin.shared.ui.dnd.EffectAllowed;
import com.vaadin.shared.ui.grid.DropMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.UI;
import com.vaadin.ui.components.grid.GridDragSource;
import com.vaadin.ui.components.grid.GridDropTarget;
import com.vaadin.ui.components.grid.GridRowDragger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @program: DgAndDpFromGrid
 * @description: 在部门，分店之间进行DragAndDrop然后安排调班时间
 * @author: ben.zhang.b.q
 * @create: 2019-01-26 00:34
 **/
@Service
//@VaadinSessionScope
public class DgAndDpFromGrid {
    private final static Logger logger = LoggerFactory.getLogger(DgAndDpFromGrid.class);
    private String dragEmpId;
    private Collection<EmployeeDto> dragEmpList;
   // @Autowired
   // private MyPopupContent popupContent = new MyPopupContent();
   // Window window = new Window("调班时间表", popupContent);
   private List<EmployeeDto> list;
    /**
     * @author ben.zhang.b.q
     * @date 2019/1/31 0:31
     * 移动的数据对象
     **/
    private Optional<EmployeeDto> dragged;

//    @Autowired
//    private ShiftCalendarView shiftCalendarView;

    public void dragAndDrop(Grid<EmployeeDto> fromGrid, Grid<EmployeeDto> toGrid) {
        // enable row dnd from left to right and handle drops
        //GridRowDragger<EmployeeDto> leftToRight = new GridRowDragger<>(fromGrid, toGrid);
        // enable row dnd from right to left and handle drops
        /// GridRowDragger<EmployeeDto> rightToLeft = new GridRowDragger<>(toGrid, fromGrid);

        //DnD数据源
        GridDragSource<EmployeeDto> dragSource = new GridDragSource<>(fromGrid);
        dragSource.setEffectAllowed(EffectAllowed.MOVE);

//        dragSource.setDragDataGenerator("text", empData -> {
//            return empData.getId() + " " + empData.getName() + " " + empData + " "
//                    + empData.getDay1() + " " + empData.getDay2() + " " + empData.getDay3() + " "
//                    + empData.getDay4() + " " + empData.getDay5() + " " + empData.getDay6() + " "
//                    + empData.getDay7() + " " + empData.getDay8() + " " + empData.getDay9() + " "
//                    + empData.getDay10() + " " + empData.getDay11() + " " + empData.getDay12() + " "
//                    + empData.getDay13() + " " + empData.getDay14() + " " + empData.getDay15() + " "
//                    + empData.getDay16() + " " + empData.getDay17() + " " + empData.getDay18() + " "
//                    + empData.getDay19() + " " + empData.getDay20() + " " + empData.getDay21() + " "
//                    + empData.getDay22() + " " + empData.getDay23() + " " + empData.getDay24() + " "
//                    + empData.getDay25() + " " + empData.getDay26() + " " + empData.getDay27() + " "
//                    + empData.getDay28() + " " + empData.getDay29() + " " + empData.getDay30() + " " + empData.getDay31() + " ";
//        });

        dragSource.addGridDragStartListener(event -> {
            //获取被鼠标拉起的Grid对象
            list = new ArrayList<>(event.getDraggedItems());

   /*         for (EmployeeDto emp : list){
                String name = emp.getName();
                Integer uid = emp.getUid();
                logger.info(name + uid);
            }*/

//            if (!list.isEmpty()) {
//                dragged = Optional.of(list.get(list.size()));
//            } else {
//                dragged = Optional.empty();
//            }

        });

        dragSource.addGridDragEndListener(event -> {
            // If drop was successful, remove dragged items from source Grid
            if (event.getDropEffect() == DropEffect.MOVE) {

                //把数据从原来的List集合中删除
//                ((ListDataProvider<EmployeeDto>) fromGrid.getDataProvider()).getItems().remove(dragged);
                fromGrid.getDataProvider().refreshAll();
                //显示返回被调动的员工
                logger.info("临时调班操作成功");

                //添加日历图
//                UI.getCurrent().setContent(shiftCalendarView);
//                UI.getCurrent().getNavigator().navigateTo(CalendarPopupContent.VIEW_NAME);
                if (list.size() == 1) {
                    UI.getCurrent().getNavigator().navigateTo(ShiftCalendarView.VIEW_NAME);
                }else {
                    Notification.show(showShiftEmp() + "\n临时调店成功" +
                            "\n多人临时调店不允许设置时间安排");
                }
               // window.setWidth(90, Sizeable.Unit.PERCENTAGE);
               // window.setHeight(90, Sizeable.Unit.PERCENTAGE);
               // window.setModal(true);
               // window.setDraggable(false);
               // window.setResizable(false);
               // window.center();
               // UI.getCurrent().addWindow(shiftCalendarView.initCalendar());
                //添加弹窗ends

                // Remove reference to dragged items
                dragged = null;
            }
        });

        //创建一个接受数据对象的目标
        GridDropTarget<EmployeeDto> dropTarget = new GridDropTarget<>(toGrid, DropMode.ON_TOP);
        dropTarget.setDropEffect(DropEffect.MOVE);

        dropTarget.addGridDropListener(event -> {

            /*if (dragged.isPresent() && !listgridsecond.contains(list.get(list.size()))) {
                listgridsecond.add(dragged.get());
                provider2.refreshAll();
            }
            */
        });

    }

    private RadioButtonGroup<DropMode> createDropModeSelect(
            GridRowDragger<EmployeeDto> gridRowDragger) {
        List<DropMode> dropLocations = Arrays.asList(DropMode.values());
        RadioButtonGroup<DropMode> dropLocationSelect = new RadioButtonGroup<>(
                "数据添加的位置", dropLocations);
        dropLocationSelect.setSelectedItem(DropMode.BETWEEN);
        dropLocationSelect.addValueChangeListener(event -> gridRowDragger
                .getGridDropTarget().setDropMode(event.getValue()));
        return dropLocationSelect;
    }

    //获取被拉起的移动员工List集合
    public List<EmployeeDto> getDragEmpList() {
        List<EmployeeDto> dragList = new ArrayList<>();
        return dragList;
    }

    //获取用户设置的调班时间
    public void getDnDAllTime() {
    }

    //恢复员工到原来的分店，返回一个List，然后把这个List加入到原来的分店员工List
    private List<EmployeeDto> getBackShp() {
        List<EmployeeDto> backEmp = new ArrayList<>();
        return backEmp;
    }

    //返回移动人员的信息
    public String showShiftEmp(){
            StringBuilder showString = new StringBuilder();
            for(ShiftEmpInfo empData : listShiftEmp()){
                showString.append(empData.toString());
            }
        return showString.toString();
    }

    //返回发生拖动的所有人员 List集合
    public List<ShiftEmpInfo> listShiftEmp(){
        List<ShiftEmpInfo> shiftList = new ArrayList<>();
        for(EmployeeDto dragEmp : list){
            ShiftEmpInfo shiftEmpInfo = new ShiftEmpInfo();
            shiftEmpInfo.setId(String.valueOf(dragEmp.getUid()));
            shiftEmpInfo.setName(dragEmp.getName());
            shiftList.add(shiftEmpInfo);
        }
        return shiftList;
    }

    public String getDragEmpId() {
        return dragEmpId;
    }

    public void setDragEmpId(String dragEmpId) {
        this.dragEmpId = dragEmpId;
    }

    public void setDragEmpList(Collection<EmployeeDto> dragEmpList) {
        this.dragEmpList = dragEmpList;
    }
}
