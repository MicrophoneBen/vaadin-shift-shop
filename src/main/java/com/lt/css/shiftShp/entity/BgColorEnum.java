package com.lt.css.shiftShp.entity;

/**
 * @author ben.zhang.b.q
 * @date 2019/2/6 17:36
 * @return Grid的设置背景颜色的枚举类型
 **/
public enum BgColorEnum {
	/**
	 * @author ben.zhang.b.q
	 * @date 2019/2/6 17:38
	 * @return 默认配置8种颜色设置不同班次的背景颜色
	 **/
	BG1(1, "bgcolor-1"),
	BG2(2, "bgcolor-2"),
	BG3(3, "bgcolor-3"),
	BG4(4, "bgcolor-4"),
	BG5(5, "bgcolor-5"),
	BG6(6, "bgcolor-6"),
	BG7(7, "bgcolor-7"),
	BG8(8, "bgcolor-8");
	
	public int index;
	public String name;
	
	private BgColorEnum(int index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public static String getName(int index) {
		for(BgColorEnum c : BgColorEnum.values()) {
			 if (c.getIndex() == index) {  
	                return c.name;  
	            }  
		}
		return null;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
