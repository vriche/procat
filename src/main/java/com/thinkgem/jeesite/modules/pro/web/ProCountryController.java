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
import com.thinkgem.jeesite.modules.pro.entity.ProCountry;
import com.thinkgem.jeesite.modules.pro.service.ProCountryService;

/**
 * 国别信息Controller
 * @author lin
 * @version 2016-05-29
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proCountry")
public class ProCountryController extends BaseController {

	@Autowired
	private ProCountryService proCountryService;
	
	@ModelAttribute
	public ProCountry get(@RequestParam(required=false) String id) {
		ProCountry entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proCountryService.get(id);
		}
		if (entity == null){
			entity = new ProCountry();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proCountry:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProCountry proCountry, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ProCountry> list = proCountryService.findList(proCountry); 
		model.addAttribute("list", list);
		return "modules/pro/proCountryList";
	}

	@RequiresPermissions("pro:proCountry:view")
	@RequestMapping(value = "form")
	public String form(ProCountry proCountry, Model model) {
		if (proCountry.getParent()!=null && StringUtils.isNotBlank(proCountry.getParent().getId())){
			proCountry.setParent(proCountryService.get(proCountry.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(proCountry.getId())){
				ProCountry proCountryChild = new ProCountry();
				proCountryChild.setParent(new ProCountry(proCountry.getParent().getId()));
				List<ProCountry> list = proCountryService.findList(proCountry); 
				if (list.size() > 0){
					proCountry.setSort(list.get(list.size()-1).getSort());
					if (proCountry.getSort() != null){
						proCountry.setSort(proCountry.getSort() + 30);
					}
				}
			}
		}
		if (proCountry.getSort() == null){
			proCountry.setSort(30);
		}
		model.addAttribute("proCountry", proCountry);
		return "modules/pro/proCountryForm";
	}

	@RequiresPermissions("pro:proCountry:edit")
	@RequestMapping(value = "save")
	public String save(ProCountry proCountry, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proCountry)){
			return form(proCountry, model);
		}
		proCountryService.save(proCountry);
		addMessage(redirectAttributes, "保存国别信息成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proCountry/?repage";
	}
	
	@RequiresPermissions("pro:proCountry:edit")
	@RequestMapping(value = "delete")
	public String delete(ProCountry proCountry, RedirectAttributes redirectAttributes) {
		proCountryService.delete(proCountry);
		addMessage(redirectAttributes, "删除国别信息成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proCountry/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ProCountry> list = proCountryService.findList(new ProCountry());
		for (int i=0; i<list.size(); i++){
			ProCountry e = list.get(i);
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