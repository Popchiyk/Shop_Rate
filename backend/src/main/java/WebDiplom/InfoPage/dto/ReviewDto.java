package WebDiplom.InfoPage.dto;

import javax.persistence.Column;

public class ReviewDto {
//    private Long id_shop;
//    private Long id_user;
    private  String text;
    private String header;
    private  String data;
    private Integer ball;

//    public Long getId_shop() {
//        return id_shop;
//    }
//
//    public void setId_shop(Long id_shop) {
//        this.id_shop = id_shop;
//    }
//
//    public Long getId_user() {
//        return id_user;
//    }
//
//    public void setId_user(Long id_user) {
//        this.id_user = id_user;
//    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getBall() {
        return ball;
    }

    public void setBall(Integer ball) {
        this.ball = ball;
    }
}
