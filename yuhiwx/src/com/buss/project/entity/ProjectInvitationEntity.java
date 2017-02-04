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
 * @Description: project_invitation
 * @author onlineGenerator
 * @date 2016-10-10 16:49:27
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project_invitation", schema = "")
@SuppressWarnings("serial")
public class ProjectInvitationEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**耗材*/
	@Excel(exportName="耗材")
	private java.lang.String iname;
	/**收费*/
	@Excel(exportName="收费")
	private java.lang.String price;
	/**备注*/
	@Excel(exportName="备注")
	private java.lang.String idesc;
	/**收费标准描述*/
	@Excel(exportName="收费标准描述")
	private java.lang.String priceDesc;
	/**parentsid*/
	private java.lang.Integer parentsid;
	//区域
	private String part;
	//使用统计
	@Excel(exportName="使用统计")
	private java.lang.Integer useCount;
	//耗材类型 1.服务耗材0.公共设施服务
	@Excel(exportName="type")
	private java.lang.Integer type;
	
	
	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  耗材
	 */
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收费
	 */
	/**
	 * @return
	 */
	@Column(name ="PRICE",nullable=true,length=10)
	public java.lang.String getPrice(){
		return this.price;
	}
	@Column(name ="INAME",nullable=true,length=50)
	public java.lang.String getIname() {
		return iname;
	}

	public void setIname(java.lang.String iname) {
		this.iname = iname;
	}
	@Column(name ="IDESC",nullable=true,length=100)
	public java.lang.String getIdesc() {
		return idesc;
	}

	public void setIdesc(java.lang.String idesc) {
		this.idesc = idesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收费
	 */
	public void setPrice(java.lang.String price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收费标准描述
	 */
	@Column(name ="PRICE_DESC",nullable=true,length=100)
	public java.lang.String getPriceDesc(){
		return this.priceDesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收费标准描述
	 */
	public void setPriceDesc(java.lang.String priceDesc){
		this.priceDesc = priceDesc;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  parentsid
	 */
	@Column(name ="PARENTSID",nullable=true,length=10)
	public java.lang.Integer getParentsid(){
		return this.parentsid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  parentsid
	 */
	public void setParentsid(java.lang.Integer parentsid){
		this.parentsid = parentsid;
	}
	@Column(name ="type",nullable=true)
	public java.lang.Integer getType() {
		return type;
	}

	public void setType(java.lang.Integer type) {
		this.type = type;
	}
	@Column(name ="use_count",nullable=true)
	public java.lang.Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(java.lang.Integer useCount) {
		this.useCount = useCount;
	}
	
}
