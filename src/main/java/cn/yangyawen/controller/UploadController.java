package cn.yangyawen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {

    /**
     * 基于SpringMVC MultipartFile实现单文件上传
     */
    @PostMapping("/upload01")
    public String upload01(String desc, MultipartFile myfile, Model model) throws IOException {
        System.out.println(desc);
        System.out.println(myfile.getOriginalFilename());
        String path = "/Users/yangyawen/img/" + myfile.getOriginalFilename();
        File file = new File(path);
        myfile.transferTo(file);
        model.addAttribute("imageName", myfile.getOriginalFilename());
        return "success";
    }

    /**
     * 基于SpringMVC MultipartFile实现多图片上传
     */
    @PostMapping("/upload02")
    public String upload02(String desc, MultipartFile[] myfiles) throws IOException {
        for (MultipartFile myfile : myfiles) {
            String path = "/Users/yangyawen/" + myfile.getOriginalFilename();
            File file = new File(path);
            myfile.transferTo(file);
        }
        return "success";
    }

    /**
     * 基于SpringMVC MultipartFile实现多图片多线程上传
     */
    @PostMapping("/upload03")
    public String upload03(String desc, MultipartFile[] myfiles) throws InterruptedException {
        for (MultipartFile myfile : myfiles) {
            Thread thread = new Thread(() -> {
                String path = "/Users/yangyawen/" + myfile.getOriginalFilename();
                File file = new File(path);
                try {
                    myfile.transferTo(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            thread.join();  // 让子线程执行完再执行主线程
        }
        return "success";
    }

}
