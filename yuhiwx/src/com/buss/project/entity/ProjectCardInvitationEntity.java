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
 * @Description: project_card_invitation
 * @author onlineGenerator
 * @date 2016-09-26 11:18:06
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project_card_invitation", schema = "")
@SuppressWarnings("serial")
public class ProjectCardInvitationEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**1.基础耗材2.人工耗材*/
	@Excel(exportName="1.基础耗材2.人工耗材")
	private java.lang.Integer typeid;
	/**name*/
	@Excel(exportName="name")
	private java.lang.String name;
	/**基础耗材id*/
	@Excel(exportName="基础耗材id")
	private java.lang.Integer invitationid;
	/**数量*/
	@Excel(exportName="数量")
	private java.lang.Integer count;
	/**单价*/
	@Excel(exportName="单价")
	private java.lang.String price;
	/**projectcardid*/
	@Excel(exportName="projectcardid")
	private java.lang.String projectcardid;
	
	@Excel(exportName="descs")
	private java.lang.String descs;
	
		
	@Excel(exportName="specifications")
	private java.lang.String specifications;
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID",nullable=false,length=10)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  1.基础耗材2.人工耗材
	 */
	@Column(name ="TYPEID",nullable=true,length=10)
	public java.lang.Integer getTypeid(){
		return this.typeid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  1.基础耗材2.人工耗材
	 */
	public void setTypeid(java.lang.Integer typeid){
		this.typeid = typeid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  name
	 */
	@Column(name ="NAME",nullable=true,length=50)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  name
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  基础耗材id
	 */
	@Column(name ="INVITATIONID",nullable=true,length=10)
	public java.lang.Integer getInvitationid(){
		return this.invitationid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  基础耗材id
	 */
	public void setInvitationid(java.lang.Integer invitationid){
		this.invitationid = invitationid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  数量
	 */
	@Column(name ="COUNT",nullable=true,length=10)
	public java.lang.Integer getCount(){
		return this.count;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  数量
	 */
	public void setCount(java.lang.Integer count){
		this.count = count;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单价
	 */
	@Column(name ="PRICE",nullable=true,length=10)
	public java.lang.String getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单价
	 */
	public void setPrice(java.lang.String price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  projectcardid
	 */
	@Column(name ="PROJECTCARDID",nullable=true,length=40)
	public java.lang.String getProjectcardid(){
		return this.projectcardid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  projectcardid
	 */
	public void setProjectcardid(java.lang.String projectcardid){
		this.projectcardid = projectcardid;
	}
	@Column(name ="descs",nullable=true,length=200)
	public java.lang.String getDescs() {
		return descs;
	}

	public void setDescs(java.lang.String descs) {
		this.descs = descs;
	}
	@Column(name ="specifications",nullable=true,length=50)
	public java.lang.String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(java.lang.String specifications) {
		this.specifications = specifications;
	}
	
}
