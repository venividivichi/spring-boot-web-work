package guru.springframework.services;

import guru.springframework.domain.MainDoc;
import guru.springframework.repositories.MainDocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 13.06.2016.
 */
@Service
public class MainDocServiceImpl implements MainDocService {

    private MainDocRepository mainDocRepository;

    @Autowired
    public void setMainDocRepository(MainDocRepository mainDocRepository) {
        this.mainDocRepository = mainDocRepository;
    }

    @Override
    public List<MainDoc> listAllMainDocs() {
        return mainDocRepository.findAll();
    }

    @Override
    public MainDoc getMainDocById(Integer id) {
        return mainDocRepository.findOne(id);
    }

    @Override
    public MainDoc saveMainDoc(MainDoc mainDoc) {
        return mainDocRepository.save(mainDoc);
    }

    @Override
    public void deleteMainDoc(Integer id) {
        mainDocRepository.delete(id);
    }
}
