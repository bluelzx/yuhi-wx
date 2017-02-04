package com.yuhi.wechar.entity;

import java.io.Serializable;
import java.util.List;

public class resultdata implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> categories;
	private List<Long> data;
	private List<String> piedata;
	public resultdata(){}
	public resultdata(List<String> categories, List<Long> data, List<String> piedata) {
		super();
		this.categories = categories;
		this.data = data;
		this.piedata = piedata;
	}
	public List<String> getPiedata() {
		return piedata;
	}
	public void setPiedata(List<String> piedata) {
		this.piedata = piedata;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public List<Long> getData() {
		return data;
	}
	public void setData(List<Long> data) {
		this.data = data;
	}
	
}

