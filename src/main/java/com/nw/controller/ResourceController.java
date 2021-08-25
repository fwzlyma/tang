package com.nw.controller;

import com.nw.po.Resource;
import com.nw.po.User;
import com.nw.repository.ResourceRepository;
import com.nw.service.ResourceService;
import org.apache.shiro.SecurityUtils;
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
 * @Time : 2021/8/22 21:08
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ResourceRepository resourceRepository;

    /**
     * 跳转到唐卡列表
     * @param model
     * @return
     */
    @RequestMapping("/resourceListMain")
    public String resourceListMain(Model model){
        model.addAttribute("count", resourceService.getAllCount());
        return "collection-grid";
    }

    /**
     * 获取所有唐卡
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/allResource")
    @ResponseBody
    public Map<String, Object> getAllResource(@RequestParam(required = true)int page, @RequestParam(required = true)int limit){
        System.out.println("来获取唐卡列表了");
        Map<String, Object> result = new HashMap<String, Object>();
        Pageable pageable = PageRequest.of(page-1,limit);
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", resourceService.getAllCount());
        result.put("data", resourceService.findAllResource(pageable));
        return result;
    }

    /**
     * 获取唐卡详细信息
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/getDetail")
    public String getDetail(Model model, @RequestParam(required = true)long id){
        Resource resource = resourceService.findResourceById(id);
        model.addAttribute("resourceDetail", resource);
        return "detail";

    }

    /**
     * 根据类型获取对应的唐卡
     * @param model
     * @param page
     * @param limit
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getResourceListByType")
    public Map<String, Object> getResourceByType(Model model, @RequestParam(required = true)int page, @RequestParam(required = true)int limit, @RequestParam(required = true)long id){
        Map<String, Object> result = new HashMap<String, Object>();
        Pageable pageable = PageRequest.of(page-1,limit);
        List<Resource> resourceList = resourceService.findResourceByTypeid(id, pageable);
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", resourceList.size());
        result.put("data", resourceList);
        return result;

    }

    /***
     * 跳转至首页
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

    /**
     * 跳转至类型列表页
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/toType")
    public String toType(Model model, @RequestParam(required = true)long id){
        model.addAttribute("resourceTypeId", id);
        model.addAttribute("count", resourceService.findCountByTypeId(id));
        return "resourcetype-detail";
    }

    /**
     * 跳转至历史页面
     * @return
     */
    @RequestMapping("/toAbout")
    public String toAbout(){
        return "about";
    }

    /**
     * 跳转至科普页
     * @return
     */
    @RequestMapping("/toMembership")
    public String toMembership(){
        return "membership";
    }

    /**
     * 跳转至流派页
     * @return
     */
    @RequestMapping("/toVenue")
    public String toVenue(){
        return "venue";
    }

    /**
     * 跳转至绘制页面
     * @return
     */
    @RequestMapping("toEvent2")
    public String toEvent2(){
        return "event-2";
    }

    /**
     * 跳转至装裱页面
     * @return
     */
    @RequestMapping("toEvent1")
    public String toEvent1(){
        return "event-1";
    }

    /**
     * 添加唐卡
     * @param resource
     * @return
     */
    @RequestMapping("/addResource")
    public Resource addResource(Resource resource){
        User user=(User) SecurityUtils.getSubject().getSession().getAttribute("user");
        resource.setUploader(user.getId());
        return resourceService.addResource(resource);

    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeResource",method = POST)
    public boolean removeResource(@RequestParam("id")long id){
        return resourceService.removeResource(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/removeAnyResource")
    public int removeResource(@RequestParam("ids[]")long[] ids){
        return resourceService.removeAnyResources(ids);
    }

    /**
     * 更新唐咯信息
     * @param resource
     * @return
     */
    @RequestMapping("/updateResource")
    public Resource updateResource(Resource resource){
        return resourceService.updateResource(resource);
    }


    /**
     * 遍历所有唐咯
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/listResoure")
    public Map<String, Object> listResoure(@RequestParam(required = true)int page, @RequestParam(required = true)int limit ){

        Pageable pageable = PageRequest.of(page-1,limit);
        long count=resourceRepository.count();
        List<Resource> lists= resourceRepository.findAll( pageable).getContent();
        Map<String,Object> result=new HashMap<>();
        result.put("msg","");
        result.put("count",count);
        result.put("code",0);
        result.put("data",lists);
        return result;
    }

    /**
     * 通过类型查找唐卡
     * @param page
     * @param limit
     * @param type
     * @return
     */
    @RequestMapping("/selectResourceBytype")
    public Map<String, Object> selectResource(@RequestParam(required = true)int page,@RequestParam(required = true)int limit, @RequestParam("type")String type){

        Pageable pageable =PageRequest.of(page-1,limit);
        long count=resourceRepository.countByType(type);
        List<Resource> lists=resourceRepository.getAllByType(type,pageable);
        Map<String,Object> result=new HashMap<String,Object>();
        result.put("msg","");
        result.put("count",count);
        result.put("code",0);
        result.put("data",lists);
        return result;
    }
}
