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
    @ResponseBody
    public String  addResourceClass(ResourceClass resourceClass){
        String[] split = resourceClass.getUploadpath().split("\\\\");
        String path = "/" + split[split.length-3] + "/" + split[split.length-2] + "/" + split[split.length-1];
        resourceClass.setUploadpath(path);
        if(resourceClassService.addResourceClass(resourceClass)==null) {
            return "0";
        }else{
            return "1";
        }
    }

    /**
     * 删除类型
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeClass")
    @ResponseBody
    public String removeClass(@RequestParam("id")long id){
        boolean result=resourceClassService.removeResourceClass(id);
        if(result){
            return "1";
        }else{
            return "0";
        }
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
    @ResponseBody
    public String  updateClass(ResourceClass resourceClass){
        // try表示获取的是绝对路径 即：修改了图片
        try {
            String[] split =resourceClass.getUploadpath().split("\\\\");
            System.out.println(split);
            String path = "/" + split[split.length-3] + "/" + split[split.length-2] + "/" + split[split.length-1];
            resourceClass.setUploadpath(path);
        }catch (Exception e){   // catch 表示获取的是相对路径  即：没有更换图片
            resourceClass.setUploadpath(resourceClass.getUploadpath());
        }finally {
            if(resourceClassService.updateResourceClass(resourceClass) == null){
                return "0";
            }else{
                return "1";
            }
        }

    }

    /**
     * 遍历所有类型
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/listClass")
    @ResponseBody
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
