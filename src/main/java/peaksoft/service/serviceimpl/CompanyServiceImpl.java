package peaksoft.service.serviceimpl;

import peaksoft.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.repository.CompanyRepository;
import peaksoft.service.CompanyService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {


        private final CompanyRepository companyRepository;

        @Autowired
        public CompanyServiceImpl(CompanyRepository companyRepository) {
            this.companyRepository = companyRepository;
        }

        @Override
        public List<Company> getAllCompany() {
            return companyRepository.getAllCompany();
        }

        @Override
        public Company getCompanyById(Long id) {
            return companyRepository.getCompanyById(id);
        }

        @Override
        public void saveCompany(Company company) {
            companyRepository.saveCompany(company);
        }

        @Override
        public void updateCompany(Company company) {
            companyRepository.updateCompany(company);
        }

        @Override
        public void deleteCompany(Company company) {
            companyRepository.deleteCompany(company);
        }
    }


