
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

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

/**
 * generates an employee array
 * @author Arian2
 */
public class EmployeeRecords{
    
    /**
     * generates an employee array from the directory "text_files\Emp.txt"
     * @return empArray if no exceptions were caught, otherwise return null
     * @throws FileNotFoundException
     */
    public static Employee[] createEmployeeArray() throws FileNotFoundException
    {
        Employee[] empArray;
        try
        {
            File inputFile = new File("text_files\\Emp.txt");
            Scanner myScanner = new Scanner(inputFile);
        
            // get the number of employees
            int arrayLength = myScanner.nextInt();
            myScanner.nextLine();
            String[] strArray = new String[arrayLength];
            empArray = new Employee[arrayLength];
            
            // put every line of text in a string array
            for (int x = 0; x < arrayLength; x++)
            {
                String tempStr = myScanner.nextLine();
                strArray[x] = tempStr;
            }
        
            myScanner.close();
        
            // create an employee instance from every index in the string array
            for (int y = 0; y < arrayLength; y++)
            {
                // split the line into a list
                String[] temp = strArray[y].split(" ");
                // the first value in the line is the ID
                int id = Integer.parseInt(temp[0]);
                // the second value is the phone number
                String phone = temp[1];
                int tempLength = temp.length;
                // the last value in the line is the number of years worked
                int years = Integer.parseInt(temp[tempLength-1]);
                // the string of text between the phone number and the years of 
                // service is the name
                String name = "";
                for (int z = 2; z < tempLength-1; z++)
                {
                    if (z==2){
                        name = name + temp[z];
                    }
                    else{
                        name = name + " " + temp[z];
                    }
                    
                }
                // create new employee instance and put it in array
                Employee tempEmp = new Employee(id, name, phone, years);
                empArray[y] = tempEmp;
            }
            return empArray;
        }
        
        
        
        
        // when file doesn't exist
        catch (FileNotFoundException e)
        {
            ErrorDialog err = new ErrorDialog("The file doesn't exist!", true);
        }
        
        // when input for number of employees is invalid
        catch (NoSuchElementException e)
        {
            ErrorDialog err = new ErrorDialog("You may not have put the number "
                    + "of employees at the top of the text file, or it may have"
                    + " been incorrect!", true);
        }
        
        // when input for ID or years of service is invalid
        catch (NumberFormatException e)
        {
            ErrorDialog err = new ErrorDialog("You may have entered the ID or "
                    + "years of service of an employee as a non-integer value!"
                    , true);
        }
        
        // when something else goes wrong
        catch (Exception e)
        {
            ErrorDialog err = new ErrorDialog("Something went wrong while the "
                    + "text file was being interpreted! Please check your text "
                    + "file and make sure it is correctly formatted!", true);
        }
        return null;
    }
}
