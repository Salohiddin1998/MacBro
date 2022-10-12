package uz.pdp.macbro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.macbro.model.AttachmentContent;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {
    AttachmentContent findByAttachmentId(Integer attachment_id);
}
