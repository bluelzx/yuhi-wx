package weixin.carserver.entity;

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
 * @Description: 用户汽车表
 * @author onlineGenerator
 * @date 2016-07-18 08:41:43
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_car_car", schema = "")
@SuppressWarnings("serial")
public class WeixinCarCarEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**品牌*/
	private java.lang.String brand;
	/**型号*/
	private java.lang.String model;
	/**车牌号码*/
	private java.lang.String carnumber;
	/**生成日期*/
	private java.util.Date createdate;
	/**里程表*/
	@Excel(exportName="里程表")
	private java.lang.Integer droducedate;
	/**上次服务时间*/
	@Excel(exportName="上次服务时间")
	private java.util.Date lastdate;
	/**下次服务内容*/
	@Excel(exportName="下次服务内容")
	private java.lang.String nextserverdesc;
	/**状态*/
	@Excel(exportName="状态")
	private java.lang.String state;
	/**客户openid*/
	@Excel(exportName="客户openid")
	private java.lang.String openid;
	
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
	 *@return: java.lang.String  品牌
	 */
	@Column(name ="BRAND",nullable=true,length=50)
	public java.lang.String getBrand(){
		return this.brand;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品牌
	 */
	public void setBrand(java.lang.String brand){
		this.brand = brand;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  型号
	 */
	@Column(name ="MODEL",nullable=true,length=20)
	public java.lang.String getModel(){
		return this.model;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  型号
	 */
	public void setModel(java.lang.String model){
		this.model = model;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车牌号码
	 */
	@Column(name ="CARNUMBER",nullable=true,length=50)
	public java.lang.String getCarnumber(){
		return this.carnumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车牌号码
	 */
	public void setCarnumber(java.lang.String carnumber){
		this.carnumber = carnumber;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生成日期
	 */
	@Column(name ="CREATEDATE",nullable=true,length=20)
	public java.util.Date getCreatedate(){
		return this.createdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生成日期
	 */
	public void setCreatedate(java.util.Date createdate){
		this.createdate = createdate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  里程表
	 */
	@Column(name ="DRODUCEDATE",nullable=true,length=32)
	public java.lang.Integer getDroducedate(){
		return this.droducedate;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  里程表
	 */
	public void setDroducedate(java.lang.Integer droducedate){
		this.droducedate = droducedate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  上次服务时间
	 */
	@Column(name ="LASTDATE",nullable=true,length=32)
	public java.util.Date getLastdate(){
		return this.lastdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  上次服务时间
	 */
	public void setLastdate(java.util.Date lastdate){
		this.lastdate = lastdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  下次服务内容
	 */
	@Column(name ="NEXTSERVERDESC",nullable=true,length=400)
	public java.lang.String getNextserverdesc(){
		return this.nextserverdesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  下次服务内容
	 */
	public void setNextserverdesc(java.lang.String nextserverdesc){
		this.nextserverdesc = nextserverdesc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATE",nullable=true,length=32)
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户openid
	 */
	@Column(name ="OPENID",nullable=true,length=32)
	public java.lang.String getOpenid(){
		return this.openid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户openid
	 */
	public void setOpenid(java.lang.String openid){
		this.openid = openid;
	}
}
