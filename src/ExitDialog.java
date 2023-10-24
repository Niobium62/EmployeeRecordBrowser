
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
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import javax.swing.JDialog;
import java.awt.Dialog.ModalityType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ExitDialog object for when the user wants to quit the application
 * @author Arian2
 */
public class ExitDialog extends JDialog{
    
    private JLabel exitLabel;
    private JButton yesButton;
    private JButton noButton;
    private JButton cancelButton;
    private JPanel blankPanel;
    private JPanel labelPanel;
    private JPanel buttonPanel;
    
    /**
     * 
     */
    public ExitDialog(){
        // create panels
        this.setVisible(false);
        blankPanel = new JPanel();
        labelPanel = new JPanel();
        buttonPanel = new JPanel();
        this.add(blankPanel, BorderLayout.NORTH);
        this.add(labelPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setTitle("Exit");
        
        // create label and buttons
        exitLabel = new JLabel("Would you like to save the modifications made?");
        cancelButton = new JButton("Cancel");
        noButton = new JButton("No");
        yesButton = new JButton("Yes");
        
        // add components to this JDialog
        labelPanel.add(exitLabel);
        buttonPanel.add(cancelButton);
        buttonPanel.add(noButton);
        buttonPanel.add(yesButton);
        
        // set size of this JDialog
        this.setLocation(740, 480);
        Dimension dimA = new Dimension(350,15);
        Dimension dimB = new Dimension(350,40);
        Dimension dimC = new Dimension(350,50);
        blankPanel.setPreferredSize(dimA);
        labelPanel.setPreferredSize(dimB);
        buttonPanel.setPreferredSize(dimC);
        this.pack();
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        
        
        // when this ActionListener is triggered, it "closes" this JDialog 
        // without closing the application
        class CancelListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent ae) {
                ExitDialog.this.setVisible(false);
            }
            
        }
        
        // close this JDialog when "cancel" button is clicked
        ActionListener cancellisten = new CancelListener();
        cancelButton.addActionListener(cancellisten);
        
        
        // when this ActionListener is triggered, it closes the application
        class NoListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
            
        }
        
        // close the application when "no" button is clicked
        ActionListener nolisten = new NoListener();
        noButton.addActionListener(nolisten);
        
        
        // when this ActionListener is triggered, it closes the application and
        // saves the changes the user made to the employee records
        class YesListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    EmployeeMain.createEmployeeTextFile();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ExitDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
            
        }
        
        // close the application and save changes when "yes" button is clicked
        ActionListener yeslisten = new YesListener();
        yesButton.addActionListener(yeslisten);
    }
}
