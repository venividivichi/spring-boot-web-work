package guru.springframework.repositories;

import guru.springframework.domain.MainDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by user on 13.06.2016.
 */
public interface MainDocRepository extends JpaRepository<MainDoc, Integer> {
}
