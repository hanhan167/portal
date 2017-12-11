package com.hansy.portal.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement.Else;
import com.hansy.portal.common.BaseReslt;
import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.common.constant.UserCosntans;
import com.hansy.portal.common.utils.Config;
import com.hansy.portal.common.utils.CusAccessObjectUtil;
import com.hansy.portal.common.utils.MD5Util;
import com.hansy.portal.common.utils.SmsApiHelper;
import com.hansy.portal.common.utils.StringUtil;
import com.hansy.portal.common.utils.UUIDUtil;
import com.hansy.portal.model.bo.LoginUser;
import com.hansy.portal.model.bo.TUserBaseInfoBo;
import com.hansy.portal.model.vo.SysLibrary;
import com.hansy.portal.model.vo.TFlowTaskInfoVo;
import com.hansy.portal.model.vo.TFlowTaskLogVo;
import com.hansy.portal.model.vo.TUserApplyVo;
import com.hansy.portal.model.vo.TUserBaseInfoVo;
import com.hansy.portal.model.vo.TUserEnterInfoVo;
import com.hansy.portal.model.vo.TUserGradeVo;
import com.hansy.portal.model.vo.TUserLoginLog;
import com.hansy.portal.model.vo.TUserPersonInfoVo;
import com.hansy.portal.model.vo.TUserSupplyInfoVo;
import com.hansy.portal.service.ISysLibraryService;
import com.hansy.portal.service.ITUserApplyService;
import com.hansy.portal.service.ITUserBaseInfoService;
import com.hansy.portal.service.ITUserEnterInfoService;
import com.hansy.portal.service.ITUserGradeService;
import com.hansy.portal.service.ITUserLoginLogService;
import com.hansy.portal.service.ITUserPersonInfoService;


@Controller
@RequestMapping(value = "/sys")
public class SystemAction {

	@Resource
	private ITUserBaseInfoService baseInfoService;
	@Resource
	private ITUserPersonInfoService personInfoService;
	@Resource
	private ITUserEnterInfoService enterInfoService;
	@Resource
	private ITUserGradeService userGradeService;
	@Resource
	private ITUserApplyService userApplyService;
	@Resource
	private ISysLibraryService sysLibraryService;
	@Resource
	private ITUserLoginLogService itUserLoginLogService;
	
	@RequestMapping(value = "login", method=RequestMethod.POST)
	@ResponseBody
	public BaseReslt<Object> login(LoginUser user,HttpSession session,HttpServletRequest request){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		if (user.getLoginName()==null||user.getLoginName().equals("")) {
			bReslt.setSuccess(false);
			bReslt.setMsg("登录名不能为空");
			return bReslt;
		}
		if (user.getLoginPwd()==null||user.getLoginPwd().equals("")) {
			bReslt.setSuccess(false);
			bReslt.setMsg("密码不能为空");
			return bReslt;
		}
		user.setLoginPwd(new MD5Util("MD5").encode(user.getLoginPwd()));
		BusinessMap<TUserBaseInfoBo> bMap=baseInfoService.findIsExist(user);
		//判断是否能够从数据库查询匹配的用户
		if (!bMap.getIsSucc()) {
			bReslt.setSuccess(false);
			bReslt.setMsg("登录失败请刷新重试");
			return bReslt;
		}else{
			if (bMap.getInfoBody()==null) {
				bReslt.setSuccess(false);
				bReslt.setMsg("用户名或者密码错误");
				return bReslt;
			}else if(bMap.getInfoBody().getRegisterStatus().equals(UserCosntans.registerStatusApply)){
				bReslt.setSuccess(false);
				bReslt.setMsg("用户注册申请正在审核中，请稍后重试");
				return bReslt;
			}else if(bMap.getInfoBody().getRegisterStatus().equals(UserCosntans.registerStatusFail)){
				bReslt.setSuccess(false);
				bReslt.setMsg("用户注册申请失败！");
				return bReslt;
			}else if(""==bMap.getInfoBody().getUserStatus()||null==bMap.getInfoBody().getUserStatus()||bMap.getInfoBody().getUserStatus().equals(UserCosntans.userStatusUsed)){
				bReslt.setSuccess(false);
				bReslt.setMsg("该用户状态未启用或禁用，请联系后台管理员！");
				return bReslt;
			}else{
				session.setAttribute("loginUser", bMap.getInfoBody());
				session.setAttribute("custNo", bMap.getInfoBody().getCustNo());
				TUserLoginLog tUserLoginLog = new TUserLoginLog();
				tUserLoginLog.setTableKey(UUIDUtil.getParseUUID());
				tUserLoginLog.setCustNo(bMap.getInfoBody().getCustNo());
				tUserLoginLog.setLoginDate(new Date());
				tUserLoginLog.setLoginIp(CusAccessObjectUtil.getIpAddress(request));
				BusinessMap<Object>businessMap = itUserLoginLogService.save(tUserLoginLog);
				if(!businessMap.getIsSucc()){
					bReslt.setSuccess(false);
					bReslt.setMsg("登录时间有误，请重新登录");
					return bReslt;
				}
				bReslt.setSuccess(true);
			}
		}
		return bReslt;
	}
	
	/**
	 * 跳转到用户登录页面
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月21日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping(value = "/toUserPage")
	public ModelAndView toUserPage(){
		 ModelAndView view = new ModelAndView("login_personage");
		 return view;
	}
	
	/**
	 * 跳转到员工登录页面
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月21日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping(value = "/toStaffPage")
	public String toStaffPage(){
//		 ModelAndView view = new ModelAndView("login_company");
//		 return view;
		String cutterUrl=Config.getInstance().getValue("cutterUrl");
		return "redirect:"+cutterUrl;
	}
	
	/**
	 * 判断是否登录
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月21日 
	 * @modifier:
	 * @modifiedDate:
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/isLogin")
	@ResponseBody
	public BaseReslt<Object> isLogin(HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		if (session.getAttribute("loginUser")!=null) {
			TUserBaseInfoBo bo = (TUserBaseInfoBo)session.getAttribute("loginUser");
			Map<String, String> param=new HashMap<String,String>();
			param.put("custNo", bo.getCustNo());
			Integer count = baseInfoService.getLoginUserOrderCount(bo.getCustNo());
			bReslt.setObj(session.getAttribute("loginUser"));
			bReslt.setMsg(count+"");
			bReslt.setSuccess(true);
		}else{
			bReslt.setSuccess(false);
		}
		return bReslt;
	}
	
	/**
	 * 注销登录
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "loginOut", method=RequestMethod.GET)
	public String loginOut(HttpSession session){
		 session.removeAttribute("loginUser");
		 return "redirect:/index.jsp";
	}
	
	/**
	 * 忘记密码页面
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月28日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/toForget")
	public ModelAndView toForget(){
		ModelAndView view = new ModelAndView("forget");
		return view;
	}
	
	/**
	 * 跳转到个人用户注册
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月22日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping(value = "/toRegisterPerson")
	public ModelAndView toRegisterPerson(){
		 ModelAndView view = new ModelAndView("personage");
		 return view;
	}
	
	/**
	 * 跳转到企业注册界面
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月22日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping(value="/toRegisterCompany")
	public ModelAndView toRegisterCompany(){
		ModelAndView view=new ModelAndView("company");
		return view;
	}
	/**
	 * 获取手机短信验证码
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月1日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/smsApi")
	@ResponseBody
	public BaseReslt<Object> smsApi(String userPhone,HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		TUserBaseInfoVo baseInfoVo=new TUserBaseInfoVo();
		Map<String, Object> verMap=new HashMap<String, Object>();
		baseInfoVo.setUserPhone(Long.parseLong(userPhone));
		//判断手机号码是否已经绑定
		Map<String, Integer> result=baseInfoService.findIsRegister(baseInfoVo);
		if (result.get("phoneIsExist")==1) {
			bReslt.setSuccess(false);
			bReslt.setMsg("手机号码已经绑定，请更换手机号码");
			return bReslt;
		}
		//调用短信接口
		BusinessMap<Object> bMap=SmsApiHelper.sendSms(userPhone);
		if (bMap.getIsSucc()==false) {
			bReslt.setSuccess(false);
			bReslt.setMsg("获取验证码失败，请重试");
			return bReslt;
		}
		//把验证码和获取时间封装成map
		verMap.put("verificationCode", bMap.getInfoBody());
		verMap.put("getTime", new Date());
		//把map存入session
		session.setAttribute("verMap", verMap);
		
		//把第一步手机存入session
		session.setAttribute("registPhone", userPhone);
		bReslt.setSuccess(true);
		bReslt.setMsg("获取验证码成功");
		return bReslt;
	}
	
	/**
	 * 验证码验证(注册第一步)
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月1日 
	 * @modifier:
	 * @modifiedDate:
	 * @param valCode
	 * @return
	 */
	@RequestMapping("/smsValidate")
	@ResponseBody
	public BaseReslt<Object> smsValidate(String valCode,String userPhone,HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		Map<String, Object> verMap=(Map<String, Object>) session.getAttribute("verMap");
		if (verMap==null) {
			bReslt.setSuccess(false);
			bReslt.setMsg("您还未获取验证码");
			return bReslt;
		}
		//判断验证码时间是否过期
		if ((new Date().getTime()-((Date)verMap.get("getTime")).getTime())>60000*10) {
			bReslt.setSuccess(false);
			bReslt.setMsg("验证码过期，请重新获取");
			return bReslt;
		}
		
		//判断验证码是否正确
		if (valCode.equals(verMap.get("verificationCode"))) {
			bReslt.setSuccess(true);
		}else {
			bReslt.setSuccess(false);
			bReslt.setMsg("验证码错误");
			return bReslt;
		}
		
		//判断验证码和手机号是否匹配
		if (!userPhone.equals(session.getAttribute("registPhone"))) {
			bReslt.setSuccess(false);
			bReslt.setMsg("手机号和验证码不匹配");
			return bReslt;
		}
		TUserBaseInfoVo baseInfoVo=new TUserBaseInfoVo();
		baseInfoVo.setUserPhone(Long.parseLong(userPhone));
		session.removeAttribute("verMap");
		session.removeAttribute("registPhone");
		
		//把用户注册第一步存入session
		session.setAttribute("rgFirst", baseInfoVo);
		bReslt.setSuccess(true);
		return bReslt;
	}
	
	/**
	 * 注册第二步
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月1日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping(value="/registerSecond",method=RequestMethod.POST)
	@ResponseBody
	public BaseReslt<Object> userRegisterSecond(TUserBaseInfoVo baseInfoVo,@RequestParam("superUserNo") String superUserNo,HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		if (session.getAttribute("rgFirst")==null) {
			 bReslt.setSuccess(false);
			 bReslt.setMsg("请先完成第一步信息填写");
			 return bReslt;
		}
		Map<String, Integer> result=baseInfoService.findIsRegister(baseInfoVo);
		if (result.get("emailIsExist")!=null&&result.get("emailIsExist")>0) {
			bReslt.setSuccess(false);
			bReslt.setMsg("邮箱已存在");
			return bReslt;
		}
		if (result.get("userAliasIsExist")!=null&&result.get("userAliasIsExist")>0) {
			bReslt.setSuccess(false);
			bReslt.setMsg("昵称已存在");
			return bReslt;
		}
		if (!StringUtil.isEmpty(superUserNo)) {
			//判断推荐人账号是否存在
			Boolean result1=baseInfoService.findUserIsExist(superUserNo);
			if (result1==false) {
				 bReslt.setSuccess(false);
				 bReslt.setMsg("推荐人不存在");
				 return bReslt;
			}
		}
		baseInfoVo.setUserPhone(((TUserBaseInfoVo)session.getAttribute("rgFirst")).getUserPhone());
		//把注册第二步信息存入session
		session.setAttribute("rgSecond", baseInfoVo);
		bReslt.setSuccess(true);
		return bReslt;
	}
	
	/**
	 * 个人注册第三步
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月1日 
	 * @modifier:
	 * @modifiedDate:
	 * @param baseInfoVo
	 * @param session
	 * @return
	 */
	@RequestMapping("/userRegisterThird")
	@ResponseBody
	public BaseReslt<Object> userRegisterThird(TUserPersonInfoVo personInfoVo,String userName,HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		TUserBaseInfoVo baseInfoVo=(TUserBaseInfoVo)session.getAttribute("rgSecond");
		if (baseInfoVo==null) {
			 bReslt.setSuccess(false);
			 bReslt.setMsg("请先完成第二步信息填写");
			 return bReslt;
		}
		
		//客户号32位uuid
		String custNo=UUIDUtil.getParseUUID();
		//用户编号,根据时间生成字符串如20170227232234
		String userNo="GR"+new Date().getTime();
		baseInfoVo.setUserNo(userNo);
		//登录名
		baseInfoVo.setUserLoginName(baseInfoVo.getUserAlias());
		//用户名
		baseInfoVo.setUserName(userName);
		//用户类型
		baseInfoVo.setUserType(UserCosntans.personalUser);
		//客户编号
		baseInfoVo.setCustNo(custNo);
		//客户名称
		baseInfoVo.setCustName(userName);
		//注册状态
		baseInfoVo.setRegisterStatus(UserCosntans.registerStatusSucc);
		//注册时间
		baseInfoVo.setInsertDate(new Date());
		baseInfoVo.setUserPwd(new MD5Util("MD5").encode(baseInfoVo.getUserPwd()));
		baseInfoVo.setUserStatus(UserCosntans.userStatusUseful);
		//个人信息表
		personInfoVo.setCustName(baseInfoVo.getUserName());
		personInfoVo.setCustNo(custNo);
		personInfoVo.setInsertDate(new Date());
		personInfoVo.setUserEmail(baseInfoVo.getUserEmail());
		personInfoVo.setUserPhone(baseInfoVo.getUserPhone().toString());
		personInfoVo.setUserType(UserCosntans.personalUser);
		
		//等级表
		TUserGradeVo userGradeVo=new TUserGradeVo();
		userGradeVo.setCustNo(custNo);
		userGradeVo.setCustName(userName);
		userGradeVo.setInsertDate(new Date());
		userGradeVo.setUserType(UserCosntans.personalUser);
		//默认为等级一
		userGradeVo.setGradeNo(UserCosntans.userGradeNoFirst);
		
		//保存基本表
		baseInfoService.insert(baseInfoVo);
		//保存用户表
		personInfoService.insert(personInfoVo);
		//保存等级表
		userGradeService.insert(userGradeVo);
		
		//清除session中保存的第一第二步信息
		session.removeAttribute("rgFirst");
		session.removeAttribute("rgSecond");
		bReslt.setSuccess(true);
		bReslt.setMsg("注册成功");
		return bReslt;
	}
	
	/**
	 * 企业注册第三步
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月1日 
	 * @modifier:
	 * @modifiedDate:
	 * @param baseInfoVo
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/comRegisterThird",method=RequestMethod.POST)
	@ResponseBody
	public BaseReslt<Object> comRegisterThird(TUserEnterInfoVo enterInfoVo,String certBeginDtP,String certEndDtP,HttpSession session){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		TUserBaseInfoVo baseInfoVo=(TUserBaseInfoVo)session.getAttribute("rgSecond");
		if (baseInfoVo==null) {
			 bReslt.setSuccess(false);
			 bReslt.setMsg("请先完成第二步信息填写");
			 return bReslt;
		}
		if (session.getAttribute("orgMap")==null) {
			bReslt.setSuccess(false);
			 bReslt.setMsg("请先上传营业执照");
			 return bReslt;
		}
		if (session.getAttribute("certMap")==null) {
			bReslt.setSuccess(false);
			bReslt.setMsg("请先上传身份证照");
			return bReslt;
		}
		//客户号32位uuid
		String custNo=UUIDUtil.getParseUUID();
		//用户编号,根据时间生成字符串如20170227232234
		String userNo="QY"+new Date().getTime();
		baseInfoVo.setUserNo(userNo);
		//登录名
		baseInfoVo.setUserLoginName(baseInfoVo.getUserAlias());
		//用户类型
		baseInfoVo.setUserType(UserCosntans.enterUser);
		//客户编号
		baseInfoVo.setCustNo(custNo);
		//客户名称
		baseInfoVo.setCustName(baseInfoVo.getUserName());
		//注册状态
		baseInfoVo.setRegisterStatus(UserCosntans.registerStatusApply);
		//注册时间
		baseInfoVo.setInsertDate(new Date());
		baseInfoVo.setUserPwd(new MD5Util("MD5").encode(baseInfoVo.getUserPwd()));
		baseInfoVo.setUserStatus(UserCosntans.userStatusUseful);
		//企业信息表
		enterInfoVo.setCustName(baseInfoVo.getUserName());
		enterInfoVo.setCustNo(custNo);
		enterInfoVo.setInsertDate(new Date());
		SimpleDateFormat sFormat=new SimpleDateFormat("YYYY-mm-dd");
		try {
			enterInfoVo.setCertBeginDt(sFormat.parse(certBeginDtP));
			enterInfoVo.setCertEndDt(sFormat.parse(certEndDtP));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//等级表
		TUserGradeVo userGradeVo=new TUserGradeVo();
		userGradeVo.setCustNo(custNo);
		userGradeVo.setCustName(baseInfoVo.getUserName());
		userGradeVo.setInsertDate(new Date());
		userGradeVo.setUserType(UserCosntans.enterUser);
		//默认为等级一
		userGradeVo.setGradeNo(UserCosntans.userGradeNoFirst);
		
		//文件保存表(营业执照)
		SysLibrary sysLibary=new SysLibrary();
		String fileName=(String) ((Map<String, Object>) session.getAttribute("orgMap")).get("fileName");		//文件名
		Long libSize=(Long) ((Map<String, Object>) session.getAttribute("orgMap")).get("libSize");				//文件大小
		if (fileName==null||fileName.equals("")) {
			bReslt.setSuccess(false);
			bReslt.setMsg("请先上传营业执照");
			return bReslt;
		}
		String fName=fileName.substring(0,fileName.indexOf('.'));
		String libExtName=fileName.substring(fileName.indexOf('.'),fileName.length());//文件后缀
		String libTitle=baseInfoVo.getCustName()+"_"+sf.format(new Date())+"营业执照";	//标题
		String libDirectory=Config.getInstance().getValue("upload.file.path")+sf.format(new Date())+"/";//文件保存路径
		sysLibary.setLibId(UUIDUtil.getParseUUID());					//主键
		sysLibary.setLibCreateTime(new Date()); 						//创建日期
		sysLibary.setLibName(fName); 								    //文件名
		sysLibary.setLibTitle(libTitle); 								//标题
		sysLibary.setLibDirectory(libDirectory); 						//路径
		sysLibary.setState(1); 											//状态
		sysLibary.setLibExtName(libExtName);
		sysLibary.setLibOwnerName("portal注册");							
		sysLibary.setLibOwnerId("portal注册");
		sysLibary.setMenuId(custNo);									//客户编号
		sysLibary.setLibSize(libSize); 									//文件大小
		
		//文件保存表(身份证照)
		SysLibrary sysLibary1=new SysLibrary();
		String fileName1=(String) ((Map<String, Object>) session.getAttribute("orgMap")).get("fileName");		//文件名
		Long libSize1=(Long) ((Map<String, Object>) session.getAttribute("orgMap")).get("libSize");				//文件大小
		String fName1=fileName1.substring(0,fileName1.indexOf('.'));
		String libExtName1=fileName1.substring(fileName1.indexOf('.'),fileName1.length());//文件后缀
		String libTitle1=baseInfoVo.getCustName()+"_"+sf.format(new Date())+"营业执照";	//标题
		String libDirectory1=Config.getInstance().getValue("upload.file.path")+sf.format(new Date())+"/";//文件保存路径
		sysLibary1.setLibId(UUIDUtil.getParseUUID());					//主键
		sysLibary1.setLibCreateTime(new Date()); 						//创建日期
		sysLibary1.setLibName(fName1); 								    //文件名
		sysLibary1.setLibTitle(libTitle1); 								//标题
		sysLibary1.setLibDirectory(libDirectory1); 						//路径
		sysLibary1.setState(1); 											//状态
		sysLibary1.setLibExtName(libExtName1);
		sysLibary1.setLibOwnerName("portal注册");							
		sysLibary1.setLibOwnerId("portal注册");
		sysLibary1.setMenuId(custNo);									//客户编号
		sysLibary1.setLibSize(libSize1); 								//文件大小
		List<SysLibrary> paramLib=new ArrayList<>();
		paramLib.add(sysLibary);
		paramLib.add(sysLibary1);
		
		//企业用户注册申请id
		String applyNo=UUIDUtil.getParseUUID();
		//企业用户申请表
		TUserApplyVo userApplyVo=new TUserApplyVo();
		userApplyVo.setApplyNo(applyNo);					//主键
		userApplyVo.setCustNo(custNo);						//用户主键
		userApplyVo.setCustName(baseInfoVo.getUserName());	
		userApplyVo.setUserType(UserCosntans.enterUser);	//用户类型
		userApplyVo.setCertNo(enterInfoVo.getRegNo());      //证件号
		userApplyVo.setApplyCustNo("etl");
		userApplyVo.setApplyCustName("portal系统自动");
		userApplyVo.setRegDate(new Date());
		
		//任务流主键
		String taskNo=UUIDUtil.getParseUUID();
		String taskDesc="portal注册发起[<span >"+baseInfoVo.getUserName()+"</span>]企业用户的注册申请流程！]流程于"+new Date().toLocaleString();
		//任务流发起表
		TFlowTaskInfoVo fTaskInfoVo=new TFlowTaskInfoVo();
		fTaskInfoVo.setTaskNo(taskNo); 					//主键
		fTaskInfoVo.setFlowNo("USER_REGISTER_FLOW"); 
		fTaskInfoVo.setBusiKey(custNo); 				//客户编号
		fTaskInfoVo.setTaskDesc(taskDesc); 					//描述
		fTaskInfoVo.setInsertUser("etl");   			//发起人
		fTaskInfoVo.setInsertUserName("portal系统自动"); 
		fTaskInfoVo.setInsertTime(new Date());
		fTaskInfoVo.setTaskState("dealing");
		fTaskInfoVo.setBusiKey1(applyNo);
		
		//任务流日志表1
		TFlowTaskLogVo fTaskLogVo1=new TFlowTaskLogVo();
		fTaskLogVo1.setTableKey(UUIDUtil.getParseUUID());//主键
		fTaskLogVo1.setTaskNo(taskNo); 					 //任务流主键
		fTaskLogVo1.setRectName("rect1");
		fTaskLogVo1.setRectState("dealed");
		fTaskLogVo1.setRectOrder(1);
		fTaskLogVo1.setDealUser("etl");
		fTaskLogVo1.setDealUserName("portal系统自动");
		fTaskLogVo1.setFinishTime(new Date());
		fTaskLogVo1.setRectType("start");
		
		//任务流日志表2
		TFlowTaskLogVo fTaskLogVo2=new TFlowTaskLogVo();
		fTaskLogVo2.setTableKey(UUIDUtil.getParseUUID());
		fTaskLogVo2.setTaskNo(taskNo);
		fTaskLogVo2.setRectName("rect3");
		fTaskLogVo2.setRectState("notTake");
		fTaskLogVo2.setRectOrder(2);
		fTaskLogVo2.setDealRole("7EE5FF22FFFFFFE60D10028E8649C825");
		fTaskLogVo2.setRectType("task");
		fTaskLogVo2.setDealPage("/jsp/flow/custRegister/userEnterApprove.jsp");
		List<TFlowTaskLogVo> param=new ArrayList<>();
		param.add(fTaskLogVo1);
		param.add(fTaskLogVo2);
		
		//保存基本表
		baseInfoService.insert(baseInfoVo);
		//保存企业表
		enterInfoService.insert(enterInfoVo);
		//保存等级表
		userGradeService.insert(userGradeVo);
		//保存文件文件表
		sysLibraryService.insert(paramLib);
		//保存注册申请表
		userApplyService.insert(userApplyVo);
		//保存工作流表
		userApplyService.insertFlowInfo(fTaskInfoVo);
		//保存工作流日志
		userApplyService.insertFlowLog(param);
		
		//清除session中保存的第一第二步信息
		session.removeAttribute("rgFirst");
		session.removeAttribute("rgSecond");
		bReslt.setSuccess(true);
		bReslt.setMsg("注册申请提交成功");
		return bReslt;
	}
	/**
	 * 展示商户供方信息表
	 * @description: TODO
	 * @creator: ou
	 * @createDate: 2017年7月17日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/userSupplyInfoQuery")
	@ResponseBody
	public BaseReslt<Object> userSupplyInfoQuery(HttpServletRequest request,HttpServletResponse response,String custName){
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		Map<String, Object>map = new HashMap<>();
		map.put("custName", custName);
		try {
			List<TUserSupplyInfoVo> list = baseInfoService.selectUserSupplyInfoQuery(map);
			if (list.size()==0) {
				bReslt.setSuccess(false);
				bReslt.setMsg("获取供方信息失败，请重试");
				return bReslt;
			}
			bReslt.setSuccess(true);
			bReslt.setObj(list);
			return bReslt;
		} catch (Exception e) {
			bReslt.setSuccess(false);
			return bReslt;
		}
	
	}
	@RequestMapping("/getUserSupplyDetail")
	@ResponseBody
	public BaseReslt<Object> getUserSupplyDetail(HttpServletRequest request,HttpServletResponse response){
		String custNo = request.getParameter("custNo");
		BaseReslt<Object> bReslt=new BaseReslt<Object>();
		try {
			TUserSupplyInfoVo tUserSupplyInfoVo= baseInfoService.getUserSupplyDetail(custNo);
			if (tUserSupplyInfoVo==null) {
				bReslt.setSuccess(false);
				bReslt.setMsg("获取供方信息失败，请重试");
				return bReslt;
			}
			bReslt.setSuccess(true);
			bReslt.setObj(tUserSupplyInfoVo);
			return bReslt;
		} catch (Exception e) {
			bReslt.setSuccess(false);
			return bReslt;
		}
	
	}
	/**
	 * 企业用户营业执照保存
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月23日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/uploadImgOrg")
	@ResponseBody
	public BaseReslt<Object> uploadImgOrg(@RequestParam("fileData") MultipartFile file,HttpServletRequest request,HttpSession session){
		BaseReslt<Object> baseReslt=new BaseReslt<Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String fileUrl=Config.getInstance().getValue("upload.file.path");
		String fileOriName=file.getOriginalFilename();//真实名称
		String libExtName=fileOriName.substring(fileOriName.indexOf('.'),fileOriName.length());//文件后缀
		Map<String, Object> map=new HashMap<>();
		//使用uuid作为文件名防止文件名相同被覆盖
		String fileName=UUIDUtil.getParseUUID()+libExtName;
		//拼接保存路径如：/home/cutter/files/2017-03-29/
		String imgUrl=fileUrl+sf.format(new Date()).toString()+"/"+fileName;
		//通过工具类保存文件
		try {
			FileUtils.writeByteArrayToFile(new File(imgUrl), file.getBytes());
			/*response.setContentType("image/png");
			response.getOutputStream().write(file.getBytes());*/
			map.put("fileName", fileOriName);
			map.put("libSize", file.getSize());
			session.setAttribute("orgMap", map);
			baseReslt.setObj(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baseReslt;
	}
	
	/**
	 * 企业用户法人省份证照
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月23日 
	 * @modifier:
	 * @modifiedDate:
	 * @return
	 */
	@RequestMapping("/uploadImgCert")
	@ResponseBody
	public BaseReslt<Object> uploadImgCert(@RequestParam("fileData") MultipartFile file,HttpServletRequest request,HttpSession session){
		BaseReslt<Object> baseReslt=new BaseReslt<Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String fileUrl=Config.getInstance().getValue("upload.file.path");
		String fileOriName=file.getOriginalFilename();//真实名称
		String libExtName=fileOriName.substring(fileOriName.indexOf('.'),fileOriName.length());//文件后缀
		Map<String, Object> map=new HashMap<>();
		//使用uuid作为文件名防止文件名相同被覆盖
		String fileName=UUIDUtil.getParseUUID()+libExtName;
		//拼接保存路径如：/home/cutter/files/2017-03-29/
		String imgUrl=fileUrl+sf.format(new Date()).toString()+"/"+fileName;
		//通过工具类保存文件
		try {
			//FileUtils.writeByteArrayToFile(new File(imgUrl), file.getBytes());
			/*response.setContentType("image/png");
			response.getOutputStream().write(file.getBytes());*/
			map.put("fileName", fileOriName);
			map.put("libSize", file.getSize());
			session.setAttribute("certMap", map);
			baseReslt.setObj(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baseReslt;
	}
	/*@RequestMapping("/enterRegister")
	@ResponseBody
	public BaseReslt<Object> enterRegister(TUserBaseInfoVo baseInfoVo,TUserEnterInfoVo enterInfoVo){
		BaseReslt<Object> baseReslt=new BaseReslt<Object>();
		//判断用户输入是否已经存在
		Map<String, Integer> result=baseInfoService.findIsRegister(baseInfoVo);
		if (result.get("phoneIsExist")!=null&&result.get("phoneIsExist")==1) {
			baseReslt.setSuccess(false);
			baseReslt.setMsg("电话号码已存在");
			return baseReslt;
		}
		if (result.get("emailIsExist")!=null&&result.get("emailIsExist")==1) {
			baseReslt.setSuccess(false);
			baseReslt.setMsg("邮箱已存在");
			return baseReslt;
		}
		if (result.get("userAliasIsExist")!=null&&result.get("userAliasIsExist")==1) {
			baseReslt.setSuccess(false);
			baseReslt.setMsg("昵称已存在");
			return baseReslt;
		}		
		
		String custNo=UUIDUtil.getParseUUID();
		//用户编号,根据时间生成字符串如20170227232234
		baseInfoVo.setUserNo(UUIDUtil.getDateString(new Date()));
		//登录名
		baseInfoVo.setUserLoginName(baseInfoVo.getUserAlias());
		//用户类型
		baseInfoVo.setUserType(UserCosntans.personalUser);
		//客户编号
		baseInfoVo.setCustNo(UUIDUtil.getParseUUID());
		//客户名称
		baseInfoVo.setCustName(baseInfoVo.getUserName());
		//注册状态
		baseInfoVo.setRegisterStatus(UserCosntans.registerStatusSucc);
		//注册时间
		baseInfoVo.setInsertDate(new Date());
		
		enterInfoVo.setInsertDate(new Date());
		enterInfoVo.setCustNo(custNo);
		enterInfoVo.setCustName(baseInfoVo.getUserName());
		
		//保存基本表
		baseInfoService.insert(baseInfoVo);
		//保存企业表
		enterInfoService.insert(enterInfoVo);
		baseReslt.setMsg("注册成功");
		baseReslt.setSuccess(true);
		
		return baseReslt;
	}*/
	
	@RequestMapping("/toTransAction")
	public String toTransAction(HttpSession session,HttpServletResponse response,HttpServletRequest request){
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		if (baseInfoVo==null) {
			PrintWriter out;
			try {
				out = response.getWriter();
				 response.setContentType("text/html;charset=UTF-8");
				String url = "<script type='text/javascript'>alert('登录超时请重新登录!');setTimeout(function(){top.location.href='"+"../.."+request.getContextPath()+"/index.jsp'},1000)</script>";
				out.print(url);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String transActionUrl=Config.getInstance().getValue("transActionUrl");
		return "redirect:"+transActionUrl+"?custNo="+baseInfoVo.getCustNo();
	}
	
	@RequestMapping("/toCutter")
	public String toCutter(){
		String cutterUrl=Config.getInstance().getValue("cutterUrl");
		return "redirect:"+cutterUrl;
	}
	
	@RequestMapping("/toTech")
	public String toTech(HttpSession session,HttpServletResponse response,HttpServletRequest request){
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		if (baseInfoVo==null) {
			PrintWriter out;
			try {
				out = response.getWriter();
				 response.setContentType("text/html;charset=UTF-8");
				String url = "<script type='text/javascript'>alert('登录超时请重新登录!');setTimeout(function(){top.location.href='"+"../.."+request.getContextPath()+"/index.jsp'},1000)</script>";
				out.print(url);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String techUrl=Config.getInstance().getValue("techUrl");
		return "redirect:"+techUrl+baseInfoVo.getCustNo();
	}
	
	@RequestMapping("/toUrl")
	@ResponseBody
	public String toUrl(String toUrl, HttpSession session){
		if(!StringUtil.isEmpty(toUrl)){
			session.setAttribute("tourl", toUrl);
		}
		return "success";
	}
}
