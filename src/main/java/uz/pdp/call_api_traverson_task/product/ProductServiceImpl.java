package uz.pdp.call_api_traverson_task.product;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public ProductDTO createProduct(@NonNull ProductCreateDTO productCreateDTO) {
        Product product = Product.builder()
                .name(productCreateDTO.getName())
                .description(productCreateDTO.getDescription())
                .price(productCreateDTO.getPrice())
                .date(productCreateDTO.getDate())
                .build();
        productRepository.save(product);
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .date(product.getDate())
                .build();
    }

    @Override
    public ProductDTO getProduct(@NonNull Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
        productRepository.save(product);
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .date(product.getDate())
                .build();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(@NonNull Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(@NonNull Integer id) {
        productRepository.deleteById(id);
    }
}
