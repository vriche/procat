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
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceTarget;
import com.thinkgem.jeesite.modules.pro.service.ProAudienceTargetService;

/**
 * 收视目标Controller
 * @author lin
 * @version 2016-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proAudienceTarget")
public class ProAudienceTargetController extends BaseController {

	@Autowired
	private ProAudienceTargetService proAudienceTargetService;
	
	@ModelAttribute
	public ProAudienceTarget get(@RequestParam(required=false) String id) {
		ProAudienceTarget entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proAudienceTargetService.get(id);
		}
		if (entity == null){
			entity = new ProAudienceTarget();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proAudienceTarget:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProAudienceTarget proAudienceTarget, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProAudienceTarget> page = proAudienceTargetService.findPage(new Page<ProAudienceTarget>(request, response), proAudienceTarget); 
		model.addAttribute("page", page);
		return "modules/pro/proAudienceTargetList";
	}

	@RequiresPermissions("pro:proAudienceTarget:view")
	@RequestMapping(value = "form")
	public String form(ProAudienceTarget proAudienceTarget, Model model) {
		model.addAttribute("proAudienceTarget", proAudienceTarget);
		return "modules/pro/proAudienceTargetForm";
	}

	@RequiresPermissions("pro:proAudienceTarget:edit")
	@RequestMapping(value = "save")
	public String save(ProAudienceTarget proAudienceTarget, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proAudienceTarget)){
			return form(proAudienceTarget, model);
		}
		proAudienceTargetService.save(proAudienceTarget);
		addMessage(redirectAttributes, "保存收视目标成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proAudienceTarget/?repage";
	}
	
	@RequiresPermissions("pro:proAudienceTarget:edit")
	@RequestMapping(value = "delete")
	public String delete(ProAudienceTarget proAudienceTarget, RedirectAttributes redirectAttributes) {
		proAudienceTargetService.delete(proAudienceTarget);
		addMessage(redirectAttributes, "删除收视目标成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proAudienceTarget/?repage";
	}

}