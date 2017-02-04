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
 * @Description: project_pick_history
 * @author onlineGenerator
 * @date 2016-10-10 15:13:04
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project_pick_history", schema = "")
@SuppressWarnings("serial")
public class ProjectPickHistoryEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**工程人员id*/
	@Excel(exportName="工程人员id")
	private java.lang.String ppopenid;
	/**工程人员名称*/
	@Excel(exportName="业主名称")
	private java.lang.String ppname;
	/**工程单id*/
	@Excel(exportName="工程单id")
	private java.lang.String pcid;
	/**0.拒单1.接单*/
	@Excel(exportName="0.拒单1.接单")
	private java.lang.Integer typeid;
	/**拒单描述*/
	@Excel(exportName="拒单描述")
	private java.lang.String pickdescs;
	/**time*/
	@Excel(exportName="time")
	private java.util.Date time;
	
	public ProjectPickHistoryEntity(Integer id, String ppopenid, String ppname,
			 Integer typeid, String pickdescs, Date time) {
		super();
		this.id = id;
		this.ppopenid = ppopenid;
		this.ppname = ppname;
		this.typeid = typeid;
		this.pickdescs = pickdescs;
		this.time = time;
	}

	public ProjectPickHistoryEntity() {
		super();
	}

	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID",nullable=false,length=19)
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
	 *@return: java.lang.String  工程人员id
	 */
	@Column(name ="PPOPENID",nullable=true,length=50)
	public java.lang.String getPpopenid(){
		return this.ppopenid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工程人员id
	 */
	public void setPpopenid(java.lang.String ppopenid){
		this.ppopenid = ppopenid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工程人员名称
	 */
	@Column(name ="PPNAME",nullable=true,length=50)
	public java.lang.String getPpname(){
		return this.ppname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工程人员名称
	 */
	public void setPpname(java.lang.String ppname){
		this.ppname = ppname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工程单id
	 */
	@Column(name ="PCID",nullable=true,length=50)
	public java.lang.String getPcid(){
		return this.pcid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工程单id
	 */
	public void setPcid(java.lang.String pcid){
		this.pcid = pcid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  0.拒单1.接单
	 */
	@Column(name ="TYPEID",nullable=true,length=10)
	public java.lang.Integer getTypeid(){
		return this.typeid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  0.拒单1.接单
	 */
	public void setTypeid(java.lang.Integer typeid){
		this.typeid = typeid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  拒单描述
	 */
	@Column(name ="PICKDESCS",nullable=true)
	public java.lang.String getPickdescs(){
		return this.pickdescs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  拒单描述
	 */
	public void setPickdescs(java.lang.String pickdescs){
		this.pickdescs = pickdescs;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  time
	 */
	@Column(name ="TIME",nullable=true)
	public java.util.Date getTime(){
		return this.time;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  time
	 */
	public void setTime(java.util.Date time){
		this.time = time;
	}
}
