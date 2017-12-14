
package com.hansy.portal.action;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hansy.portal.common.BaseReslt;
import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.common.constant.UserCosntans;
import com.hansy.portal.common.utils.Config;
import com.hansy.portal.common.utils.CusAccessObjectUtil;
import com.hansy.portal.common.utils.MD5Util;
import com.hansy.portal.common.utils.Pager;
import com.hansy.portal.common.utils.SmsApiHelper;
import com.hansy.portal.common.utils.StringUtil;
import com.hansy.portal.common.utils.UUIDUtil;
import com.hansy.portal.filter.ValidateCodeServlet;
import com.hansy.portal.model.bo.LoginUser;
import com.hansy.portal.model.bo.MyCustomer;
import com.hansy.portal.model.bo.TUserBaseInfoBo;
import com.hansy.portal.model.bo.UserBasic;
import com.hansy.portal.model.vo.GoodsVo;
import com.hansy.portal.model.vo.Order;
import com.hansy.portal.model.vo.TBusBillVo;
import com.hansy.portal.model.vo.TBusCompleteBillVo;
import com.hansy.portal.model.vo.TBusPamentVo;
import com.hansy.portal.model.vo.TBusPamentVoS;
import com.hansy.portal.model.vo.TUserAddressVo;
import com.hansy.portal.model.vo.TUserBillVo;
import com.hansy.portal.model.vo.TUserLoginLog;
import com.hansy.portal.service.ITBusBillService;
import com.hansy.portal.service.ITBusCompleteBillService;
import com.hansy.portal.service.ITBusPamentService;
import com.hansy.portal.service.ITUserAddressService;
import com.hansy.portal.service.ITUserBaseInfoService;
import com.hansy.portal.service.ITUserBillService;
import com.hansy.portal.service.ITUserEnterInfoService;
import com.hansy.portal.service.ITUserGradeService;
import com.hansy.portal.service.ITUserLoginLogService;
import com.hansy.portal.service.ITUserPersonInfoService;

@Controller
@RequestMapping(value = "/user")
public class UserAction {
	@Resource
	private ITUserBaseInfoService baseInfoService;
	@Resource
	private ITUserPersonInfoService personInfoService;
	@Resource
	private ITUserEnterInfoService enterInfoService;
	@Resource
	private ITUserAddressService addressService;
	@Resource
	private ITUserGradeService gradeService;
	@Resource
	private ITBusBillService itBusBillService;
	@Resource
	private ITUserBillService itUserBillService;
	@Resource
	private ITUserLoginLogService itUserLoginLogService;
	@Resource
	private ITBusPamentService itBusPamentService;
	@Resource
	private ITBusCompleteBillService itBusCompleteBillService;
	@RequestMapping("toshoppinglogin")
	public ModelAndView toShoppingCar(String custNo,HttpServletRequest request){
		custNo = request.getParameter("custNo");
		TUserLoginLog tUserLoginLog = new TUserLoginLog();
		tUserLoginLog.setTableKey(UUIDUtil.getParseUUID());
		tUserLoginLog.setCustNo(custNo);
		tUserLoginLog.setLoginDate(new Date());
		tUserLoginLog.setLoginIp(CusAccessObjectUtil.getIpAddress(request));
		itUserLoginLogService.save(tUserLoginLog);
		String url=Config.getInstance().getValue("transActionUrl");
		url = url.replace("login", "loginShoopingCarback");
		return new ModelAndView("redirect:"+url+"?custNo="+custNo);
	};
	@RequestMapping("/getBillByOrderNo")
	@ResponseBody
	public BaseReslt<Pager<TBusBillVo>> getBillByOrderNo(String orderNo,String pageNo,HttpSession session,String startTime,String endTime){
		BaseReslt<Pager<TBusBillVo>> bReslt=new BaseReslt<>();
		Pager<TBusBillVo>pager = new Pager<TBusBillVo>();
		Map<String, String>map = new HashMap<>();
		String custNo=(String) session.getAttribute("custNo");
		map.put("orderNo", orderNo);
		map.put("pageNo", pageNo);
		map.put("custNo", custNo);
		if(""!=startTime&&null!=startTime){
			map.put("startTime", startTime);
		}
		if (""!=endTime&&null!=endTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			try {
				cd.setTime(sdf.parse(endTime));
				cd.add(Calendar.DATE, 1);// 增加一天
				map.put("endTime", sdf.format(cd.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		try {
			//默认每页8条数据
			pager.setPageNo(Integer.parseInt(pageNo == null ? "1" : pageNo));
			pager.setPageSize(8);
			Pager<TBusBillVo>pager2= itBusBillService.getBillByOrderNo(map, pager);
			bReslt.setSuccess(true);
			bReslt.setObj(pager2);
			return bReslt;
		} catch (Exception e) {
			bReslt.setSuccess(false);
			bReslt.setMsg("获取数据失败");
			return bReslt;
		}
	}
	@RequestMapping(value="/toBillManageMenu")
	public ModelAndView toBillManageMenu(){
		ModelAndView view=new ModelAndView("invoiceList");
		return view;
	}
		@RequestMapping(value="/toBillManage")
	public ModelAndView toBillManage(){
		ModelAndView view=new ModelAndView("invoiceManage");
		return view;
	}
	
	
	@RequestMapping(value="/toSelectSale")
	public ModelAndView toSelectSale(){
		ModelAndView view=new ModelAndView("selectSale");
		return view;
	}
	@RequestMapping(value="/toConsumeRank")
	public ModelAndView toConsumeRank(){
		ModelAndView view=new ModelAndView("consumeRank");
		return view;
	}
	@RequestMapping(value="/toProductRank")
	public ModelAndView toProductRank(){
		ModelAndView view=new ModelAndView("productRank");
		return view;
	}
	
	
	
	@RequestMapping(value="/toInvoice")
	public ModelAndView toInvoice(){
		ModelAndView view=new ModelAndView("invoice");
		return view;
	}
	
	@RequestMapping(value="/toInvoiceOperate")
	public ModelAndView toInvoiceOperate(){
		ModelAndView view=new ModelAndView("invoiceOperate");
		return view;
	}
	/**
	 * 跳转到修改密码界面
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月22日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping(value="/toAmend")
	public ModelAndView toAmend(){
		ModelAndView view=new ModelAndView("amend");
		return view;
	}
	
	/**
	 * 个人信息
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/toInformation")
	public ModelAndView toInformation(){
		ModelAndView view=new ModelAndView("information");
		return view;
	}
	
	/**
	 * 个人信息指定开票
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/toInformationOpenTicket")
	public ModelAndView toInformationOpenTicket(){
		String ifm = "123";
		String url=Config.getInstance().getValue("toOpenTicket");
		//ModelAndView view=new ModelAndView("information");
		return new ModelAndView("redirect:"+url+"?ifm="+ifm);
		
	}
	
	
	
	/**
	 * 个人基本信息
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/toBasic")
	public ModelAndView toBasic(){
		ModelAndView view=new ModelAndView("basic");
		return view;
	}
	
	/**
	 * 跳转到粉丝界面
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/toFans")
	public ModelAndView toFans(){
		ModelAndView view=new ModelAndView("fans");
		return view;
	}
	
	/**
	 * 跳转到我的客户界面
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/tomyCustomer")
	public ModelAndView tomyCustomer(){
		ModelAndView view=new ModelAndView("myCustomer");
		return view;
	}
	@RequestMapping("/productRank")
	@ResponseBody
	public BaseReslt<Object> productRank(Integer pageNo,HttpSession session,String Sort1,String Sort2,String query,String startTime,String endTime)throws Exception{
		if (pageNo==null) {
			pageNo=1;
		}
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		int pageSize=5;
		Pager pager=new Pager();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		Map<String, Object>map = new HashMap<>();
		map.put("query", query);
		if(""!=startTime&&null!=startTime){
			map.put("startTime", startTime);
		}
		if(Sort2!=null&&Sort2!=""){
			map.put("Sort2", Sort2);
		}
		if(Sort1!=null&&Sort1!=""){
			map.put("Sort1", Sort1);
		}
		map.put("orderStatus", "090005");
		if (""!=endTime&&null!=endTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(endTime));
			cd.add(Calendar.DATE, 1);//  增加一天 
			map.put("endTime", sdf.format(cd.getTime()));
		}
		Pager resultPage=baseInfoService.productRank(map,pager);
		bReslt.setObj(pager);
		return bReslt;
	}
	/**
	 * 
	 * @description: 用户消费排行
	 * @creator: 欧诗阳
	 * @createDate: 2017年9月5日 
	 * @modifier:
	 * @modifiedDate:
	 * @param pageNo
	 * @param session
	 * @param name
	 * @param Sort1
	 * @param Sort2
	 * @param query
	 * @return
	 */
	@RequestMapping("/consumeRank")
	@ResponseBody
	public BaseReslt<Object> consumeRank(Integer pageNo,HttpSession session,String Sort1,String query,String startTime,String endTime)throws Exception{
		if (pageNo==null) {
			pageNo=1;
		}
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		int pageSize=5;
		Pager pager=new Pager();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		Map<String, Object>map = new HashMap<>();
		map.put("userPhone", baseInfoVo.getUserPhone());
		map.put("query", query);
		if(""!=startTime&&null!=startTime){
			map.put("startTime", startTime);
		}
		map.put("Sort1", Sort1);
		if (""!=endTime&&null!=endTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(endTime));
			cd.add(Calendar.DATE, 1);//  增加一天 
			map.put("endTime", sdf.format(cd.getTime()));
		}
		Pager resultPage=baseInfoService.consumeRank(map,pager);
		bReslt.setObj(pager);
		return bReslt;
	}
	/**
	 * 
	 * @description: 获取我的销售员
	 * @creator: 欧诗阳
	 * @createDate: 2017年9月5日 
	 * @modifier:
	 * @modifiedDate:
	 * @param pageNo
	 * @param session
	 * @param name
	 * @param Sort1
	 * @param Sort2
	 * @param query
	 * @return
	 */
	@RequestMapping("/selectSale")
	@ResponseBody
	public BaseReslt<Object> selectSale(Integer pageNo,HttpSession session,String name,String Sort1,String Sort2,String query){
		if (pageNo==null) {
			pageNo=1;
		}
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		int pageSize=5;
		Pager pager=new Pager();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		Map<String, Object>map = new HashMap<>();
		map.put("userPhone", baseInfoVo.getUserPhone());
		map.put("query", query);
		
		Pager resultPage=baseInfoService.selectSale(map,pager);
		bReslt.setObj(pager);
		return bReslt;
	}
	/**
	 * 获取粉丝page
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/getFansPage")
	@ResponseBody
	public BaseReslt<Object> getFansPage(Integer pageNo,HttpSession session,String name,String Sort1,String Sort2,String query,String startTime,String endTime) throws Exception{
		if (pageNo==null) {
			pageNo=1;
		}
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		int pageSize=5;
		Pager pager=new Pager();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		Map<String, Object>map = new HashMap<>();
		map.put("userPhone", baseInfoVo.getUserPhone());
		map.put("query", query);
		if( ""!=startTime&&null!=startTime){
			map.put("startTime", startTime);
		}
		if (""!=endTime&&null!=endTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(endTime));
			cd.add(Calendar.DATE, 1);//  增加一天 
			map.put("endTime", sdf.format(cd.getTime()));
		}
		
		if(Sort2!=null&&Sort2!=""){
			map.put("Sort2", Sort2);
		}
		if(Sort1!=null&&Sort1!=""){
			map.put("Sort1", Sort1);
		}
		map.put("name", name);
		
		Pager resultPage=baseInfoService.getFansPage(map,pager);
		bReslt.setObj(pager);
		return bReslt;
	}
	
	
	/**
	 * 获取我的客户信息page
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/getMyCustomerPage")
	@ResponseBody
	public BaseReslt<Object> getMyCustomerPage(Integer pageNo,HttpSession session){
		if (pageNo==null) {
			pageNo=1;
		}
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		int pageSize=8;
		Pager pager=new Pager();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		Pager resultPage=baseInfoService.getMyCustomerPage(baseInfoVo.getCustNo(),pager);
		bReslt.setObj(pager);
		return bReslt;
	}
	@RequestMapping("/getMyCustomerPage1")
	@ResponseBody
	public BaseReslt<Pager<MyCustomer>> getMyCustomerPage1(String Sort1,String Sort2,
			Integer pageNo,HttpSession session,HttpServletRequest request,MyCustomer myCustomer){
		Map<String, String>map = new HashMap<>();
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		if(myCustomer.getName()!=null&&myCustomer.getName()!=""){
			map.put("name", myCustomer.getName());
		}
		if(myCustomer.getTotalAmt()!=null){
			map.put("totalAmt", myCustomer.getTotalAmt()+"");
		}
		if(myCustomer.getTotalNum()!=null&&myCustomer.getTotalNum()!=""){
			map.put("totalNum", myCustomer.getTotalNum());
		}
		if(Sort2!=null&&Sort2!=""){
			map.put("Sort2", Sort2);
		}
		if(Sort1!=null&&Sort1!=""){
			map.put("Sort1", Sort1);
		}
		map.put("custNo", baseInfoVo.getCustNo());
		BaseReslt<Pager<MyCustomer>> result = new BaseReslt<Pager<MyCustomer>>();
		Pager<MyCustomer> p=new Pager<MyCustomer>();
		p.setPageNo(pageNo == null ? 1 : pageNo);
		p.setPageSize(5);
		Pager<MyCustomer> pager = baseInfoService.getMyCustomerPage1(map, p);
		result.setObj(pager);
		return result;
	}
	/**
	 * 跳转到收货地址页面
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping(value="/toSite",method=RequestMethod.GET)
	public ModelAndView toSite(Integer pageNo){
		if (pageNo==null) {
			pageNo=1;
		}
		ModelAndView view=new ModelAndView("site");
		view.addObject("pageNo", pageNo);
		return view;
	}
	/**
	 * 获取收货地址分页
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月28日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/getSitePage")
	@ResponseBody
	public BaseReslt<Object> getSitePage(Integer pageNo,HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		int pageSize=5;
		Pager pager=new Pager();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		Pager resultPage=addressService.getPage(baseInfoVo.getCustNo(),pager);
		bReslt.setObj(pager);
		return bReslt;
	}
	
	/**
	 * 到编辑收货地址
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/toEditSite")
	public ModelAndView toEditSite(String tableKey){
		ModelAndView view=new ModelAndView("site_update");
		TUserAddressVo addressVo=addressService.getByTableKey(tableKey);
		view.addObject("addressVo", addressVo);
		return view;
	}
	
	/**
	 * 跳转编辑或者修改site页面
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/toAddSite")
	public ModelAndView toAddSite(){
		ModelAndView view=new ModelAndView("site_add");
		return view;
	}
	
	/**
	 * 修改密码
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月28日 
	 * @modifier:
	 * @modifiedDate:
	 * @param oldPwd
	 * @param newPwd
	 * @param session
	 * @return
	 */
	@RequestMapping("/updatePwd")
	@ResponseBody
	public BaseReslt<Object> updatePwd(String oldPwd,String newPwd,HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		LoginUser loginUser=new LoginUser();
		loginUser.setLoginName(baseInfoVo.getUserLoginName());
		loginUser.setLoginPwd(new MD5Util("MD5").encode(oldPwd));
		System.out.println(oldPwd);
		System.out.println(loginUser.getLoginName());
		System.out.println(loginUser.getLoginPwd());
		//判断旧密码是否正确
		BusinessMap<TUserBaseInfoBo> bMap=baseInfoService.findIsExist(loginUser);
		if (bMap.getInfoBody()==null) {
			bReslt.setSuccess(false);
			bReslt.setMsg("旧密码不正确");
			return bReslt;
		}
		baseInfoVo.setUserPwd(new MD5Util("MD5").encode(newPwd));
		BusinessMap<Object> bMap1=baseInfoService.update(baseInfoVo);
		if (bMap1.getIsSucc()==false) {
			bReslt.setMsg("修改密码失败");
			bReslt.setSuccess(false);
		}
		bReslt.setSuccess(true);
		return bReslt;
	}
	
	/**
	 * 根据用户类型获取用户基本信息
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月2日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/getUserBaic")
	@ResponseBody
	public BaseReslt<Object> getUserBaic(HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		//判断登录用户是企业用户还是个人用户
		if (baseInfoVo.getUserType().equals(UserCosntans.personalUser)) {
			UserBasic userBasic=baseInfoService.getPersonBasic(baseInfoVo.getUserNo());
			if (userBasic==null) {
				bReslt.setSuccess(false);
				bReslt.setMsg("获取基本信息失败，请重试");
				return bReslt;
			}
			bReslt.setSuccess(true);
			bReslt.setObj(userBasic);
		}else if(baseInfoVo.getUserType().equals(UserCosntans.enterUser)){
			UserBasic userBasic=baseInfoService.getEnterBasic(baseInfoVo.getUserNo());
			if (userBasic==null) {
				bReslt.setSuccess(false);
				bReslt.setMsg("获取基本信息失败，请重试");
				return bReslt;
			}
			bReslt.setSuccess(true);
			bReslt.setObj(userBasic);
		}else{
			UserBasic userBasic=baseInfoService.getSupplyBasic(baseInfoVo.getUserNo());
			if (userBasic==null) {
				bReslt.setSuccess(false);
				bReslt.setMsg("获取基本信息失败，请重试");
				return bReslt;
			}
			bReslt.setSuccess(true);
			bReslt.setObj(userBasic);
		}
		
		return bReslt;
	}
	
	/**
	 * 更新基本信息
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月2日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/updateUserBasic")
	@ResponseBody
	public BaseReslt<Object> updateUserBasic(TUserBaseInfoBo userBaseInfoVo,HttpSession session,String oldPhone){
		BaseReslt<Object> baseReslt=new BaseReslt<Object>();
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		//判断用户输入是否已经存在
		Map<String, Integer> result=baseInfoService.findIsEdit(userBaseInfoVo);
		if (result.get("phoneIsExist")!=null&&result.get("phoneIsExist")>0) {
			baseReslt.setSuccess(false);
			baseReslt.setMsg("电话号码已存在");
			return baseReslt;
		}
		if (result.get("emailIsExist")!=null&&result.get("emailIsExist")>0) {
			baseReslt.setSuccess(false);
			baseReslt.setMsg("邮箱已存在");
			return baseReslt;
		}
		if (result.get("userAliasIsExist")!=null&&result.get("userAliasIsExist")>0) {
			baseReslt.setSuccess(false);
			baseReslt.setMsg("昵称已存在");
			return baseReslt;
		}		
		if (userBaseInfoVo.getSuperUserNo()!=null&&userBaseInfoVo.getSuperUserNo()!="") {
			//判断用户输入的superUserNo是否和自己的userNo一样
			if (userBaseInfoVo.getSuperUserNo().equals(baseInfoVo.getUserNo())) {
				baseReslt.setSuccess(false);
				baseReslt.setMsg("不能输入自己的用户号");
				return baseReslt;
			}else{
				//判断推荐人账号是否存在
				Boolean result1=baseInfoService.findUserNoIsExistOther(userBaseInfoVo.getSuperUserNo(),baseInfoVo.getUserNo());
				if (result1==false) {
					baseReslt.setSuccess(false);
					baseReslt.setMsg("推荐人不存在");
					return baseReslt;
				}
			}
		}
		userBaseInfoVo.setUserLoginName(userBaseInfoVo.getUserAlias());
		Map<String, String>map = new HashMap<>();
		map.put("newPhone", String.valueOf(userBaseInfoVo.getUserPhone()));
		map.put("oldPhone", oldPhone);
		try {
			baseInfoService.update(userBaseInfoVo);
			baseInfoService.update1(map);
			baseReslt.setSuccess(true);
			baseReslt.setMsg("修改成功");
			return baseReslt;
		} catch (Exception e) {
			baseReslt.setSuccess(false);
			baseReslt.setMsg("修改失败");
			return baseReslt;
		}
	
	}
	/**
	 * 新增收货地址
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/addSite")
	@ResponseBody
	public BaseReslt<Object> addSite(TUserAddressVo addressVo,HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		//判断tableKey是否为空，不为空则为修改，为空则为新增
		if (addressVo.getTableKey()!=null) {
			//更新时间
			addressVo.setUpdateDate(new Date());
			BusinessMap<Object> bMap=addressService.update(addressVo);
			if (bMap.getIsSucc()==false) {
				bReslt.setMsg(bMap.getMsg());
				bReslt.setSuccess(false);
				return bReslt;
			}
			bReslt.setSuccess(true);
		}else{
			//主键
			addressVo.setTableKey(UUIDUtil.getParseUUID());
			//用户编号
			addressVo.setUserNo(baseInfoVo.getCustNo());
			//用户名称
			addressVo.setUserName(baseInfoVo.getUserName());
			//插入时间
			addressVo.setInsertDate(new Date());
			//默认地址标志
			addressVo.setAddressStatus(UserCosntans.defaultAddressNo);
			BusinessMap<Object> bMap=addressService.add(addressVo);
			if (bMap.getIsSucc()==false) {
				bReslt.setMsg(bMap.getMsg());
				bReslt.setSuccess(false);
			}
			bReslt.setSuccess(true);
		}
		return bReslt;
	}

	@RequestMapping("/updateAddress")
	@ResponseBody
	public BaseReslt<Object> updateAddress(TUserAddressVo addressVo, HttpSession session) {
		BaseReslt<Object> bReslt = new BaseReslt<Object>();
		// 判断tableKey是否为空，不为空则为修改，为空则为新增
		addressVo.setUpdateDate(new Date());
		BusinessMap<Object> bMap = addressService.update(addressVo);
		if (bMap.getIsSucc() == false) {
			bReslt.setMsg(bMap.getMsg());
			bReslt.setSuccess(false);
			return bReslt;
		} else {
			bReslt.setSuccess(true);
			return bReslt;
		}
	}
	
	/**
	 * 根据用户真实姓名与绑定手机号码重置密码，并且发送到用户绑定手机
	 * @param custName
	 * @param phone
	 * @param vno
	 * @return
	 */
	@RequestMapping("/getPwd")
	@ResponseBody
	public BaseReslt<Object> getPwd(String custName,String phone,String vno,HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		if(StringUtil.isEmpty(custName)){
			bReslt.setMsg("用户的真实姓名不能为空！");
			bReslt.setSuccess(false);
			return bReslt;
		}
		if(StringUtil.isEmpty(phone)){
			bReslt.setMsg("用户的绑定手机号码不能为空！");
			bReslt.setSuccess(false);
			return bReslt;
		}
		if(StringUtil.isEmpty(vno)){
			bReslt.setMsg("验证码不能为空！");
			bReslt.setSuccess(false);
			return bReslt;
		}
		String rand = (String)session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
		if(!vno.toUpperCase().equals(rand.toUpperCase())){
			bReslt.setMsg("验证码错误！");
			bReslt.setSuccess(false);
			return bReslt;
		}
		try {
			TUserBaseInfoBo vo = baseInfoService.getUserBaseInfoByCustName(custName, phone);
			if(vo == null||StringUtil.isEmpty(vo.getCustNo())){
				bReslt.setMsg("没有【"+ custName +"】这个用户！");
				bReslt.setSuccess(false);
				return bReslt;
			}
			if(!phone.equals(vo.getUserPhone()+"")){
				bReslt.setMsg("手机号码与用户【"+ custName +"】绑定的手机号码不匹配！");
				bReslt.setSuccess(false);
				return bReslt;
			}
			String pwd = "a"+(int)((Math.random()*9+1)*100000);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("custNo", vo.getCustNo());
			param.put("userPwd", new MD5Util("MD5").encode(pwd));
			BusinessMap<Object> bMap=baseInfoService.updateUserPwd(param);
			if (bMap.getIsSucc()==false) {
				bReslt.setMsg(bMap.getMsg());
				bReslt.setSuccess(false);
			}
			BusinessMap<Object> bMap2=SmsApiHelper.sendPwd(phone,pwd);
			if (bMap2.getIsSucc()==false) {
				bReslt.setMsg(bMap.getMsg());
				bReslt.setSuccess(false);
			}
			bReslt.setSuccess(true);
			return bReslt;
		} catch (Exception e) {
			bReslt.setSuccess(false);
			return bReslt;
		}
		
	}
	
	
	/**
	 * 删除收货地址
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @param tableKey
	 * @return
	 */
	@RequestMapping("/deleteSite")
	@ResponseBody
	public BaseReslt<Object> deleteSite(String tableKey){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		BusinessMap<Object> bMap=addressService.delete(tableKey);
		if (bMap.getIsSucc()==false) {
			bReslt.setMsg(bMap.getMsg());
			bReslt.setSuccess(false);
		}
		bReslt.setSuccess(true);
		bReslt.setMsg("删除收货地址成功");
		return bReslt;
	}
	
	/**
	 * 修改默认收货地址
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月6日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/defaultAddress")
	@ResponseBody
	public BaseReslt<Object> defaultAddress(String tableKey,HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		String custNo=((TUserBaseInfoBo)session.getAttribute("loginUser")).getCustNo();
		//修改默认地址
		addressService.updateStatus(tableKey,custNo);
		bReslt.setSuccess(true);
		return bReslt;
	}
	public static void main(String[] args) {
		System.out.println(new MD5Util("MD5").encode("a123456"));
		System.out.println(new Date().getTime());
	}
	
	@RequestMapping("/filterByMoneyOrNum")
	@ResponseBody
	public BaseReslt<Pager<Order>> filterByMoneyOrNum(String custNo,String pageNo,HttpSession session,String startTime,String endTime){
		BaseReslt<Pager<Order>> bReslt=new BaseReslt<>();
		Pager<Order>pager = new Pager<Order>();
		Map<String, String>map = new HashMap<>();
//		custNo=((TUserBaseInfoBo)session.getAttribute("loginUser")).getCustNo();
		map.put("custNo", custNo);
		map.put("pageNo", pageNo);
		map.put("Sort1", "1");
		map.put("Sort2", "0");
		if(""!=startTime&&null!=startTime){
			map.put("startTime", startTime);
		}
		if (""!=endTime&&null!=endTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			try {
				cd.setTime(sdf.parse(endTime));
				cd.add(Calendar.DATE, 1);// 增加一天
				map.put("endTime", sdf.format(cd.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		try {
			//默认每页8条数据
			pager.setPageNo(Integer.parseInt(pageNo == null ? "1" : pageNo));
			pager.setPageSize(8);
			Pager<Order>pager2= baseInfoService.filterByMoneyOrNum(map, pager);
			bReslt.setSuccess(true);
			bReslt.setObj(pager2);
			return bReslt;
		} catch (Exception e) {
			bReslt.setSuccess(false);
			bReslt.setMsg("获取数据失败");
			return bReslt;
		}
	}
	
	
	
	
	
	@RequestMapping("/adds")
	@ResponseBody
	public BaseReslt<Object> add(TUserBillVo TUserBillVo,HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		//根据custNo获取用户
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		TUserBillVo.setCustNo(baseInfoVo.getCustNo());
		BusinessMap<Object> bMap1=itUserBillService.save(TUserBillVo);
			if (bMap1.getIsSucc()==false) {
				bReslt.setMsg(bMap1.getMsg());
				bReslt.setSuccess(false);
				return bReslt;
			}
			bReslt.setSuccess(true);
			return bReslt;
		}
	
	@RequestMapping("/deletes")
	@ResponseBody
	public BaseReslt<Object> delete(TUserBillVo TUserBillVo,HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		//根据custNo获取用户
		String custNo = (String) session.getAttribute("custNo");
		BusinessMap<Object> bMap1=itUserBillService.delete(custNo);
			if (bMap1.getIsSucc()==false) {
				bReslt.setMsg(bMap1.getMsg());
				bReslt.setSuccess(false);
				return bReslt;
			}
			bReslt.setSuccess(true);
			return bReslt;
	}
	@RequestMapping("/updates")
	@ResponseBody
	public BaseReslt<Object> update(TUserBillVo TUserBillVo,HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		//根据custNo获取用户
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		TUserBillVo.setCustNo(baseInfoVo.getCustNo());
		BusinessMap<Object> bMap1=itUserBillService.update(TUserBillVo);
			if (bMap1.getIsSucc()==false) {
				bReslt.setMsg(bMap1.getMsg());
				bReslt.setSuccess(false);
				return bReslt;
			}
			bReslt.setSuccess(true);
			return bReslt;
	}
	
	
	@RequestMapping("/getByIds")
	@ResponseBody
	public BaseReslt<Object> getById(TUserBillVo TUserBillVo,HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		//根据custNo获取用户
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		BusinessMap<Object> bMap1=itUserBillService.getById(baseInfoVo.getCustNo());
			if (bMap1.getIsSucc()==false) {
				bReslt.setMsg(bMap1.getMsg());
				bReslt.setSuccess(false);
				return bReslt;
			}
			bReslt.setObj(bMap1.getInfoBody());
			bReslt.setSuccess(true);
			return bReslt;
	}
	
	//发票的条件查询
	@RequestMapping("/getBillPagePay")
	@ResponseBody
	public BaseReslt<Object> getBillPagePay(Integer pageNo,HttpSession session,String startTime,String endTime,String orderNo,String billStatus){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		int pageSize=5;
		Pager pager=new Pager();
		Map<String, String>map = new HashMap<>();
		map.put("billNo", orderNo);
		map.put("billStatus", billStatus);
		map.put("supplyNo", baseInfoVo.getCustNo());
		if(""!=startTime&&null!=startTime){
			map.put("startTime", startTime);
		}
		if (""!=endTime&&null!=endTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			try {
				cd.setTime(sdf.parse(endTime));
				cd.add(Calendar.DATE, 1);// 增加一天
				map.put("endTime", sdf.format(cd.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		try {
			Pager resultPage=itBusPamentService.getPage(map,pager);
			bReslt.setObj(pager);
			bReslt.setSuccess(true);
			return bReslt;
		} catch (Exception e) {
			bReslt.setSuccess(false);
			return bReslt;
		}
		
	}

	//点进去之后的查询
	@RequestMapping("/getBillCompleteBill")
	@ResponseBody
	public BaseReslt<Object> getBillCompleteBill(Integer pageNo,HttpSession session,String billNo){
		if(pageNo==null){
			pageNo=1;
		}
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		int pageSize=5;
		Pager pager=new Pager();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		Map<String, String>map = new HashMap<>();
		map.put("billNo", billNo);
		Map<String, Object>map1 = new HashMap<>();
		Map<String, Object>map2 = new HashMap<>();
		map2.put("billNo", billNo);
		try {
			BusinessMap<Object>bsmap=itBusCompleteBillService.getById(map);
			Pager resultPage=itBusCompleteBillService.getContactOrder(map2,pager);
			map1.put("completeBill", bsmap.getInfoBody());
			bReslt.setObj(resultPage);
			bReslt.setMap(map1);
			bReslt.setSuccess(true);
			return bReslt;
		} catch (Exception e) {
			bReslt.setSuccess(false);
			return bReslt;
		}
	}
		//查询关联发票
	@RequestMapping("/getContactOrder")
	@ResponseBody
	public BaseReslt<Object> getContactOrder(Integer pageNo,HttpSession session,String billNo)throws Exception{
			if (pageNo==null) {
				pageNo=1;
			}
			BaseReslt<Object> bReslt=new BaseReslt<Object>();
			int pageSize=5;
			Pager pager=new Pager();
			pager.setPageNo(pageNo);
			pager.setPageSize(pageSize);
			Map<String, Object>map = new HashMap<>();
			map.put("billNo", billNo);
			Pager resultPage=itBusCompleteBillService.getContactOrder(map,pager);
			bReslt.setObj(pager);
			return bReslt;
			}
	
	//开发票(第一步)
	@RequestMapping("/getOrderCustName")
	@ResponseBody
	public BaseReslt<Object> getOrderCustName(Integer pageNo,HttpSession session,String query)throws Exception{
		if (pageNo==null) {
			pageNo=1;
		}
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		int pageSize=5;
		Pager pager=new Pager();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		String custNo = baseInfoVo.getCustNo();
		Map<String, Object>map = new HashMap<>();
		map.put("supplyNo", custNo);
		if(""!=query&&null!=query)
		{
			map.put("query", query);
		}
		Pager resultPage=baseInfoService.getOrderCustName(map,pager);
		bReslt.setObj(resultPage);
		return bReslt;
	}
	//开发票(第二步)
		@RequestMapping("/getMyOrderNoBill")
		@ResponseBody
		public BaseReslt<Object> getMyOrderNoBill(Integer pageNo,HttpSession session,String startTime,String query,String endTime,String startAmt,String endAmt,String custNos)throws Exception{
			if (pageNo==null) {
				pageNo=1;
			}
			BaseReslt<Object> bReslt=new BaseReslt<Object>();
			TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
			String custNo = baseInfoVo.getCustNo();
			int pageSize=5;
			Pager pager=new Pager();
			pager.setPageNo(pageNo);
			pager.setPageSize(pageSize);
			Map<String, Object>map = new HashMap<>();
			map.put("query", query);
			map.put("supplyNo", custNo);
			map.put("startAmt", startAmt);
			map.put("endAmt", endAmt);
			map.put("custNos", custNos);
			if(""!=startTime&&null!=startTime){
				map.put("startTime", startTime);
			}
			map.put("orderStatus", "090005");
			if (""!=endTime&&null!=endTime) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cd = Calendar.getInstance();
				cd.setTime(sdf.parse(endTime));
				cd.add(Calendar.DATE, 1);//  增加一天 
				map.put("endTime", sdf.format(cd.getTime()));
			}
			session.setAttribute("mapCondition", map);
			Pager resultPage=baseInfoService.getMyOrderNoBill(map,pager);
			bReslt.setObj(resultPage);
			return bReslt;
		}
	
		
		
		//开发票(第三步)
		@RequestMapping("/getMyOrderNoBillDetail")
		@ResponseBody
		public BaseReslt<Object> getMyOrderNoBillDetail(Integer pageNo,HttpSession session,String custNos,String orderNo,String applyNo)throws Exception{
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
			if (pageNo==null) {
			pageNo=1;
			}
			int pageSize=5;
			Pager pager=new Pager();
			pager.setPageNo(pageNo);
			pager.setPageSize(pageSize);
			//发票单位的custNo查询出地址
			BusinessMap<Object> bMap1=itUserBillService.getById(applyNo);
			Map<String, Object>map =new HashMap<>();
			
			map.put("applyNo", applyNo);
			Pager resultPage=baseInfoService.getMyOrderNoBillDetail(map, pager);
			bReslt.setObj(resultPage);
			map.clear();
			map.put("address", bMap1.getInfoBody());
			bReslt.setMap(map);
			if (bMap1.getIsSucc()==false) {
			bReslt.setMsg(bMap1.getMsg());
			bReslt.setSuccess(false);
			return bReslt;
			}
			
			return bReslt;
	}
		
		//开发票第三步骤中保存发票的信息（注意保存的是订单与发票的关联关系，保存的不是最后的开票信息）
		@RequestMapping(value="/saveConditionBill",method=RequestMethod.POST)
		@ResponseBody
		public BaseReslt<Object> saveCondition(HttpSession session,String applyNo,String billMoney,String[] billNoArr)throws Exception{
			TBusCompleteBillVo tBusCompleteBillVo = new TBusCompleteBillVo();
			
			BaseReslt<Object> bReslt=new BaseReslt<Object>();
			//以逗号隔开的billNo
			StringBuffer billNoStr = new StringBuffer();
			if(billNoArr == null || billNoArr.length == 0){
				bReslt.setSuccess(false);
				bReslt.setMsg("没有填写发票编号");
				return bReslt;
			}
			billNoStr.append(billNoArr[0]);
			for (int i = 1; i < billNoArr.length; i++) {
				billNoStr.append(","+billNoArr[i]);
			}
			
			TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
			String supplyNo = baseInfoVo.getCustNo();
			
			tBusCompleteBillVo = itBusBillService.getInvoiceByApplyNo(applyNo);
			
			Date date = new Date();
			tBusCompleteBillVo.setBillMoney(Double.parseDouble(billMoney.substring(1)));
			tBusCompleteBillVo.setApplyNo(applyNo);
			tBusCompleteBillVo.setBillNo(billNoStr.toString());//以逗号隔开的billNo
			tBusCompleteBillVo.setInsertDate(date);//新增时间
			tBusCompleteBillVo.setBillDate(date);//开票时间
			tBusCompleteBillVo.setBillStatus("1");//未寄送
			tBusCompleteBillVo.setSupplyNo(supplyNo);//供方编号
			//存入bus complete bill表
			BusinessMap<Object> bMap1 = itBusCompleteBillService.save(tBusCompleteBillVo);
			if(!bMap1.getIsSucc()){
				bReslt.setSuccess(false);
				bReslt.setMsg(bMap1.getMsg());
				return bReslt;
			}
			//更改bus bill表状态为已开发票
			BusinessMap<Object> bMap2 = itBusBillService.updateBillStatus(applyNo);
			if(!bMap2.getIsSucc()){
				bReslt.setSuccess(false);
				bReslt.setMsg(bMap2.getMsg());
			}
			
			return bReslt;
		}
		
		//开发票第四步(获取用户的地址信息)
		@RequestMapping("/getByCust")
		@ResponseBody
		public BaseReslt<Object> getByCust(String custNos,HttpSession session){
			BaseReslt<Object> bReslt=new BaseReslt<Object>();
			//根据custNo获取用户
			BusinessMap<Object> bMap1=itUserBillService.getById(custNos);
				if (bMap1.getIsSucc()==false) {
					bReslt.setMsg(bMap1.getMsg());
					bReslt.setSuccess(false);
					return bReslt;
				}
				bReslt.setObj(bMap1.getInfoBody());
				session.setAttribute("TUserBillVo", bMap1.getInfoBody());
				bReslt.setSuccess(true);
				return bReslt;
		}
		//开发票第四步(寄送发票)
		@RequestMapping("/saveComplete")
		@ResponseBody
		public BaseReslt<Object> saveComplete(HttpSession session,String billNo,String logisticsName,String expressNumber) throws Exception{
			BaseReslt<Object> bReslt=new BaseReslt<Object>();
			
			TBusCompleteBillVo tBusCompleteBillVo = new TBusCompleteBillVo();
			tBusCompleteBillVo.setBillNo(billNo);
			tBusCompleteBillVo.setLogisticsName(logisticsName);//物流公司
			tBusCompleteBillVo.setExpressNumber(expressNumber);//物流编号
			tBusCompleteBillVo.setBillStatus("2");//已寄送
			BusinessMap<Object> bMap1 = itBusCompleteBillService.update(tBusCompleteBillVo);
			
			//更改bus order表状态为已开发票
			BusinessMap<Object> bMap3 = itBusBillService.updateBillStatusOfOrder(billNo);
			if(!bMap3.getIsSucc()){
				bReslt.setSuccess(false);
				bReslt.setMsg(bMap3.getMsg());
			}
/*			TUserBillVo tUserBillVo =(TUserBillVo)session.getAttribute("TUserBillVo");
			tBusCompleteBillVo.setBillReceivePhone(tUserBillVo.getBillReceivePhone());
			tBusCompleteBillVo.setBillReceiveAddress(tUserBillVo.getRegisterAddress());
			tBusCompleteBillVo.setBillReceiveName(tUserBillVo.getBillReceiveName());
			tBusCompleteBillVo.setBillReceiveMail(tUserBillVo.getBillReceiveMail());
*/			
			if (bMap1.getIsSucc()==false) {
				bReslt.setMsg(bMap1.getMsg());
				bReslt.setSuccess(false);
				return bReslt;
			}
			return bReslt;
		}
}

