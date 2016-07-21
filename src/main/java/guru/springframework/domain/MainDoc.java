package guru.springframework.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "maindocument")
public class
MainDoc implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    private Integer docID;
    private String shortDis;



    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateCome;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateDone;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @ManyToOne
    @JoinColumn(name = "executant_id")

    private Executant executant;


    public MainDoc() {

    }

    public MainDoc(Integer docID, String shortDis, Date dateCome, Date dateDone) {


        this.docID = docID;
        this.shortDis = shortDis;
        this.dateCome = dateCome;
        this.dateDone = dateDone;
    }

    public MainDoc(Integer docID, String shortDis, Date dateCome, Date dateDone, Customer customer, Category category, Executant executant) {

        this.docID = docID;
        this.shortDis = shortDis;
        this.dateCome = dateCome;
        this.dateDone = dateDone;
        this.customer = customer;
        this.category = category;
        this.executant = executant;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDis() {
        return shortDis;
    }

    public void setShortDis(String shortDis) {
        this.shortDis = shortDis;
    }

    public Date getDateCome() {
        return dateCome;
    }

    public void setDateCome(Date dateCome) {
        this.dateCome = dateCome;
    }

    public Date getDateDone() {
        return dateDone;
    }

    public void setDateDone(Date dateDone) {
        this.dateDone = dateDone;
    }

    public Integer getDocID() { return docID; }

    public void setDocID(Integer docID) { this.docID = docID; }


    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Category getCategory() { return category; }
    public void setCategory(Category category) {
        this.category = category;
    }

    public Executant getExecutant() { return executant; }
    public void setExecutant(Executant executant) { this.executant = executant; }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MainDoc{");
        sb.append("id=").append(id);
        sb.append(", docID=").append(docID);
        sb.append(", shortDis='").append(shortDis).append('\'');
        sb.append(", dateCome=").append(dateCome);
        sb.append(", dateDone=").append(dateDone);
        sb.append(", customer=").append(customer);
        sb.append(", category=").append(category);
        sb.append(", executant=").append(executant);
        sb.append('}');
        return sb.toString();
    }


}
