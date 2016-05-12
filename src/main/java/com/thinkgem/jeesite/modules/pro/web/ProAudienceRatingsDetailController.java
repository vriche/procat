/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceRatings;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceRatingsDetail;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceTarget;
import com.thinkgem.jeesite.modules.pro.service.ProAudienceRatingsDetailService;
import com.thinkgem.jeesite.modules.pro.service.ProAudienceRatingsService;
import com.thinkgem.jeesite.modules.pro.service.ProAudienceTargetService;
import com.thinkgem.jeesite.modules.pro.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 收视明细Controller
 * @author lin
 * @version 2016-05-23
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/proAudienceRatingsDetail")
public class ProAudienceRatingsDetailController extends BaseController {
	
	@Autowired
	private ProAudienceTargetService proAudienceTargetService;
	
	@Autowired
	private ProAudienceRatingsService proAudienceRatingsService;

	@Autowired
	private ProAudienceRatingsDetailService proAudienceRatingsDetailService;
	
	
	@ModelAttribute
	public ProAudienceRatingsDetail get(@RequestParam(required=false) String id) {
		ProAudienceRatingsDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proAudienceRatingsDetailService.get(id);
		}
		if (entity == null){
			entity = new ProAudienceRatingsDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:proAudienceRatingsDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProAudienceRatingsDetail proAudienceRatingsDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
//		System.out.print("list 88888888888888888888888>>>>>>"+proAudienceRatingsDetail.getProAudienceRatings().getProBaseInfo().getId()+"\n");
		Page<ProAudienceRatingsDetail> page = proAudienceRatingsDetailService.findPage(new Page<ProAudienceRatingsDetail>(request, response), proAudienceRatingsDetail); 
		model.addAttribute("page", page);
		return "modules/pro/proAudienceRatingsDetailList";
	}

	@RequiresPermissions("pro:proAudienceRatingsDetail:view")
	@RequestMapping(value = "form")
	public String form(ProAudienceRatingsDetail proAudienceRatingsDetail, Model model) {
		model.addAttribute("proAudienceRatingsDetail", proAudienceRatingsDetail);
		return "modules/pro/proAudienceRatingsDetailForm";
	}

	@RequiresPermissions("pro:proAudienceRatingsDetail:edit")
	@RequestMapping(value = "save")
	public String save(ProAudienceRatingsDetail proAudienceRatingsDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proAudienceRatingsDetail)){
			return form(proAudienceRatingsDetail, model);
		}
		proAudienceRatingsDetailService.save(proAudienceRatingsDetail);
		addMessage(redirectAttributes, "保存收视明细成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proAudienceRatingsDetail/?repage";
	}
	
	@RequiresPermissions("pro:proAudienceRatingsDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(ProAudienceRatingsDetail proAudienceRatingsDetail, RedirectAttributes redirectAttributes) {
		proAudienceRatingsDetailService.delete(proAudienceRatingsDetail);
		addMessage(redirectAttributes, "删除收视明细成功");
		return "redirect:"+Global.getAdminPath()+"/pro/proAudienceRatingsDetail/?repage";
	}
	
	
	/**
	 * 导入数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pro:proAudienceRatingsDetail:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/pro/proAudienceRatingsDetail/?repage";
		}

		try {
			int successNum = 0;
			int failureNum = 0;
			int rowIndex = 0;
			int colTagerIndex = 8;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 1);
			
//			System.out.print("importFile ei 88888888888888888888888>>>>>>");
//			for (int i = ei.getDataRowNum(); i < ei.getLastDataRowNum(); i++) {
//				Row row = ei.getRow(i);
//				for (int j = 0; j < ei.getLastCellNum(); j++) {
//					Object val = ei.getCellValue(row, j);
//					System.out.print(val+", ");
//				}
//			}
			List<String> targValues = new ArrayList();
			Map<String,Object>  targMap1= DictUtils.getDictMap("proAudienceTarget",1);
			Map<String,Object>  targMap2= DictUtils.getDictMap("proAudienceTarget",2);
			Row row = ei.getRow(1);
			for (int j = colTagerIndex; j < ei.getLastCellNum(); j++) {
				Object val = ei.getCellValue(row, j);
				String id = String.valueOf(targMap1.get(val));
				targValues.add(id);
//				System.out.print("importFile 6666666 >>>>>>targValues id>>>>>>>>>"+id+"\n");
			}
//			System.out.print("importFile 6666666 >>>>>>targValues.size()>>>>>>>>>"+targValues.size()+"\n");
	
			Map<String,Map<String,Object>> mpAll = getProAudienceRatingsDetailExit();
			Map<String,Object> mpMain = mpAll.get("keyMain");
			Map<String,Object> mpDetail = mpAll.get("keyDetail");
			
			
			List<ProAudienceRatings> listMain = ei.getDataList(ProAudienceRatings.class);
			
			System.out.print("importFile 77777777 >>>>>>listDetail.size()>>>>>>>>>"+listMain.size()+"\n");
			int i = ei.getDataRowNum();
			for (ProAudienceRatings main : listMain){

				main.setOffice(new Office("1"));
				main.setVersion(new Integer(0));
	
				Row rowDetail = ei.getRow(i++);
				String proName = main.getProBaseInfo().getName();
//				System.out.print("importFile 888888888888 5>>>>>>targId>>>>>>>>>"+ detail.getBroDate()+"\n");
				String broDate = DateUtils.formatDate((main.getBroDate()),"yyyy-MM-dd");;
//				System.out.print("importFile 888888888888 6>>>>>>targId>>>>>>>>>"+ detail.getProAudienceSource()+"\n");
				String sourceName =main.getProAudienceSource().getName();
//				System.out.print("importFile 888888888888 7>>>>>>targId>>>>>>>>>"+ detail.getProAudienceCarrier()+"\n");
				String carrierName = main.getProAudienceCarrier().getName();
				
				
				String broTimeStartStr ="1999-01-01 "+  String.valueOf(ei.getCellValue(rowDetail, 6));
				String broTimeEndStr =  "1999-01-01 "+String.valueOf(ei.getCellValue(rowDetail, 7));
				Date  broTimeStart =  DateUtils.parseDate(broTimeStartStr,"yyyy-MM-dd HH:mm:ss");
				Date  broTimeEnd =  DateUtils.parseDate(broTimeEndStr,"yyyy-MM-dd HH:mm:ss");
				main.setBroTimeStart(broTimeStart);
				main.setBroTimeEnd(broTimeEnd);
				

				System.out.print("importFile sourceName 99999999999999999999999>>>>>>"+sourceName+"\n");
				System.out.print("importFile carrierName 99999999999999999999999>>>>>>"+carrierName+"\n");
				System.out.print("importFile proName 99999999999999999999999 >>>>>>"+proName+"\n");
				System.out.print("importFile broDate 99999999999999999999999>>>>>>"+broDate+"\n");
				System.out.print("importFile broTimeStart 99999999999999999999999>>>>>>"+broTimeStart+"\n");
				System.out.print("importFile broTimeEnd 99999999999999999999999>>>>>>"+broTimeEnd+"\n");
			
				
				
				try{
//					System.out.print("importFile 8888 1>>>>>>getProAudienceCity>>>>>>>>>"+detail.getProAudienceCity().getName()+"\n");
//					System.out.print("importFile 888888888888 3>>>>>>detail.getProBaseInfo()>>>>>>>>>"+ detail.getProBaseInfo()+"\n");
					
					String mainId = checkExitsMain(mpMain,main);
					if(!"0".equals(mainId)){
						main.setId(mainId);
//						proAudienceRatingsService.save(main);
					}else{
						main.setId(null);
						BeanValidators.validateWithException(validator, main);
						proAudienceRatingsService.save(main);
					}

					System.out.print("importFile mainId 99999999999999999999999>>>>>>"+ mainId +"\n");

					int j = colTagerIndex;
					
					for (String targId : targValues){
						ProAudienceRatingsDetail detail = new ProAudienceRatingsDetail();
						String targName = String.valueOf(targMap2.get(targId));
						String audienceRate = String.valueOf(ei.getCellValue(rowDetail, j++));
						
						detail.setMarketRate("0");
						detail.setProAudienceRatings(main);
						ProAudienceTarget proAudienceTarget = new  ProAudienceTarget(targId);
						detail.setProAudienceTarget(proAudienceTarget);
						detail.setAudienceRate(audienceRate);
						
//						ProAudienceRatingsDetail detail2 = (ProAudienceRatingsDetail)BeanUtils.cloneBean(detail);

						String detailId = checkExitsDetail(mpDetail,detail);
						
						System.out.print("importFile targName 000000000000 >>>>>>"+targName+ " || "+audienceRate+ "|"+detailId+ "\n");
						
						if(!"0".equals(detailId)){
							detail.setId(detailId);
							proAudienceRatingsDetailService.save(detail);
							addMessage(redirectAttributes, "<"+proName+"> "+targName+" "+broDate+"不允许操作！");
							failureMsg.append("<br/><"+proName+"> "+targName+" "+ broDate +" 已存在; ");
							failureNum++;
						}else{
							detail.setId(null);
							BeanValidators.validateWithException(validator, detail);
							proAudienceRatingsDetailService.save(detail);
							successNum++;
						}

						
					}
					
			
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>节目名 "+ proName+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>节目名 "+ proName +" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/pro/proAudienceRatingsDetail/?repage";
    }
	


	private Map<String,Map<String,Object>> getProAudienceRatingsDetailExit(){
		Map<String,Map<String,Object>> mp = Maps.newHashMap();
		Map<String,Object> mpMain = Maps.newHashMap();
		Map<String,Object> mpDetail = Maps.newHashMap();
		List<ProAudienceRatingsDetail> listDetail = proAudienceRatingsDetailService.findList(new ProAudienceRatingsDetail());
		for (ProAudienceRatingsDetail detail : listDetail){
			ProAudienceRatings main = detail.getProAudienceRatings();
			String officeId = main.getOffice().getId();
			String sourceId = main.getProAudienceSource().getId();
			String carrierId = main.getProAudienceCarrier().getId();
			String proId = main.getProBaseInfo().getId();
			String crownName = main.getCrownName();
			String date = DateUtils.formatDate(main.getBroDate(), "yyyy-MM-dd");
			String  broTimeStart =  DateUtils.formatDate(main.getBroTimeStart(), "HH:mm:ss");
			String  broTimeEnd =  DateUtils.formatDate(main.getBroTimeEnd(), "HH:mm:ss");

			String targId = detail.getProAudienceTarget().getId();
			
			String keyMain = officeId+","+sourceId+","+carrierId+","+proId+","+crownName+","+date+","+broTimeStart+","+broTimeEnd;
			
			String keyDetail = keyMain+","+targId;
//			System.out.print("key>>>>>>"+key);
//			System.out.print("\n");
			
			if(!mpMain.containsKey(keyMain))mpMain.put(keyMain, detail.getProAudienceRatings().getId());
			if(!mpDetail.containsKey(keyDetail))mpDetail.put(keyDetail, detail.getId());
		}
		mp.put("keyMain", mpMain);
		mp.put("keyDetail", mpDetail);
		
		return mp;
	}
	

	
	private String  checkExitsMain(Map<String,Object> mp,ProAudienceRatings main){
		
		String officeId = main.getOffice().getId();
		String sourceId = main.getProAudienceSource().getId();
		String carrierId = main.getProAudienceCarrier().getId();
		String proId = main.getProBaseInfo().getId();
		String crownName = main.getCrownName();
		String date = DateUtils.formatDate(main.getBroDate(), "yyyy-MM-dd");
		String broTimeStart =  DateUtils.formatDate(main.getBroTimeStart(), "HH:mm:ss");
		String broTimeEnd =  DateUtils.formatDate(main.getBroTimeEnd(), "HH:mm:ss");
		
		String keyMain = officeId+","+sourceId+","+carrierId+","+proId+","+crownName+","+date+","+broTimeStart+","+broTimeEnd;
//		Map<String,Object> mpMain = mp.get("keyMain");
//		Map<String,Object> mpDetail = mp.get("keyDetail");
		if(mp.containsKey(keyMain)) return (String)mp.get(keyMain);
		return "0";
	}
	private String  checkExitsDetail(Map<String,Object> mp,ProAudienceRatingsDetail detail){
		
				
		ProAudienceRatings main = detail.getProAudienceRatings();
		String officeId = main.getOffice().getId();
		String sourceId = main.getProAudienceSource().getId();
		String carrierId = main.getProAudienceCarrier().getId();
		String proId = main.getProBaseInfo().getId();
		String crownName = main.getCrownName();
		String date = DateUtils.formatDate(main.getBroDate(), "yyyy-MM-dd");
		String broTimeStart =  DateUtils.formatDate(main.getBroTimeStart(), "HH:mm:ss");
		String broTimeEnd =  DateUtils.formatDate(main.getBroTimeEnd(), "HH:mm:ss");
		
		String targId = detail.getProAudienceTarget().getId();
		
		
		
		String keyMain = officeId+","+sourceId+","+carrierId+","+proId+","+crownName+","+date+","+broTimeStart+","+broTimeEnd;
		String keyDetail = keyMain+","+targId;

//		Map<String,Object> mpDetail = mp.get("keyDetail");
		if(mp.containsKey(keyDetail)) return (String)mp.get(keyDetail);
		return "0";
	}

}