package WebDiplom.InfoPage.dto;

import WebDiplom.InfoPage.models.Kategory;

import javax.persistence.Column;
import javax.persistence.Lob;

public class InfoShopRequest {
    private  Long id;
    private String name_shop;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String   logo;
    private String email;
    private String website;
    private Integer ball;
    private Kategory id_Kategory;
    private  Long SubscribeID=null;
    private boolean subscribe;
    private String phone;
    private String contacts_people;
    private  int response;

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

    public Kategory getId_Kategory() {
        return id_Kategory;
    }

    public void setId_Kategory(Kategory id_Kategory) {
        this.id_Kategory = id_Kategory;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubscribeID() {
        return SubscribeID;
    }

    public void setSubscribeID(Long subscribeID) {
        SubscribeID = subscribeID;
    }

    public Integer getBall() {
        return ball;
    }

    public void setBall(Integer ball) {
        this.ball = ball;
    }

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public boolean isSubscribe() {
        return subscribe;
    }

    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }
}
