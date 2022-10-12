package uz.pdp.macbro.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.macbro.dto.ProductDto;
import uz.pdp.macbro.projection.ProductProjection;
import uz.pdp.macbro.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductProjection> getAllProducts() {
        List<ProductProjection> products = productService.getAllProductsFromDb();
        return products;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addProductForDb(
            @RequestBody ProductDto productDto,
            @RequestPart MultipartFile file
    ) {


        productService.addProductForDb(productDto);
        return ResponseEntity.ok(productDto);
    }


}
