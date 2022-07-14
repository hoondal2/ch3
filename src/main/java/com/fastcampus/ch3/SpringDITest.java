package com.fastcampus.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class Car{
    String color;
    int oil;
    Engine engine;
    Door[] doors;

    public void setColor(String color) {
        this.color = color;
    }

    public void setOil(int oil) {
        this.oil = oil;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setDoors(Door[] doors) {
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", oil=" + oil +
                ", engine=" + engine +
                ", doors=" + Arrays.toString(doors) +
                '}';
    }
}
@Component class Engine{}
@Component class Door{}

public class SpringDITest {
    public static void main(String[] args){
        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");

        Car car = (Car)ac.getBean("car"); // byName /  아래와 같음
        // Car car = ac.getBean("car", Car.class); // byName (타입을 같이 써줄 수 있다.)
        Car car2 = (Car)ac.getBean(Car.class); // byType

        Engine engine = (Engine) ac.getBean("engine");
        Door door = (Door) ac.getBean("door");

//        car.setColor("red");
//        car.setOil(100);
//        car.setEngine(engine);
//        car.setDoors(new Door[]{ac.getBean("door", Door.class), (Door)ac.getBean("door")});
        System.out.println("car = " + car);

    }
}
