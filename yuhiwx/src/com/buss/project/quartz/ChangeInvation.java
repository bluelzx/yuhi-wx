package com.buss.project.quartz;

import org.apache.log4j.Logger;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.buss.project.controller.OwnerController;
import com.yuhi.wechar.facade.ProjectInvitationFacade;


/**
 * 耗材增量统计
 * @author 李森林
 *
 */
@Component("changeInvation")
public class ChangeInvation{

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OwnerController.class);

	@Autowired
	private ProjectInvitationFacade projectInvitationService;
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void doJob(){
		try {
			int count=projectInvitationService.changeIncrement();
			logger.info("耗材使用增量本次更新了"+count+"条");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("耗材增量更新异常.....");
		}
	}

	
	
}