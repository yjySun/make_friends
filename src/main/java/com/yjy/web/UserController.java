package com.yjy.web;

import com.yjy.pojo.User;
import com.yjy.service.impl.CommentServiceImpl;
import com.yjy.service.impl.StatisticsServiceImpl;
import com.yjy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private StatisticsServiceImpl statisticsService;

    @GetMapping("/editUser")
    @ResponseBody
    public String editUser(User user, HttpSession session){
        User nameUser = userService.getEditUser(user);
        if(nameUser == null) {
            try {
                userService.editUser(user);
            } catch (Exception e) {
                e.printStackTrace();
                return "editFailure";
            }
        }else {
            return "usernameExist";
        }
        User editUser = userService.getUser(user.getId());
        session.setAttribute("user", editUser);

        statisticsService.editManCount();
        statisticsService.editManRatio();

        return "editSuccess";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(Integer id,String originalImages, MultipartFile uploadFile, HttpServletRequest request, HttpSession session) {

        File folder = new File(request.getServletContext().getRealPath("titleImages"));

        if (!folder.isDirectory()) {
            folder.mkdirs();
        }

        // 如果以前上传过头像则删除原头像
        File originalFile = new File(request.getServletContext().getRealPath("titleImages")+"\\"+originalImages);
        if(originalFile.exists()){
            originalFile.delete();
        }

        // 对上传的文件重命名，避免文件重名
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));

        userService.editTitleImages(id, newName);
        User user = userService.getUser(id);
        commentService.editCommentImages(id, newName);//改变此评论时显示的头像

        session.setAttribute("user",user);
        try {
            // 文件保存
            uploadFile.transferTo(new File(folder, newName));
            return "uploadSuccess";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "uploadFailure";
    }
}
