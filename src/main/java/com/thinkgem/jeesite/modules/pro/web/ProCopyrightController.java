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
import com.thinkgem.jeesite.modules.pro.entity.ProCopyright;
import com.thinkgem.jeesite.modules.pro.service.ProCopyrightService;

/**
 * 版权信息Controller
 * @author lin
 * @version 2016-05-23
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proCopyright")
public class ProCopyrightController extends BaseController {

	@Autowired
	private ProCopyrightService proCopyrightService;
	
	@ModelAttribute
	public ProCopyright get(@RequestParam(required=false) String id) {
		ProCopyright entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proCopyrightService.get(id);
		}
		if (entity == null){
			entity = new ProCopyright();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proCopyright:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProCopyright proCopyright, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProCopyright> page = proCopyrightService.findPage(new Page<ProCopyright>(request, response), proCopyright); 
		model.addAttribute("page", page);
		return "modules/pro/proCopyrightList";
	}

	@RequiresPermissions("pro:proCopyright:view")
	@RequestMapping(value = "form")
	public String form(ProCopyright proCopyright, Model model) {
		model.addAttribute("proCopyright", proCopyright);
		return "modules/pro/proCopyrightForm";
	}

	@RequiresPermissions("pro:proCopyright:edit")
	@RequestMapping(value = "save")
	public String save(ProCopyright proCopyright, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proCopyright)){
			return form(proCopyright, model);
		}
		proCopyrightService.save(proCopyright);
		addMessage(redirectAttributes, "保存版权信息成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proCopyright/?repage";
	}
	
	@RequiresPermissions("pro:proCopyright:edit")
	@RequestMapping(value = "delete")
	public String delete(ProCopyright proCopyright, RedirectAttributes redirectAttributes) {
		proCopyrightService.delete(proCopyright);
		addMessage(redirectAttributes, "删除版权信息成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proCopyright/?repage";
	}

}