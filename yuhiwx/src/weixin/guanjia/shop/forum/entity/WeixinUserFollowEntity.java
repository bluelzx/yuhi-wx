package weixin.guanjia.shop.forum.entity;

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
 * @Description: 跟帖表
 * @author onlineGenerator
 * @date 2016-06-14 16:48:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_user_follow", schema = "")
@SuppressWarnings("serial")
public class WeixinUserFollowEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**帖子id*/
	@Excel(exportName="帖子id")
	private java.lang.String sendcardid;
	/**创建日期*/
	@Excel(exportName="创建日期")
	private java.util.Date date;
	/**跟帖人名称*/
	@Excel(exportName="跟帖人名称")
	private java.lang.String username;
	/**跟帖人头像*/
	@Excel(exportName="跟帖人头像")
	private java.lang.String usertitleimg;
	/**跟帖内容*/
	@Excel(exportName="跟帖内容")
	private java.lang.String followcontnt;
	/**openid*/
	@Excel(exportName="openid")
	private java.lang.String useropenid;
	
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
	 *@return: java.lang.String  帖子id
	 */
	@Column(name ="SENDCARDID",nullable=true,length=50)
	public java.lang.String getSendcardid(){
		return this.sendcardid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  帖子id
	 */
	public void setSendcardid(java.lang.String sendcardid){
		this.sendcardid = sendcardid;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="DATE",nullable=true,length=50)
	public java.util.Date getDate(){
		return this.date;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setDate(java.util.Date date){
		this.date = date;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  跟帖人名称
	 */
	@Column(name ="USERNAME",nullable=true,length=50)
	public java.lang.String getUsername(){
		return this.username;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  跟帖人名称
	 */
	public void setUsername(java.lang.String username){
		this.username = username;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  跟帖人头像
	 */
	@Column(name ="USERTITLEIMG",nullable=true,length=200)
	public java.lang.String getUsertitleimg(){
		return this.usertitleimg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  跟帖人头像
	 */
	public void setUsertitleimg(java.lang.String usertitleimg){
		this.usertitleimg = usertitleimg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  跟帖内容
	 */
	@Column(name ="FOLLOWCONTNT",nullable=true,length=500)
	public java.lang.String getFollowcontnt(){
		return this.followcontnt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  跟帖内容
	 */
	public void setFollowcontnt(java.lang.String followcontnt){
		this.followcontnt = followcontnt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  openid
	 */
	@Column(name ="USEROPENID",nullable=true,length=50)
	public java.lang.String getUseropenid(){
		return this.useropenid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  openid
	 */
	public void setUseropenid(java.lang.String useropenid){
		this.useropenid = useropenid;
	}
}
