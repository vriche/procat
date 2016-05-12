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
import com.thinkgem.jeesite.modules.pro.entity.ProTapeBro;
import com.thinkgem.jeesite.modules.pro.service.ProTapeBroService;

/**
 * 成品带信息Controller
 * @author lin
 * @version 2016-05-23
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proTapeBro")
public class ProTapeBroController extends BaseController {

	@Autowired
	private ProTapeBroService proTapeBroService;
	
	@ModelAttribute
	public ProTapeBro get(@RequestParam(required=false) String id) {
		ProTapeBro entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proTapeBroService.get(id);
		}
		if (entity == null){
			entity = new ProTapeBro();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proTapeBro:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProTapeBro proTapeBro, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProTapeBro> page = proTapeBroService.findPage(new Page<ProTapeBro>(request, response), proTapeBro); 
		model.addAttribute("page", page);
		return "modules/pro/proTapeBroList";
	}

	@RequiresPermissions("pro:proTapeBro:view")
	@RequestMapping(value = "form")
	public String form(ProTapeBro proTapeBro, Model model) {
		model.addAttribute("proTapeBro", proTapeBro);
		return "modules/pro/proTapeBroForm";
	}

	@RequiresPermissions("pro:proTapeBro:edit")
	@RequestMapping(value = "save")
	public String save(ProTapeBro proTapeBro, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proTapeBro)){
			return form(proTapeBro, model);
		}
		proTapeBroService.save(proTapeBro);
		addMessage(redirectAttributes, "保存成品带信息成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proTapeBro/?repage";
	}
	
	@RequiresPermissions("pro:proTapeBro:edit")
	@RequestMapping(value = "delete")
	public String delete(ProTapeBro proTapeBro, RedirectAttributes redirectAttributes) {
		proTapeBroService.delete(proTapeBro);
		addMessage(redirectAttributes, "删除成品带信息成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proTapeBro/?repage";
	}

}