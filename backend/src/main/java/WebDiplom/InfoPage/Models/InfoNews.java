package WebDiplom.InfoPage.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class InfoNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name_news;
    private String  text;
    private Date date_push;
    @ManyToOne
    @JoinColumn(name = "id_shop",nullable = false)
    private InfoShop id_shop;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_news() {
        return name_news;
    }

    public void setName_news(String name_news) {
        this.name_news = name_news;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate_push() {
        return date_push;
    }

    public void setDate_push(Date date_push) {
        this.date_push = date_push;
    }

    public InfoShop getId_shop() {
        return id_shop;
    }

    public void setId_shop(InfoShop id_shop) {
        this.id_shop = id_shop;
    }

    public InfoNews() {
    }


}
