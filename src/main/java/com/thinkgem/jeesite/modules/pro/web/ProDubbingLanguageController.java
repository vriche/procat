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
import com.thinkgem.jeesite.modules.pro.entity.ProDubbingLanguage;
import com.thinkgem.jeesite.modules.pro.service.ProDubbingLanguageService;

/**
 * 配音语言Controller
 * @author lin
 * @version 2016-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proDubbingLanguage")
public class ProDubbingLanguageController extends BaseController {

	@Autowired
	private ProDubbingLanguageService proDubbingLanguageService;
	
	@ModelAttribute
	public ProDubbingLanguage get(@RequestParam(required=false) String id) {
		ProDubbingLanguage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proDubbingLanguageService.get(id);
		}
		if (entity == null){
			entity = new ProDubbingLanguage();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proDubbingLanguage:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProDubbingLanguage proDubbingLanguage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProDubbingLanguage> page = proDubbingLanguageService.findPage(new Page<ProDubbingLanguage>(request, response), proDubbingLanguage); 
		model.addAttribute("page", page);
		return "modules/pro/proDubbingLanguageList";
	}

	@RequiresPermissions("pro:proDubbingLanguage:view")
	@RequestMapping(value = "form")
	public String form(ProDubbingLanguage proDubbingLanguage, Model model) {
		model.addAttribute("proDubbingLanguage", proDubbingLanguage);
		return "modules/pro/proDubbingLanguageForm";
	}

	@RequiresPermissions("pro:proDubbingLanguage:edit")
	@RequestMapping(value = "save")
	public String save(ProDubbingLanguage proDubbingLanguage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proDubbingLanguage)){
			return form(proDubbingLanguage, model);
		}
		proDubbingLanguageService.save(proDubbingLanguage);
		addMessage(redirectAttributes, "保存配音语言成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proDubbingLanguage/?repage";
	}
	
	@RequiresPermissions("pro:proDubbingLanguage:edit")
	@RequestMapping(value = "delete")
	public String delete(ProDubbingLanguage proDubbingLanguage, RedirectAttributes redirectAttributes) {
		proDubbingLanguageService.delete(proDubbingLanguage);
		addMessage(redirectAttributes, "删除配音语言成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proDubbingLanguage/?repage";
	}

}