/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pro.entity.ProBaseInfo;
import com.thinkgem.jeesite.modules.pro.service.ProBaseInfoService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 节目基本信息Controller
 * @author lin
 * @version 2016-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proBaseInfo")
public class ProBaseInfoController extends BaseController {

	@Autowired
	private ProBaseInfoService proBaseInfoService;
	
	@ModelAttribute
	public ProBaseInfo get(@RequestParam(required=false) String id) {
		ProBaseInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proBaseInfoService.get(id);
		}
		if (entity == null){
			entity = new ProBaseInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proBaseInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProBaseInfo proBaseInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProBaseInfo> page = proBaseInfoService.findPage(new Page<ProBaseInfo>(request, response), proBaseInfo); 
		model.addAttribute("page", page);
		return "modules/pro/proBaseInfoList";
	}

	
	/**
	 * 文章选择列表
	 */
	@RequiresPermissions("pro:proBaseInfo:view")
	@RequestMapping(value = "selectList")
	public String selectList(ProBaseInfo proBaseInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
//        list(proBaseInfo, request, response, model);
		String ids = (String)request.getParameter("ids"); 
		
//		System.out.println("ids>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ids);
		
		Page<ProBaseInfo> page = proBaseInfoService.findPageByIds(new Page<ProBaseInfo>(request, response), proBaseInfo,ids); 
		
		model.addAttribute("page", page);
		return "modules/pro/proBaseInfoSelectList";
	}
	
	
	@RequiresPermissions("pro:proBaseInfo:view")
	@RequestMapping(value = "form")
	public String form(ProBaseInfo proBaseInfo, Model model) {
		model.addAttribute("proBaseInfo", proBaseInfo);
		return "modules/pro/proBaseInfoForm";
	}

	@RequiresPermissions("pro:proBaseInfo:edit")
	@RequestMapping(value = "save")
	public String save(ProBaseInfo proBaseInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proBaseInfo)){
			return form(proBaseInfo, model);
		}
		proBaseInfoService.save(proBaseInfo);
		
		addMessage(redirectAttributes, "保存节目基本信息成功");
		
		proBaseInfo = this.get(proBaseInfo.getId());
		return form(proBaseInfo, model);
		
//		return "redirect:"+Global.getAdminPath()+"/pro/proBaseInfo/?repage";
	}
	
	@RequiresPermissions("pro:proBaseInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(ProBaseInfo proBaseInfo, RedirectAttributes redirectAttributes) {
		proBaseInfoService.delete(proBaseInfo);
		addMessage(redirectAttributes, "删除节目基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proBaseInfo/?repage";
	}
	
	
	/**
	 * 返回用户信息 
	 * @return
	 */
//	@RequiresPermissions("proBaseInfo")
	@ResponseBody
	@RequestMapping(value = "infoData")
	public List<Map<String, Object>> infoData(ProBaseInfo proBaseInfo, HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("term>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		String name = (String)request.getParameter("term");
		proBaseInfo.setName(name);
//		proBaseInfo.setNameEn(name);
//		proBaseInfo.setHelpCode(name);
		System.out.println("term>5555555>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+request.getParameter("term"));
		
		Page<ProBaseInfo> page = proBaseInfoService.findPage(new Page<ProBaseInfo>(request, response), proBaseInfo); 
		List<ProBaseInfo> list = page.getList();
		System.out.println("term>666666>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+list.size());
		for (int i=0; i<list.size(); i++){
			ProBaseInfo e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("value", e.getId());
			map.put("label", StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}

}