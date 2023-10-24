
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

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * starts up the GUI for editing employee info
 * @author Arian2
 */
public class EmployeeMain {
    
    private static EmployeeFrame myEmpFrame;
    
    /**
     *
     * @param args the command line arguments
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException{
        
        createEmployeeFrame();
        
    }
    
    /**
     * create employee frame that allows user to edit employee info
     * @throws FileNotFoundException
     */
    public static void createEmployeeFrame() throws FileNotFoundException{
        // generate employee records from text file
        Employee[] empArr = EmployeeRecords.createEmployeeArray();
        
        // if there are no errors, create employee frame instance
        if (empArr != null){
            myEmpFrame = new EmployeeFrame(empArr);
        }
        
    }
    
    /**
     * creates the employee text file from the changes the user has made to
     * the employee records array. overwrites "text_files\Emp.txt"
     * @throws FileNotFoundException
     */
    public static void createEmployeeTextFile() throws FileNotFoundException{
        // get employee records
        Employee[] myEmpArray;
        myEmpArray = myEmpFrame.getEmpArray();
        int arrLength = myEmpArray.length;
        
        // create text file
        try (PrintWriter outputFile = new PrintWriter("text_files\\Emp.txt")) { 
            outputFile.println(arrLength);
            // for each employee in the employee records
            for (int x = 0; x < arrLength; x++)
            {
                // create a line of text that contains the id, phone, name and years
                String appendToFile = myEmpArray[x].getEmpId() + " " +
                        myEmpArray[x].getTelephone() + " " +
                        myEmpArray[x].getName() + " " +
                        myEmpArray[x].getYears();
                
                // put that line in the file
                outputFile.println(appendToFile);
            }
        }
    }
}
    

