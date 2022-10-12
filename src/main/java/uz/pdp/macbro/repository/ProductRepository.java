package uz.pdp.macbro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.macbro.entity.Product;
import uz.pdp.macbro.projection.ProductProjection;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select p.id, " +
            "name, price, quantity, category_id as categoryId, " +
            "main_image_id as mainImageId from products p", nativeQuery = true)
    List<ProductProjection> findAllProduct();

    @Query(nativeQuery = true, value = "select last_value from products_id_seq")
    int lastIndexProduct();


    @Modifying
    @Query(nativeQuery = true, value = "insert into products_character_char_values(products_id, character_char_values_id)\n" +
            "VALUES (?, ?)")
    void addProductCharValues(int lastIdProduct, int elementFromDb);


}
