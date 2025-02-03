package com.tj703.advance;

import com.tj703.employees.dao.EmployeesDaoImp;
import com.tj703.employees.dao.EmployeesDao;
import com.tj703.employees.dto.EmployeesDto;
import com.tj703.employees.dto.SalariesDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class L07StreamEX2 {
    public static void main(String[] args) throws Exception {
        List<EmployeesDto> empList=null;
        EmployeesDao empDao=new EmployeesDaoImp();
        empList=empDao.findWithSalaries(0,10);
        System.out.println(empList);
        //map,collect
        //1번 문제  : empList=> List<List<SalariesDto>> 이걸로 변경
        //2번 문제  : List<List<Integer>> 위의 데이터로 변경
        // {
        //  { 10001 {sal:66073}{sal:66100}{sal:71000}}, => {66073,66100,71000, ....}
        //  { 10002 {}{}{}},=> {76073,86100,91000, ....}
        //      ...
        // }
        //{66073,66100,71000, ....},{76073,86100,91000, ....}}
        //1번 문제  : empList=> List<List<SalariesDto>> 이걸로 변경
        //2번 문제  : List<List<Integer>> 위의 데이터로 변경
        //3.전체 급여의 합을 구하세요! reduce

        empList.stream()
                .forEach((emp)->{
                    List<SalariesDto> sals =emp.getSalariesList();
                    //System.out.println(sals);
                    sals.stream().forEach((sal)->{
                        //System.out.println(sal.getSalary());
                    });
                });
        List<List<SalariesDto>> sals=empList.stream()
                .map((emp)->emp.getSalariesList())
                .collect(Collectors.toList());
        System.out.println(sals);


        List<List<Integer>> salList2=empList.stream()
                .map((emp)->emp.getSalariesList())
                .map((salList)->{
                    List<Integer> s=salList.stream()
                            .map((sal)->sal.getSalary())
                            .collect(Collectors.toList());
                    return s;
                })
                .collect(Collectors.toList());
        //System.out.println(salList2);
        //[ 10001010,201010,444444 ..]
        List<Integer> sums=salList2.stream()
                .map((salList)->{
                    Optional<Integer> sumOpt=salList.stream()
                            .reduce((n1, n2)->n1+n2);
                    return  sumOpt.get();
                })
                .collect(Collectors.toList());
        System.out.println(sums);

    }
}
