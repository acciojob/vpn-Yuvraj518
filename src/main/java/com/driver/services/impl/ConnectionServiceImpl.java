package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ConnectionRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    UserRepository userRepository2;
    @Autowired
    ServiceProviderRepository serviceProviderRepository2;
    @Autowired
    ConnectionRepository connectionRepository2;

    @Override
    public User connect(int userId, String countryName) throws Exception {
        User user=userRepository2.findById(userId).get();
        if(user.getConnected()){
            throw new Exception("Already connected");
        }
        if(user.getOriginalCountry().getCountryName().equals(countryName)){
            return user;
        }
        if(user.getServiceProviderList().size()==0){
            throw new Exception("Unable to connect");
        }
        List<ServiceProvider> list=user.getServiceProviderList();
        boolean flag=true;
        ServiceProvider s1 = null;
        Country c1 = null;
        for(ServiceProvider x: list){
            List<Country> countries=x.getCountryList();
            for(Country xx: countries){
                if(xx.getCountryName().equals(countryName)){
                    s1=x;
                    c1=xx;
                    flag=false;
                    break;
                }
            }
            if(!flag){break;}
        }
        if(flag){
            throw new Exception("Unable to connect");
        }
        user.setMaskedIp(c1.getCode()+"."+s1.getId()+"."+userId);
        return user;
    }
    @Override
    public User disconnect(int userId) throws Exception {
        User user=userRepository2.findById(userId).get();
        if(!user.getConnected()){
            throw new Exception("Already disconnected");
        }
        List<Connection> connectionList=user.getConnectionList();
        for(Connection x:connectionList){
            x.setUser(null);
        }
        user.setMaskedIp(null);
        user.setConnected(false);
        user.setConnectionList(null);
        return user;
    }
    @Override
    public User communicate(int senderId, int receiverId){
        User user=new User();
        return user;
    }
}
