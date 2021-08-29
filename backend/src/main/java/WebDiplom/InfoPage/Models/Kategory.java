package WebDiplom.InfoPage.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_kategory;
    private String name_kategory;

    public Kategory() {

    }

    public Long getId_kategory() {
        return id_kategory;
    }

    public void setId_kategory(Long id_kategory) {
        this.id_kategory = id_kategory;
    }

    public String getName_kategory() {
        return name_kategory;
    }

    public void setName_kategory(String name_kategory) {
        this.name_kategory = name_kategory;
    }
}