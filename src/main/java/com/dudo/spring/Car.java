package com.dudo.spring;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//
///**
// * User: zk
// * Date: 13-7-12
// * Time: 下午1:24
// */
//public class Car {
//    private String name;
//    private String company;
//
//    public Car(String name, String company) {
//        this.company = company;
//        this.name = name;
//    }
//
//    public Car() {
//    }
//
//    public Car(String name) {
//        this.name = name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Qualifier("asd")
//    public void setCompany(String company) {
//        this.company = company;
//    }
//
//    @Override
//    public String toString() {
//        System.out.println("Car{" +
//                "name='" + name + '\'' +
//                ", company='" + company + '\'' +
//                '}');
//        return "Car{" +
//                "name='" + name + '\'' +
//                ", company='" + company + '\'' +
//                '}';
//    }
//}
