package com.tj703.advance;

import com.tj703.employees.dao.EmployeesDaoImp;
import com.tj703.employees.dto.EmployeesDto;
import com.tj703.employees.dto.Order;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class L10StreamEx {
    static int sum=0;


    public static void main(String[] args) throws Exception {
        EmployeesDaoImp empDao=new EmployeesDaoImp();
        List<EmployeesDto> empList=empDao.findWithSalary(0,100,Order.Column.emp_no,Order.Direct.ASC);
        //System.out.println(empList);
        //slalary의 총합
        //함수가 외부의 필드를 공유하고 있으면 재사용이 힘들다
        empList.stream().forEach((emp)->{
            //System.out.println(emp.getSalary().getSalary());
            sum+=emp.getSalary().getSalary();
        });
        System.out.println(sum);

//        empList.stream().reduce((emp1,emp2)->{
//            return emp1.getSalary().getSalary()+emp2.getSalary().getSalary();
//        });
        Optional<Integer> optSum=empList.stream()
                .map(emp->emp.getSalary().getSalary())
                //.forEach((salary)->{System.out.println(salary);});
                .reduce((sal1,sal2)->sal1+sal2);
        if(optSum.isPresent()){ //null 이 아니면
            System.out.println(optSum.get());
        }
        //1. filter  : 사원의 성별이 여성인 분들의 급여의 합을 구하세요!
        //stream() : 기본형 스트립을 별도로 제공
        //기본형 더하기의 결과는 무조건 null 이 나올수 없다.
        int sum=IntStream.range(1,101) //[0,1,2,3,4,5,6,7,....99)
                //.forEach((n)->{System.out.println(n); });
                .sum(); // Stream() 자료형이기 때문에 sum()이 없다.
        System.out.println(sum);
        //OptionalDouble : 기본형을 지원하느 optional
        OptionalDouble avg=IntStream.range(1,11).average();
        System.out.println(avg.getAsDouble());

        //System.out.println(empList.stream().collect(Collectors.groupingBy(emp->emp.getEmpNo())));
        //기본형 반복문의 장점 : 메모리 절약, 연산이 빠르다
        Integer objN=10; //new Integer(10)
        sum=objN+objN;
        sum=objN.intValue()+objN.intValue();

        int [] numArr={1,2,3,4,5,6,7};
        Arrays.asList(numArr).forEach((num)->{
            //num type intx --> Integer
        });
        //List<int> numList=new ArrayList<>();
        List<Integer> numList=new ArrayList<>();
        //mapToInt, mapToDouble, mapToLong, 나머지 기본형은 없다.
        //자바에서 정수를 입력하면 int 가되고 실수를 입력하면 double 이 되기 때문에, 연산할때 int 보다 큰데이터 사용하라고 long

        sum=empList.stream().mapToInt(emp->emp.getSalary().getSalary())
                .sum();
        System.out.println(sum);
        avg=empList.stream().mapToInt(emp->emp.getSalary().getSalary())
                .average();
        System.out.println(avg.getAsDouble());
        //메서드 참조
        //컴파일러가 자동완성하는데 상상할 수 있는만큼 줄이는 문법
        //콜백함수의 매개변수를 함수에서 바로 사용할 때만 사용 가능
        //System.out::println : System.out의 println 를 실행하는데 콜백함수의 매개변수를 println의 매개변수로 써라
        //System.out::println
        empList.stream().forEach(System.out::println);
    }
}