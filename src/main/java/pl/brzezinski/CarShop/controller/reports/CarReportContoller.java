package pl.brzezinski.CarShop.controller.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.brzezinski.CarShop.model.CarReport;
import pl.brzezinski.CarShop.service.CarReportFactory;

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

    @GetMapping("/searchBy")
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
