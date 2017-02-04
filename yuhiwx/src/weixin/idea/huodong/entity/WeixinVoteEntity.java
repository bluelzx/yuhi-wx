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
 * @Description: 投票表
 * @author onlineGenerator
 * @date 2016-06-29 16:06:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_vote", schema = "")
@SuppressWarnings("serial")
public class WeixinVoteEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	@Excel(exportName="创建日期")
	private java.util.Date createDate;
	/**累计参与人数*/
	@Excel(exportName="累计参与人数")
	private java.lang.Integer totalperson;
	/**活动说明*/
	@Excel(exportName="活动说明")
	private java.lang.String explains;
	/**累计总票数*/
	@Excel(exportName="累计总票数")
	private java.lang.String totalpicket;
	/**投票标题*/
	@Excel(exportName="投票标题")
	private java.lang.String title;
	/**创建人id*/
	@Excel(exportName="创建人id")
	private java.lang.String accountid;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  累计参与人数
	 */
	@Column(name ="TOTALPERSON",nullable=true,length=5)
	public java.lang.Integer getTotalperson(){
		return this.totalperson;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  累计参与人数
	 */
	public void setTotalperson(java.lang.Integer totalperson){
		this.totalperson = totalperson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动说明
	 */
	@Column(name ="EXPLAINS",nullable=true,length=32)
	public java.lang.String getExplains(){
		return this.explains;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动说明
	 */
	public void setExplains(java.lang.String explains){
		this.explains = explains;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  累计总票数
	 */
	@Column(name ="TOTALPICKET",nullable=true,length=32)
	public java.lang.String getTotalpicket(){
		return this.totalpicket;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  累计总票数
	 */
	public void setTotalpicket(java.lang.String totalpicket){
		this.totalpicket = totalpicket;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  投票标题
	 */
	@Column(name ="TITLE",nullable=true,length=32)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  投票标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	@Column(name ="accountid",nullable=true,length=40)
	public java.lang.String getAccountid() {
		return accountid;
	}

	public void setAccountid(java.lang.String accountid) {
		this.accountid = accountid;
	}
	
	
	
}
