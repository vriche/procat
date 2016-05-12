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
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceSource;
import com.thinkgem.jeesite.modules.pro.service.ProAudienceSourceService;

/**
 * 收视率数据源Controller
 * @author lin
 * @version 2016-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proAudienceSource")
public class ProAudienceSourceController extends BaseController {

	@Autowired
	private ProAudienceSourceService proAudienceSourceService;
	
	@ModelAttribute
	public ProAudienceSource get(@RequestParam(required=false) String id) {
		ProAudienceSource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proAudienceSourceService.get(id);
		}
		if (entity == null){
			entity = new ProAudienceSource();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proAudienceSource:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProAudienceSource proAudienceSource, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProAudienceSource> page = proAudienceSourceService.findPage(new Page<ProAudienceSource>(request, response), proAudienceSource); 
		model.addAttribute("page", page);
		return "modules/pro/proAudienceSourceList";
	}

	@RequiresPermissions("pro:proAudienceSource:view")
	@RequestMapping(value = "form")
	public String form(ProAudienceSource proAudienceSource, Model model) {
		model.addAttribute("proAudienceSource", proAudienceSource);
		return "modules/pro/proAudienceSourceForm";
	}

	@RequiresPermissions("pro:proAudienceSource:edit")
	@RequestMapping(value = "save")
	public String save(ProAudienceSource proAudienceSource, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proAudienceSource)){
			return form(proAudienceSource, model);
		}
		proAudienceSourceService.save(proAudienceSource);
		addMessage(redirectAttributes, "保存收视率数据源成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proAudienceSource/?repage";
	}
	
	@RequiresPermissions("pro:proAudienceSource:edit")
	@RequestMapping(value = "delete")
	public String delete(ProAudienceSource proAudienceSource, RedirectAttributes redirectAttributes) {
		proAudienceSourceService.delete(proAudienceSource);
		addMessage(redirectAttributes, "删除收视率数据源成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proAudienceSource/?repage";
	}

}