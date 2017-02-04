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
 * @Description: 客户表
 * @author onlineGenerator
 * @date 2016-07-18 08:42:17
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_car_customer", schema = "")
@SuppressWarnings("serial")
public class WeixinCarCustomerEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人openid*/
	private java.lang.String openid;
	/**手机号码*/
	private java.lang.Integer phone;
	/**会员制*/
	private java.lang.Integer level;
	/**积分*/
	private java.lang.Integer point;
	/**预存款余额*/
	@Excel(exportName="预存款余额")
	private java.lang.Double money;
	
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
	 *@return: java.lang.String  创建人openid
	 */
	@Column(name ="OPENID",nullable=true,length=50)
	public java.lang.String getOpenid(){
		return this.openid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人openid
	 */
	public void setOpenid(java.lang.String openid){
		this.openid = openid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  手机号码
	 */
	@Column(name ="PHONE",nullable=true,length=20)
	public java.lang.Integer getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  手机号码
	 */
	public void setPhone(java.lang.Integer phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  会员制
	 */
	@Column(name ="LEVEL",nullable=true,length=20)
	public java.lang.Integer getLevel(){
		return this.level;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  会员制
	 */
	public void setLevel(java.lang.Integer level){
		this.level = level;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  积分
	 */
	@Column(name ="POINT",nullable=true,length=20)
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  预存款余额
	 */
	@Column(name ="MONEY",nullable=true,length=32)
	public java.lang.Double getMoney(){
		return this.money;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  预存款余额
	 */
	public void setMoney(java.lang.Double money){
		this.money = money;
	}
}
