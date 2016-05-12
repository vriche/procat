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
import com.thinkgem.jeesite.modules.pro.entity.ProPlotAspect;
import com.thinkgem.jeesite.modules.pro.service.ProPlotAspectService;

/**
 * 剧情看点Controller
 * @author lin
 * @version 2016-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proPlotAspect")
public class ProPlotAspectController extends BaseController {

	@Autowired
	private ProPlotAspectService proPlotAspectService;
	
	@ModelAttribute
	public ProPlotAspect get(@RequestParam(required=false) String id) {
		ProPlotAspect entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proPlotAspectService.get(id);
		}
		if (entity == null){
			entity = new ProPlotAspect();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proPlotAspect:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProPlotAspect proPlotAspect, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ProPlotAspect> list = proPlotAspectService.findList(proPlotAspect); 
		model.addAttribute("list", list);
		return "modules/pro/proPlotAspectList";
	}

	@RequiresPermissions("pro:proPlotAspect:view")
	@RequestMapping(value = "form")
	public String form(ProPlotAspect proPlotAspect, Model model) {
		if (proPlotAspect.getParent()!=null && StringUtils.isNotBlank(proPlotAspect.getParent().getId())){
			proPlotAspect.setParent(proPlotAspectService.get(proPlotAspect.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(proPlotAspect.getId())){
				ProPlotAspect proPlotAspectChild = new ProPlotAspect();
				proPlotAspectChild.setParent(new ProPlotAspect(proPlotAspect.getParent().getId()));
				List<ProPlotAspect> list = proPlotAspectService.findList(proPlotAspect); 
				if (list.size() > 0){
					proPlotAspect.setSort(list.get(list.size()-1).getSort());
					if (proPlotAspect.getSort() != null){
						proPlotAspect.setSort(proPlotAspect.getSort() + 30);
					}
				}
			}
		}
		if (proPlotAspect.getSort() == null){
			proPlotAspect.setSort(30);
		}
		model.addAttribute("proPlotAspect", proPlotAspect);
		return "modules/pro/proPlotAspectForm";
	}

	@RequiresPermissions("pro:proPlotAspect:edit")
	@RequestMapping(value = "save")
	public String save(ProPlotAspect proPlotAspect, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proPlotAspect)){
			return form(proPlotAspect, model);
		}
		proPlotAspectService.save(proPlotAspect);
		addMessage(redirectAttributes, "保存剧情看点成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proPlotAspect/?repage";
	}
	
	@RequiresPermissions("pro:proPlotAspect:edit")
	@RequestMapping(value = "delete")
	public String delete(ProPlotAspect proPlotAspect, RedirectAttributes redirectAttributes) {
		proPlotAspectService.delete(proPlotAspect);
		addMessage(redirectAttributes, "删除剧情看点成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proPlotAspect/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ProPlotAspect> list = proPlotAspectService.findList(new ProPlotAspect());
		for (int i=0; i<list.size(); i++){
			ProPlotAspect e = list.get(i);
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