package com.buss.project.entity;

import java.io.Serializable;
import java.util.Date;

public class ProjectCardDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String placedetail;
	private String phone;
	private String ownername;
	private Date starttime;
	private Date endtime;
	private String otherconcat;
	private String state;
	private String repair;
	private String content;
	private String descs;
	
	public ProjectCardDetail() {
		super();
	}
	
	public ProjectCardDetail(String placedetail, String phone,
			String ownername, Date starttime, Date endtime, String otherconcat,
			String content, String descs) {
		super();
		this.placedetail = placedetail;
		this.phone = phone;
		this.ownername = ownername;
		this.starttime = starttime;
		this.endtime = endtime;
		this.otherconcat = otherconcat;
		this.content = content;
		this.descs = descs;
	}

	public ProjectCardDetail(String placedetail, String phone,
			String ownername, Date starttime, Date endtime, String otherconcat,
			String state, String repair, String content, String descs) {
		super();
		this.placedetail = placedetail;
		this.phone = phone;
		this.ownername = ownername;
		this.starttime = starttime;
		this.endtime = endtime;
		this.otherconcat = otherconcat;
		this.state = state;
		this.repair = repair;
		this.content = content;
		this.descs = descs;
	}

	public String getPlacedetail() {
		return placedetail;
	}
	public void setPlacedetail(String placedetail) {
		this.placedetail = placedetail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public String getOtherconcat() {
		return otherconcat;
	}
	public void setOtherconcat(String otherconcat) {
		this.otherconcat = otherconcat;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRepair() {
		return repair;
	}
	public void setRepair(String repair) {
		this.repair = repair;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescs() {
		return descs;
	}
	public void setDescs(String descs) {
		this.descs = descs;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
}
