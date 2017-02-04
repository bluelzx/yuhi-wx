package junit.Qcode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import weixin.util.QCode.MatrixToImageWriter;
import weixin.util.QCode.QRCodeUtil;

import com.alibaba.fastjson.JSON;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class TestLogo {
	

	
	@Test
	public void martch(){
//			List<String> strlist=new ArrayList<String>();
//			strlist.add("$1001$&1002&-A8,l-(16)*1006*");
//			strlist.add("$1001$&1002&-A3-1,m-(5)*1014*");
//			strlist.add("$1001$&1002&-wz-()*1010*")
			Map<String, String> strlist=new HashMap<String, String>();
			strlist.put("name", "测试1");
			strlist.put("value", "测试2");
			String jsonString = JSON.toJSONString(strlist);
			System.out.println(jsonString);
	}
	
	/**
	 * 生产二维码
	 */
	@Test
	public void makePICbyTextNoImage(){
		 try {
				String content = "https://www.baidu.com";
				 String path = "E:/";
				 
				 MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
				 Map hints = new HashMap();
				 hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
				 BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
				 File file1 = new File(path,"01.jpg");
				 MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
			} catch (WriterException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
	/**
	 * 生产图片logo二维码
	 */
	@Test
public void MakeQcodeByimage(){
	try {
		String text = "https://www.baidu.com";  
		QRCodeUtil.encode(text, "e:/01.jpg", "e:/image/", true);
	} catch (Exception e) {
		e.printStackTrace();
	}  
}
	

}
