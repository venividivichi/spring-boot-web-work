package guru.springframework.services;

import guru.springframework.domain.Executant;

public interface ExecutantService {

    Iterable<Executant> listAllExecutants();

    Executant getExecutantById(Integer id);

    Executant saveExecutant(Executant executant);

    void deleteExecutant(Integer id);
}
