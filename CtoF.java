import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtoF extends JFrame implements ActionListener, ChangeListener
{
    private JLabel Cel, Fah;
    private JTextField TC,TF;
    private JButton convert;
    private double Tcel, Tfah;
    private JCheckBox checkBox;
    private ButtonGroup buttonGroupSize;
    private JRadioButton small,mid,big;
    private JRadioButton buttonToCelsius, buttonToFah;
    private ButtonGroup buttonGroupChoice;
    private JLabel labelCelcius,labelFah;
    private JSlider slideCelsiuc, slideFah;
    private int tempCelcius,tempFah;
    public CtoF() {
        setSize(510, 520);
        setTitle("Temperature Converter");
        setLayout(null);

        Cel = new JLabel("Celsius Grades: ");
        Cel.setBounds(20, 20, 150, 20);
        add(Cel);

        TC = new JTextField(" ");
        TC.setBounds(170, 20, 150, 20);
        add(TC);
        TC.addActionListener(this);
        TC.setToolTipText("Enter temperature in Celsius");

        Fah = new JLabel("Fahrenheit grades: ");
        Fah.setBounds(20, 60, 150, 20);
        add(Fah);

        TF = new JTextField(" ");
        TF.setBounds(170, 60, 150, 20);
        add(TF);
        TF.setToolTipText("Shows Temperature in Fahrenheit");

        convert = new JButton("CONVERT");
        convert.setBounds(20, 100, 150, 20);
        add(convert);
        convert.addActionListener(this);

        checkBox = new JCheckBox("Bigger font");
        checkBox.setBounds(180, 100, 150, 20);
        add(checkBox);
     //   checkBox.addActionListener(this);

        buttonGroupSize = new ButtonGroup();
        small = new JRadioButton("Small", true);
        small.setBounds(50, 150, 100, 20);
        buttonGroupSize.add(small);
        add(small);
        small.addActionListener(this);
        mid = new JRadioButton("Mid",false);
        mid.setBounds(50,170,100,20);
        buttonGroupSize.add(mid);
        add(mid);
        mid.addActionListener(this);
        big = new JRadioButton("Large",false);
        big.setBounds(50,190,100,20);
        buttonGroupSize.add(big);
        add(big);
        big.addActionListener(this);

        buttonGroupChoice = new ButtonGroup();

        buttonToCelsius = new JRadioButton("From Fahrenheit to Celsius",false);
        buttonToCelsius.setBounds(150,150,200,20);
        buttonToCelsius.setLayout(null);
        buttonGroupChoice.add(buttonToCelsius);
        add(buttonToCelsius);
        buttonToCelsius.addActionListener(this);

        buttonToFah = new JRadioButton("From Celsius to Fahrenheit",true);
        buttonToFah.setBounds(150,170,200,20);
        buttonToCelsius.setLayout(null);
        buttonGroupChoice.add(buttonToFah);
        add(buttonToFah);
        buttonToFah.addActionListener(this);

        slideCelsiuc = new JSlider(0,100,0);
        slideCelsiuc.setBounds(150,240,300,50);
        slideCelsiuc.setMajorTickSpacing(10);
        slideCelsiuc.setMinorTickSpacing(5);
        slideCelsiuc.setPaintLabels(true);
        slideCelsiuc.setPaintTicks(true);
        add(slideCelsiuc);
        slideCelsiuc.addChangeListener(this);

        slideFah = new JSlider(30,212,32);
        slideFah.setBounds(150,300,300,50);
        slideFah.setMajorTickSpacing(20);
        slideFah.setMinorTickSpacing(5);
        slideFah.setPaintLabels(true);
        slideFah.setPaintTicks(true);
        add(slideFah);
        slideFah.addChangeListener(this);
        labelCelcius= new JLabel("Celcius ");
        labelCelcius.setBounds(50,225,100,50);
        add(labelCelcius);
        labelFah = new JLabel("Fahrenheit");
        labelFah.setBounds(50,285,100,50);
        add(labelFah);

    }
    public static void main(String[] args)
    {
        CtoF app = new CtoF();
        app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        app.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source =e.getSource();
        if (source == small) {
            TF.setFont(new Font("SanSerif", Font.PLAIN,12));
            TC.setFont(new Font("SanSerif", Font.PLAIN,12));
        }
        else if (source == mid) {
            TF.setFont(new Font("SanSerif", Font.PLAIN, 16));
            TC.setFont(new Font("SanSerif", Font.PLAIN,16));
        }
        else if (source == big) {
            TF.setFont(new Font("SanSerif", Font.PLAIN, 20));
            TC.setFont(new Font("SanSerif", Font.PLAIN,20));
        }
        if(source==convert|| source==TC|| source==TF)
            if (buttonToFah.isSelected()) {
                if (checkBox.isSelected()) {
                    TF.setFont(new Font("SansSerif", Font.BOLD, 18));
                } else {
                    TF.setFont(new Font("SansSerif", Font.BOLD, 13));
                }
                Tcel = Double.parseDouble(TC.getText());
                Tfah = 32.0 + (9.0 / 5) * Tcel;
                TF.setText(String.valueOf(Tfah));
            }
            else if (buttonToCelsius.isSelected()) {
                if (checkBox.isSelected()) {
                    TC.setFont(new Font("SanSerif", Font.BOLD, 18));
                } else {
                    TC.setFont(new Font("SanSerif", Font.BOLD, 13));
                }
                Tfah = Double.parseDouble(TF.getText());
                Tcel = (Tfah - 32) * (5.0 / 9.0);
                TC.setText(String.valueOf(Tcel));
            }

        }
    @Override
    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if(source==slideCelsiuc) {
            tempCelcius = slideCelsiuc.getValue();
            labelCelcius.setText("Celcius: " + tempCelcius+"\u200E°C");
            tempFah =(int)Math.round(32+(9/5)*tempCelcius);
            labelFah.setText("Fahrenheit: "+tempFah+"\u200E°F");
            slideFah.setValue(tempFah);

        }
        else if(source==slideFah) {
            tempFah = slideFah.getValue();
            labelFah.setText("Fahrenheit: "+tempFah+"\u200E°F");
            tempCelcius =(int)Math.round((tempFah -32)*(5/9));
            labelCelcius.setText("Celcius: "+tempCelcius+"\u200E°C");
            slideCelsiuc.setValue(tempCelcius);
        }
    }
}

