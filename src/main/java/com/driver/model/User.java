package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String originIp;
    private String markedIp;
    private boolean connected;
    @OneToOne
    @JoinColumn
    Country country;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Connection> connectionList=new ArrayList<>();
    @ManyToMany
    @JoinColumn
    List<ServiceProvider> serviceProviderList=new ArrayList<>();




    public User(){}

    public User(String userName, String password, String originIP, String markedIP, Boolean connected) {
        this.username = userName;
        this.password = password;
        this.originIp = originIP;
        this.markedIp = markedIP;
        this.connected = connected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOriginIp() {
        return originIp;
    }

    public void setOriginIp(String originIp) {
        this.originIp = originIp;
    }

    public String getMarkedIp() {
        return markedIp;
    }

    public void setMarkedIp(String markedIp) {
        this.markedIp = markedIp;
    }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }

    public List<ServiceProvider> getServiceProviderList() {
        return serviceProviderList;
    }

    public void setServiceProviderList(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }
}
