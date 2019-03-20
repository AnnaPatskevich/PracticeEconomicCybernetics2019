import javax.swing.*;

public class Add extends JFrame {

    Catalog frame;
    private JLabel typeLabel;
    private JTextField typeTextField;
    private JButton closeButton;
    private JLabel serialLabel;
    private JTextField serialTextField;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JLabel manufacturerLabel;
    private JTextField manufacturerTextField;
    private JButton okButton;
    private JLabel modLabel;
    private JTextField modTextField;
    public Add(Catalog temp) {

        frame = temp;
        initComponents();
        this.setTitle("Добавление цветка");
        this.setBounds(400, 200, 400, 150);
        this.setSize(350, 250);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    private void initComponents() {

        typeLabel = new JLabel();
        modLabel = new JLabel();
        manufacturerLabel = new JLabel();
        typeTextField = new JTextField();
        modTextField = new JTextField();
        manufacturerTextField = new JTextField();
        serialTextField = new JTextField();
        serialLabel = new JLabel();
        jSeparator1 = new JSeparator();
        jSeparator2 = new JSeparator();
        okButton = new JButton();
        closeButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        typeLabel.setText("Тип устройства: ");
        modLabel.setText("Производитель: ");
        manufacturerLabel.setText("Модель ");

        typeTextField.addCaretListener(this::manufacturerTextFieldCaretUpdate);

        modTextField.addCaretListener(this::modTextFieldCaretUpdate);
        manufacturerTextField.addCaretListener(this::typeTextFieldCaretUpdate);
        serialLabel.setText("Серийный номер:");
        okButton.setText("Добавить");
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });
        closeButton.setText("Закрыть");
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(serialLabel)
                                                        .addComponent(manufacturerLabel)
                                                        .addComponent(modLabel)
                                                        .addComponent(typeLabel)

                                                )
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(modTextField, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(manufacturerTextField, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(serialTextField, GroupLayout.Alignment.TRAILING)

                                                        .addComponent(typeTextField)
                                                ))
                                        .addComponent(jSeparator2)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(okButton)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(closeButton))
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addGap(18, 18, 18)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))
                                                        ))
                                                .addGap(0, 4, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(typeLabel)
                                        .addComponent(typeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(modLabel)
                                        .addComponent(modTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(manufacturerLabel)
                                        .addComponent(manufacturerTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(serialTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(serialLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(closeButton))
                                .addContainerGap())
        );
        pack();
    }

    private void manufacturerTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_typeTextFieldCaretUpdate
        checkOkClose();
    }

    private void modTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_modTextFieldCaretUpdate
        checkOkClose();
    }

    private void typeTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_manufacturerTextFieldCaretUpdate
        checkOkClose();
    }

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
        this.dispose();
    }

    private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
        if (this.typeTextField.getText().isEmpty() || this.modTextField.getText().isEmpty()
                || this.manufacturerTextField.getText().isEmpty() || this.serialTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Необходимо заполнить все поля!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (this.okButton.isEnabled()) {
            MobileN nb = new MobileN(
                    this.typeTextField.getText(), this.modTextField.getText(), this.manufacturerTextField.getText(),
                    this.serialTextField.getText().isEmpty() ? "-" : this.serialTextField.getText()
            );
            Catalog.addResult = nb;
            frame.addNewItem();
            this.dispose();
        }
    }

    public void checkOkClose() {

        if (!this.typeTextField.getText().isEmpty()
                && !this.modTextField.getText().isEmpty()
                && !this.manufacturerTextField.getText().isEmpty()) {
            this.okButton.setEnabled(true);
        }
    }

    public void setKeyboardState(boolean flag) {

        this.typeTextField.setEditable(flag);
        this.modTextField.setEditable(flag);
        this.modTextField.setEditable(flag);
        this.manufacturerTextField.setEditable(flag);
        this.serialTextField.setEditable(flag);
        this.okButton.setEnabled(false);
        this.closeButton.setEnabled(true);
        checkOkClose();
    }
}