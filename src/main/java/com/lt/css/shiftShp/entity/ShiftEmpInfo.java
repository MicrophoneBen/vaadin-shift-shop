package com.lt.css.shiftShp.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ShiftEmpInfo
 * @description: 拖动的人员信息
 * @author: ben.zhang.b.q
 * @create: 2019-02-08 15:09
 **/
public class ShiftEmpInfo {
        private String id;
        private String name;

        @Override
        public String toString() {
            return "'id='" + id + '\'' +
                    ", name='" + name + '\'';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    /*    //发生调班的所有人员
        public String getEmpInfoList() {
            for (ShiftEmpInfo empInfo : empInfoList) {
                empNotification.append(empInfo.toString());
            }
            return empNotification.toString();
        }

        //添加调班人员
        public void setEmpInfoList(ShiftEmpList.EmpInfo empInfo) {
            empInfoList.add(empInfo);
        }*/

}
