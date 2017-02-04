package SpringJunitTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.util.ExcelReadUtil;
import org.jeecgframework.core.util.ExcelUtil;
import org.junit.Test;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath*:spring-*.xml"})
public class importPartDesc {

//	@Autowired
//	private ProjectOwnermesServiceI projectOwnermesService;
	@Test
	public	void TestimportPartDesc() throws Exception {
		List<String[]> excelToArrayList = ExcelReadUtil.
				excelToArrayList("E:\\0610000758500120161124135106.xls", 1);
		String sbh="440604758314273";
		String sbmc="广东元海资产物业投资管理有限公司";
		String zzrq="2015-05-01至2015-05-30";
		ExcelUtil exu=new ExcelUtil();
		
		int num=0;
		for (String[] str : excelToArrayList) {
			if(num<2){
				num++;
				continue;	
			}
			String n1=str[1];
			String n2=str[5];
			String n3=str[3];
			String n4=str[4];
			String n5=str[7];
			String n6=str[9];
			String n7=str[11];
			String n8=str[7];
			String n9="技术服务费";
			String n10=str[6];
			String lsh=str[12];

			System.out.println("*************************************************************");
			System.out.println("识别号："+sbh+"\t流水号："+lsh);
			System.out.println("纳税人名称："+sbmc+"\t起止日期："+zzrq);
			System.out.println();
			System.out.print(n1+"|"+n2+"|"+n3+"|"+n4+"|"+n5+"|"+n6+"|"+n7+"|"+n8+"|"+n9+"|"+n10+"|");
			System.out.println("*************************************************************");
			
			Map<String,Object> dataMap=new HashMap<String, Object>();
			dataMap.put("A2", n1);
			
			
		}
	
	}
	
	
	
	
}
