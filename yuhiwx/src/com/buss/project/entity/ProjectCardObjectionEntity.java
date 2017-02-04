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
 * @Description: project_card_objection
 * @author onlineGenerator
 * @date 2016-09-26 11:18:12
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project_card_objection", schema = "")
@SuppressWarnings("serial")
public class ProjectCardObjectionEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**1.情况属实2.维修及格3.维修不及格4.自然损耗5.其他原因*/
	@Excel(exportName="1.情况属实2.维修及格3.维修不及格4.自然损耗5.其他原因")
	private java.lang.Integer status;
	/**1.物业方面意见（单张未开始）2.保修方面意见3.物业验收意见4.公司验收意见*/
	@Excel(exportName="1.物业方面意见（单张未开始）2.保修方面意见3.物业验收意见4.公司验收意见")
	private java.lang.Integer typeid;
	/**其他补充*/
	@Excel(exportName="其他补充")
	private java.lang.String othercontent;
	/**创建时间*/
	@Excel(exportName="创建时间")
	private java.util.Date createtime;
	/**关联工单号*/
	@Excel(exportName="关联工单号")
	private java.lang.String projectcardid;
	/**操作人*/
	@Excel(exportName="操作人")
	private java.lang.String usercode;
	
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
	 *@return: java.lang.Integer  1.情况属实2.维修及格3.维修不及格4.自然损耗5.其他原因
	 */
	@Column(name ="STATUS",nullable=true,length=10)
	public java.lang.Integer getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  1.情况属实2.维修及格3.维修不及格4.自然损耗5.其他原因
	 */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  1.物业方面意见（单张未开始）2.保修方面意见3.物业验收意见4.公司验收意见
	 */
	@Column(name ="TYPEID",nullable=true,length=10)
	public java.lang.Integer getTypeid(){
		return this.typeid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  1.物业方面意见（单张未开始）2.保修方面意见3.物业验收意见4.公司验收意见
	 */
	public void setTypeid(java.lang.Integer typeid){
		this.typeid = typeid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  其他补充
	 */
	@Column(name ="OTHERCONTENT",nullable=true)
	public java.lang.String getOthercontent(){
		return this.othercontent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  其他补充
	 */
	public void setOthercontent(java.lang.String othercontent){
		this.othercontent = othercontent;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATETIME",nullable=true)
	public java.util.Date getCreatetime(){
		return this.createtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreatetime(java.util.Date createtime){
		this.createtime = createtime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  关联工单号
	 */
	@Column(name ="PROJECTCARDID",nullable=true,length=40)
	public java.lang.String getProjectcardid(){
		return this.projectcardid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关联工单号
	 */
	public void setProjectcardid(java.lang.String projectcardid){
		this.projectcardid = projectcardid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作人
	 */
	@Column(name ="USERCODE",nullable=true,length=255)
	public java.lang.String getUsercode(){
		return this.usercode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作人
	 */
	public void setUsercode(java.lang.String usercode){
		this.usercode = usercode;
	}
}
