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
 * @Description: 签到记录表
 * @author onlineGenerator
 * @date 2016-09-26 11:30:44
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project_sign", schema = "")
@SuppressWarnings("serial")
public class ProjectSignEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**签到人openid*/
	@Excel(exportName="签到人openid")
	private java.lang.String openid;
	/**签到时间*/
	@Excel(exportName="签到时间")
	private java.util.Date createTime;
	/**1上班2下班*/
	@Excel(exportName="1上班2下班")
	private java.lang.String type;
	private String part;
	
	public ProjectSignEntity(String id,String openid, Date createTime, String type) {
		super();
		this.id=id;
		this.openid = openid;
		this.createTime = createTime;
		this.type = type;
	}

	public ProjectSignEntity() {
		super();
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  签到人openid
	 */
	@Column(name ="OPENID",nullable=true,length=50)
	public java.lang.String getOpenid(){
		return this.openid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  签到人openid
	 */
	public void setOpenid(java.lang.String openid){
		this.openid = openid;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  签到时间
	 */
	@Column(name ="CREATE_TIME",nullable=true,length=20)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  签到时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  1上班2下班
	 */
	@Column(name ="TYPE",nullable=true,length=50)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  1上班2下班
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	@Column(name ="PART",nullable=true,length=50)
	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}
	
}
