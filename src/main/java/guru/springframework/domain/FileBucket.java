package guru.springframework.domain;

/**
 * Created by user on 21.09.2016.
 */

import org.springframework.web.multipart.MultipartFile;

public class FileBucket {

    MultipartFile file;


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}