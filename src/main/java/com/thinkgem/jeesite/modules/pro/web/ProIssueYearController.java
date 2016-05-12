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
import com.thinkgem.jeesite.modules.pro.entity.ProIssueYear;
import com.thinkgem.jeesite.modules.pro.service.ProIssueYearService;

/**
 * 出品年份Controller
 * @author lin
 * @version 2016-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proIssueYear")
public class ProIssueYearController extends BaseController {

	@Autowired
	private ProIssueYearService proIssueYearService;
	
	@ModelAttribute
	public ProIssueYear get(@RequestParam(required=false) String id) {
		ProIssueYear entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proIssueYearService.get(id);
		}
		if (entity == null){
			entity = new ProIssueYear();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proIssueYear:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProIssueYear proIssueYear, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ProIssueYear> list = proIssueYearService.findList(proIssueYear); 
		model.addAttribute("list", list);
		return "modules/pro/proIssueYearList";
	}

	@RequiresPermissions("pro:proIssueYear:view")
	@RequestMapping(value = "form")
	public String form(ProIssueYear proIssueYear, Model model) {
		if (proIssueYear.getParent()!=null && StringUtils.isNotBlank(proIssueYear.getParent().getId())){
			proIssueYear.setParent(proIssueYearService.get(proIssueYear.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(proIssueYear.getId())){
				ProIssueYear proIssueYearChild = new ProIssueYear();
				proIssueYearChild.setParent(new ProIssueYear(proIssueYear.getParent().getId()));
				List<ProIssueYear> list = proIssueYearService.findList(proIssueYear); 
				if (list.size() > 0){
					proIssueYear.setSort(list.get(list.size()-1).getSort());
					if (proIssueYear.getSort() != null){
						proIssueYear.setSort(proIssueYear.getSort() + 30);
					}
				}
			}
		}
		if (proIssueYear.getSort() == null){
			proIssueYear.setSort(30);
		}
		model.addAttribute("proIssueYear", proIssueYear);
		return "modules/pro/proIssueYearForm";
	}

	@RequiresPermissions("pro:proIssueYear:edit")
	@RequestMapping(value = "save")
	public String save(ProIssueYear proIssueYear, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proIssueYear)){
			return form(proIssueYear, model);
		}
		proIssueYearService.save(proIssueYear);
		addMessage(redirectAttributes, "保存出品年份成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proIssueYear/?repage";
	}
	
	@RequiresPermissions("pro:proIssueYear:edit")
	@RequestMapping(value = "delete")
	public String delete(ProIssueYear proIssueYear, RedirectAttributes redirectAttributes) {
		proIssueYearService.delete(proIssueYear);
		addMessage(redirectAttributes, "删除出品年份成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proIssueYear/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ProIssueYear> list = proIssueYearService.findList(new ProIssueYear());
		for (int i=0; i<list.size(); i++){
			ProIssueYear e = list.get(i);
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