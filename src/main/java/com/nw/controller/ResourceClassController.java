package com.nw.controller;

import com.nw.po.ResourceClass;
import com.nw.repository.ResourceClassRepository;
import com.nw.service.ResourceClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @Author : baitao
 * @Time : 2021/8/21 20:23
 */
@Controller
@RequestMapping("/resourceClassType")
public class ResourceClassController {
    @Autowired
    private ResourceClassService resourceClassService;
    @Autowired
    private ResourceClassRepository resourceClassRepository;

    @RequestMapping("/toAllType")
    public String toAllType(){
        return "collection-fullwidth";
    }
    /**
     * 获取唐卡类型列表
     * @param
     * @param
     * @return
     */
    @RequestMapping("/getTypeList")
    @ResponseBody
    public Map<String, Object> getTypeClass(){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", resourceClassService.getAllCount());
        result.put("data", resourceClassService.getAllType());
        return result;
    }

    /**
     * 获取类型详情
     * @param id
     * @return
     */
    @RequestMapping("/getDetailType")
    public String getDetail(Model model, @RequestParam(required = true)long id){
        ResourceClass resourceClass = resourceClassService.findResourceClassById(id);
        model.addAttribute("typeDetail", resourceClass);
        return "type-detail";

    }

    /**
     * 添加类型
     * @param resourceClass
     * @return
     */
    @RequestMapping("/addClass")
    public ResourceClass addResourceClass(ResourceClass resourceClass){

        return resourceClassService.addResourceClass(resourceClass);
    }

    /**
     * 删除类型
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeClass",method =POST)
    public int removeClass(@RequestParam("id")long id){
        return resourceClassService.removeResourceClass(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/removeAnyClass")
    public int removeAnyClass(@RequestParam("ids[]")long[] ids){
        return resourceClassService.removeAnyClass(ids);
    }

    /**
     * 增加类型
     * @param resourceClass
     * @return
     */
    @RequestMapping("/updateClass")
    public ResourceClass updateClass(ResourceClass resourceClass){
//        resourceClass.setId(5L);
//        resourceClass.setTypename("dhfjk");
        return resourceClassService.updateResourceClass(resourceClass);
    }

    /**
     * 遍历所有类型
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/listClass")
    public Map<String, Object> listClass(@RequestParam(required = true)int page, @RequestParam(required = true)int limit ){

        Pageable pageable = PageRequest.of(page-1,limit);
        long count=resourceClassRepository.count();
        List<ResourceClass> lists=resourceClassRepository.findAll( pageable).getContent();
        Map<String,Object> result=new HashMap<String,Object>();
        result.put("msg","");
        result.put("count",count);
        result.put("code",0);
        result.put("data",lists);
        return result;
    }

}
