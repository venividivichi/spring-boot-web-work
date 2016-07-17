package guru.springframework.repositories;

import guru.springframework.domain.Executant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ExecutantRepository extends JpaRepository<Executant, Integer> {


}
