package WebDiplom.InfoPage.models;

import javax.persistence.*;

@Entity
@Table
public class InfoShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;
    private String name_shop;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String   logo;
    private String email;
    private String website;
    @ManyToOne
    @JoinColumn(name="id_kategory",nullable = false)
    private Kategory id_Kategory;
    private String phone;
    private String contacts_people;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_shop() {
        return name_shop;
    }

    public void setName_shop(String name_shop) {
        this.name_shop = name_shop;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContacts_people() {
        return contacts_people;
    }

    public void setContacts_people(String contacts_people) {
        this.contacts_people = contacts_people;
    }

    public Kategory getId_Kategory() {
        return id_Kategory;
    }

    public void setId_Kategory(Kategory id_Kategory) {
        this.id_Kategory = id_Kategory;
    }

}
