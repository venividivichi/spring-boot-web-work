package guru.springframework.services;

import guru.springframework.domain.MainDoc;

import java.util.List;

/**
 * Created by user on 13.06.2016.
 */

public interface MainDocService {

    List<MainDoc> listAllMainDocs();

    MainDoc getMainDocById(Integer id);

    MainDoc saveMainDoc(MainDoc mainDoc);

    void deleteMainDoc(Integer id);


}


