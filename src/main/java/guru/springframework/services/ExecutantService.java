package guru.springframework.services;

import guru.springframework.domain.Executant;

import java.util.List;

public interface ExecutantService {

    List<Executant> listAllExecutants();

    Executant getExecutantById(Integer id);

    Executant saveExecutant(Executant executant);

    void deleteExecutant(Integer id);
}
