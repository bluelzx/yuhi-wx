package weixin.guanjia.shop.goods.shopcar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: weixin_shop_shopcar
 * @author onlineGenerator
 * @date 2016-06-13 12:51:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_shop_shopcar", schema = "")
@SuppressWarnings("serial")
public class WeixinShopShopcarEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**商品id*/
	@Excel(exportName="商品id")
	private java.lang.String wsGoodsId;
	/**修改日期*/
	@Excel(exportName="修改日期")
	private java.util.Date createDate;
	/**单价*/
	@Excel(exportName="单价")
	private java.lang.String wsGoodsPrice;
	/**数量*/
	@Excel(exportName="数量")
	private java.lang.Integer wsGoodsCount;
	/**买家名称*/
	@Excel(exportName="买家名称")
	private java.lang.String userid;
	
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
	 *@return: java.lang.String  商品id
	 */
	@Column(name ="WS_GOODS_ID",nullable=true,length=50)
	public java.lang.String getWsGoodsId(){
		return this.wsGoodsId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品id
	 */
	public void setWsGoodsId(java.lang.String wsGoodsId){
		this.wsGoodsId = wsGoodsId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改日期
	 */
	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单价
	 */
	@Column(name ="WS_GOODS_PRICE",nullable=true,length=50)
	public java.lang.String getWsGoodsPrice(){
		return this.wsGoodsPrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单价
	 */
	public void setWsGoodsPrice(java.lang.String wsGoodsPrice){
		this.wsGoodsPrice = wsGoodsPrice;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  数量
	 */
	@Column(name ="WS_GOODS_COUNT",nullable=true,length=10)
	public java.lang.Integer getWsGoodsCount(){
		return this.wsGoodsCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  数量
	 */
	public void setWsGoodsCount(java.lang.Integer wsGoodsCount){
		this.wsGoodsCount = wsGoodsCount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  买家名称
	 */
	@Column(name ="USERID",nullable=true,length=32)
	public java.lang.String getUserid(){
		return this.userid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  买家名称
	 */
	public void setUserid(java.lang.String userid){
		this.userid = userid;
	}
}
