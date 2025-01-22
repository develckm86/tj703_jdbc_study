package com.tj703.advance;

import java.util.function.BinaryOperator;
import java.util.function.Function;

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

        BinaryOperator<Integer> add=(a,b)->a+b; //apply
        System.out.println(add.apply(55,66));
    }
}
