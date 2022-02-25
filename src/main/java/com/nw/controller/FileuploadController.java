package com.nw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class FileuploadController {

    @RequestMapping("/file")
    @ResponseBody
    public Object uploadSimpleFile(@RequestParam("file") MultipartFile file, HttpServletRequest req, Model model){
        String fileName = file.getOriginalFilename();
        // 返回对象
        Map<String,Object> resultMap = new HashMap<>();
        // 获取时间戳
        String pathValue = System.currentTimeMillis()+"";
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\upload\\" + pathValue+".jpg";
        System.out.println("path:" + path);
        File newFile = new File(path);
        if(!newFile.exists()){
            newFile.mkdirs();
        }
        try {
            newFile.createNewFile();
            file.transferTo(newFile);
            // 返回给前端的数据
            resultMap.put("code", "0");
            resultMap.put("uploadpath", path);
            System.out.println("上传成功！");
        } catch (IOException e) {
            System.out.println("上传失败！");
            e.printStackTrace();
        }
        return  resultMap;
    }


}
