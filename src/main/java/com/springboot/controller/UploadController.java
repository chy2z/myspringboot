package com.springboot.controller;

import com.springboot.fastFDS.FastFDSConfig;
import com.springboot.model.RequestResult;
import com.springboot.util.FastDFSUtil;
import com.springboot.util.JsonUtil;
import com.springboot.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @Title: UploadController
* @Description: 上传文件测试
* @author chy
* @date 2018/3/30 13:38
*/
@RestController
@EnableAutoConfiguration
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    FastFDSConfig fastFDSConfig;

    @RequestMapping("/index")
    public String index(){

        return "index";
    }

    @RequestMapping("/upfile")
    public void uploadfile(@RequestParam(value = "file",required = false) MultipartFile file,
                             HttpServletRequest request ,
                             HttpServletResponse response) throws IOException {

        RequestResult result = new RequestResult();

        String fileExt = "png";

        String fileName = file.getOriginalFilename();

        if (StringUtil.isNotBlank(fileName) && fileName.contains(".")) {
            //去除"."字符
            fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
        }

        String path = FastDFSUtil.uploadPic(fastFDSConfig.getConfig(),fileExt, file);

        result.setSucceed("上传成功", path);

        response.getWriter().write(JsonUtil.writeValueAsString(result));
    }

}
