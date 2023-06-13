package WebDiplom.InfoPage.dto;

import WebDiplom.InfoPage.models.InfoShop;
import WebDiplom.InfoPage.models.User;

public class StatisticsResult {
    private double variance;
    private double standardDeviation;

    public StatisticsResult(double variance, double standardDeviation) {
        this.variance = variance;
        this.standardDeviation = standardDeviation;
    }

    public double getVariance() {
        return variance;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }
}
