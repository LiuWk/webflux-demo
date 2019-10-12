package com.example.webflux.demo.bean.dto;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author lwk
 * @date 2019-10-11 14:46
 */
@Document
public class TestEntity {
    private String addr;
    private String company;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
