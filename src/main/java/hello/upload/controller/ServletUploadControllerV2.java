package hello.upload.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v2")
public class ServletUploadControllerV2 {

    /*
     application.properties에 있는 file.dir을 가져와서 쓸수있음 신기하네
    * */
    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile(HttpServletRequest request, Model model){
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV1(HttpServletRequest request) throws ServletException, IOException {

        String itemName = request.getParameter("itemName");
        Collection<Part> parts = request.getParts();

        for (Part part : parts) {
            //데이터 읽기
            InputStream inputStream = part.getInputStream();
            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

            //파일에 저장하기
            if(StringUtils.hasText(part.getSubmittedFileName())){
                //파일 풀 경로
                String fullPath = fileDir + part.getSubmittedFileName();
                part.write(fullPath);
            }
        }
        return "/servlet/v2/upload";
    }

}
