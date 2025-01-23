package com.tj703.advance;

import com.tj703.employees.dao.EmployeesDaoImp;
import com.tj703.employees.dto.EmployeesDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class L06SteamEX {
    public static void main(String[] args) throws Exception {
        EmployeesDaoImp empDao=new EmployeesDaoImp();
        List<EmployeesDto> empList=empDao.findAll();
        System.out.println(empList);
        //int empNoArr[]=
        List<Integer> empNoList=new ArrayList();
        for(EmployeesDto emp:empList){
            if(emp.getGender()=='M'){
                empNoList.add(emp.getEmpNo());
            }
        }
        System.out.println(empNoList.size());
        System.out.println(empNoList);

        //steam api 의 코드가 외부 반복문 보다 간결하고 재사용성이 좋고 수정이 용이하다.
        List<Integer> nos=empList.stream()
                .filter(emp->emp.getGender()=='M')
                .map(emp->emp.getEmpNo())
                .collect(Collectors.toList());
                //.forEach(empNo->{System.out.print(empNo+",");});
        System.out.println(nos);

    }
}
