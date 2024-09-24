package uz.pdp.call_api_traverson_task.product;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        return ResponseEntity.ok(productService.createProduct(productCreateDTO));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getProductAll() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateProduct(@RequestBody Product updateProduct) {
        Product product = productRepository.findById(updateProduct.getId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + updateProduct.getId()));
        System.out.println("product: " + product);
        if (updateProduct.getId() != null) {
            product.setId(updateProduct.getId());
        }
        if (updateProduct.getName() != null) {
            product.setName(updateProduct.getName());
        }
        if (updateProduct.getDescription() != null) {
            product.setDescription(updateProduct.getDescription());
        }
        if (updateProduct.getPrice() != null) {
            product.setPrice(updateProduct.getPrice());
        }
        if (updateProduct.getDate() != null) {
            product.setDate(updateProduct.getDate());
        }
        productService.updateProduct(product);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProduct(@NonNull Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
