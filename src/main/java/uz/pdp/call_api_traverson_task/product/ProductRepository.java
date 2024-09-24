package uz.pdp.call_api_traverson_task.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Transactional
    @Modifying
    @Query("""
            update Product p set p.name = ?1, p.description = ?2, p.price = ?3 where p.id = ?4""")
    int updateNameAndDescriptionAndPrice(String name, String description, Double price, Integer id);
}
