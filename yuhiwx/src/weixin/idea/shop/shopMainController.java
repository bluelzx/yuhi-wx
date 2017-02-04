package weixin.idea.shop;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import weixin.guanjia.shop.goods.items.entity.WeixinShopGoodsEntity;
import weixin.guanjia.shop.goods.items.service.WeixinShopGoodsServiceI;
import weixin.guanjia.shop.goods.shopcar.entity.WeixinShopShopcarEntity;
import weixin.guanjia.shop.goods.shopcar.service.WeixinShopShopcarServiceI;
import weixin.guanjia.subrice.entity.SyvscribeusersEntity;
import weixin.guanjia.subrice.service.SyvscribeusersServiceI;
import weixin.util.UserUtil;
import weixin.util.WeixinRedirectUrl;

@Scope("prototype")
@Controller
@RequestMapping("/shopMainController")
public class shopMainController {
	
	@Autowired
	private WeixinShopGoodsServiceI weixinShopGoodsService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private SyvscribeusersServiceI syvscribeusersServiceI;
	@Autowired
	private WeixinShopShopcarServiceI weixinShopShopcarService;
	/**
	 * 个人中心
	 * @param request
	 * @return 
	 */
	@RequestMapping(params = "showUserHome")
	public String showUserHome(HttpServletRequest request){
		//UserUtil.saveToSession(request, "oFsDqwi4j7GDdLU2_onXkeqVMCW0", OPENID);
		String openid = UserUtil.getToSessionString(request, WeixinRedirectUrl.OPENID);
		if(!StringUtil.isEmpty(openid)){
			SyvscribeusersEntity Entity = syvscribeusersServiceI.get(SyvscribeusersEntity.class, openid);
			request.setAttribute("user", Entity);
			return "weixin/idea/shop/userHome";
		}
		return ""; //未进行区域授权
	}
	
	/**
	 * 查看商品验证用户是否绑定
	 * @param request
	 * @param respnose
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params = "getGoodsList")
	public String getGoodsList(HttpServletRequest request, HttpServletResponse respnose,@RequestParam(required=true) Integer type) throws IOException{
		String code=request.getParameter("code");
		if(code!=null){ 
			String openid = syvscribeusersServiceI.getSendOpenid(request, code);
			SyvscribeusersEntity syvscribeusersEntity = syvscribeusersServiceI.get(SyvscribeusersEntity.class, openid);
			if(syvscribeusersEntity==null||StringUtil.isEmpty(syvscribeusersEntity.getPartid())){ 						//用户未绑定地区
				return "redirect:"+WeixinRedirectUrl.REDIRECTUSERBINGURL;
			}
			UserUtil.saveToSession(request, openid, WeixinRedirectUrl.OPENID);
			//			wsGoodsService
			//绑定地址： https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx23093572b097a654&redirect_uri=http%3a%2f%2f08e215b5.ngrok.io%2fyuhiwx%2fsyvscribeusersController.do%3fuserlocal&response_type=code&scope=snsapi_base#wechat_redirect
			//用户验证成功跳转到商城列表页面
			UserUtil.saveToSession(request,syvscribeusersEntity.getPartid(), WeixinRedirectUrl.PART);
			//大于即是商城小于即是论坛
			return type>1000?"redirect:shopMainController.do?getGoodsListforpage":"redirect:forumController.do?showSendCards";
				//	"&part="+syvscribeusersEntity.getPartid();
		}else{
			//授权失败
			respnose.getWriter().write("授权失败");
			return "404";
		}
	}
	/**
	 * 查看商品
	 * @param part
	 * @param pageindex
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "getGoodsListforpage")
	public String getGoodsListforpage(@RequestParam(defaultValue="1",required=false) Integer pageindex,
			HttpServletRequest request) throws UnsupportedEncodingException{
			String search=request.getParameter("search");
			String part = UserUtil.getToSessionString(request, WeixinRedirectUrl.PART);
			StringBuffer sqlurl=new StringBuffer("select create_date,title,title_img,simple_descs,price,goods.id "+
					"from weixin_shop_goods goods,weixin_shop_business bus where goods.seller_id=bus.id and  bus.part='"+part+"'");
			if(!StringUtil.isEmpty(search)){
				sqlurl.append(" and title like '%"+search+"%'");
			}
			Map<String, Object> map=new HashMap<String, Object>();
			HqlQuery hqlQuery=new HqlQuery(sqlurl.toString(), map);
			hqlQuery.setCurPage(pageindex);
			PageList pagelist=	weixinShopGoodsService.getPageListBySql(hqlQuery, false);
			request.setAttribute("goodslist", pagelist);
			request.setAttribute("part", part);
			return "weixin/idea/shop/shopitemlist";
		
	}
	
	/**
	 * 查詢詳細信息
	 * @param id
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "getGoodsforDetail")
	public String getGoodsforDetail(@RequestParam(required=true) String id,
			HttpServletRequest request) throws UnsupportedEncodingException{
		WeixinShopGoodsEntity weixinShopGoodsEntity = weixinShopGoodsService.get(WeixinShopGoodsEntity.class, id);
		request.setAttribute("goods", weixinShopGoodsEntity);
		return "weixin/idea/shop/itemsDetails";
	}
	
	/**
	 * 添加购物车
	 * @param id
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "AddshopCar")
	public String AddshopCar( WeixinShopShopcarEntity entity ,
			HttpServletRequest request) throws UnsupportedEncodingException{
		//加入购物车
		String productId = request.getParameter("productId");
		String buyAmount = request.getParameter("buyAmount");
		String wsGoodsPrice= request.getParameter("wsGoodsPrice");
		String openid = UserUtil.getToSessionString(request, WeixinRedirectUrl.OPENID);
		if(!StringUtil.isEmpty(openid)){
			entity=weixinShopShopcarService.isExeitShopcar(productId,openid);
			if(entity!=null){  //判断是否存在
				entity.setCreateDate(new Date());
				entity.setWsGoodsCount((entity.getWsGoodsCount()+Integer.valueOf(buyAmount)));
			}else{
				if(!StringUtil.isEmpty(productId)&&!StringUtil.isEmpty(buyAmount)){
					if(entity==null)entity=new WeixinShopShopcarEntity();
					entity.setCreateDate(new Date());
					entity.setWsGoodsCount(Integer.valueOf(buyAmount));
					entity.setWsGoodsId(productId);
					entity.setWsGoodsPrice(wsGoodsPrice);
					entity.setUserid(openid);
				}
			}
			weixinShopShopcarService.saveOrUpdate(entity);
			return "redirect:shopMainController.do?goshopCar"; 
		}
		
		
		return "";//授权失败  ，直接访问
	}
	/**
	 * 跳转购物车
	 * @param id
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "goshopCar")
	public String goshopCar(HttpServletRequest request,@RequestParam(defaultValue="1",required=false) Integer pageindex) throws UnsupportedEncodingException{
		//测试
//		UserUtil.saveToSession(request, "oFsDqwi4j7GDdLU2_onXkeqVMCW0", OPENID);
		String openid = UserUtil.getToSessionString(request, WeixinRedirectUrl.OPENID);
		if(!StringUtil.isEmpty(openid)){
			//根据用户id查询购物车
			List<Map<String, Object>> result =weixinShopShopcarService
			.findForJdbc("select goods.price,goods.title,goods.title_img,goods.simple_descs,shopcar.create_date,shopcar.ws_goods_count "+
						 "from weixin_shop_shopcar  shopcar"+
						 " LEFT JOIN weixin_shop_goods goods on shopcar.ws_goods_id=goods.id"+
						 " where userid='"+openid+"'",1,10);
			request.setAttribute("shopcarlist", result);
			return "weixin/idea/shop/shopcar";
		}
		return "";//授权失败  ，直接访问
	}
	/**
	 * 添加订单
	 * @param id
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "AddshopOrder")
	public String AddshopOrder( WeixinShopGoodsEntity entity ,
			HttpServletRequest request) throws UnsupportedEncodingException{
		String openid = UserUtil.getToSessionString(request, WeixinRedirectUrl.OPENID);
		if(!StringUtil.isEmpty(openid)){
			//根据用户id添加订单
			
			//赋予订单状态
			
			
			
			
			
			return "redirect:shopMainController.do?goshopOrder";
		}
		return "";//授权失败  ，直接访问
	}
	/**
	 * 跳转购物车
	 * @param id
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "goshopOrder")
	public String goshopOrder(HttpServletRequest request){
		String openid = UserUtil.getToSessionString(request, WeixinRedirectUrl.OPENID);
		if(!StringUtil.isEmpty(openid)){
			//根据用户查询订单
			
			return "weixin/idea/shop/UserShopOrder";
		}
		return ""; //未进行区域授权	
		
	}
}
