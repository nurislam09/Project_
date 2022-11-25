package peaksoft.repository;

import peaksoft.model.Company;

import java.util.List;

public interface CompanyRepository {

    List<Company> getAllCompany();
    Company getCompanyById(Long id);
    void saveCompany(Company company);
    void updateCompany(Company company);
    void deleteCompany(Company company);


}
