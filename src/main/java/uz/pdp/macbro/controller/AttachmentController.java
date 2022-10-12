package uz.pdp.macbro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class AttachmentController {

    String filePath = "Users/MyPic/";

    @GetMapping("/getForm")
    public String getForm() {
        return "index";
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(filePath + fileName));
            redirectAttributes.addFlashAttribute("msg", "Succesfully uploaded!");
            return "redirect:/getFiles";
        } catch (IOException e) {
            return "index";
        }


    }


    @GetMapping("/getFiles")
    public String getFiles(Model model) {
        File folder = new File(filePath);
        File[] files = folder.listFiles();
        model.addAttribute("fileList", files);
        return "files";
    }

    @GetMapping("files/{fileName}")
    @ResponseBody
    public void downloadFile(@PathVariable String fileName, HttpServletResponse response) {
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setHeader("Content-Transfer-Encoding", "binary");
        try {
            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            FileInputStream inputStream = new FileInputStream(filePath + fileName);
            int len;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.close();
            response.flushBuffer();;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
