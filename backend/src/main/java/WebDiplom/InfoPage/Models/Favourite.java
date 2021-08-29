package WebDiplom.InfoPage.Models;

import javax.persistence.*;

@Entity
@Table
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_users",nullable = false)
    private UserEntity id_users;
    @ManyToOne
    @JoinColumn(name = "id_shop",nullable = false)
    private InfoShop id_shop;

    public Favourite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getId_users() {
        return id_users;
    }

    public void setId_users(UserEntity id_users) {
        this.id_users = id_users;
    }

    public InfoShop getId_shop() {
        return id_shop;
    }

    public void setId_shop(InfoShop id_shop) {
        this.id_shop = id_shop;
    }
}
