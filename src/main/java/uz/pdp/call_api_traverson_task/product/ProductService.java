package uz.pdp.call_api_traverson_task.product;


import org.springframework.lang.NonNull;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(@NonNull ProductCreateDTO productCreateDTO);
    ProductDTO getProduct(@NonNull Integer id);
    List<Product> getAllProduct();
    void updateProduct(@NonNull Product product);
    void deleteProduct(@NonNull Integer id);
}
