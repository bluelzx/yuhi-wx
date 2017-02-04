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
 * @Description: project_card_statistics
 * @author onlineGenerator
 * @date 2016-11-30 09:25:39
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project_card_statistics", schema = "")
@SuppressWarnings("serial")
public class ProjectCardStatisticsEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**startTime*/
	@Excel(exportName="startTime")
	private java.lang.String startTime;
	/**endTime*/
	@Excel(exportName="endTime")
	private java.lang.String endTime;
	/**工单类型(选)*/
	@Excel(exportName="工单类型(选)")
	private java.lang.String cardType;
	/**工单状态(选)*/
	@Excel(exportName="工单状态(选)")
	private java.lang.String cardState;
	/**工程人员(选)*/
	@Excel(exportName="工程人员(选)")
	private java.lang.String pperson;
	/**业主(选)*/
	@Excel(exportName="业主(选)")
	private java.lang.String owner;
	/**服务类型(选)*/
	@Excel(exportName="服务类型(选)")
	private java.lang.String pcServer;
	/**支付(选)*/
	@Excel(exportName="支付(选)")
	private java.lang.String ispay;
	/**审核(选)*/
	@Excel(exportName="审核(选)")
	private java.lang.String remindState;
	/**区域*/
	@Excel(exportName="区域")
	private java.lang.String part;
	
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
	 *@return: java.lang.String  startTime
	 */
	@Column(name ="START_TIME",nullable=true,length=10)
	public java.lang.String getStartTime(){
		return this.startTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  startTime
	 */
	public void setStartTime(java.lang.String startTime){
		this.startTime = startTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  endTime
	 */
	@Column(name ="END_TIME",nullable=true,length=10)
	public java.lang.String getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  endTime
	 */
	public void setEndTime(java.lang.String endTime){
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工单类型(选)
	 */
	@Column(name ="CARD_TYPE",nullable=true,length=10)
	public java.lang.String getCardType(){
		return this.cardType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工单类型(选)
	 */
	public void setCardType(java.lang.String cardType){
		this.cardType = cardType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工单状态(选)
	 */
	@Column(name ="CARD_STATE",nullable=true,length=10)
	public java.lang.String getCardState(){
		return this.cardState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工单状态(选)
	 */
	public void setCardState(java.lang.String cardState){
		this.cardState = cardState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工程人员(选)
	 */
	@Column(name ="PPERSON",nullable=true,length=36)
	public java.lang.String getPperson(){
		return this.pperson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工程人员(选)
	 */
	public void setPperson(java.lang.String pperson){
		this.pperson = pperson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业主(选)
	 */
	@Column(name ="OWNER",nullable=true,length=36)
	public java.lang.String getOwner(){
		return this.owner;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业主(选)
	 */
	public void setOwner(java.lang.String owner){
		this.owner = owner;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务类型(选)
	 */
	@Column(name ="PC_SERVER",nullable=true,length=5)
	public java.lang.String getPcServer(){
		return this.pcServer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务类型(选)
	 */
	public void setPcServer(java.lang.String pcServer){
		this.pcServer = pcServer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付(选)
	 */
	@Column(name ="ISPAY",nullable=true,length=5)
	public java.lang.String getIspay(){
		return this.ispay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付(选)
	 */
	public void setIspay(java.lang.String ispay){
		this.ispay = ispay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核(选)
	 */
	@Column(name ="REMIND_STATE",nullable=true,length=5)
	public java.lang.String getRemindState(){
		return this.remindState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核(选)
	 */
	public void setRemindState(java.lang.String remindState){
		this.remindState = remindState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  区域
	 */
	@Column(name ="PART",nullable=true,length=36)
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
}
