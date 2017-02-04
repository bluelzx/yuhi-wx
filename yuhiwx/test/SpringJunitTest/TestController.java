package SpringJunitTest;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import weixin.guanjia.subrice.entity.SyvscribeusersEntity;
import weixin.guanjia.subrice.service.SyvscribeusersServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-*.xml"})
public class TestController {

	@Resource
	private SyvscribeusersServiceI syvscribeusersServiceI;
	
	@Test
	public	void test() {

		SyvscribeusersEntity entity=new SyvscribeusersEntity();
		entity.setCity("asd");
		entity.setCountry("asd");
		entity.setGroupid("asd");
		entity.setHeadimgurl("asd");
		entity.setId("asd");
		entity.setLanguage("asd");
		entity.setNickname("asd");
		entity.setProvince("asd");
		entity.setRemark("asd");
		entity.setSex("asd");
		entity.setSubscribe("asd");
		entity.setSubscribetime("asd");
		syvscribeusersServiceI.save(entity);
	}
	
//	@Resource
//	public ArticleMapper articleMapper;
//	
//	@Test
//	public void getArticle(){
//		ArticleExample example=new ArticleExample();
//		List<Article> selectByExample = articleMapper.selectByExample(example,1,10);
//		System.out.println(selectByExample.size());
//	}
	
	
	
}
