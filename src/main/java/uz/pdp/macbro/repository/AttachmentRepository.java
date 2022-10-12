package uz.pdp.macbro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.macbro.model.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
