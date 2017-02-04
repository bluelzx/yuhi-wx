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
 * @Description: project_part
 * @author onlineGenerator
 * @date 2016-10-10 17:23:06
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project_part", schema = "")
@SuppressWarnings("serial")
public class ProjectPartEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**地点*/
	@Excel(exportName="地点")
	private java.lang.String region;
	/**描述*/
	@Excel(exportName="描述")
	private java.lang.String descs;
	/**父id*/
	@Excel(exportName="父id")
	private java.lang.String parentsid;
	/**父id*/
	@Excel(exportName="地区")
	private java.lang.String part;
	
	public ProjectPartEntity() {
		super();
	}

	public ProjectPartEntity(String id) {
		super();
		this.id = id;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
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
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地点
	 */
	@Column(name ="REGION",nullable=true,length=50)
	public java.lang.String getRegion(){
		return this.region;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地点
	 */
	public void setRegion(java.lang.String region){
		this.region = region;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="DESCS",nullable=true,length=50)
	public java.lang.String getDescs(){
		return this.descs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setDescs(java.lang.String descs){
		this.descs = descs;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  父id
	 */
	@Column(name ="PARENTSID",nullable=true,length=36)
	public java.lang.String getParentsid(){
		return this.parentsid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  父id
	 */
	public void setParentsid(java.lang.String parentsid){
		this.parentsid = parentsid;
	}
	@Column(name ="part",nullable=true,length=36)
	public java.lang.String getPart() {
		return part;
	}

	public void setPart(java.lang.String part) {
		this.part = part;
	}
	
	
}
