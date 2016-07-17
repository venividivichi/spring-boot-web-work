package guru.springframework.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "executant")
public class Executant implements Serializable {

    private int id;
    private String PIB;
    private String positionn;
    private String department;
    private Set<MainDoc> mainDocs;

    public Executant() {

    }

    public Executant(String PIB, String positionn, String department) {

        this.PIB = PIB;
        this.positionn = positionn;
        this.department = department;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPIB() {
        return PIB;
    }

    public void setPIB(String PIB) {
        this.PIB = PIB;
    }

    public String getPositionn() {
        return positionn;
    }

    public void setPositionn(String positionn) {
        this.positionn = positionn;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @OneToMany(mappedBy = "executant", cascade = CascadeType.ALL)

    public Set<MainDoc> getMainDocs() {
        return mainDocs;
    }

    public void setMainDocs(Set<MainDoc> mainDocs) {
        this.mainDocs = mainDocs;
    }

/*
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Executant{");
        sb.append("id=").append(id);
        sb.append(", PIB='").append(PIB).append('\'');
        sb.append(", positionn='").append(positionn).append('\'');
        sb.append(", department='").append(department).append('\'');
        sb.append(", mainDocs=").append(mainDocs);
        sb.append('}');
        return sb.toString();
    }*/
}

