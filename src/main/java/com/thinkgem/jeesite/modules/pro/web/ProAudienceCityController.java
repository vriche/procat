/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceCity;
import com.thinkgem.jeesite.modules.pro.service.ProAudienceCityService;

/**
 * 收视区域Controller
 * @author lin
 * @version 2016-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proAudienceCity")
public class ProAudienceCityController extends BaseController {

	@Autowired
	private ProAudienceCityService proAudienceCityService;
	
	@ModelAttribute
	public ProAudienceCity get(@RequestParam(required=false) String id) {
		ProAudienceCity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proAudienceCityService.get(id);
		}
		if (entity == null){
			entity = new ProAudienceCity();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proAudienceCity:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProAudienceCity proAudienceCity, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ProAudienceCity> list = proAudienceCityService.findList(proAudienceCity); 
		model.addAttribute("list", list);
		return "modules/pro/proAudienceCityList";
	}

	@RequiresPermissions("pro:proAudienceCity:view")
	@RequestMapping(value = "form")
	public String form(ProAudienceCity proAudienceCity, Model model) {
		if (proAudienceCity.getParent()!=null && StringUtils.isNotBlank(proAudienceCity.getParent().getId())){
			proAudienceCity.setParent(proAudienceCityService.get(proAudienceCity.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(proAudienceCity.getId())){
				ProAudienceCity proAudienceCityChild = new ProAudienceCity();
				proAudienceCityChild.setParent(new ProAudienceCity(proAudienceCity.getParent().getId()));
				List<ProAudienceCity> list = proAudienceCityService.findList(proAudienceCity); 
				if (list.size() > 0){
					proAudienceCity.setSort(list.get(list.size()-1).getSort());
					if (proAudienceCity.getSort() != null){
						proAudienceCity.setSort(proAudienceCity.getSort() + 30);
					}
				}
			}
		}
		if (proAudienceCity.getSort() == null){
			proAudienceCity.setSort(30);
		}
		model.addAttribute("proAudienceCity", proAudienceCity);
		return "modules/pro/proAudienceCityForm";
	}

	@RequiresPermissions("pro:proAudienceCity:edit")
	@RequestMapping(value = "save")
	public String save(ProAudienceCity proAudienceCity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proAudienceCity)){
			return form(proAudienceCity, model);
		}
		proAudienceCityService.save(proAudienceCity);
		addMessage(redirectAttributes, "保存收视区域成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proAudienceCity/?repage";
	}
	
	@RequiresPermissions("pro:proAudienceCity:edit")
	@RequestMapping(value = "delete")
	public String delete(ProAudienceCity proAudienceCity, RedirectAttributes redirectAttributes) {
		proAudienceCityService.delete(proAudienceCity);
		addMessage(redirectAttributes, "删除收视区域成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proAudienceCity/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ProAudienceCity> list = proAudienceCityService.findList(new ProAudienceCity());
		for (int i=0; i<list.size(); i++){
			ProAudienceCity e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}