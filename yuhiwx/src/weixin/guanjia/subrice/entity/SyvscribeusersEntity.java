package weixin.guanjia.subrice.entity;

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
 * @Description: 关注用户
 * @author onlineGenerator
 * @date 2016-06-06 12:33:41
 * @version V1.0   
 *
 */
@Entity
@Table(name = "syvscribeUsers", schema = "")
@SuppressWarnings("serial")
public class SyvscribeusersEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**城市*/
	@Excel(exportName="城市")
	private java.lang.String city;
	/**头像*/
	@Excel(exportName="头像")
	private java.lang.String headimgurl;
	/**语言*/
	@Excel(exportName="语言")
	private java.lang.String language;
	/**国家*/
	@Excel(exportName="国家")
	private java.lang.String country;
	/**分组id*/
	@Excel(exportName="分组id")
	private java.lang.String groupid;
	/**昵称*/
	@Excel(exportName="昵称")
	private java.lang.String nickname;
	/**地区*/
	@Excel(exportName="地区")
	private java.lang.String province;
	/**备注*/
	@Excel(exportName="备注")
	private java.lang.String remark;
	/**关注时间*/
	@Excel(exportName="关注时间")
	private java.lang.String subscribetime;
	/**关注*/
	@Excel(exportName="关注")
	private java.lang.String subscribe;
	/**性别*/
	@Excel(exportName="性别")
	private java.lang.String sex;
	/**区域*/
	@Excel(exportName="区域")
	private java.lang.String partid;
	/**手机号码*/
	@Excel(exportName="手机号码")
	private java.lang.String phone;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
//	@GeneratedValue(generator = "paymentableGenerator")
//	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Id
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
	 *@return: java.lang.String  城市
	 */
	@Column(name ="CITY",nullable=true,length=32)
	public java.lang.String getCity(){
		return this.city;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  城市
	 */
	public void setCity(java.lang.String city){
		this.city = city;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  头像
	 */
	@Column(name ="HEADIMGURL",nullable=true,length=32)
	public java.lang.String getHeadimgurl(){
		return this.headimgurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  头像
	 */
	public void setHeadimgurl(java.lang.String headimgurl){
		this.headimgurl = headimgurl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  语言
	 */
	@Column(name ="LANGUAGE",nullable=true,length=32)
	public java.lang.String getLanguage(){
		return this.language;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  语言
	 */
	public void setLanguage(java.lang.String language){
		this.language = language;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国家
	 */
	@Column(name ="COUNTRY",nullable=true,length=32)
	public java.lang.String getCountry(){
		return this.country;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国家
	 */
	public void setCountry(java.lang.String country){
		this.country = country;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分组id
	 */
	@Column(name ="GROUPID",nullable=true,length=32)
	public java.lang.String getGroupid(){
		return this.groupid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分组id
	 */
	public void setGroupid(java.lang.String groupid){
		this.groupid = groupid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  昵称
	 */
	@Column(name ="NICKNAME",nullable=true,length=32)
	public java.lang.String getNickname(){
		return this.nickname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  昵称
	 */
	public void setNickname(java.lang.String nickname){
		this.nickname = nickname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地区
	 */
	@Column(name ="PROVINCE",nullable=true,length=32)
	public java.lang.String getProvince(){
		return this.province;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地区
	 */
	public void setProvince(java.lang.String province){
		this.province = province;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=32)
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
	 *@return: java.lang.String  关注时间
	 */
	@Column(name ="SUBSCRIBETIME",nullable=true,length=32)
	public java.lang.String getSubscribetime(){
		return this.subscribetime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关注时间
	 */
	public void setSubscribetime(java.lang.String subscribetime){
		this.subscribetime = subscribetime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  关注
	 */
	@Column(name ="SUBSCRIBE",nullable=true,length=32)
	public java.lang.String getSubscribe(){
		return this.subscribe;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关注
	 */
	public void setSubscribe(java.lang.String subscribe){
		this.subscribe = subscribe;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	@Column(name ="SEX",nullable=true,length=32)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  区域
	 */
	@Column(name ="PARTID",nullable=true,length=32)
	public java.lang.String getPartid(){
		return this.partid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  区域
	 */
	public void setPartid(java.lang.String partid){
		this.partid = partid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  手机号码
	 */
	@Column(name ="PHONE",nullable=true,length=32)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  手机号码
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	
	
}
