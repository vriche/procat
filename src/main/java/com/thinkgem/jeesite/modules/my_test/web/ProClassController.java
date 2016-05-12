/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.my_test.web;

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
import com.thinkgem.jeesite.modules.my_test.entity.ProClass;
import com.thinkgem.jeesite.modules.my_test.service.ProClassService;

/**
 * aaController
 * @author aa
 * @version 2016-05-11
 */
@Controller
@RequestMapping(value = "${adminPath}/my_test/proClass")
public class ProClassController extends BaseController {

	@Autowired
	private ProClassService proClassService;
	
	@ModelAttribute
	public ProClass get(@RequestParam(required=false) String id) {
		ProClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proClassService.get(id);
		}
		if (entity == null){
			entity = new ProClass();
		}
		return entity;
	}
	
	@RequiresPermissions("my_test:proClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProClass proClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProClass> page = proClassService.findPage(new Page<ProClass>(request, response), proClass); 
		model.addAttribute("page", page);
		return "modules/my_test/proClassList";
	}

	@RequiresPermissions("my_test:proClass:view")
	@RequestMapping(value = "form")
	public String form(ProClass proClass, Model model) {
		model.addAttribute("proClass", proClass);
		return "modules/my_test/proClassForm";
	}

	@RequiresPermissions("my_test:proClass:edit")
	@RequestMapping(value = "save")
	public String save(ProClass proClass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proClass)){
			return form(proClass, model);
		}
		proClassService.save(proClass);
		addMessage(redirectAttributes, "保存aa成功");
		return "redirect:"+Global.getAdminPath()+"/my_test/proClass/?repage";
	}
	
	@RequiresPermissions("my_test:proClass:edit")
	@RequestMapping(value = "delete")
	public String delete(ProClass proClass, RedirectAttributes redirectAttributes) {
		proClassService.delete(proClass);
		addMessage(redirectAttributes, "删除aa成功");
		return "redirect:"+Global.getAdminPath()+"/my_test/proClass/?repage";
	}

}