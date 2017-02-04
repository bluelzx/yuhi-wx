package com.buss.project.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelIgnore;

/**   
 * @Title: Entity
 * @Description: project_card
 * @author onlineGenerator
 * @date 2016-09-26 11:22:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project_card", schema = "")
@SuppressWarnings("serial")
public class ProjectCardEntity implements java.io.Serializable {
	/**id*/
	@Excel(exportName="工程单号",exportFieldWidth=18,orderNum="0")
	private java.lang.String id;
	/**创建人openid*/
	@Excel(exportName="客户",exportFieldWidth=10)
	private java.lang.String ownerid;
	/**创建日期*/
	@Excel(exportName="创建日期",exportFieldWidth=10)
	private java.util.Date createTime;
	/**办理时间*/
	@Excel(exportName="办理时间")
	private java.util.Date handleTime;
	/**分配时间*/
	@Excel(exportName="分配时间")
	private java.util.Date distributionTime;
	/**接单时间*/
	@Excel(exportName="接单时间")
	private java.util.Date getTime;
	/**结束时间*/
	@Excel(exportName="结束时间")
	private java.util.Date finishedTime;
	/**办理人openid*/
	@Excel(exportName="办理人")
	private java.lang.String distributionOpenid;
	/**是否可用*/
	@Excel(exportName="是否可用")
	private java.lang.String isok;
	/**工单内容*/
	@Excel(exportName="工单内容",exportFieldWidth=70)
	private java.lang.String content;
	/**状态*/
	@Excel(exportName="状态",orderNum="3")
	private java.lang.String state;
	/**物业方查验情况意见*/
	@ExcelIgnore
	private java.lang.Integer propertyobjectionsid;
	/**保修方验收意见*/
	@ExcelIgnore
	private java.lang.Integer ownerobjectionid;
	/**评分等级*/
	@ExcelIgnore
	private java.lang.String successlevel;
	/**物业方验收意见*/
	@ExcelIgnore
	private java.lang.Integer propertyobjectionfid;
	/**公司验收意见*/
	@ExcelIgnore
	private java.lang.Integer companyobjectionfid;
	/**备注*/
	@Excel(exportName="备注",exportFieldWidth=70)
	private java.lang.String descs;
	/**版本号*/
	@ExcelIgnore
	private java.lang.Integer edition;
	@Excel(exportName="可入户开始时间")
	private Date starttime;
	@Excel(exportName="可入户结束时间")
	private Date endtime;
	
	@Excel(exportName="手机",exportFieldWidth=12)
	private java.lang.String phone;
	@Excel(exportName="其他联系方式",exportFieldWidth=20)
	private java.lang.String otherContact;
	@ExcelIgnore
	private java.lang.Integer invitationid;
	@ExcelIgnore
	private java.lang.Integer reminderstate;
	@ExcelIgnore
	private java.lang.Integer throughAudit;
	@ExcelIgnore
	private java.lang.String part;
	@ExcelIgnore
	private java.lang.Integer isPay;
	@ExcelIgnore
	private java.lang.Integer pctype;
	@ExcelIgnore
	private java.lang.String pcserver;
	@ExcelIgnore
	private java.lang.String publicplace;
	@Excel(exportName="房号")
	private java.lang.String roomid;
	
	
	public ProjectCardEntity(String id, String ownerid, Date createTime,
			Date handleTime, Date distributionTime, Date getTime,
			Date finishedTime, String distributionOpenid, String isok,
			String content, String state, Integer propertyobjectionsid,
			Integer ownerobjectionid, String successlevel,
			Integer propertyobjectionfid, Integer companyobjectionfid,
			String descs, Integer edition, Date starttime, Date endtime,
			String phone, String otherContact, Integer invitationid,
			Integer reminderstate, Integer throughAudit, String part,
			Integer isPay,Integer pctype,String pcserver,String roomid) {
		super();
		this.id = id;
		this.ownerid = ownerid;
		this.createTime = createTime;
		this.handleTime = handleTime;
		this.distributionTime = distributionTime;
		this.getTime = getTime;
		this.finishedTime = finishedTime;
		this.distributionOpenid = distributionOpenid;
		this.isok = isok;
		this.content = content;
		this.state = state;
		this.propertyobjectionsid = propertyobjectionsid;
		this.ownerobjectionid = ownerobjectionid;
		this.successlevel = successlevel;
		this.propertyobjectionfid = propertyobjectionfid;
		this.companyobjectionfid = companyobjectionfid;
		this.descs = descs;
		this.edition = edition;
		this.starttime = starttime;
		this.endtime = endtime;
		this.phone = phone;
		this.otherContact = otherContact;
		this.invitationid = invitationid;
		this.reminderstate = reminderstate;
		this.throughAudit = throughAudit;
		this.part = part;
		this.isPay = isPay;
		this.pctype = pctype;
		this.pcserver=pcserver;
		this.roomid=roomid;
	}

	public ProjectCardEntity() {
		super();
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	@Id
//	@GeneratedValue(generator = "paymentableGenerator")
//	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
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
	 *@return: java.lang.String  创建人openid
	 */
	@Column(name ="OWNERID",nullable=true,length=50)
	public java.lang.String getOwnerid(){
		return this.ownerid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人openid
	 */
	public void setOwnerid(java.lang.String ownerid){
		this.ownerid = ownerid;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  办理时间
	 */
	@Column(name ="HANDLE_TIME",nullable=true)
	public java.util.Date getHandleTime(){
		return this.handleTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  办理时间
	 */
	public void setHandleTime(java.util.Date handleTime){
		this.handleTime = handleTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  分配时间
	 */
	@Column(name ="DISTRIBUTION_TIME",nullable=true)
	public java.util.Date getDistributionTime(){
		return this.distributionTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  分配时间
	 */
	public void setDistributionTime(java.util.Date distributionTime){
		this.distributionTime = distributionTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  接单时间
	 */
	@Column(name ="GET_TIME",nullable=true)
	public java.util.Date getGetTime(){
		return this.getTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  接单时间
	 */
	public void setGetTime(java.util.Date getTime){
		this.getTime = getTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	@Column(name ="FINISHED_TIME",nullable=true)
	public java.util.Date getFinishedTime(){
		return this.finishedTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setFinishedTime(java.util.Date finishedTime){
		this.finishedTime = finishedTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  办理人openid
	 */
	@Column(name ="DISTRIBUTION_OPENID",nullable=true,length=32)
	public java.lang.String getDistributionOpenid(){
		return this.distributionOpenid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  办理人openid
	 */
	public void setDistributionOpenid(java.lang.String distributionOpenid){
		this.distributionOpenid = distributionOpenid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否可用
	 */
	@Column(name ="ISOK",nullable=true,length=5)
	public java.lang.String getIsok(){
		return this.isok;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否可用
	 */
	public void setIsok(java.lang.String isok){
		this.isok = isok;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工单内容
	 */
	@Column(name ="CONTENT",nullable=true)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工单内容
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATE",nullable=true,length=32)
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  物业方查验情况意见
	 */
	@Column(name ="PROPERTYOBJECTIONSID",nullable=true,length=10)
	public java.lang.Integer getPropertyobjectionsid(){
		return this.propertyobjectionsid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  物业方查验情况意见
	 */
	public void setPropertyobjectionsid(java.lang.Integer propertyobjectionsid){
		this.propertyobjectionsid = propertyobjectionsid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  保修方验收意见
	 */
	@Column(name ="OWNEROBJECTIONID",nullable=true,length=10)
	public java.lang.Integer getOwnerobjectionid(){
		return this.ownerobjectionid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  保修方验收意见
	 */
	public void setOwnerobjectionid(java.lang.Integer ownerobjectionid){
		this.ownerobjectionid = ownerobjectionid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  评分等级
	 */
	@Column(name ="SUCCESSLEVEL",nullable=true,length=5)
	public java.lang.String getSuccesslevel(){
		return this.successlevel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评分等级
	 */
	public void setSuccesslevel(java.lang.String successlevel){
		this.successlevel = successlevel;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  物业方验收意见
	 */
	@Column(name ="PROPERTYOBJECTIONFID",nullable=true,length=10)
	public java.lang.Integer getPropertyobjectionfid(){
		return this.propertyobjectionfid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  物业方验收意见
	 */
	public void setPropertyobjectionfid(java.lang.Integer propertyobjectionfid){
		this.propertyobjectionfid = propertyobjectionfid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  公司验收意见
	 */
	@Column(name ="COMPANYOBJECTIONFID",nullable=true,length=10)
	public java.lang.Integer getCompanyobjectionfid(){
		return this.companyobjectionfid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  公司验收意见
	 */
	public void setCompanyobjectionfid(java.lang.Integer companyobjectionfid){
		this.companyobjectionfid = companyobjectionfid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="DESCS",nullable=true)
	public java.lang.String getDescs(){
		return this.descs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setDescs(java.lang.String descs){
		this.descs = descs;
	}

	public java.lang.Integer getEdition() {
		return edition;
	}
	@Column(name ="EDITION",nullable=true)
	public void setEdition(java.lang.Integer edition) {
		this.edition = edition;
	}
	@Column(name ="STARTTIME",nullable=true)
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	@Column(name ="ENDTIME",nullable=true)
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	@Column(name ="phone",nullable=true)
	public java.lang.String getPhone() {
		return phone;
	}
	
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	@Column(name ="otherContact",nullable=true)
	public java.lang.String getOtherContact() {
		return otherContact;
	}

	public void setOtherContact(java.lang.String otherContact) {
		this.otherContact = otherContact;
	}
	
	@Column(name ="invitationid",nullable=true)
	public java.lang.Integer getInvitationid() {
		return invitationid;
	}

	public void setInvitationid(java.lang.Integer invitationid) {
		this.invitationid = invitationid;
	}

	@Column(name ="reminderstate",nullable=true)
	public java.lang.Integer getReminderstate() {
		return reminderstate;
	}

	public void setReminderstate(java.lang.Integer reminderstate) {
		this.reminderstate = reminderstate;
	}
	@Column(name ="throughAudit",nullable=true)
	public java.lang.Integer getThroughAudit() {
		return throughAudit;
	}

	public void setThroughAudit(java.lang.Integer throughAudit) {
		this.throughAudit = throughAudit;
	}
	@Column(name ="part",nullable=true)
	public java.lang.String getPart() {
		return part;
	}
	
	public void setPart(java.lang.String part) {
		this.part = part;
	}
	@Column(name ="isPay",nullable=true)
	public java.lang.Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(java.lang.Integer isPay) {
		this.isPay = isPay;
	}
	@Column(name ="pctype",nullable=true)
	public java.lang.Integer getPctype() {
		return pctype;
	}

	public void setPctype(java.lang.Integer pctype) {
		this.pctype = pctype;
	}
	@Column(name ="pcserver",nullable=true)
	public java.lang.String getPcserver() {
		return pcserver;
	}

	public void setPcserver(java.lang.String pcserver) {
		this.pcserver = pcserver;
	}
	
	@Column(name ="publicplace",nullable=true)
	public java.lang.String getPublicplace() {
		return publicplace;
	}

	public void setPublicplace(java.lang.String publicplace) {
		this.publicplace = publicplace;
	}
	
	@Column(name ="roomid",nullable=true)
	public java.lang.String getRoomid() {
		return roomid;
	}

	public void setRoomid(java.lang.String roomid) {
		this.roomid = roomid;
	}
	//处理状态可视化
	public static void changeState(ProjectCardEntity e){
		switch (e.getState()) {
		case "1":
			e.setState("待接单");
			break;
		case "2":
			e.setState("待办理");
			break;
		case "3":
			e.setState("办理中");
			break;
		case "4":
			e.setState("办理结束(待评价)");
			break;
		case "5":
			e.setState("待回访");
			break;
		case "6":
			e.setState("办理完成");
			break;
		}
	}
	//处理状态可视化
	public static void changeisok(ProjectCardEntity e){
		switch (e.getIsok()) {
		case "1":
			e.setIsok("可用");
			break;
		default:
			e.setIsok("禁用");
			break;
		}
	}
}
