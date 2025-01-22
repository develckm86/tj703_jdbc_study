package com.tj703.advance;

import java.util.*;

public class L04StreamAPI {
    public static void main(String[] args) {
        //반복문 검색 방법
        //1. 직접 참조 : for(int i=0; i<arr.length; i++) {arr[i]}
        //2. Iterator 이용 : for(int n : arr){}
        //3. Stream Api : Arrays.stream(arr).forEach((n)->{})
        //3. Stream Api : list.stream().forEach((n)->{})
        //직접 참조, Iterator 반복문은 외부 반복문 : 반복문이 직접 들어남
        //String API 를 내부 반복문 : 재정의한 callback 함수를 반복 호출
        //js: 내부반복문을 List 타입에 이미 다 구현해 놓음.
        //java: 내부반복문이 없다가 Stream API로 구현

        List list=new ArrayList();
        list.add("안녕");
        list.add(null);
        list.add('a');
        list.add(39);
        list.add(true);
        for(int i=0; i<list.size(); i++){
            System.out.print(list.get(i)+",");
        }
        System.out.println();
        for(Object o:list){
            System.out.print(o+",");
        }
        System.out.println("\n List가 js따라서 만든 내부 반복문 ");
        list.forEach((o)->{
            System.out.print(o+",");
        });
        System.out.println("\n List만 내부 반복문을 가지고 있어서 " +
                "모든 자료를 내부 반복문으로 사용할 수 있도록 돕는 자료형 Stream");
        list.stream().forEach((o)->{
            System.out.print(o+",");
        });
        //Collection 으로 구현한 자료형(List,Set,Map), Array
        //List,Array : 순서와 길이가 있는 잘료
        //Set : 중복을 허용하지 않는 자료로 순서가 없다.
        //Map(==JS Object): key(Set) 와 value 로 된 자료
        Set set=new HashSet();
        set.add(11);
        set.add(22);
        set.add(33);
        set.add(44);
        set.add(11);
        set.add(11);
        set.add(22);
        //List{11,22,33,44,11,11,22}
        //Set(11,22,33,44) -> [33, 22, 11, 44]
        System.out.println("\n"+set+", size : "+set.size());
        //Map : Set을 key로 사용
        Map<String,Object> map=new HashMap();
        map.put("id","tj703");
        map.put("name","경민");
        map.put("age",39);
        map.put("married",true);
        map.put("age",40);
        System.out.println("\n"+map);
        //{name=경민, id=tj703, married=true, age=39, age=40}
        //{name=경민, id=tj703, married=true, age=40}
        //key 인 age 가 중복될 수 없기 때문에 기존의 age 를 바꾼다.


        set.stream().forEach((o)->{
            System.out.print(o+",");
        });
        //steam 자료는 list 와 유사하기 때문에 Map은 steam으로 바꿀 수 없다.
        //Map의 데이터를 Entry 바꿔서 가져와서 반복문 사용
        Set keys=map.entrySet();
        System.out.println("/n"+keys);
        //{'key': 'value','key': 'value'} =>{'key=value','key=value'}
        map.entrySet().stream().forEach((entry)->{
            System.out.println(entry.getKey()+","+entry.getValue());
        });
        map.forEach((k,v)->{
            System.out.println(k+","+v);
        });
    }
}
