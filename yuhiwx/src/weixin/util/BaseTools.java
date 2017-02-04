package weixin.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class BaseTools {


	 /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
    public   static String getdate(){
    	return Calendar.getInstance().get(Calendar.YEAR)+""+(Calendar.getInstance().get(Calendar.MONTH)+1)+""+
    	Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
    
    public static String wirteFile(MultipartFile pictureFile){
			String pictureFile_name = pictureFile.getOriginalFilename();
			String newFileName = UUID.randomUUID().toString()+ pictureFile_name.substring(pictureFile_name.lastIndexOf("."));
			File uploadPic = new java.io.File("F:\\pic\\tmp\\" + newFileName); // 修改新名称
			if (!uploadPic.exists()) { // 判断文件路径是否存在
				uploadPic.mkdirs(); // 不存在即创建
			}
			try {
				pictureFile.transferTo(uploadPic);
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			} // 向磁盘写文件
		return newFileName;
    }
    

	public static String getDate(Date date) {
		return 	date==null?new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()):new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
	}
	
	public static String getNowDateWithChinese() {
		return new  SimpleDateFormat("yyyy年MM月dd日").format(new Date());
	}
	
	  /** 
     * 根据年 月 获取对应的月份 天数 
     * */  
    public static int getDaysByYearMonth(int year, int month) {  
          
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
	
}
