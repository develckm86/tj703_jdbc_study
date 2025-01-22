package com.tj703.advance;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//interface 와 abstract class 는 구현이 되지 않은 추상메소드를 포함하고 있기 때문에
//객체가 될 수 없다. (추상메소드==설계)
@FunctionalInterface //나는 람다식이 될거야!(만약 람다식 규칙에서 벗어나면 컴파일 오류)
interface A{
    abstract public void add(int a, int b);
    //void a();
}
//개발자가 제일 싫어하는 것 1 : 이름 짓기
//개발자가 제일 싫어하는 것 2 : 코드가 길어지는 것
//-> 익명클래스
class Aimp implements A{
    @Override
    public void add(int a, int b) {
        System.out.println(a+b);
    }
}
public class L01Lambda {
    class One implements A{
        @Override
        public void add(int a, int b) {

        }
    }
    public static void main(String[] args) {
        //A a=new A();
        A a = new Aimp();
        a=new A(){
            @Override
            public void add(int a, int b) {

            }
        };//L01Lambda$1.class
        a=new L01Lambda().new One();
        //익명클래스를 작성하면 컴파일러가 1(One) class 를 자동완성한다
        a=new A(){
            @Override
            public void add(int a, int b) {

            }
        };//L01Lambda$2.class

        //node.addEventListener("click",function(e){})
        new JButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //함수형언어는 함수의 프로토타입이 객체라 매개변수가 되던데 객체지향 언어 구리네!!
        //js가 자바를 따라한것, GC,class,let,const,get set,
        //=>람다식 등장 (Interface 인데 함수가 오직 하나인 것만 화살표 함수가 대체됨)
        a=(n1,n2)->{};
        //자바의 주장 우리도 함수형 언어를 지원해!(x)
        //자바 함수형 언어를 문법 적으로 지원해!(o) => 문법적 설탕

    }
}
