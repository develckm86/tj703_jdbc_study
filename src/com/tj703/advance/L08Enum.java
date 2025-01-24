package com.tj703.advance;

import javax.swing.*;
import java.awt.*;
class emNumtest{
    public static final int OPEN=3;
}
public class L08Enum {
    //정보로의 상수
    public static final int CLOSE=3;
    public static void main(String[] args) {
        System.out.println(Math.PI);
        System.out.println(BorderLayout.EAST);
        System.out.println(JFrame.EXIT_ON_CLOSE);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(emNumtest.OPEN);
        //정보로의 상수 단점  : 보통 문자열이거나 수
        //매개변수의 타입이 문자열이거나 수이기 때문에 아무데이터나 입력가능

        //enum : 내부에 정의된 모든 필드를 상수처럼 사용가능 모든 필드가 고유값을 갖는다.
        System.out.println(STATE.OPEN);
        System.out.println(STATE2.OPEN.say);
    }
    enum STATE{
        CLOSE,OPEN,PAUSE
    }
    enum STATE2{
        CLOSE("닫기"),OPEN("열림"),PAUSE("대기");
        //public static final STATE2 PADDING= new STATE2("대기2");

        private String say;
        STATE2(String say) {
            this.say = say;
        }
    }
    public static void defaultClose(int state){
        if(state==1){
            //
        }
    }
    public static void defaultClose(STATE state){
        if(STATE.CLOSE==state){
            //
        }
    }
}
