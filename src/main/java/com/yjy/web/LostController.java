package com.yjy.web;

import com.yjy.pojo.Lost;
import com.yjy.pojo.LostGather;
import com.yjy.pojo.LostImages;
import com.yjy.pojo.User;
import com.yjy.service.impl.LostServiceImpl;
import com.yjy.service.impl.StatisticsServiceImpl;
import com.yjy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class LostController {

    @Autowired
    private LostServiceImpl lostService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StatisticsServiceImpl statisticsService;


    @PostMapping("/lost")
    @ResponseBody
    public String lost(Lost lost,HttpSession session, MultipartFile lostImages1,MultipartFile lostImages2,MultipartFile lostImages3, HttpServletRequest request){
        String uuid = UUID.randomUUID() + "";
        lost.setUuid(uuid);
        lost.setLookCount(0);
        lost.setPushTime(new Date());
        lostService.addLost(lost);
        userService.editLostCount(lost.getUserId());
        /*上传失物图片*/
        lostImages(lostImages1, uuid, request);
        lostImages(lostImages2, uuid, request);
        lostImages(lostImages3, uuid, request);

        User user = userService.getUser(lost.getUserId());
        session.setAttribute("user",user);
        statisticsService.editAllLost();
        return "addSuccess";
    }

    @GetMapping("/market")
    public String Marker(HttpSession session, Model model){
        List<LostGather> allLost = lostService.getAllLost();
        for (int i = 0; i < allLost.size(); i++) {
            LostGather lostGather = allLost.get(i);
            List<String> lostImages = lostService.getLostImages(lostGather.getUuid());
            lostGather.setLostImages(lostImages);
        }

        session.setAttribute("allLost",allLost);
        return "/part/market";
    }

    @PostMapping("/addLookCount")
    @ResponseBody
    public String addLookCount(int id, HttpSession session, Model model){
        lostService.editLookCount(id);
        return "";
    }

    @GetMapping("/userMarket")
    public String userMarket(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<LostGather> allLost = lostService.getUserLost(user.getId());
        for (int i = 0; i < allLost.size(); i++) {
            LostGather lostGather = allLost.get(i);
            List<String> lostImages = lostService.getLostImages(lostGather.getUuid());
            lostGather.setLostImages(lostImages);
        }

        session.setAttribute("allLost",allLost);
        return "/part/userMarket";
    }

    @GetMapping("/deleteLost")
    @ResponseBody
    public String deleteLost(String uuid, HttpSession session, Model model, HttpServletRequest request){
        List<String> lostImages = lostService.getLostImages(uuid);
        if(lostImages.size() != 0){
            for (int i = 0; i < lostImages.size(); i++) {
                // 如果以前有图片则删除
                File originalFile = new File(request.getServletContext().getRealPath("lostImages")+"\\"+lostImages.get(i));
                if(originalFile.exists()){
                    originalFile.delete();
                }
            }
        }

        User user = (User) session.getAttribute("user");
        lostService.deleteLostImages(uuid);
        lostService.deleteLost(uuid);
        userService.subtractLostCount(user.getId());

        User user1 = userService.getUser(user.getId());
        session.setAttribute("user",user1);

        statisticsService.editAllLost();
        return "deleteSuccess";
    }

    public void lostImages(MultipartFile file,String uuid,HttpServletRequest request){
        if(file != null){
            File folder = new File(request.getServletContext().getRealPath("lostImages"));
            if (!folder.isDirectory()) {
                folder.mkdirs();
            }
            // 对上传的文件重命名，避免文件重名
            String oldName = file.getOriginalFilename();
            String newName = UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));
            LostImages lostImages = new LostImages();
            lostImages.setImages(newName);
            lostImages.setLostUuid(uuid);
            try {
                // 文件保存
                file.transferTo(new File(folder, newName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            lostService.addLostImages(lostImages);
        }

    }
}
