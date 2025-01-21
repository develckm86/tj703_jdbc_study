package com.tj703.employees.swing;

import com.tj703.employees.dao.DepartmentsDao;
import com.tj703.employees.dao.DepartmentsDaoImp;
import com.tj703.employees.dto.DepartmentsDto;

import javax.swing.*;
import java.util.List;

public class DeptTable extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel panel;
    private String[] columns;
    private String[][] data;
    public DeptTable() {
        super("부서테이블");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(1000, 100, 450, 300);
        columns=new String[]{"부서번호","부서이름"};
        DepartmentsDao dao=null;
        try{
            dao=new DepartmentsDaoImp();
            List<DepartmentsDto> deptList=dao.findAll();
            //System.out.println(deptList);
            int rowSize=deptList.size();
            data=new String[rowSize][columns.length];
            for(int i=0;i<data.length;i++){
                String arr[]=data[i];
                DepartmentsDto dept=deptList.get(i);
                arr[0]= dept.getDeptNo();
                arr[1]=dept.getDeptName();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(dao!=null)dao.close();
        }
        for(String arr[] : data){
            for(String str :arr){
                System.out.print(str+",");
            }
            System.out.println();
        }

        table = new JTable(data,columns);
        scrollPane = new JScrollPane(table);
        panel = new JPanel();
        panel.add(scrollPane);
        this.add(panel);
        setVisible(true);

    }

    public static void main(String[] args) {
        new DeptTable();
    }
}
