package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Calculator implements ActionListener {
    Stack<Character> operands = new Stack<>();
    Stack<String> numbers = new Stack<>();
    String expression = "";
    String numberBuffer = "";
    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, clrButton;
    JPanel panel;
    Font myFont = new Font("Ink Free", Font.BOLD, 30);

    String num1, num2, result = "0";
    char operator;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(true);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        clrButton = new JButton("Clr");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = clrButton;

        for (int i = 0; i < 7; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        clrButton.setBounds(250, 430, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(clrButton);
        frame.add(textfield);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            textfield.setText(textfield.getText().concat("."));
        }
        if (e.getSource() == addButton) {
            textfield.setText(textfield.getText().concat("+"));
        }

        if (e.getSource() == subButton) {
            textfield.setText(textfield.getText().concat("-"));
        }

        if (e.getSource() == mulButton) {
            textfield.setText(textfield.getText().concat("*"));
        }
        if (e.getSource() == divButton) {
            textfield.setText(textfield.getText().concat("/"));
        }

        if (e.getSource() == equButton) {
            fillExpressionStack();
//            JOptionPane.showMessageDialog(null, expression, "Expression", JOptionPane.WARNING_MESSAGE);

            textfield.setText(Double.toString(calculate()));
            expression = "";
            numbers.clear();
            operands.clear();
            numberBuffer = "";
        }

        if (e.getSource() == clrButton) {
            textfield.setText("");
        }
    }

    private int getPriority(char token) {
        return switch (token) {
            case '(' -> 1;
            case ')' -> -1;
            case '*' -> 3;
            case '/' -> 3;
            case '^' -> 4;
            case '+' -> 2;
            case '-' -> 2;
            default -> 0;
        };
    }

    //    private double calculate(){
//
//    }

    private void fillExpressionStack() {
        String input = textfield.getText();
        String sentence = "";

        int priority;
        for (char symbol :
                input.toCharArray()) {

            priority = getPriority(symbol);

            if (priority == 0) sentence += Character.toString(symbol);
            if (priority == 1) operands.push(symbol); //open brace

            if (priority > 1) {

                sentence += ",";

                while (!operands.isEmpty()) {
                    if (getPriority(operands.peek()) >= priority) {
                        sentence += Character.toString(operands.pop()) + ",";
                    } else {
                        break;
                    }
                }
                operands.push(symbol);
            }

            if (priority == -1) { //closed brace
                while (getPriority(operands.peek()) != 1) {
                    if(!sentence.startsWith(",")){
                        sentence += ",";
                    }
                    sentence += Character.toString(operands.pop());
                }
                operands.pop();
            }
        }

        while (!operands.isEmpty()) {
            sentence += "," + Character.toString(operands.pop());
        }

        expression = trimLastComma(sentence.trim());
    }

    private double calculate() {
        Stack<Double> calculator = new Stack<>();

        for (String exp:
             expression.split(",")) {
            if(isOperand(exp)){
                Double b = calculator.pop();
                Double a = calculator.pop();
                calculator.push(execExpression(a,b,exp));
                continue;
            }
            if(validateDouble(exp)){
                calculator.push(Double.parseDouble(exp));
                continue;
            }
            JOptionPane.showMessageDialog(null, "String given in.", "Expression", JOptionPane.ERROR_MESSAGE);
        }
        return calculator.pop();
    }

    private String trimLastComma(String str) {
        if ((str == null || str.length() == 0)) {
            return "";
        }

        if (str.charAt(str.length() - 1) == ',') {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }

    private boolean validateDouble(String str) {
        if (str == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private boolean isOperand(String str) {
        return getPriority(str.charAt(str.length() - 1)) > 1;
    }

    private Double execExpression(Double a, Double b, String operand) {
        return switch (operand) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            case "^" -> Math.pow(a, b);
            default -> 0.0;
        };
    }
}