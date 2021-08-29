package WebDiplom.InfoPage.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "review_entity")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @ManyToOne
    @JoinColumn(name = "id_shop",nullable = false)
    private InfoShop id_shop;
    @ManyToOne
    @JoinColumn(name = "id_user",nullable = false)
    private  UserEntity id_user;
    @Column(length = 414)
    private  String text;
    private String header;
    @Column(columnDefinition = "Date")
    private  String data;
    private Integer ball;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InfoShop getId_shop() {
        return id_shop;
    }

    public void setId_shop(InfoShop id_shop) {
        this.id_shop = id_shop;
    }

    public Integer getBall() {
        return ball;
    }

    public void setBall(Integer ball) {
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

    public UserEntity getId_user() {
        return id_user;
    }

    public void setId_user(UserEntity id_user) {
        this.id_user = id_user;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
