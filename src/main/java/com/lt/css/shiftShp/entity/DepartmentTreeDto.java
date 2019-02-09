package com.lt.css.shiftShp.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: MyDeptComponent
 * @description: 部门树
 * @author: ben.zhang.b.q
 * @create: 2018-12-04 13:23
 **/
public class DepartmentTreeDto {
   
    private String id;
    private String name;
    
    private List<DepartmentTreeDto> childs = new ArrayList<>();

    public DepartmentTreeDto() {
    	
    }
    
    public DepartmentTreeDto(String id, String name) {
    	this.id = id;
        this.name = name;
    }
    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
        return name;
    }


    public List<DepartmentTreeDto> getChilds() {
		return childs;
	}

	public void setChilds(List<DepartmentTreeDto> childs) {
		this.childs = childs;
	}

}
