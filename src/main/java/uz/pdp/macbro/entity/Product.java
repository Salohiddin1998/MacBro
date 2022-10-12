package uz.pdp.macbro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.macbro.model.Attachment;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double price;

    private Integer quantity;

    @ManyToOne
    private Category category;

    @ManyToMany
    private List<CharacterCharValues> characterCharValues;

    @OneToOne
    private Attachment mainImage;

    @OneToMany
    private List<Attachment> imageList;
}
