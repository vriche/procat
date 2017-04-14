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
import com.thinkgem.jeesite.modules.pro.entity.ProClass;
import com.thinkgem.jeesite.modules.pro.service.ProClassService;

/**
 * 节目分类Controller
 * @author lin
 * @version 2016-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proClass")
public class ProClassController extends BaseController {

	@Autowired
	private ProClassService proClassService;
	
	@ModelAttribute
	public ProClass get(@RequestParam(required=false) String id) {
		ProClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proClassService.get(id);
		}
		if (entity == null){
			entity = new ProClass();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProClass proClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ProClass> list = proClassService.findList(proClass); 
		model.addAttribute("list", list);
		return "modules/pro/proClassList";
	}

	@RequiresPermissions("pro:proClass:view")
	@RequestMapping(value = "form")
	public String form(ProClass proClass, Model model) {
		if (proClass.getParent()!=null && StringUtils.isNotBlank(proClass.getParent().getId())){
			proClass.setParent(proClassService.get(proClass.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(proClass.getId())){
				ProClass proClassChild = new ProClass();
				proClassChild.setParent(new ProClass(proClass.getParent().getId()));
				List<ProClass> list = proClassService.findList(proClass); 
				if (list.size() > 0){
					proClass.setSort(list.get(list.size()-1).getSort());
					if (proClass.getSort() != null){
						proClass.setSort(proClass.getSort() + 30);
					}
				}
			}
		}
		if (proClass.getSort() == null){
			proClass.setSort(30);
		}
		model.addAttribute("proClass", proClass);
		return "modules/pro/proClassForm";
	}

	@RequiresPermissions("pro:proClass:edit")
	@RequestMapping(value = "save")
	public String save(ProClass proClass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proClass)){
			return form(proClass, model);
		}
		proClassService.save(proClass);
		addMessage(redirectAttributes, "保存节目分类成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proClass/?repage";
	}
	
	@RequiresPermissions("pro:proClass:edit")
	@RequestMapping(value = "delete")
	public String delete(ProClass proClass, RedirectAttributes redirectAttributes) {
		proClassService.delete(proClass);
		addMessage(redirectAttributes, "删除节目分类成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proClass/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ProClass> list = proClassService.findList(new ProClass());
		for (int i=0; i<list.size(); i++){
			ProClass e = list.get(i);
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