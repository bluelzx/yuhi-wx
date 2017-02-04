package com.yuhi.wechar.entity;

import java.io.Serializable;
import java.util.List;

public class InvitationtreeNode implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8320918850312682587L;
	private Integer id;
	private String text;
	private Boolean checked;
	private String desc;
	protected String state="closed";   
	private  List<InvitationtreeNode> children; //子节点
	/**收费*/
	private java.lang.String price;
	/**收费标准描述*/
	private java.lang.String priceDesc;
	
	
	public InvitationtreeNode() {
		super();
	}
	public InvitationtreeNode(Integer id, String text, String desc,
			String price, String priceDesc) {
		super();
		this.id = id;
		this.text = text;
		this.desc = desc;
		this.price = price;
		this.priceDesc = priceDesc;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<InvitationtreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<InvitationtreeNode> children) {
		this.children = children;
	}
	public java.lang.String getPrice() {
		return price;
	}
	public void setPrice(java.lang.String price) {
		this.price = price;
	}
	public java.lang.String getPriceDesc() {
		return priceDesc;
	}
	public void setPriceDesc(java.lang.String priceDesc) {
		this.priceDesc = priceDesc;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
