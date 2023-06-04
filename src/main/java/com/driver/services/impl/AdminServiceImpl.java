package com.driver.services.impl;

import com.driver.model.Admin;
import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;

    @Override
    public Admin register(String username, String password) {
        Admin admin=new Admin(username,password);
        return adminRepository1.save(admin);
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) {
        Admin admin=adminRepository1.findById(adminId).get();
        ServiceProvider serviceProvider=new ServiceProvider(providerName);
        admin.getServiceProviderList().add(serviceProvider);
        serviceProvider.setAdmin(admin);
        return adminRepository1.save(admin);
    }
    //ind, aus, usa, chi, jpn

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception {
        ServiceProvider serviceProvider=serviceProviderRepository1.findById(serviceProviderId).get();
        if(!(countryName.toLowerCase()).equals("ind") || !(countryName.toLowerCase()).equals("aus")
                ||!(countryName.toLowerCase()).equals("usa") || !(countryName.toLowerCase()).equals("chi")
                || !(countryName.toLowerCase()).equals("jpn")){
            throw new Exception("Country not found");
        }
        Country country=new Country();
        country.setCountryName(CountryName.valueOf(countryName));
        serviceProvider.getCountryList().add(country);
        country.setServiceProvider(serviceProvider);
        return serviceProviderRepository1.save(serviceProvider);
    }
}
