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
 * @Description: 园区通告
 * @author onlineGenerator
 * @date 2016-11-13 09:35:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project_ownerMes", schema = "")
@SuppressWarnings("serial")
public class ProjectOwnermesEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**修改人名称*/
	private java.lang.String updateName;
	/**修改日期*/
	private java.util.Date updateDate;
	/**标题*/
	@Excel(exportName="标题")
	private java.lang.String title;
	/**内容*/
	@Excel(exportName="内容")
	private java.lang.String content;
	/**备注*/
	@Excel(exportName="备注")
	private java.lang.String remark;
	/**上传模板路径*/
	@Excel(exportName="上传模板路径")
	private java.lang.String templateword;
	/**模板页面路径*/
	@Excel(exportName="模板页面路径")
	private java.lang.String templatehtml;
	@Excel(exportName="所属区域")
	private java.lang.String part;
	
	
	
	
	public ProjectOwnermesEntity() {
		super();
	}

	public ProjectOwnermesEntity(String id, String title) {
		super();
		this.id = id;
		this.title = title;
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
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
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
	 *@return: java.lang.String  修改人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标题
	 */
	@Column(name ="TITLE",nullable=true,length=100)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  内容
	 */
	@Column(name ="CONTENT",nullable=true)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  内容
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=500)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上传模板路径
	 */
	@Column(name ="TEMPLATEWORD",nullable=true,length=200)
	public java.lang.String getTemplateword(){
		return this.templateword;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上传模板路径
	 */
	public void setTemplateword(java.lang.String templateword){
		this.templateword = templateword;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  模板页面路径
	 */
	@Column(name ="TEMPLATEHTML",nullable=true,length=200)
	public java.lang.String getTemplatehtml(){
		return this.templatehtml;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模板页面路径
	 */
	public void setTemplatehtml(java.lang.String templatehtml){
		this.templatehtml = templatehtml;
	}
	@Column(name ="part",nullable=true,length=50)
	public java.lang.String getPart() {
		return part;
	}

	public void setPart(java.lang.String part) {
		this.part = part;
	}
	
	
}
