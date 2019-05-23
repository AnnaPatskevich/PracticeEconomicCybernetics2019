
package bsu.fpmi.educational_practice2019;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout.Group;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author Даша
 */

enum ButtonValues {
    BtnType1, BtnType2, BtnType3
}
enum LabelValues {
    TextType1, TextType2
}
enum RadioButtonValues {
    RBtnType1, RBtnType2, RBtnType3, RBtnType4
}
enum KeyValues {
    a, b, c, d, e
}

public class MySpecComponent extends javax.swing.JPanel {
	char key = 'a';
	String STR = "Button is preesed";
	ActionListener listener = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (jRadioButton1.isSelected())
            {
                STR = "Button is pressed, " + jRadioButton1.getText() + " is active";
                return;
            }
            else if (jRadioButton2.isSelected())
            {
                STR = "Button is pressed, " + jRadioButton2.getText() + " is active";
                return;
            }
            else
                STR = "Button is pressed, RadioButtons no active";
            return;
           
        }
    };
    
    LabelValues lbValues;
    public void setLabelText(LabelValues lbValues)
    {
        this.lbValues = lbValues;
        switch (this.lbValues) {
            case TextType1:
                jLabel1.setText("Label1");
                break;
            case TextType2:
                jLabel1.setText("Label2");
                break;
        }
    }
    ButtonValues bValues;
    public void setButtonText(ButtonValues bValues)
    {
        this.bValues = bValues;
        switch (this.bValues) {
            case BtnType1:
                jButton1.setText("Button1");
                break;
            case BtnType2:
                jButton1.setText("Button2");
                break;
            case BtnType3:
                jButton1.setText("Button3");
                break;
        }
    }
    
    RadioButtonValues rbValues1;
    public void setRadioButton1Text(RadioButtonValues rbValues1)
    {
        this.rbValues1 = rbValues1;
        switch (this.rbValues1) {
            case RBtnType1:
                jRadioButton1.setText("RadioButton1");
                break;
            case RBtnType2:
                jRadioButton1.setText("RadioButton2");
                break;
            case RBtnType3:
                jRadioButton1.setText("RadioButton3");
                break;
            case RBtnType4:
                jRadioButton1.setText("RadioButton4");
                break;
        }
    }
    RadioButtonValues rbValues2;
    public void setRadioButton2Text(RadioButtonValues rbValues2)
    {
        this.rbValues2 = rbValues2;
        switch (this.rbValues2) {
            case RBtnType1:
                jRadioButton2.setText("RadioButton1");
                break;
            case RBtnType2:
                jRadioButton2.setText("RadioButton2");
                break;
            case RBtnType3:
                jRadioButton2.setText("RadioButton3");
                break;
            case RBtnType4:
                jRadioButton2.setText("RadioButton4");
                break;
        }
    }
      public ButtonValues getButtonText()
      {
          return bValues;
      }
      public RadioButtonValues getRadioButton1Text()
      {
          return rbValues1;
      }
      public RadioButtonValues getRadioButton2Text()
      {
          return rbValues2;
      }
       public LabelValues getLabelText()
      {
          return lbValues;
      }
        //private char c;
        
      KeyValues keyValues;
      public KeyValues getKey() {
        return keyValues;
      }
    public void setKey(KeyValues keyValues) {
        this.keyValues = keyValues;
        key = this.keyValues.toString().charAt(0);
    } 
    public MySpecComponent() {

        initComponents();
        initEnterKey();
    }

    
    @SuppressWarnings("unchecked")
    private void initComponents()
    {
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton(); 
        
        jRadioButton1.addActionListener(listener);
        jRadioButton1.setSelected(true);

        jRadioButton2 = new javax.swing.JRadioButton();
        
        jRadioButton2.addActionListener(listener);
        jRadioButton2.setSelected(true);
        
        ButtonGroup group = new ButtonGroup();
        group.add(jRadioButton1);
        group.add(jRadioButton2);
        
        setToolTipText("");

        jLabel1.setText("Label1");
        
        jRadioButton1.setText("RadioButton1");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });
        jRadioButton2.setText("RadioButton2");
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });
        jButton1.setText("Button1");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addComponent(jRadioButton1)
                .addComponent(jRadioButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    )
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)
    {
        JOptionPane.showMessageDialog(this, STR);
    }

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {                                           
       
             jRadioButton1.setSelected(true);
             jRadioButton2.setSelected(false);
        
    }                                          

    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {                                           

             jRadioButton2.setSelected(true);
             jRadioButton1.setSelected(false);
       
        
    }            

    private javax.swing.JButton jButton1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JLabel jLabel1;

    private void initEnterKey() {
        jButton1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == key) {
                    //jButton1.doClick();
                    //jButton1ActionPerformed(e, STR);
                     //JOptionPane.showMessageDialog(jButton1, STR);
                     //jButton1.
                     if (jRadioButton1.isSelected())
                    {
                        STR = "Button is pressed, " + jRadioButton1.getText() + " is active";
                    }
                    else if (jRadioButton2.isSelected())
                    {
                        STR = "Button is pressed, " + jRadioButton2.getText() + " is active";

                    }
                    else
                        STR = "Button is pressed, RadioButtons no active";

                     JOptionPane.showMessageDialog(jButton1, STR);
                 
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}


