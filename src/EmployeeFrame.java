
/*
 * Copyright (C) 2017 Arian Mohamad Hosaini.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import java.io.FileNotFoundException;

/**
 * the GUI of the application that allows users to edit employee info
 * @author Arian2
 */
public class EmployeeFrame extends JFrame{

    /**
     * constructor creates all buttons, labels, and fields necessary for GUI
     * Employee[] empArray stores the employee records
     * int index is the index of the current employee in empArray
     */
    private JLabel idLabel;
    private JLabel phoneLabel;
    private JLabel nameLabel;
    private JLabel yearsLabel;
    private JTextField idField;
    private JTextField phoneField;
    private JTextField nameField;
    private JTextField yearsField;
    private JTextArea employeeTable;
    private JButton prevButton;
    private JButton nextButton;
    private JButton dispButton;
    private JButton exitButton;
    private JPanel panelA;
    private JPanel panelB;
    private JPanel panelC;
    private Employee[] empArray;
    private int index = 0;
    private ExitDialog exitBox;
    private JScrollPane scrollPaneB;
    private ErrorDialog inputError;
            
    /**
     *
     * @param empArray
     * @throws FileNotFoundException
     */
    public EmployeeFrame(Employee[] empArray) throws FileNotFoundException {
        //store employee array that contains records to display and edit
        this.empArray = empArray;
        //create dialog that pops up when entering invalid data
        inputError = new ErrorDialog("You inputted a non-integer value as the "
                + "years of service for an employee! The value was unchanged.",
                false);
        
        createTextFields();
        createTextArea();
        createButtons();
        createPanel();
    }
    
    /**
     * returns the employee array that contains the records for each individual
     * @return empArray
     */
    public Employee[] getEmpArray(){
        return empArray;
    }
    
    private void createTextFields(){
        // create labels
        idLabel = new JLabel("Employee Identification");
        phoneLabel = new JLabel("Telephone Number");
        nameLabel = new JLabel("Employee Name");
        yearsLabel = new JLabel("Years of Work");
        
        // create text fields that the user can enter data in
        idField = new JTextField(10);
        idField.setEditable(false);
        phoneField = new JTextField(15);
        nameField = new JTextField(30);
        yearsField = new JTextField(2);
        displayFields();
    
    }
    
    private void displayFields(){
        // get id, phone, name and years of the current employee
        int id = empArray[index].getEmpId();
        String phone = empArray[index].getTelephone();
        String name = empArray[index].getName();
        int years = empArray[index].getYears();
        
        // set the text field info to the info of the employee
        idField.setText(String.valueOf(id));
        phoneField.setText(phone);
        nameField.setText(name);
        yearsField.setText(String.valueOf(years));
    }
    
    private void setEmployeeData(){
        // get the info of the text fields
        String id = idField.getText().trim();
        String phone = phoneField.getText().trim();
        String name = nameField.getText().trim();
        String years = yearsField.getText().trim();
        
        // set the info of the employee to that of the text fields
        empArray[index].setEmpId(Integer.parseInt(id));
        empArray[index].setTelephone(phone);
        empArray[index].setName(name);
        try{
            empArray[index].setYears(Integer.parseInt(years));
        }
        // if years of service cannot be converted to an integer, display 
        // an error
        catch (NumberFormatException e){
            inputError.setVisible(true);
        }
    }
    

    
    private void createTextArea(){
        // create text area that displays employee records
        employeeTable = new JTextArea(11,38); 
        employeeTable.setEditable(false);
        
    }
    
    
    private void displayData(){
        // display employee records in text area
        String empTableData = "";
        for (Employee empArray1 : empArray) {
            empTableData = empTableData + empArray1 + '\n';
        }
        employeeTable.setText(empTableData);
    }
    
    
    private void createButtons(){
        // create buttons for navigating employees and saving their info
        prevButton = new JButton("< < Previous");
        nextButton = new JButton("Next > >");
        dispButton = new JButton("Display All");
        exitButton = new JButton("Exit");
        prevButton.setEnabled(false);
    }
    
    private void createPanel(){
        //create panels
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelA = new JPanel();
        panelB = new JPanel();
        panelC = new JPanel();
        scrollPaneB = new JScrollPane(employeeTable);
        
        // create error message
        exitBox = new ExitDialog();
        
        // add panels to this frame
        this.add(panelA, BorderLayout.NORTH);
        this.add(panelB, BorderLayout.CENTER);
        this.add(panelC, BorderLayout.SOUTH);
        this.setTitle("Employee Record Browser");
        
        // create borders for panels
        TitledBorder borderA = new TitledBorder("Current Record");
        borderA.setTitleJustification(TitledBorder.LEFT);
        borderA.setTitlePosition(TitledBorder.TOP);
        TitledBorder borderB = new TitledBorder("All Records");
        borderB.setTitleJustification(TitledBorder.LEFT);
        borderB.setTitlePosition(TitledBorder.TOP);
        panelA.setBorder(borderA);
        panelB.setBorder(borderB);
        
        // add components to panels
        panelA.setLayout(new GridLayout(4, 2));
        panelA.add(idLabel);
        panelA.add(idField);
        panelA.add(phoneLabel);
        panelA.add(phoneField);
        panelA.add(nameLabel);
        panelA.add(nameField);
        panelA.add(yearsLabel);
        panelA.add(yearsField);
        panelB.add(scrollPaneB);
        panelC.add(prevButton);
        panelC.add(nextButton);
        panelC.add(dispButton);
        panelC.add(exitButton);
        
        // set size of panels
        Dimension dimA = new Dimension(450,120);
        Dimension dimB = new Dimension(450,220);
        Dimension dimC = new Dimension(450,40);
        panelA.setPreferredSize(dimA);
        panelB.setPreferredSize(dimB);
        panelC.setPreferredSize(dimC);
        this.pack();
        this.setLocation(700, 350);
        
        // when this Actionlistener is triggered, it displays employee records
        class DispListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent ae) {
                displayData();
            }
            
        }
        
        // display employee records when "display all" button is clicked
        ActionListener displisten = new DispListener();
        dispButton.addActionListener(displisten);
        
        // when this ActionListener is triggered, it saves the current employee
        // data and goes to the next employee
        class NextListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent ae) {
                setEmployeeData();
                index++;
                displayFields();
                if (index == empArray.length-1){
                    nextButton.setEnabled(false);
                }
                if (index != 0){
                    prevButton.setEnabled(true);
                }
            }
            
        }
        
        // go to the next employee when "next" button is clicked
        ActionListener nextlisten = new NextListener();
        nextButton.addActionListener(nextlisten);
        
        // when this ActionListener is triggered, it saves the current employee
        // data and goes to the previous employee
        class PrevListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent ae) {
                setEmployeeData();
                index--;
                displayFields();
                if (index == 0){
                    prevButton.setEnabled(false);
                }
                if (index != empArray.length-1){
                    nextButton.setEnabled(true);
                }
            }
            
        }
        
        // go to the next employee when "previous" button is clicked
        ActionListener prevlisten = new PrevListener();
        prevButton.addActionListener(prevlisten);
        
        // when this ActionListener is triggered, it prompts user if they would
        // like to save their data before closing the application
        class ExitListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent ae) {
                exitBox.setVisible(true);
            }
            
        }
        
        // ask user if they want to exit when "exit" button is clicked
        ActionListener exitlisten = new ExitListener();
        exitButton.addActionListener(exitlisten);
        
        this.setVisible(true);
    }    
}


