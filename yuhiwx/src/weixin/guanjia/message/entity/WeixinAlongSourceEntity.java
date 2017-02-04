package weixin.guanjia.message.entity;

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
 * @Description: weixin_along_source
 * @author onlineGenerator
 * @date 2016-06-18 12:38:20
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_along_source", schema = "")
@SuppressWarnings("serial")
public class WeixinAlongSourceEntity implements java.io.Serializable {
	/**mediaId*/
	@Excel(exportName="mediaId")
	private java.lang.String mediaId;
	/**url*/
	@Excel(exportName="url")
	private java.lang.String url;
	/**createDate*/
	@Excel(exportName="createDate")
	private java.util.Date createDate;
	/**createDate*/
	@Excel(exportName="type")
	private java.lang.String type;
	/**createDate*/
	@Excel(exportName="name")
	private java.lang.String name;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  mediaId
	 */
	@Id
	@Column(name ="MEDIA_ID",nullable=false,length=150)
	public java.lang.String getMediaId(){
		return this.mediaId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  mediaId
	 */
	public void setMediaId(java.lang.String mediaId){
		this.mediaId = mediaId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  url
	 */
	@Column(name ="URL",nullable=true,length=300)
	public java.lang.String getUrl(){
		return this.url;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  url
	 */
	public void setUrl(java.lang.String url){
		this.url = url;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  createDate
	 */
	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  createDate
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	@Column(name ="type",nullable=true)
	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}
	@Column(name ="name",nullable=true)
	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	
	
}
