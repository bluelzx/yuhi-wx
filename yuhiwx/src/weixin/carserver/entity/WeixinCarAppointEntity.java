package weixin.carserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 客户预约表
 * @author onlineGenerator
 * @date 2016-07-18 08:42:23
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_car_appoint", schema = "")
@SuppressWarnings("serial")
public class WeixinCarAppointEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String openid;
	/**创建日期*/
	private java.util.Date createdate;
	/**服务套餐id*/
	private java.lang.String serverid;
	/**预约日期*/
	private java.util.Date orderdate;
	/**创建人车辆id*/
	@Excel(exportName="创建人车辆id")
	private java.lang.String carid;
	/**联系方式*/
	@Excel(exportName="联系方式")
	private java.lang.String phone;
	/**状态*/
	@Excel(exportName="状态")
	private java.lang.String state;
	/**原价*/
	@Excel(exportName="原价")
	private java.lang.Double originalprice;
	/**折扣金额*/
	@Excel(exportName="折扣金额")
	private java.lang.Double discountprice;
	/**应收金额*/
	@Excel(exportName="应收金额")
	private java.lang.Double price;
	/**创建人描述*/
	@Excel(exportName="创建人描述")
	private java.lang.String descs;
	/**附加金额*/
	@Excel(exportName="附加金额")
	private java.lang.Double additionalprice;
	/**附加描述*/
	@Excel(exportName="附加描述")
	private java.lang.String additionaldesc;
	
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
	@Column(name ="OPENID",nullable=true,length=50)
	public java.lang.String getOpenid(){
		return this.openid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setOpenid(java.lang.String openid){
		this.openid = openid;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATEDATE",nullable=true,length=7)
	public java.util.Date getCreatedate(){
		return this.createdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreatedate(java.util.Date createdate){
		this.createdate = createdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务套餐id
	 */
	@Column(name ="SERVERID",nullable=true,length=50)
	public java.lang.String getServerid(){
		return this.serverid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务套餐id
	 */
	public void setServerid(java.lang.String serverid){
		this.serverid = serverid;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  预约日期
	 */
	@Column(name ="ORDERDATE",nullable=true,length=7)
	public java.util.Date getOrderdate(){
		return this.orderdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  预约日期
	 */
	public void setOrderdate(java.util.Date orderdate){
		this.orderdate = orderdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人车辆id
	 */
	@Column(name ="CARID",nullable=true,length=32)
	public java.lang.String getCarid(){
		return this.carid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人车辆id
	 */
	public void setCarid(java.lang.String carid){
		this.carid = carid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系方式
	 */
	@Column(name ="PHONE",nullable=true,length=32)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系方式
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  原价
	 */
	@Column(name ="ORIGINALPRICE",nullable=true,scale=2,length=5)
	public java.lang.Double getOriginalprice(){
		return this.originalprice;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  原价
	 */
	public void setOriginalprice(java.lang.Double originalprice){
		this.originalprice = originalprice;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  折扣金额
	 */
	@Column(name ="DISCOUNTPRICE",nullable=true,scale=2,length=5)
	public java.lang.Double getDiscountprice(){
		return this.discountprice;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  折扣金额
	 */
	public void setDiscountprice(java.lang.Double discountprice){
		this.discountprice = discountprice;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  应收金额
	 */
	@Column(name ="PRICE",nullable=true,scale=2,length=5)
	public java.lang.Double getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  应收金额
	 */
	public void setPrice(java.lang.Double price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人描述
	 */
	@Column(name ="DESCS",nullable=true,length=400)
	public java.lang.String getDescs(){
		return this.descs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人描述
	 */
	public void setDescs(java.lang.String descs){
		this.descs = descs;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  附加金额
	 */
	@Column(name ="ADDITIONALPRICE",nullable=true,scale=2,length=5)
	public java.lang.Double getAdditionalprice(){
		return this.additionalprice;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  附加金额
	 */
	public void setAdditionalprice(java.lang.Double additionalprice){
		this.additionalprice = additionalprice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  附加描述
	 */
	@Column(name ="ADDITIONALDESC",nullable=true,length=400)
	public java.lang.String getAdditionaldesc(){
		return this.additionaldesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  附加描述
	 */
	public void setAdditionaldesc(java.lang.String additionaldesc){
		this.additionaldesc = additionaldesc;
	}
}
