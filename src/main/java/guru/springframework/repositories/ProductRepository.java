package guru.springframework.repositories;

import guru.springframework.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
