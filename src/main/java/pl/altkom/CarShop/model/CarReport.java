package pl.altkom.CarShop.model;

import java.util.List;

public class CarReport {

    private String reportTitle;
    private List<Car> reportData;
    private int numberOfRecords;

    public CarReport(String reportTitle, List<Car> reportData) {
        this.reportTitle = reportTitle;
        this.reportData = reportData;
        this.numberOfRecords = reportData != null ? reportData.size() : 0;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public List<Car> getReportData() {
        return reportData;
    }

    public int getNumberOfRecords() {
        return numberOfRecords;
    }
}
