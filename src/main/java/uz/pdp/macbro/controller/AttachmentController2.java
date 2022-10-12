package uz.pdp.macbro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.pdp.macbro.model.Attachment;
import uz.pdp.macbro.model.AttachmentContent;
import uz.pdp.macbro.repository.AttachmentContentRepository;
import uz.pdp.macbro.repository.AttachmentRepository;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/attachment")

public class AttachmentController2 {

    @Autowired
    AttachmentRepository attachmentRepo;

    @Autowired
    AttachmentContentRepository attachmentContentRepo;

    @GetMapping({"/", "/getFormDb"})
    public String getForm() {
        return "file-upload-form-db";
    }

    @GetMapping("/getFilesDb")
    public String getFilesFromDb(Model model) {
        List<Attachment> files = attachmentRepo.findAll();
        model.addAttribute("fileList", files);
        return "files-db";
    }

    @PostMapping("/upload-db")
    public ResponseEntity<?> uploadFileDb(@RequestBody MultipartFile file,
                                          RedirectAttributes redirectAttributes) {
        try {
            Attachment savedAttch = attachmentRepo.save(new Attachment(file.getOriginalFilename(), file.getSize(), file.getContentType()));
            attachmentContentRepo.save(new AttachmentContent(file.getBytes(), savedAttch));
            return ResponseEntity.ok(savedAttch.getId());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @GetMapping("/download-db/{attachmentId}")
    public ResponseEntity<ByteArrayResource> downloadFileFromDb(@PathVariable Integer attachmentId){
        AttachmentContent attachmentContent = attachmentContentRepo.findByAttachmentId(attachmentId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachmentContent.getAttachment().getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + attachmentContent.getAttachment().getName() + "\"")
                .body(new ByteArrayResource(attachmentContent.getData()));
    }

}
