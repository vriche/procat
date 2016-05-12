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
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceCarrier;
import com.thinkgem.jeesite.modules.pro.service.ProAudienceCarrierService;

/**
 * 成品带信息Controller
 * @author lin
 * @version 2016-05-19
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proAudienceCarrier")
public class ProAudienceCarrierController extends BaseController {

	@Autowired
	private ProAudienceCarrierService proAudienceCarrierService;
	
	@ModelAttribute
	public ProAudienceCarrier get(@RequestParam(required=false) String id) {
		ProAudienceCarrier entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proAudienceCarrierService.get(id);
		}
		if (entity == null){
			entity = new ProAudienceCarrier();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proAudienceCarrier:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProAudienceCarrier proAudienceCarrier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProAudienceCarrier> page = proAudienceCarrierService.findPage(new Page<ProAudienceCarrier>(request, response), proAudienceCarrier); 
		model.addAttribute("page", page);
		return "modules/pro/proAudienceCarrierList";
	}

	@RequiresPermissions("pro:proAudienceCarrier:view")
	@RequestMapping(value = "form")
	public String form(ProAudienceCarrier proAudienceCarrier, Model model) {
		model.addAttribute("proAudienceCarrier", proAudienceCarrier);
		return "modules/pro/proAudienceCarrierForm";
	}

	@RequiresPermissions("pro:proAudienceCarrier:edit")
	@RequestMapping(value = "save")
	public String save(ProAudienceCarrier proAudienceCarrier, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proAudienceCarrier)){
			return form(proAudienceCarrier, model);
		}
		proAudienceCarrierService.save(proAudienceCarrier);
		addMessage(redirectAttributes, "保存成品带信息成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proAudienceCarrier/?repage";
	}
	
	@RequiresPermissions("pro:proAudienceCarrier:edit")
	@RequestMapping(value = "delete")
	public String delete(ProAudienceCarrier proAudienceCarrier, RedirectAttributes redirectAttributes) {
		proAudienceCarrierService.delete(proAudienceCarrier);
		addMessage(redirectAttributes, "删除成品带信息成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proAudienceCarrier/?repage";
	}

}