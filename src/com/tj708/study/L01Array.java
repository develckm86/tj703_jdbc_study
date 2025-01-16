package com.tj708.study;

import java.util.Arrays;
import java.util.Date;

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
    }
}
