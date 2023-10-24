
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
import javax.swing.JFrame;

/**
 * A generic error dialog for when the user does something wrong
 * @author Arian2
 */
public class ErrorDialog extends JFrame{
    
    private JLabel errorLabel;
    private JButton okButton;
    private JPanel blankPanel;
    private JPanel labelPanel;
    private JPanel buttonPanel;
    private boolean exitOnClick;
    
    /**
     *
     * @param message error message displayed to user
     * @param exitOnClick if true, closes application when closed. otherwise keeps app running
     */
    public ErrorDialog(String message, boolean exitOnClick){
        
        this.exitOnClick = exitOnClick;
        this.setVisible(this.exitOnClick);
        
        // create panels
        blankPanel = new JPanel();
        labelPanel = new JPanel();
        buttonPanel = new JPanel();
        this.add(blankPanel, BorderLayout.NORTH);
        this.add(labelPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setTitle("Sorry, but there was an error!");
        
        // create label and button
        errorLabel = new JLabel(message);
        okButton = new JButton("OK");
        labelPanel.add(errorLabel);
        buttonPanel.add(okButton);
        
        // set size
        this.setLocation(740, 480);
        Dimension dimA = new Dimension(760,15);
        Dimension dimB = new Dimension(760,40);
        Dimension dimC = new Dimension(760,50);
        blankPanel.setPreferredSize(dimA);
        labelPanel.setPreferredSize(dimB);
        buttonPanel.setPreferredSize(dimC);
        
        // if exitOnClick is true, it closes application when closed
        if (exitOnClick == true){
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else{
            this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        }
        this.pack();
        
        
        // when this ActionListener is triggered, it closes this dialog and
        // then if exitOnClick is true it closes the application.
        class OkListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (exitOnClick == true){
                    System.exit(0);
                }
                else{
                    setVisible(false);
                }
            }
            
        }
        ActionListener oklisten = new OkListener();
        okButton.addActionListener(oklisten);
        
        
    }
}
