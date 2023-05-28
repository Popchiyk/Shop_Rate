package WebDiplom.InfoPage.dto;

import java.util.List;

public class ChartDTO {
    private List<String> data;

    private int count;

    public ChartDTO() {
    }

    public ChartDTO(List<String> data, int count) {
        this.data = data;
        this.count = count;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
