package cn.yangyawen.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class DownloadController {

    /**
     * 基于servlet API的文件下载
     */
    @RequestMapping("/download01")
    public String download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String realPath = request.getServletContext().getRealPath("/image/DSC_2316.jpg");
        File file = new File(realPath);
        String fileName = file.getName();
        response.setHeader("content-disposition", "attachment:filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        InputStream in = new FileInputStream(realPath);
        int len = 0;
        byte[] buffer = new byte[1024];
        OutputStream out = response.getOutputStream();
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        return null;
    }

    /**
     * 基于Spring ResponseEntity的文件下载 不支持缓冲区，会一次性把文件放到内存里，所以不建议使用
     * ResponseEntity 可以定制文件的响应内容、响应头、响应状态码
     */
    @RequestMapping("/download02")
    public ResponseEntity<String> download02() {
        String body = "Hello Spring";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Set-Cookie", "name=杨啊啊");
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    @RequestMapping("/download03")
    public ResponseEntity<byte[]> download03(HttpServletRequest request) throws IOException {
        String realPath = request.getServletContext().getRealPath("/image/DSC_2316.jpg");
        File file = new File(realPath);
        String fileName = file.getName();
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-disposition", "attachment:filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        InputStream in = null;
        try {
            in = new FileInputStream(realPath);
            return new ResponseEntity<>(new byte[in.available()], headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return null;
    }
}
