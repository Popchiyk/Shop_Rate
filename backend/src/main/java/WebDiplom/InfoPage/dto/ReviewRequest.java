package WebDiplom.InfoPage.dto;


import WebDiplom.InfoPage.Models.UserEntity;

public class ReviewRequest {
        private Long id;
        private Integer ball;
        private String data;
        private  String text;
        private String username;
        private String name;
        private String logo;
        private String surname;
        private String header;
        private Long id_shop;

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_shop() {
        return id_shop;
    }

    public void setId_shop(Long id_shop) {
        this.id_shop = id_shop;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
