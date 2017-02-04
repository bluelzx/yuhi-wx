package weixin.idea.huodong.entity;

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
 * @Description: 投票选择项
 * @author onlineGenerator
 * @date 2016-06-29 16:07:06
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_vote_items", schema = "")
@SuppressWarnings("serial")
public class WeixinVoteItemsEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**所属投票活动*/
	@Excel(exportName="所属投票活动")
	private java.lang.String voteid;
	/**图片*/
	@Excel(exportName="图片")
	private java.lang.String src;
	/**名字*/
	@Excel(exportName="名字")
	private java.lang.String name;
	/**号码*/
	@Excel(exportName="号码")
	private java.lang.Integer orderid;
	/**票数*/
	@Excel(exportName="票数")
	private java.lang.String piaocount;
	
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
	 *@return: java.lang.String  所属投票活动
	 */
	@Column(name ="VOTEID",nullable=true,length=32)
	public java.lang.String getVoteid(){
		return this.voteid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属投票活动
	 */
	public void setVoteid(java.lang.String voteid){
		this.voteid = voteid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片
	 */
	@Column(name ="SRC",nullable=true,length=100)
	public java.lang.String getSrc(){
		return this.src;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片
	 */
	public void setSrc(java.lang.String src){
		this.src = src;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  名字
	 */
	@Column(name ="NAME",nullable=true,length=32)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名字
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  号码
	 */
	@Column(name ="ORDERID",nullable=true,length=5)
	public java.lang.Integer getOrderid(){
		return this.orderid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  号码
	 */
	public void setOrderid(java.lang.Integer orderid){
		this.orderid = orderid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  票数
	 */
	@Column(name ="PIAOCOUNT",nullable=true,length=32)
	public java.lang.String getPiaocount(){
		return this.piaocount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  票数
	 */
	public void setPiaocount(java.lang.String piaocount){
		this.piaocount = piaocount;
	}
}
