package WebDiplom.InfoPage.models;

import javax.persistence.*;

@Entity
public class Kategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
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