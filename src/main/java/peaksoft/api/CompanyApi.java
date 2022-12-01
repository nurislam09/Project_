package peaksoft.api;

import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CompanyApi {
    private final CompanyService companyService;
    private Company company = new Company();

    @Autowired
    public CompanyApi(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/getAllCompany")
    public String getAllCompany(Model model) {
        model.addAttribute("getAllCompany", companyService.getAllCompany());
        company.sout();
        return "/company/companies";
    }

    @GetMapping("/getCompanyById/{id}")
    public String getCompanyById(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "redirect:/getAllCompany";
    }

    @GetMapping("/new")
    public String newCompany(Model model) {
        model.addAttribute("newCompany", new Company());
        return "/company/addCompany";
    }

    @PostMapping("/save") // th:action="@{save}"
    public String saveCompany(@ModelAttribute("newCompany") Company company) { // th:object="${newCompany}"
        companyService.saveCompany(company);
        return "redirect:/getAllCompany";
    }

    @GetMapping("/updateCompany")
    public String updateCompany(@RequestParam("companyId") Long id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "/company/updateCompany";
    }

    @PostMapping("/saveUpdateCompany")
    public String saveUpdateCompany(@ModelAttribute("company") Company company) {
        companyService.updateCompany(company);
        return "redirect:/getAllCompany";
    }

    @GetMapping("/deleteCompany")
    public String deleteCompany(@RequestParam("companyId") Long id) {
        companyService.deleteCompany(companyService.getCompanyById(id));
        return "redirect:/getAllCompany";
    }

}