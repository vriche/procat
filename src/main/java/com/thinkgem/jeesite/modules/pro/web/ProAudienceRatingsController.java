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
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceRatings;
import com.thinkgem.jeesite.modules.pro.service.ProAudienceRatingsService;

/**
 * 收视率信息Controller
 * @author lin
 * @version 2016-05-23
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proAudienceRatings")
public class ProAudienceRatingsController extends BaseController {

	@Autowired
	private ProAudienceRatingsService proAudienceRatingsService;
	
	@ModelAttribute
	public ProAudienceRatings get(@RequestParam(required=false) String id) {
		ProAudienceRatings entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proAudienceRatingsService.get(id);
		}
		if (entity == null){
			entity = new ProAudienceRatings();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proAudienceRatings:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProAudienceRatings proAudienceRatings, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProAudienceRatings> page = proAudienceRatingsService.findPage(new Page<ProAudienceRatings>(request, response), proAudienceRatings); 
		model.addAttribute("page", page);
		return "modules/pro/proAudienceRatingsList";
	}

	@RequiresPermissions("pro:proAudienceRatings:view")
	@RequestMapping(value = "form")
	public String form(ProAudienceRatings proAudienceRatings, Model model) {
		model.addAttribute("proAudienceRatings", proAudienceRatings);
		return "modules/pro/proAudienceRatingsForm";
	}

	@RequiresPermissions("pro:proAudienceRatings:edit")
	@RequestMapping(value = "save")
	public String save(ProAudienceRatings proAudienceRatings, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proAudienceRatings)){
			return form(proAudienceRatings, model);
		}
		System.out.print("save ProAudienceRatings 99999999999999999999999  aaaaaaaaaa>>>>>>"+ proAudienceRatings.getBroTimeStart() +"\n");
		
		proAudienceRatingsService.save(proAudienceRatings);
		addMessage(redirectAttributes, "保存收视率信息成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proAudienceRatings/?repage";
	}
	
	@RequiresPermissions("pro:proAudienceRatings:edit")
	@RequestMapping(value = "delete")
	public String delete(ProAudienceRatings proAudienceRatings, RedirectAttributes redirectAttributes) {
		proAudienceRatingsService.delete(proAudienceRatings);
		addMessage(redirectAttributes, "删除收视率信息成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proAudienceRatings/?repage";
	}

}