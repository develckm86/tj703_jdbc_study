package com.tj703.study;

import java.util.*;

public class L01Array {
    public static void main(String[] args) {
        int [] nums = {1,2,3,4,5};
        System.out.println(nums[0]);
        //[] : Array 고정 길이 배열
        //nums[5]=6;
        byte [] nums2=new byte[5];//{0,0,0,0,0}
        System.out.println(nums2[0]);
        String [] strs=new String[5]; //{null,null,null,null,null}

        int [][] nums3=new int[2][5]; // {{0,0,0,0,0},{0,0,0,0,0}}
        System.out.println(nums3);
        System.out.println(Arrays.toString(strs));
        System.out.println(Arrays.toString(nums2));
        System.out.println(Arrays.toString(nums3));
        int n=0;
        for(int i=0;i<nums3.length;i++){
            int [] arr=nums3[i];
            for(int j=0; j<nums3[i].length;j++){
                //int num=nums3[i][j];
                arr[j]=(++n);
            }
        }
        //iterator
        for(int [] arr: nums3) {
            for(int num : arr){
                System.out.print(num+",");
            }
        }
        Object[][] data={{10001,"경민",true},{10002,"기원", false},{10003,"승엽",false}};
        //콘솔에 전체를 순환해서 출력!
        //10분 for , iterator
        System.out.println("\ndata iterator 출력");
        for(Object[] arr : data) {
            //Object o=11; => [new Integer(11)>new Object()]
            //Object s="안녕" => ["안녕" : new String()>new Object()]
            //기본형을 자료형이 아니기 때문에 Object 가 참조함 기본형의 랩퍼클래스가
            //자료형으로 생성후 변수가 참조
            //자료형 객체는 모두가 Object 객체를 포함하고 있기 때문에 변수가 Object
            //만 참조
            for(Object obj : arr) {
                System.out.print(obj+",");
            }
        }
        System.out.println("\ndata for i j 출력");
        for(int i=0; i<data.length;i++){
            for(int j=0; j<data[i].length;j++){
                System.out.print(data[i][j]+",");
            }
        }
        //배열 == 줄서있는 사람
        //데이터가 빠지면 뒤쪽 모든 데이터를 앞으로 이동
        List list=new ArrayList();
        //LinkedList : 번호표(linked)를 받아서 줄서있는 사람
        list=new LinkedList();
        //객체가 부모타입의 변수를 참조가능한 것

        //ArrayList == js Array
        list.add(10);
        list.add(20);
        list.add("30");
        list.add(true);
        list.add(new Date());
        //ArrayList 는 길이 변경이 가능
        System.out.println("\nlist 출력");
        System.out.println(list);
        //List는 기본으로 Object 를 참조한다
        //데이터 타입을 고정(자료형) : Generic <>
        List<Integer> nums4=new ArrayList<>();
        nums4.add(1);
        //nums4.add("2");
        //class List<E>{   E=Integer
        //    Object value;
        //    E value2;
        //    add(E e){}
        //}
        nums4.add(2);
        nums4.add(3);
        nums4.add(4);
        for(int i=0; i< nums4.size(); i++){
            System.out.println(nums4.get(i));
        }
        int nn=Integer.valueOf(10);
        //nn=10;
        for(Integer num : nums4){
            System.out.println(num+",");
        }
        List<int[]> numArr=new ArrayList<>(); //2차원 배열
        //numArr.add({1,2,3,4}); //[1,2,3,4] x => new Int{1,2,3,4}
        numArr.add(new int[]{1,2,3,4,5});
        int [] nums5={6,7,8,9,10,11}; //선언즉시 대입할때는 new int[] 를 생략
        numArr.add(nums5);
        numArr.add(new int[]{11,12,13,14,15});
        //numArr 모든 수를 더하세요!
        int sum=0;
        for(int[] arr : numArr){
            for(int num : arr){
                sum+=num;
            }
        }
        System.out.println("\nnumArr의 모든 수를 더한 결과");
        System.out.println(sum);
    }
}
