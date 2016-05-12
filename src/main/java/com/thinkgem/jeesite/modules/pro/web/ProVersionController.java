/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pro.entity.ProVersion;
import com.thinkgem.jeesite.modules.pro.service.ProBaseInfoService;
import com.thinkgem.jeesite.modules.pro.service.ProVersionService;

/**
 * 节目版本Controller
 * @author lin
 * @version 2016-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proVersion")
public class ProVersionController extends BaseController {

	@Autowired
	private ProVersionService proVersionService;
	

	@Autowired
	private ProBaseInfoService proBaseInfoService;
	
	@ModelAttribute
	public ProVersion get(@RequestParam(required=false) String id) {
		ProVersion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proVersionService.get(id);
		}
		if (entity == null){
			entity = new ProVersion();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proVersion:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProVersion proVersion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProVersion> page = proVersionService.findPage(new Page<ProVersion>(request, response), proVersion); 
		model.addAttribute("page", page);
		return "modules/pro/proVersionList";
	}

	@RequiresPermissions("pro:proVersion:view")
	@RequestMapping(value = "form")
	public String form(ProVersion proVersion, Model model) {
		model.addAttribute("proVersion", proVersion);
		return "modules/pro/proVersionForm";
	}

	@RequiresPermissions("pro:proVersion:edit")
	@RequestMapping(value = "save")
	public String save(ProVersion proVersion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proVersion)){
			return form(proVersion, model);
		}
		proVersionService.save(proVersion);
		addMessage(redirectAttributes, "保存节目版本成功"); 

		model.addAttribute("proBaseInfo", proBaseInfoService.get(proVersion.getProBaseInfo().getId()));
		return "modules/pro/proBaseInfoForm";
		
		
//		return "redirect:"+Global.getAdminPath()+"/pro/proVersion/?repage";
	}
	
	@RequiresPermissions("pro:proVersion:edit")
	@RequestMapping(value = "delete")
	public String delete(ProVersion proVersion, Model model,RedirectAttributes redirectAttributes) {
		proVersionService.delete(proVersion);
		addMessage(redirectAttributes, "删除节目版本成功");
		
		model.addAttribute("proBaseInfo", proBaseInfoService.get(proVersion.getProBaseInfo().getId()));
		return "modules/pro/proBaseInfoForm";

//		return "redirect:"+Global.getAdminPath()+"/pro/proBaseInfoForm?id="+proVersion.getProBaseInfo().getId();
		
		
//		return "redirect:"+Global.getAdminPath()+"/pro/proVersion/?repage";
	}

}