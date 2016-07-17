package guru.springframework.services;

import guru.springframework.domain.Executant;
import guru.springframework.repositories.ExecutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 13.06.2016.
 */

@Service
public class ExecutantServiceImpl implements ExecutantService {

    private ExecutantRepository executantRepository;

    @Autowired
    public void setCustomerRepository(ExecutantRepository executantRepository) {
        this.executantRepository = executantRepository;
    }

    @Override
    public List<Executant> listAllExecutants() {
        return executantRepository.findAll();
    }

    @Override
    public Executant getExecutantById(Integer id) {
        return executantRepository.findOne(id);
    }

    @Override
    public Executant saveExecutant(Executant executant) {
        return executantRepository.save(executant);
    }

    @Override
    public void deleteExecutant(Integer id) {
        executantRepository.delete(id);
    }

}
