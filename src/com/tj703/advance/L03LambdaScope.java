package com.tj703.advance;

public class L03LambdaScope {
    static final int a=0;
    class One implements Runnable{
        public void run(){
            System.out.println(a);
        }
    }
    public static void main(String[] args) {
        int num=0;
        Runnable r = () -> {
            //num++;
            //num=2;
            System.out.println(num);
        };
        //num=2;
        //r.run() 기준으로 변수 n을 부모영역(scope)에 존재하는 것 처럼 보이지만 완전히 다른 영역이다.
        //int n : L03LambdaScope.main
        //run() : L03LambdaScope$1.run
        //때문에 run() 에서 n을 참조할 수 없어야하는데 컴파일러가 참조할 수 있도록 돕는다.
        //run() n을 참조하면 : L03LambdaScope.n => final static int n=0; 으로 바꿔서 생성
        //때문에 참조는 가능하지만 바꿀수는 없다.
        //1. java 입장에서 참조하게 하는 것도 억지인데 바꿀 수 있는 것은 더 억지다!
        //2. static 필드는(클래스변수) 바뀌지 않는 것이 안전하기 때문.
        //=> 클래스변수는 GC가 자동으로 삭제하지 않고 어디서든 참조가능하기 때문에
        // 누구나 다 내용을 바꾸고 삭제할 수 있기 때문에 보통은 상수로 정의하고 만약 바꿔야 한다면
        //싱클톤패턴으로 구현하는 것이 더 좋다.

        int i=10;
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println(i);
                //**람다식,익명클래스 모두 지역변수를 참조하면  클래스변수(final)로 변경 후 참조
            }
        };
        //개발자를 돕기 위한 조치

    }
}
