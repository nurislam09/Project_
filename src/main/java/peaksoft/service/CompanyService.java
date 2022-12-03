package peaksoft.service;

import peaksoft.model.Company;

import java.io.IOException;
import java.util.List;

public interface CompanyService {
        List<Company> getAllCompany();
        Company getCompanyById(Long id);
        void saveCompany(Company company) throws IOException;
        void updateCompany(Company company) throws IOException;
        void deleteCompany(Company company);
    }

