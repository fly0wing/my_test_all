package com.dudo.spring;
//
//import com.dudo.utils.Assert;
//import org.springframework.beans.factory.annotation.Autowire;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.ContextResource;
//
///**
// * User: zk
// * Date: 13-7-11
// * Time: 上午11:36
// */
//
//
//public class Test1 {
//    public void setMyCar(Car myCar) {
//        this.myCar = myCar;
//    }
//
//    public Car getMyCar() {
//        return myCar;
//    }
//
//    //        @Autowired(required = true)
//    private Car myCar;
//
//    public static void main(String[] args) {
//        aa();
//    }
//    public static void aa(){
//        ClassPathResource res = new ClassPathResource("/applicationContext.xml");
//        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
//        System.out.println("此配置文件个共配置了"+reader.loadBeanDefinitions(res)+"类文件");
//        //GrouponAllService service = (GrouponAllService)factory.getBean("test1"); //spring beanId  必须用接口类进行强转和接收
//        String beanName="test1";
//        String carBeanName="car1";
////        System.out.println("是否存在此bean:"+factory.containsBean(beanName));
//        Car car = factory.getBean(carBeanName,Car.class);
//        car.toString();
////        BeanDefinition bd = factory.getBeanDefinition(beanName);
////        Test1 t = factory.getBean(beanName,Test1.class);
////        t.myCar.toString();
////        System.out.println(factory.getBean("myCar3",Car.class).equals(factory.getBean("myCar",Car.class)));
////        System.out.println(factory.getBean("myCar3",Car.class).equals(factory.getBean("car1",Car.class)));
//
//        System.out.println("-----------------------------------------------------------");
//    }
//}
