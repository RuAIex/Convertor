import model.Currency;
import send.CurrencyReceiver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class Main {


    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton JButton;

    final CurrencyReceiver currencyReceiver = new CurrencyReceiver();
    public Main() {
        textField1.setText("0");
        textField1.setPreferredSize(new Dimension(300, 25));

        this.comboBox1.setModel(new DefaultComboBoxModel<>(Currency.values()));
this.comboBox1.getModel().setSelectedItem(Currency.RUB);
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                convert();
            }
        });
        textField2.setText("0");
        textField2.setPreferredSize(new Dimension(300, 25));
        this.comboBox2.setModel(new DefaultComboBoxModel<>(Currency.values()));
        this.comboBox2.getModel().setSelectedItem(Currency.USD);
        comboBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                convert();
            }
        });
        JButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();

            }
        });
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty()){
                    textField1.setText("0");
                }
            }
        });
    }
    public void convert (){
        if (textField1.getText().isEmpty()){
            textField1.setText("0");
        }
        String t1 = comboBox1.getModel().getSelectedItem().toString();
        String t2 = comboBox2.getModel().getSelectedItem().toString();
        String procent = currencyReceiver.getCurrency(t1,t2);
        String sum = textField1.getText();
        Float result = Float.parseFloat(procent)* Float.parseFloat(sum);
        textField2.setText(result.toString());
    }
    public static void main (final String []args){
        final JFrame frame = new JFrame("Конвертор");
        frame.setContentPane(new Main().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        frame.pack();
        frame.setVisible(true);

    }
}
