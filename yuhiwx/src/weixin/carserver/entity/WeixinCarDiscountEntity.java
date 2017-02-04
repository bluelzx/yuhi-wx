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
 * @Description: 套餐折扣
 * @author onlineGenerator
 * @date 2016-07-18 08:42:29
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_car_discount", schema = "")
@SuppressWarnings("serial")
public class WeixinCarDiscountEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**服务套餐*/
	@Excel(exportName="服务套餐")
	private java.lang.String goodid;
	/**会员等级条件*/
	@Excel(exportName="会员等级条件")
	private java.lang.Integer level;
	/**计算方式*/
	@Excel(exportName="计算方式")
	private java.lang.String type;
	/**计算比率*/
	@Excel(exportName="计算比率")
	private java.lang.Double value;
	/**开始时间*/
	@Excel(exportName="开始时间")
	private java.util.Date startdate;
	/**结束时间*/
	@Excel(exportName="结束时间")
	private java.util.Date enddate;
	/**状态*/
	@Excel(exportName="状态")
	private java.lang.Integer state;
	
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
	 *@return: java.lang.String  服务套餐
	 */
	@Column(name ="GOODID",nullable=true,length=32)
	public java.lang.String getGoodid(){
		return this.goodid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务套餐
	 */
	public void setGoodid(java.lang.String goodid){
		this.goodid = goodid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  会员等级条件
	 */
	@Column(name ="LEVEL",nullable=true,length=32)
	public java.lang.Integer getLevel(){
		return this.level;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  会员等级条件
	 */
	public void setLevel(java.lang.Integer level){
		this.level = level;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计算方式
	 */
	@Column(name ="TYPE",nullable=true,length=32)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计算方式
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  计算比率
	 */
	@Column(name ="VALUE",nullable=true,length=32)
	public java.lang.Double getValue(){
		return this.value;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  计算比率
	 */
	public void setValue(java.lang.Double value){
		this.value = value;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始时间
	 */
	@Column(name ="STARTDATE",nullable=true,length=32)
	public java.util.Date getStartdate(){
		return this.startdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setStartdate(java.util.Date startdate){
		this.startdate = startdate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	@Column(name ="ENDDATE",nullable=true,length=32)
	public java.util.Date getEnddate(){
		return this.enddate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setEnddate(java.util.Date enddate){
		this.enddate = enddate;
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
}
