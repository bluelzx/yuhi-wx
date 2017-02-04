package com.yuhi.wechar.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//zTree
public class TreeNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7803446174559546592L;
	protected String id;
	protected String text;
	protected Boolean checked;
	protected String desc;
	protected String state="closed";   
	private  List<TreeNode> children=new ArrayList<TreeNode>(); //子节点
	
	public TreeNode() {
		super();
	}

	public TreeNode(String id, String text,String desc) {
		super();
		this.id = id;
		this.text = text;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", text=" + text + ", checked=" + checked
				+ ", children=" + children + "]";
	}
	
	
	
	
}
