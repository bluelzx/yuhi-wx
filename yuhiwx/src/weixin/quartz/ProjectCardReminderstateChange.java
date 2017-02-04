package weixin.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yuhi.wechar.facade.ProjectCardFacade;


/**
 * @author 李森林
 *
 */
@Component("ProjectCardReminderstateChange")
public class ProjectCardReminderstateChange{
	@Autowired
	private ProjectCardFacade projectCardService;
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void doJob(){
//		projectCardService.updateBySqlString("update project_card set reminderstate='0'");
	}
	
}