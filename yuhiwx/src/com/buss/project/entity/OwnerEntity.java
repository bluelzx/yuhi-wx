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
 * @Description: owner
 * @author onlineGenerator
 * @date 2016-10-31 09:07:16
 * @version V1.0   
 *
 */
@Entity
@Table(name = "owner", schema = "")
@SuppressWarnings("serial")
public class OwnerEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**称呼*/
	@Excel(exportName="称呼")
	private java.lang.String name;
	/**phone*/
	@Excel(exportName="phone")
	private java.lang.String phone;
	/**地区*/
	@Excel(exportName="地区")
	private java.lang.String partid;
	/**男女*/
	@Excel(exportName="男女")
	private java.lang.Integer sex;
	/**openid*/
	@Excel(exportName="openid")
	private java.lang.String openid;
	/**是否可用*/
	@Excel(exportName="是否可用")
	private java.lang.Integer isok;
	
	/**partdesc*/
	@Excel(exportName="partdesc")
	private java.lang.String partdesc;
	/**part*/
	@Excel(exportName="part")
	private java.lang.String part;
	
	
	
	public OwnerEntity(String id, String name, String part, String partdesc) {
		super();
		this.id = id;
		this.name = name;
		this.part = part;
		this.partdesc = partdesc;
	}

	public OwnerEntity(String id) {
		super();
		this.id = id;
	}

	public OwnerEntity() {
		super();
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=40)
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
	 *@return: java.lang.String  称呼
	 */
	@Column(name ="NAME",nullable=true,length=20)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  称呼
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  phone
	 */
	@Column(name ="PHONE",nullable=true,length=10)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  phone
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地区
	 */
	@Column(name ="PARTID",nullable=true,length=40)
	public java.lang.String getPartid(){
		return this.partid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地区
	 */
	public void setPartid(java.lang.String partid){
		this.partid = partid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  男女
	 */
	@Column(name ="SEX",nullable=true,length=10)
	public java.lang.Integer getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  男女
	 */
	public void setSex(java.lang.Integer sex){
		this.sex = sex;
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
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否可用
	 */
	@Column(name ="ISOK",nullable=true,length=10)
	public java.lang.Integer getIsok(){
		return this.isok;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否可用
	 */
	public void setIsok(java.lang.Integer isok){
		this.isok = isok;
	}
	@Column(name ="partdesc",nullable=true)
	public java.lang.String getPartdesc() {
		return partdesc;
	}

	public void setPartdesc(java.lang.String partdesc) {
		this.partdesc = partdesc;
	}
	@Column(name ="part",nullable=true)
	public java.lang.String getPart() {
		return part;
	}

	public void setPart(java.lang.String part) {
		this.part = part;
	}
	
	
	
}
