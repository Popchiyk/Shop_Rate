package WebDiplom.InfoPage.dto;

public class StatsDTO {
    private int countUsers;
    private int countReview;
    private int countShop;
    private int countCategory;


    public StatsDTO(int countUsers, int countReview, int countShop, int countCategory) {
        this.countUsers = countUsers;
        this.countReview = countReview;
        this.countShop = countShop;
        this.countCategory = countCategory;
    }

    public StatsDTO() {
    }

    public int getCountUsers() {
        return countUsers;
    }

    public void setCountUsers(int countUsers) {
        this.countUsers = countUsers;
    }

    public int getCountReview() {
        return countReview;
    }

    public void setCountReview(int countReview) {
        this.countReview = countReview;
    }

    public int getCountShop() {
        return countShop;
    }

    public void setCountShop(int countShop) {
        this.countShop = countShop;
    }

    public int getCountCategory() {
        return countCategory;
    }

    public void setCountCategory(int countCategory) {
        this.countCategory = countCategory;
    }
}
