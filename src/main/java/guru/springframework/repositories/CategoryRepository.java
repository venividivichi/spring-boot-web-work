package guru.springframework.repositories;

import guru.springframework.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by user on 10.06.2016.
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
