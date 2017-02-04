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
 * @Description: 服务套餐
 * @author onlineGenerator
 * @date 2016-07-18 08:42:35
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_car_servser", schema = "")
@SuppressWarnings("serial")
public class WeixinCarServserEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**套餐描述*/
	@Excel(exportName="套餐描述")
	private java.lang.String name;
	/**套餐价格*/
	@Excel(exportName="套餐价格")
	private java.lang.Double price;
	/**状态*/
	@Excel(exportName="状态")
	private java.lang.Integer state;
	/**开始日期*/
	@Excel(exportName="开始日期")
	private java.util.Date startdate;
	/**结束日期*/
	@Excel(exportName="结束日期")
	private java.util.Date enddate;
	
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
	 *@return: java.lang.String  套餐描述
	 */
	@Column(name ="NAME",nullable=true,length=400)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  套餐描述
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  套餐价格
	 */
	@Column(name ="PRICE",nullable=true,length=32)
	public java.lang.Double getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  套餐价格
	 */
	public void setPrice(java.lang.Double price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  状态
	 */
	@Column(name ="STATE",nullable=true,length=32)
	public java.lang.Integer getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  状态
	 */
	public void setState(java.lang.Integer state){
		this.state = state;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始日期
	 */
	@Column(name ="STARTDATE",nullable=true,length=32)
	public java.util.Date getStartdate(){
		return this.startdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始日期
	 */
	public void setStartdate(java.util.Date startdate){
		this.startdate = startdate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束日期
	 */
	@Column(name ="ENDDATE",nullable=true,length=32)
	public java.util.Date getEnddate(){
		return this.enddate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束日期
	 */
	public void setEnddate(java.util.Date enddate){
		this.enddate = enddate;
	}
}
