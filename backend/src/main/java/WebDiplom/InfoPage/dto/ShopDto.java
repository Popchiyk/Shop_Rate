package WebDiplom.InfoPage.dto;

public class ShopDto {
    private String name;
    private String phone;
    private String email;
    private String website;
    private String contact_people;
    private Long kategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getContact_people() {
        return contact_people;
    }

    public void setContact_people(String contact_people) {
        this.contact_people = contact_people;
    }


    public Long getKategory() {
        return kategory;
    }

    public void setKategory(Long kategory) {
        this.kategory = kategory;
    }
}
