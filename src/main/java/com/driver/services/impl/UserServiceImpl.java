package com.driver.services.impl;

import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.model.User;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository3;
    @Autowired
    ServiceProviderRepository serviceProviderRepository3;
    @Autowired
    CountryRepository countryRepository3;

    @Override
    public User register(String username, String password, String countryName) throws Exception{
        String pp=countryName.toUpperCase();
        if(!(pp).equals("IND") && !(pp).equals("AUS")
                &&!(pp).equals("USA") && !(pp).equals("CHI")
                && !(pp).equals("JPN")){
            throw new Exception("Country not found");
        }
        Country country=new Country(CountryName.valueOf(pp.toString()),CountryName.valueOf(pp).toCode());
        User user=new User();
        user.getOriginalCountry().setCountryName(CountryName.valueOf(countryName));
        user.setUsername(username);
        user.setPassword(password);
        user.setConnected(false);
        country.setUser(user);
        user.setOriginalCountry(country);
        User savedUser=userRepository3.save(user);
        savedUser.setOriginalIp(new String(user.getOriginalCountry().getCode()+"."+savedUser.getId());

        return userRepository3.save(savedUser);
    }

    @Override
    public User subscribe(Integer userId, Integer serviceProviderId) {
        User user=userRepository3.findById(userId).get();
        ServiceProvider serviceProvider=serviceProviderRepository3.findById(serviceProviderId).get();
        user.getServiceProviderList().add(serviceProvider);
        serviceProvider.getUsers().add(user);
        serviceProviderRepository3.save(serviceProvider);
        return user;
    }
}
