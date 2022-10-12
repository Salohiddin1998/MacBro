package uz.pdp.macbro.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.macbro.dto.CharacterValuesDto;
import uz.pdp.macbro.dto.ProductDto;
import uz.pdp.macbro.entity.Category;
import uz.pdp.macbro.entity.Character;
import uz.pdp.macbro.entity.CharacterValue;
import uz.pdp.macbro.entity.Product;
import uz.pdp.macbro.model.Attachment;
import uz.pdp.macbro.projection.ProductProjection;
import uz.pdp.macbro.repository.CharacterCharValueRepository;
import uz.pdp.macbro.repository.CharacterValueRepo;
import uz.pdp.macbro.repository.CharacteristicRepository;
import uz.pdp.macbro.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepo;

    private final CharacterCharValueRepository characterValueRepo;
    private final CharacteristicRepository characteristicRepository;
    private final CharacterValueRepo valueRepo;


    public List<ProductProjection> getAllProductsFromDb() {
        List<ProductProjection> allProduct = productRepo.findAllProduct();
        return allProduct;
    }


    @Transactional
    public void addProductForDb(ProductDto productDto) {

        Product product = Product.builder()
                .name(productDto.getName())
                .quantity(productDto.getQuantity())
                .price(productDto.getPrice())
                .mainImage(Attachment.builder()
                        .id(productDto.getMainImageId()).build())
                .category(Category.builder()
                        .id(productDto.getCategoryId()).build())
                .build();

        productRepo.save(product);


        int lastIdProduct = productRepo.lastIndexProduct();

        for (CharacterValuesDto characterValuesDto : productDto.getCharacterAndValuesList()) {
            Optional<CharacterValue> valueById = valueRepo.findById(characterValuesDto.getValueId());
            Optional<Character> characterById = characteristicRepository.findById(characterValuesDto.getCharacterId());
            if (valueById.isPresent() && characterById.isPresent()) {
                Integer elementFromDb = characterValueRepo.findElementFromDb(characterValuesDto.getCharacterId(),
                        characterValuesDto.getValueId());
                if (elementFromDb != 0) {
                    productRepo.addProductCharValues(lastIdProduct, elementFromDb);
                }
            }
        }
    }


}
