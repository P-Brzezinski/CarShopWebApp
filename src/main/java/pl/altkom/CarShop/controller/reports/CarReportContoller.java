package pl.altkom.CarShop.controller.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.altkom.CarShop.model.Car;
import pl.altkom.CarShop.model.CarReport;
import pl.altkom.CarShop.service.CarReportFactory;

import java.util.List;

@Controller
public class CarReportContoller {

    @Autowired
    private CarReportFactory reportFactory;

    @GetMapping(path = "/carReport")
    public String processReportRequest(final Model model, @RequestParam(name = "reportId") Integer reportId) {

        CarReport report = reportFactory.createReport(reportId);
        model.addAttribute("reportData", report);
        return "report";
    }

    @GetMapping("/search")
    public String returnForm(){
        return "searchByCriteria";
    }

    @GetMapping(path = "/searchByUserInput")
    public String showAllCarsList(final Model model,
                                  @RequestParam(name = "carBrand") String carBrand,
                                  @RequestParam(name = "carModel") String carModel,
                                  @RequestParam(name = "color") String color) {

        CarReport report = reportFactory.createReportByUserInput(carBrand, carModel, color);
        model.addAttribute("reportData", report);
        return "report";
    }


}
