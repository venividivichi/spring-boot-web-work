package guru.springframework.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    private String name;

    @OneToMany( mappedBy = "category")
    private Set<MainDoc> mainDocs;

    public Category(){

    }

    public Category(String name) {

        this.name = name;
    }

   public Category(String name, Set<MainDoc> mainDocs) {
        this.name = name;
        this.mainDocs = mainDocs;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MainDoc> getMainDocs() {
        return mainDocs;
    }

    public void setMainDocs(Set<MainDoc> mainDocs) {
        this.mainDocs = mainDocs;
    }

   /* @Override
    public String toString() {
        return name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mainDocs=" + mainDocs +
                '}';
    }*/
}
