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
import com.thinkgem.jeesite.modules.pro.entity.ProContract;
import com.thinkgem.jeesite.modules.pro.service.ProContractService;

/**
 * 节目合同Controller
 * @author lin
 * @version 2016-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proContract")
public class ProContractController extends BaseController {

	@Autowired
	private ProContractService proContractService;
	
	@ModelAttribute
	public ProContract get(@RequestParam(required=false) String id) {
		ProContract entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proContractService.get(id);
		}
		if (entity == null){
			entity = new ProContract();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proContract:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProContract proContract, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProContract> page = proContractService.findPage(new Page<ProContract>(request, response), proContract); 
		model.addAttribute("page", page);
		return "modules/pro/proContractList";
	}

	@RequiresPermissions("pro:proContract:view")
	@RequestMapping(value = "form")
	public String form(ProContract proContract, Model model) {
		model.addAttribute("proContract", proContract);
		return "modules/pro/proContractForm";
	}

	@RequiresPermissions("pro:proContract:edit")
	@RequestMapping(value = "save")
	public String save(ProContract proContract, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proContract)){
			return form(proContract, model);
		}
		proContractService.save(proContract);
		addMessage(redirectAttributes, "保存节目合同成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proContract/?repage";
	}
	
	@RequiresPermissions("pro:proContract:edit")
	@RequestMapping(value = "delete")
	public String delete(ProContract proContract, RedirectAttributes redirectAttributes) {
		proContractService.delete(proContract);
		addMessage(redirectAttributes, "删除节目合同成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proContract/?repage";
	}

}