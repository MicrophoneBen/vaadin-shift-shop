package com.lt.css.shiftShp.component.viewCmp;

import com.lt.css.shiftShp.component.AbstractEmpTable;
import com.lt.css.shiftShp.entity.EmployeeDto;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: EmpTabel
 * @description: 一个部门的所有员工数据载入的表格
 * @author: ben.zhang.b.q
 * @create: 2019-01-25 19:12
 **/
@Component
public class EmpTabel extends AbstractEmpTable{
    private List<EmployeeDto> fromlist = new ArrayList<>(1);
    private List<EmployeeDto> tolist  = new ArrayList<>(1);
    public ListDataProvider<EmployeeDto> fromProvider;
    public ListDataProvider<EmployeeDto> toProvider;

    public EmpTabel(){
        setEmployee(true, getFromShiftShp(), fromlist);
        setEmployee(true, getToShiftShp(), tolist);
    }

    /**
     * @author ben.zhang.b.q
     * @date 2019/1/25 20:33
     * 返回要调出员工部门的员工List集合
     * @return java.util.List<com.lt.css.shiftShp.entity.EmployeeDto>
     **/
    public List<EmployeeDto> getFromlist() {
        return fromlist;
    }
    /**
     * @author ben.zhang.b.q
     * @date 2019/1/25 20:35
     * 设置要调出员工部门的远员工List集合
     * @return void
     **/
    public void setFromlist(List<EmployeeDto> fromlist) {
        this.fromlist = fromlist;
    }
    /**
     * @author ben.zhang.b.q
     * @date 2019/1/25 20:36
     * 返回要调往部门的员工集合
     * @return java.util.List<com.lt.css.shiftShp.entity.EmployeeDto>
     **/
    public List<EmployeeDto> getTolist() {
        return tolist;
    }

    /**
     * @author ben.zhang.b.q
     * @date 2019/1/25 20:37
     * 设置要调往部门的员工List集合
     * @return void
     **/
    public void setTolist(List<EmployeeDto> tolist) {
        this.tolist = tolist;
    }

    /**
     *  初始Grid
     * @param b true代表當月，false代表下一月
     * @param grid
     * @param list
     */
    public void setEmployee(boolean b, Grid<EmployeeDto> grid, List<EmployeeDto> list) {
        grid.removeAllColumns();
        grid.setItems(list);
        grid.addColumn(EmployeeDto::getName).setCaption("名稱");
        grid.setFrozenColumnCount(1);
        grid.addColumn(EmployeeDto::getTitle).setCaption("職稱");
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setFrozenColumnCount(1);
        Map<String, String> map = getDays(b);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            switch (entry.getKey()) {
                case "1":
                    grid.addColumn(EmployeeDto::getDay1WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay1Backgroud();
                    });
                    break;
                case "2":
                    grid.addColumn(EmployeeDto::getDay2WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay2Backgroud();
                    });
                    break;
                case "3":
                    grid.addColumn(EmployeeDto::getDay3WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay3Backgroud();
                    });
                    break;
                case "4":
                    grid.addColumn(EmployeeDto::getDay4WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay4Backgroud();
                    });
                    break;
                case "5":
                    grid.addColumn(EmployeeDto::getDay5WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay5Backgroud();
                    });
                    break;
                case "6":
                    grid.addColumn(EmployeeDto::getDay6WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay6Backgroud();
                    });
                    break;
                case "7":
                    grid.addColumn(EmployeeDto::getDay7WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay7Backgroud();
                    });
                    break;
                case "8":
                    grid.addColumn(EmployeeDto::getDay8WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay8Backgroud();
                    });
                    break;
                case "9":
                    grid.addColumn(EmployeeDto::getDay9WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay9Backgroud();
                    });
                    break;
                case "10":
                    grid.addColumn(EmployeeDto::getDay10WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay10Backgroud();
                    });
                    break;
                case "11":
                    grid.addColumn(EmployeeDto::getDay11WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay11Backgroud();
                    });
                    break;
                case "12":
                    grid.addColumn(EmployeeDto::getDay12WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay12Backgroud();
                    });
                    break;
                case "13":
                    grid.addColumn(EmployeeDto::getDay13WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay13Backgroud();
                    });
                    break;
                case "14":
                    grid.addColumn(EmployeeDto::getDay14WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay14Backgroud();
                    });
                    break;
                case "15":
                    grid.addColumn(EmployeeDto::getDay15WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay15Backgroud();
                    });
                    break;
                case "16":
                    grid.addColumn(EmployeeDto::getDay16WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay16Backgroud();
                    });
                    break;
                case "17":
                    grid.addColumn(EmployeeDto::getDay17WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay17Backgroud();
                    });
                    break;
                case "18":
                    grid.addColumn(EmployeeDto::getDay18WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay18Backgroud();
                    });
                    break;
                case "19":
                    grid.addColumn(EmployeeDto::getDay19WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay19Backgroud();
                    });
                    break;
                case "20":
                    grid.addColumn(EmployeeDto::getDay20WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay20Backgroud();
                    });
                    break;
                case "21":
                    grid.addColumn(EmployeeDto::getDay21WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay21Backgroud();
                    });
                    break;
                case "22":
                    grid.addColumn(EmployeeDto::getDay22WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay22Backgroud();
                    });
                    break;
                case "23":
                    grid.addColumn(EmployeeDto::getDay23WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay23Backgroud();
                    });
                    break;
                case "24":
                    grid.addColumn(EmployeeDto::getDay24WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay24Backgroud();
                    });
                    break;
                case "25":
                    grid.addColumn(EmployeeDto::getDay25WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay25Backgroud();
                    });
                    break;
                case "26":
                    grid.addColumn(EmployeeDto::getDay26WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay26Backgroud();
                    });
                    break;
                case "27":
                    grid.addColumn(EmployeeDto::getDay27WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay27Backgroud();
                    });
                    break;
                case "28":
                    grid.addColumn(EmployeeDto::getDay28WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay28Backgroud();
                    });
                    break;
                case "29":
                    grid.addColumn(EmployeeDto::getDay29WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay29Backgroud();
                    });
                    break;
                case "30":
                    grid.addColumn(EmployeeDto::getDay30WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay30Backgroud();
                    });
                    break;
                case "31":
                    grid.addColumn(EmployeeDto::getDay31WorkLength).setCaption(entry.getValue()).setStyleGenerator(item -> {
                        return "v-grid-cell-bgcolor-"+item.getDay31Backgroud();
                    });
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * 獲取一月日期
     * @return
     */
    public Map<String, String> getDays(boolean b){
        Map<String, String> map = new LinkedHashMap<>();
        LocalDate today;
        //当前日期
        if(b) {
            today = LocalDate.now();
        }else {
            today = LocalDate.now().plusMonths(1);
        }
        //本月的最后一天
        int monthEnd = today.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        String[][] strArray = {{"1", "一"}, {"2", "二"}, {"3", "三"}, {"4", "四"}, {"5", "五"}, {"6", "六"}, {"7", "日"}};
        for (int i = 1; i <= monthEnd; i++) {
            LocalDate date = LocalDate.of(today.getYear(), today.getMonth(), i);
            String k = String.valueOf(date.getDayOfWeek().getValue());
            for (String[] aStrArray : strArray) {
                if (k.equals(aStrArray[0])) {
                    k = aStrArray[1];
                    break;
                }
            }
            map.put(String.valueOf(i), i+"("+"周"+k+")");
        }
        return map;
    }
}
