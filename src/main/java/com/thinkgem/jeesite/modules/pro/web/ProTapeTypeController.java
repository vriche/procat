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
import com.thinkgem.jeesite.modules.pro.entity.ProTapeType;
import com.thinkgem.jeesite.modules.pro.service.ProTapeTypeService;

/**
 * 磁带类型Controller
 * @author lin
 * @version 2016-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proTapeType")
public class ProTapeTypeController extends BaseController {

	@Autowired
	private ProTapeTypeService proTapeTypeService;
	
	@ModelAttribute
	public ProTapeType get(@RequestParam(required=false) String id) {
		ProTapeType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proTapeTypeService.get(id);
		}
		if (entity == null){
			entity = new ProTapeType();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proTapeType:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProTapeType proTapeType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProTapeType> page = proTapeTypeService.findPage(new Page<ProTapeType>(request, response), proTapeType); 
		model.addAttribute("page", page);
		return "modules/pro/proTapeTypeList";
	}

	@RequiresPermissions("pro:proTapeType:view")
	@RequestMapping(value = "form")
	public String form(ProTapeType proTapeType, Model model) {
		model.addAttribute("proTapeType", proTapeType);
		return "modules/pro/proTapeTypeForm";
	}

	@RequiresPermissions("pro:proTapeType:edit")
	@RequestMapping(value = "save")
	public String save(ProTapeType proTapeType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proTapeType)){
			return form(proTapeType, model);
		}
		proTapeTypeService.save(proTapeType);
		addMessage(redirectAttributes, "保存磁带类型成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proTapeType/?repage";
	}
	
	@RequiresPermissions("pro:proTapeType:edit")
	@RequestMapping(value = "delete")
	public String delete(ProTapeType proTapeType, RedirectAttributes redirectAttributes) {
		proTapeTypeService.delete(proTapeType);
		addMessage(redirectAttributes, "删除磁带类型成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proTapeType/?repage";
	}

}