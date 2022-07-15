//package com.fastcampus.ch3;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Arrays;
//
//
//class Engine{} // <bean id="engine" class="com.fascampus.ch3.Engine/>
//@Component class SuperEngine extends Engine{}
//@Component class TurboEngine extends Engine{}
//@Component class Door{}
//@Component
//class Car{
//    // autowired를 이용해, setter 대신 주입하기 (bean들 간의 관계 맺기)
//    @Value("red") String color;
//    @Value("100") int oil;
////    @Autowired    // byType - 타입으로 먼저 검색, 여러개면 이름으로 검색 - engine (타입의 첫글자가 소문자인 것)
////    @Qualifier("superEngine")     // 여러 후보들(SuperEngine, TurboEngine) 중에서 superEngine을 사용하겠다고 명시
//
//// aoutowired를 더 선호하는 이유? -> 이름이 바뀔 가능성이 있기 때문. 타입은 잘 안바뀐다.
//    @Resource(name="superEngine")   // byName
//    Engine engine;
//    @Autowired Door[] doors;
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public void setOil(int oil) {
//        this.oil = oil;
//    }
//
//    public void setEngine(Engine engine) {
//        this.engine = engine;
//    }
//
//    public void setDoors(Door[] doors) {
//        this.doors = doors;
//    }
//    @Override
//    public String toString() {
//        return "Car{" +
//                "color='" + color + '\'' +
//                ", oil=" + oil +
//                ", engine=" + engine +
//                ", doors=" + Arrays.toString(doors) +
//                '}';
//    }
//}
//
//public class SpringDITest {
//    public static void main(String[] args){
//        ApplicationContext ac = new GenericXmlApplicationContext("config1.xml");
//
//        Car car = (Car)ac.getBean("car"); // byName /  아래와 같음
//        // Car car = ac.getBean("car", Car.class); // byName (타입을 같이 써줄 수 있다.)
////        Car car2 = (Car)ac.getBean(Car.class); // byType
////
////        Engine engine = (Engine) ac.getBean("engine"); // byName => 이름은 key니까 에러 안남
////      //  Engine engine = (Engine) ac.getBean(Engine.class);  //  => 에러 (found 3), 같은 타입이 세개이기 때문
////        Door door = (Door) ac.getBean("door");
//
////        car.setColor("red");
////        car.setOil(100);
////        car.setEngine(engine);
////        car.setDoors(new Door[]{ac.getBean("door", Door.class), (Door)ac.getBean("door")});
//        System.out.println("car = " + car);
////        System.out.println("engine = " + engine);
//    }
//}
