package com.tj703.employees.swing;

import com.tj703.employees.dao.DepartmentsDao;
import com.tj703.employees.dao.DepartmentsDaoImp;
import com.tj703.employees.dto.DepartmentsDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeptTable extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel panel;
    private String[] columns;
    private String[][] data;
    private JButton addBtn;
    private JButton refreshBtn;
    private JPanel btnP;
    public DeptTable() {
        super("부서테이블");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(1000, 100, 450, 300);

        refreshTable();

        addBtn=new JButton("부서등록");
        refreshBtn=new JButton("새로고침");
        btnP =new JPanel();
        refreshBtn.addActionListener((e)->{
            refreshTable();
        });
        btnP.add(refreshBtn);
        addBtn.addActionListener((e)->{
            new DeptAddDialog();
        });
        btnP.add(addBtn);
        this.add(btnP, BorderLayout.SOUTH);
        setVisible(true);
    }
    public void refreshTable(){
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
        //기존에 추가된 panel 을 삭제해야 다음 panel 을 추가할 수 있다.
        if(panel!=null) this.remove(panel);
        table = new JTable(data,columns);
        scrollPane = new JScrollPane(table);
        panel = new JPanel();
        panel.add(scrollPane);
        this.add(panel);
        //재랜더링
        this.revalidate();
        this.repaint();
    }
    class DeptAddDialog extends JDialog{
        private JTextField txtDeptNo; //==input
        private JTextField txtDeptName;
        private JLabel lbDeptNo;
        private JLabel lbDeptName;
        private JButton btnAdd;
        private JPanel panel;
        public DeptAddDialog(){
            super(DeptTable.this,"부서등록 Dialog");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setBounds(1500, 100, 450, 300);
            lbDeptNo=new JLabel("부서번호");
            lbDeptName=new JLabel("부서이름");
            txtDeptNo=new JTextField();
            txtDeptName=new JTextField();
            btnAdd=new JButton("부서등록");
            btnAdd.addActionListener((e)->{
                try{
                    DepartmentsDao dao=new DepartmentsDaoImp();
                    DepartmentsDto dept=new DepartmentsDto();
                    dept.setDeptNo(txtDeptNo.getText());
                    dept.setDeptName(txtDeptName.getText());
                    int create=dao.create(dept);
                    if(create>0){
                        System.out.println("등록성공");
                        DeptTable.this.refreshTable();
                        DeptAddDialog.this.setVisible(false); //Dialog
                        DeptAddDialog.this.dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            panel=new JPanel();
            panel.setLayout(new GridLayout(2,2));
            panel.add(lbDeptNo);
            panel.add(txtDeptNo);
            panel.add(lbDeptName);
            panel.add(txtDeptName);
            this.add(panel);
            this.add(btnAdd,BorderLayout.SOUTH);
            setVisible(true);
        }
    }


    public static void main(String[] args) {
        new DeptTable();
    }
}
