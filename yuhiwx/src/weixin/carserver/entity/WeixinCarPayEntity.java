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
 * @Description: 消费记录
 * @author onlineGenerator
 * @date 2016-07-18 08:42:11
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_car_pay", schema = "")
@SuppressWarnings("serial")
public class WeixinCarPayEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**消费人openid*/
	private java.lang.String openid;
	/**创建日期*/
	private java.util.Date createdate;
	/**收支状态*/
	private java.lang.Integer state;
	/**消费金额*/
	private java.lang.Double money;
	/**积分*/
	@Excel(exportName="积分")
	private java.lang.Integer point;
	/**消费描述*/
	@Excel(exportName="消费描述")
	private java.lang.String descs;
	
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
	 *@return: java.lang.String  消费人openid
	 */
	@Column(name ="OPENID",nullable=true,length=50)
	public java.lang.String getOpenid(){
		return this.openid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  消费人openid
	 */
	public void setOpenid(java.lang.String openid){
		this.openid = openid;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATEDATE",nullable=true,length=20)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  收支状态
	 */
	@Column(name ="STATE",nullable=true,length=2)
	public java.lang.Integer getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  收支状态
	 */
	public void setState(java.lang.Integer state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  消费金额
	 */
	@Column(name ="MONEY",nullable=true,scale=2,length=5)
	public java.lang.Double getMoney(){
		return this.money;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  消费金额
	 */
	public void setMoney(java.lang.Double money){
		this.money = money;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  积分
	 */
	@Column(name ="POINT",nullable=true,length=32)
	public java.lang.Integer getPoint(){
		return this.point;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  积分
	 */
	public void setPoint(java.lang.Integer point){
		this.point = point;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  消费描述
	 */
	@Column(name ="DESCS",nullable=true,length=400)
	public java.lang.String getDescs(){
		return this.descs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  消费描述
	 */
	public void setDescs(java.lang.String descs){
		this.descs = descs;
	}
}
