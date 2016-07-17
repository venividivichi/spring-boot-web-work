package guru.springframework.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nameO;
    private String nameP;

    @OneToMany(mappedBy = "customer")
    private Set<MainDoc> mainDocs;

    public Customer(){

    }

    public Customer(String nameO, String nameP) {

        this.nameO = nameO;
        this.nameP = nameP;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameO() {
        return nameO;
    }

    public void setNameO(String nameO) {
        this.nameO = nameO;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }


    public Set<MainDoc> getMainDocs() {
        return mainDocs;
    }

    public void setMainDocs(Set<MainDoc> mainDocs) {
        this.mainDocs = mainDocs;
    }

  /*  @Override
    public String toString() {
        String result = String.format(
                "Customer[id=%d, nameOrg='%s', namePerson='%s']%n",
                id, nameO, nameP);
        if (mainDocs != null) {
            for(MainDoc book : mainDocs) {
                result += String.format(
                        "MainDoc[id=%d, shortDis='%s', dateIn='%s']%n",
                        book.getId(), book.getShortDis(), book.getDateIn());
            }
        }

        return result;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Customer{");
        sb.append("id=").append(id);
        sb.append(", nameO='").append(nameO).append('\'');
        sb.append(", nameP='").append(nameP).append('\'');
        sb.append(", mainDocs=").append(mainDocs);
        sb.append('}');
        return sb.toString();
    }*/
}
