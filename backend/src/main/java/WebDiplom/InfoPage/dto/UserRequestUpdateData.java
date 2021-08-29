package WebDiplom.InfoPage.dto;

import javax.persistence.Column;
import javax.persistence.Lob;

public class UserRequestUpdateData {
    private String name;
    private String surname;
    private  String lastname;
    private String phone;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String logo;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
}
