package com.buss.project.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: project_create
 * @author onlineGenerator
 * @date 2016-09-26 11:26:15
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project_create", schema = "")
@SuppressWarnings("serial")
public class ProjectCreateEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**发单人id*/
	@Excel(exportName="发单人id")
	private java.lang.String ownerid;
	/**创建日期*/
	@Excel(exportName="创建日期")
	private java.util.Date createDate;
	/**设备设施问题描述*/
	@Excel(exportName="设备设施问题描述")
	private java.lang.String content;
	/**耗材 服务id*/
	@Excel(exportName="耗材")
	private java.lang.Integer invitationid;
	@Excel(exportName="可入户开始时间")
	private Date starttime;
	@Excel(exportName="可入户结束时间")
	private Date endtime;
	
	private String desc;
	private String phone;
	private String otherContact;
	@Excel(exportName="区域")
	private java.lang.String part;
	@Excel(exportName="工单类型") //服务单_0,公共单_1
	private java.lang.Integer pctype;
	
	@Excel(exportName="服务类型")
	private java.lang.String pcserver;
	@Excel(exportName="服务地点")
	private java.lang.String publicplace;
	@Excel(exportName="房号")
	private java.lang.String roomid;
	public ProjectCreateEntity() {
		super();
	}

	public ProjectCreateEntity(String id, String ownerid, Date createDate,
			String content, Date starttime, Date endtime, String desc,
			String phone, String otherContact,Integer pctype,String publicplace) {
		super();
		this.id = id;
		this.ownerid = ownerid;
		this.createDate = createDate;
		this.content = content;
		this.starttime = starttime;
		this.endtime = endtime;
		this.desc = desc;
		this.phone = phone;
		this.otherContact = otherContact;
		this.pctype=pctype;
		this.publicplace=publicplace;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
//	@GeneratedValue(generator = "paymentableGenerator")
//	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Id
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发单人id
	 */
	@Column(name ="OWNERID",nullable=true,length=50)
	public java.lang.String getOwnerid(){
		return this.ownerid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发单人id
	 */
	public void setOwnerid(java.lang.String ownerid){
		this.ownerid = ownerid;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备设施问题描述
	 */
	@Column(name ="CONTENT",nullable=true)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备设施问题描述
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  耗材
	 */
	@Column(name ="INVITATIONID",nullable=true,length=10)
	public java.lang.Integer getInvitationid(){
		return this.invitationid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  耗材
	 */
	public void setInvitationid(java.lang.Integer invitationid){
		this.invitationid = invitationid;
	}
	@Column(name ="STARTTIME",nullable=true)
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	@Column(name ="ENDTIME",nullable=true)
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	@Column(name ="descs",nullable=true)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Column(name ="phone",nullable=true)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name ="otherContact",nullable=true)
	public String getOtherContact() {
		return otherContact;
	}

	public void setOtherContact(String otherContact) {
		this.otherContact = otherContact;
	}
	
	@Column(name ="part",nullable=true)
	public java.lang.String getPart() {
		return part;
	}

	public void setPart(java.lang.String part) {
		this.part = part;
	}
	@Column(name ="pctype",nullable=true)
	public java.lang.Integer getPctype() {
		return pctype;
	}

	public void setPctype(java.lang.Integer pctype) {
		this.pctype = pctype;
	}
	@Column(name ="pcserver",nullable=true)
	public java.lang.String getPcserver() {
		return pcserver;
	}

	public void setPcserver(java.lang.String pcserver) {
		this.pcserver = pcserver;
	}
	@Column(name ="publicplace",nullable=true)
	public java.lang.String getPublicplace() {
		return publicplace;
	}

	public void setPublicplace(java.lang.String publicplace) {
		this.publicplace = publicplace;
	}
	@Column(name ="roomid",nullable=true)
	public java.lang.String getRoomid() {
		return roomid;
	}

	public void setRoomid(java.lang.String roomid) {
		this.roomid = roomid;
	}
	
	
}
