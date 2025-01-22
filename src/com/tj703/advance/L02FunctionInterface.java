package com.tj703.advance;

import java.util.Date;
import java.util.function.*;

public class L02FunctionInterface {
    public static void main(String[] args) {
        //자바가 미리 선언해 놓은 FunctionalInterface 의 종류들
        //스트림api(Collection 자료형의 반복을 위해 나온 자료형), forEach,map

        Runnable run=new Runnable() {
            public void run() {}
        };
        run=()->{};
        Function<Integer,String> fun=new Function<>() {
            @Override
            public String apply(Integer i) {
                return i*i+"";
            }
        };
        System.out.println(fun.apply(10));
        fun=(a)->{return (a+a*a)+"";};
        System.out.println(fun.apply(55));
        Function<Integer,Integer> pow=a -> a*a; //중괄호를 지우면 자동으로 return
        //Function : 매개변수의 타입과 반환값이 다를때 보통사용
        //Function 을 구현한 Operator : 매개변수와 return 타입이 같다.
        UnaryOperator<Integer> pow2=a -> a*a;
        BiFunction<Integer,Integer,Integer> pow3=(a,b)->a*b;

        BinaryOperator<Integer> add=(a,b)->a+b; //apply
        System.out.println(add.apply(55,66));

        //Consumer.accept : 매개변수만 있고 반환하는 것이 없는 함수
        Consumer<Integer> addCon=(a)->{
            System.out.println(a*a);
        };
        BiConsumer<Integer,Integer> addCon2=(a,b)->{
            System.out.println(a+b);
        };
        //bin == binary code 2진수 == 기계어 ==실행파일
        //Supplier.get : 매개변수 없이 반환값만 존재
        Supplier<String> sayHello=()->"hello";

        // Function=>Operator
        // Consumer
        // Suppler
        // Runnable
        // Predicate.test : 매개변수를 비교연산의 결과로 반환
        Predicate<String> p=a->a.length()<5;
        new Thread(()->{
            while(true){
                try {Thread.sleep(1000);} catch (InterruptedException e) {}
                System.out.println(new Date());
            }
        }).start();
        //재정의된 run을 기반으로 Thread 를 만들어서 실행(start)
        //js node.onclick=function(e){}
        //node 의 이벤트리스너가 마우스의 클릭 이벤트를 계속 대기하고(무한반복문) 있다.
        //마우스 클릭 이벤트가 발생하면 node.click(){this.onclick();}  실행
    }
}
