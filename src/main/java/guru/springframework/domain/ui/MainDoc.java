package guru.springframework.domain.ui;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Executant;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


public class MainDoc implements Serializable{
    private Integer id;
    private Integer docID;
    private String shortDis;
    private Date dateCome;
    private Date dateDone;

    private int customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private int category;

    private int executant;


    public Integer getDocID() {
        return docID;
    }

    public void setDocID(Integer docID) {
        this.docID = docID;
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

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getExecutant() {
        return executant;
    }

    public void setExecutant(int executant) {
        this.executant = executant;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MainDoc{");
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
