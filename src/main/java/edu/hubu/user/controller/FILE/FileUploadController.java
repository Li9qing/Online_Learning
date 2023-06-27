package edu.hubu.user.controller.FILE;

import edu.hubu.common.utils.R;
import edu.hubu.user.entity.UserEntity;
import edu.hubu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
@RequestMapping("user/center")
@CrossOrigin
@RestController
public class FileUploadController {
    @Autowired
    UserService SERuser;
    private final String DIR = "C:\\Users\\K\\Videos\\summer_camp\\SELF\\images";
    @RequestMapping("/ttt")
    public R ttt(){
        return R.error(2000,"try me");
    }


    @RequestMapping("/upload")
    public R uploadFile(@RequestBody MultipartFile file,@RequestParam("token")String token) {
        // 根据业务逻辑处理文件上传和保存
        if(file == null)return R.error(404,"no img");

        UserEntity user = SERuser.getUserDetail(token);
        if(user == null)return R.error(404,"NO USER!");

        UserEntity usr = SERuser.findUserInfo(user.getUsername());
        if(usr == null||usr.getId()==null)return R.error(404,"NO USER!");


        return PraseFileToUser(file, usr);
    }
    @RequestMapping("/save")
    public R save(@RequestBody MultipartFile file,@RequestParam("id")String id) {
        // 根据业务逻辑处理文件上传和保存
        if(file == null)return R.error(404,"no img");


        UserEntity usr = SERuser.getById(id);
        System.out.println(usr);
        if(usr == null||usr.getId()==null)return R.error(404,"NO USER!");


        return PraseFileToUser(file, usr);
    }

    private R PraseFileToUser(MultipartFile file, UserEntity usr) {
        R r1 ;
        if (!file.isEmpty()) {
            try {
                // 获取文件名
                String fileName = usr.getId()+  file.getOriginalFilename();

                // 生成文件保存路径，例如在本地存储
                String filePath = DIR + fileName;

                // 将文件保存到指定路径
                File dest = new File(filePath);
                file.transferTo(dest);

                // 将文件地址与ID关联保存到数据库或其他持久化存储中
                // 这里只是示例，可以根据具体业务需求进行调整
                usr.setAvatar(fileName);
                r1 =  R.ok("文件上传成功");
            } catch (IOException e) {
                e.printStackTrace();
                r1 =  R.error(500,("文件上传失败"));
            }
        } else {
            r1 =  R.error(404,"文件为空");
        }
        if(!SERuser.updateById(usr)) r1 =  R.error(500,"SQL EXEC FAIL!");
        return  r1;
    }

    @GetMapping("/iconPerson/{id}")
    public R displayImage(@PathVariable String id,String avatar) {
        // 根据ID从数据库或其他持久化存储中获取文件地址
        String filePath = DIR + id + avatar;

        try {
            // 创建文件资源对象
            Resource resource = new UrlResource(filePath);

            // 检查文件是否存在并可读
            if (resource.exists() && resource.isReadable()) {
                // 设置响应头，告诉客户端返回的是图片
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // 根据实际情况设置图片类型

                // 返回文件资源对象
                return R.ok().put("img",resource);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // 文件不存在或无法访问时返回404错误
        return R.error(404,"not found");
    }

}
