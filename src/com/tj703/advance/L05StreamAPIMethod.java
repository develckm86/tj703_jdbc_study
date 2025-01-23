package com.tj703.advance;

import java.util.Arrays;
import java.util.Optional;

public class L05StreamAPIMethod {
    public static void main(String[] args) {
        /*
        * SteamAPI 최종 연산자
        * .forEach(Consumer (a)->{})
        * .reduce(BinaryOperator (n1,n2)->n1+n2) : 최조 결과물 하나
        * .anyMatch(Predicate (a)->a>0) : 하나라도 맞으면 true
        * .allMatch(Predicate (a)->a>0) : 모두가 맞으면 true
        * .noneMatch(Predicate (a)->a>0) : 모두가 틀리면 true
        * .findFirst() : 0번째 요소 반환
        * .findAny() : 랜덤하게 1개 반환
        * .collect() : ??
        *
        * 중간연산자 : 기존의 steam 자료를 변환한 새로운 steam 자료로 반환 때문에
        *   출력이나 결과(최종연산자)를 확인 할 수 없다.
        *   중간 연산자로 method chaining 이 가능합니다.
        *   이때 중간 연산자는 lazy 연산을 하기 때문에 반복문을 여러번 작성해도 성능에 크게 문제 없다.
        *   (반복문을 여러번 작성하면 성능 저하는 무조건 올 수 밖에 없고 lazy 연산이 크게 동작하지도 않는다.)
        *.filter(Predicate) : 조건에 부합(true)한 요소만 (steam 자료로 반환)**
        *.map(Function(str)->Integer.parseInt(str)) : 콜백함수에서 반환한 데이터로 변환 후 (steam 자료로 반환)**
        * {"1","3","11"}.map((str)->Integer.parseInt(str)) => {1,3,11}
        *.sorted(Comparator or null) : 정렬
        *.distinct() : 중복제거
        *.limit(int length) : length 만큼 스트림을 잘라서 반환
        *.skip(int n) : n 만큼 스킵하고 스트림을 반환
        *.peek(Consumer) : steam 을 아무런 가공 없이 그대로 반환, 콜백함수는 debug 용
        *
        * Stream API로 메서드 체이닝을 작성하면 마치 쿼리(sql) 처럼 요소를 처리할 수 있고
        * 작성한 메서드를 주석처리하거나 자유롭게 이동하기 용이하며
        * 콜백함수를 재사용할 수 있다(내부반복문 장점).
        * */
        Optional<Integer> sumOpt=Arrays.stream(new String[]{"11","11","3","삼", "22", "3"})
                .distinct()
                .filter((s)->{
                    boolean b=true;
                    try{
                        Integer.parseInt(s);
                    }catch(NumberFormatException e){
                        b=false;
                    }
                    return b;
                })
                //.peek((s)-> System.out.println(s))
                .map(s->Integer.parseInt(s))
                .reduce((n1,n2)->n1+n2);
        System.out.println(sumOpt.get());
    }
}
