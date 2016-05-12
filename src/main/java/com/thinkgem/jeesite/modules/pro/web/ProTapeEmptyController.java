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
import com.thinkgem.jeesite.modules.pro.entity.ProTapeEmpty;
import com.thinkgem.jeesite.modules.pro.service.ProTapeEmptyService;

/**
 * 空白磁带Controller
 * @author lin
 * @version 2016-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proTapeEmpty")
public class ProTapeEmptyController extends BaseController {

	@Autowired
	private ProTapeEmptyService proTapeEmptyService;
	
	@ModelAttribute
	public ProTapeEmpty get(@RequestParam(required=false) String id) {
		ProTapeEmpty entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proTapeEmptyService.get(id);
		}
		if (entity == null){
			entity = new ProTapeEmpty();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proTapeEmpty:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProTapeEmpty proTapeEmpty, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProTapeEmpty> page = proTapeEmptyService.findPage(new Page<ProTapeEmpty>(request, response), proTapeEmpty); 
		model.addAttribute("page", page);
		return "modules/pro/proTapeEmptyList";
	}

	@RequiresPermissions("pro:proTapeEmpty:view")
	@RequestMapping(value = "form")
	public String form(ProTapeEmpty proTapeEmpty, Model model) {
		model.addAttribute("proTapeEmpty", proTapeEmpty);
		return "modules/pro/proTapeEmptyForm";
	}

	@RequiresPermissions("pro:proTapeEmpty:edit")
	@RequestMapping(value = "save")
	public String save(ProTapeEmpty proTapeEmpty, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proTapeEmpty)){
			return form(proTapeEmpty, model);
		}
		proTapeEmptyService.save(proTapeEmpty);
		addMessage(redirectAttributes, "保存空白磁带成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proTapeEmpty/?repage";
	}
	
	@RequiresPermissions("pro:proTapeEmpty:edit")
	@RequestMapping(value = "delete")
	public String delete(ProTapeEmpty proTapeEmpty, RedirectAttributes redirectAttributes) {
		proTapeEmptyService.delete(proTapeEmpty);
		addMessage(redirectAttributes, "删除空白磁带成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proTapeEmpty/?repage";
	}

}