package com.tj703.employees.swing;

import com.tj703.employees.dao.DepartmentsDao;
import com.tj703.employees.dao.DepartmentsDaoImp;
import com.tj703.employees.dto.DepartmentsDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        createTable();
        setVisible(true);
    }
    public void createTable(){
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
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                int row = table.getSelectedRow();
                //int col = table.getSelectedColumn();
                String deptNo=table.getValueAt(row, 0).toString();
                String deptName=table.getValueAt(row, 1).toString();
                System.out.println(deptNo+" "+deptName);
                new DeptFormDialog(DeptTable.this,deptNo,deptName);
            }
        });
        if(scrollPane!=null)this.remove(scrollPane);
        if(panel!=null)this.remove(panel);
        if(table!=null)this.remove(table);
        DeptTable.this.revalidate();
        DeptTable.this.repaint();
        scrollPane = new JScrollPane(table);
        panel = new JPanel();
        panel.add(scrollPane);

        this.add(panel);
        DeptTable.this.revalidate();
        DeptTable.this.repaint();
        DeptTable.this.setVisible(false);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        DeptTable.this.setVisible(true);

    }

    class DeptFormDialog extends JDialog{
        private JTextField txtDeptNo;
        private JTextField txtDeptName;
        private JButton saveBtn;
        private JButton updateBtn;
        private JButton deleteBtn;

        private DeptFormDialog(JFrame parent,String deptNo,String deptName){
            super(parent,"dept 수정 및 등록 양식",true);
            setBounds(1500, 100, 450, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            txtDeptNo = new JTextField();
            txtDeptName = new JTextField();
            saveBtn=new JButton("등록");
            updateBtn=new JButton("수정");
            deleteBtn=new JButton("삭제");
            // 부서번호와 부서이름 입력을 위한 패널
            JPanel deptPanel = new JPanel(new GridLayout(2, 2));
            deptPanel.add(new JLabel("부서번호:"));
            txtDeptNo.setText(deptNo);
            deptPanel.add(txtDeptNo);
            deptPanel.add(new JLabel("부서이름:"));
            txtDeptName.setText(deptName);
            deptPanel.add(txtDeptName);
            JPanel btnPanel = new JPanel();
            btnPanel.add(saveBtn);
            btnPanel.add(updateBtn);
            btnPanel.add(deleteBtn);
            this.add(deptPanel);
            this.add(btnPanel, BorderLayout.SOUTH);
            deleteBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    DepartmentsDao dao=null;
                    try {
                        dao=new DepartmentsDaoImp();
                        System.out.println(dao.delete(deptNo));
                        DeptFormDialog.this.setVisible(false);
                        //DeptFormDialog.this.dispose();
                        DeptTable.this.createTable();
                        DeptTable.this.revalidate();
                        DeptTable.this.repaint();
                    }catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
            setVisible(true);
        }
    }



    public static void main(String[] args) {
        new DeptTable();
    }
}
