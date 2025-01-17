package com.tj708.study;

import java.util.ArrayList;
import java.util.List;

public class L02ArrayType {
    public static void main(String[] args) {
        int [] arr=new int[2];
        arr[0] = 1;
        arr[1] = 2;
        //컬렉션 프레임워크 : 개발을 위해 도움되는 자료들을 Collection 으로 구현 후 제공해서
        //Map Set List ArrayList, LinkedList
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);
        //Object[] : Object 는 모든 데이터를 참조 가능하기 때문
        Object[] objArr=list.toArray();
        //list.toArray(Integer[]); :js 타입자체가 매개변수로 사용가능
        Integer[] numArr=list.toArray(new Integer[0]);
        for(int num:numArr){
            System.out.println(num);
        }

        String [][] strArr=new String[3][2];
        strArr[0][0]="1";
        strArr[0][1]="2";
        strArr[1][0]="3";
        strArr[1][1]="4";
        strArr[2]=new String[]{"5","6"};

        List<Object[]> strList=new ArrayList<>();
        strList.add(new String[]{"1","2"});
        strList.add(new String[]{"3","4"});
        strList.add(new String[]{"5","6"});
        //new JTable(columns:[],data:[][])
        Object[][] data=strList.toArray(new Object[0][0]);
        //제네릭을 사용했는데도 매개변수로 toArray 로 반환할 타입을 객체로 확인 중
        //=>바보같은 일

        String[][] strData=strList.toArray(new String[0][0]);

    }
}
