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
 * @Description: 工程人员表
 * @author onlineGenerator
 * @date 2016-09-26 11:30:06
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project_person", schema = "")
@SuppressWarnings("serial")
public class ProjectPersonEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	@Excel(exportName="创建人名称")
	private java.lang.String name;
	/**区域*/
	@Excel(exportName="区域")
	private java.lang.String part;
	/**手机*/
	@Excel(exportName="手机")
	private java.lang.String phone;
	/**技能*/
	@Excel(exportName="技能")
	private java.lang.String skill;
	/**可用*/
	@Excel(exportName="可用")
	private java.lang.String isok;
	/**openid*/
	@Excel(exportName="openid")
	private java.lang.String openid;
	/**是否可用*/
	@Excel(exportName="地区名冗余")
	private java.lang.String partdesc;
	
	
	
	
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
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="NAME",nullable=true,length=50)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  区域
	 */
	@Column(name ="PART",nullable=true,length=50)
	public java.lang.String getPart(){
		return this.part;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  区域
	 */
	public void setPart(java.lang.String part){
		this.part = part;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机
	 */
	@Column(name ="PHONE",nullable=true,length=50)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  技能
	 */
	@Column(name ="SKILL",nullable=true,length=120)
	public java.lang.String getSkill(){
		return this.skill;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  技能
	 */
	public void setSkill(java.lang.String skill){
		this.skill = skill;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  可用
	 */
	@Column(name ="ISOK",nullable=true,length=5)
	public java.lang.String getIsok(){
		return this.isok;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  可用
	 */
	public void setIsok(java.lang.String isok){
		this.isok = isok;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  openid
	 */
	@Column(name ="OPENID",nullable=true,length=50)
	public java.lang.String getOpenid(){
		return this.openid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  openid
	 */
	public void setOpenid(java.lang.String openid){
		this.openid = openid;
	}

	@Column(name ="partdesc",nullable=true,length=10)
	public java.lang.String getPartdesc() {
		return partdesc;
	}

	public void setPartdesc(java.lang.String partdesc) {
		this.partdesc = partdesc;
	}
	
}
