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
 * @Description: 用户发帖表
 * @author onlineGenerator
 * @date 2016-06-14 16:49:04
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_user_sendcard", schema = "")
@SuppressWarnings("serial")
public class WeixinUserSendcardEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**openid*/
	@Excel(exportName="openid")
	private java.lang.String openid;
	/**帖子区域*/
	@Excel(exportName="帖子区域")
	private java.lang.String part;
	/**主题*/
	@Excel(exportName="主题")
	private java.lang.String title;
	/**修改日期*/
	@Excel(exportName="修改日期")
	private java.util.Date updateDate;
	/**发帖内容*/
	@Excel(exportName="发帖内容")
	private java.lang.String cardcontent;
	/**排序*/
	@Excel(exportName="排序")
	private java.lang.String orderid;
	/**类型*/
	@Excel(exportName="类型")
	private java.lang.String type;
	
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
	 *@return: java.lang.String  openid
	 */
	@Column(name ="OPENID",nullable=true,length=50)
	public java.lang.String getOpenid(){
		return this.openid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  openid
	 */
	public void setOpenid(java.lang.String openid){
		this.openid = openid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  帖子区域
	 */
	@Column(name ="PART",nullable=true,length=20)
	public java.lang.String getPart(){
		return this.part;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  帖子区域
	 */
	public void setPart(java.lang.String part){
		this.part = part;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主题
	 */
	@Column(name ="TITLE",nullable=true,length=50)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发帖内容
	 */
	@Column(name ="CARDCONTENT",nullable=true,length=500)
	public java.lang.String getCardcontent(){
		return this.cardcontent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发帖内容
	 */
	public void setCardcontent(java.lang.String cardcontent){
		this.cardcontent = cardcontent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  排序
	 */
	@Column(name ="ORDERID",nullable=true,length=32)
	public java.lang.String getOrderid(){
		return this.orderid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  排序
	 */
	public void setOrderid(java.lang.String orderid){
		this.orderid = orderid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型
	 */
	@Column(name ="TYPE",nullable=true,length=32)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
}
