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
import com.thinkgem.jeesite.modules.pro.entity.ProGrade;
import com.thinkgem.jeesite.modules.pro.service.ProGradeService;

/**
 * 节目等级Controller
 * @author lin
 * @version 2016-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proGrade")
public class ProGradeController extends BaseController {

	@Autowired
	private ProGradeService proGradeService;
	
	@ModelAttribute
	public ProGrade get(@RequestParam(required=false) String id) {
		ProGrade entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proGradeService.get(id);
		}
		if (entity == null){
			entity = new ProGrade();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proGrade:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProGrade proGrade, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProGrade> page = proGradeService.findPage(new Page<ProGrade>(request, response), proGrade); 
		model.addAttribute("page", page);
		return "modules/pro/proGradeList";
	}

	@RequiresPermissions("pro:proGrade:view")
	@RequestMapping(value = "form")
	public String form(ProGrade proGrade, Model model) {
		model.addAttribute("proGrade", proGrade);
		return "modules/pro/proGradeForm";
	}

	@RequiresPermissions("pro:proGrade:edit")
	@RequestMapping(value = "save")
	public String save(ProGrade proGrade, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proGrade)){
			return form(proGrade, model);
		}
		proGradeService.save(proGrade);
		addMessage(redirectAttributes, "保存节目等级成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proGrade/?repage";
	}
	
	@RequiresPermissions("pro:proGrade:edit")
	@RequestMapping(value = "delete")
	public String delete(ProGrade proGrade, RedirectAttributes redirectAttributes) {
		proGradeService.delete(proGrade);
		addMessage(redirectAttributes, "删除节目等级成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proGrade/?repage";
	}

}