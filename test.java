import javax.swing.*;
import java.awt.*;

public class test {

     static double calculate(String equation) {               //       2+(4-1*3) --->. indexOf("(")  --> 2
    //                                                                        --->. indexOf(")")  --> 8
    
    //                                                                        substring(2, 8)
    //                                                                                 result - Tiv    
    //                                                                                   2+1

          /// 9 +   2-8
        if(equation.contains("+")){
            String[] parts = equation.split("\\+");
            double firstNumber = Double.parseDouble(parts[0]);
            double secondNumber = Double.parseDouble(parts[1]);
            return firstNumber + secondNumber;
        }
        if(equation.contains("-")){
            String[] parts = equation.split("-");  //3-(9+2-8)          
            return Double.parseDouble(parts[0]) - Double.parseDouble(parts[1]);
        }
        if(equation.contains("*")){
            String[] parts = equation.split("\\*");  
            
            
            int index = equation.indexOf("*"); //3*(9+2-8)           
            return Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]);
        }
        if(equation.contains("/")){
            String[] parts = equation.split("/");  // 3(9+2-8)           
            return Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
        }
        return Double.parseDouble(equation);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Կալկուլյատոր");
        frame.setSize(350, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JTextField display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 5, 10, 20));

        String[] buttons = {
                "7", "8", "9", "+", "X",
                "4", "5", "6", "-", "(",
                "1", "2", "3", "*", ")", 
                ".", "0", "=", "/", "C"
        
        };

        final double[] firstNumber = {0};
        final String[] operation = {""};

        for (String text : buttons) {

            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 22));

            panel.add(button);

            button.addActionListener(e -> {

                String value = button.getText();

                // Եթե թիվ է (9+58-8)
                if (value.matches("[0-9]") || value.equals(".") || value.equals("(") || value.equals(")")) {       //2 կամ 3 նաև 2.5

                    display.setText(display.getText() + value);
                }


                // Մաքրել  ամբողջ էկրանը
                else if (value.equals("C")) {
                    display.setText("");
                    firstNumber[0] = 0; ///0 1244.78    QA
                    operation[0] = "";
                }
                // Ջնջում է վերջին նիշը
                else if (value.equals("X")){
                    String currentText = display.getText();  
                    
                    if(!currentText.isEmpty()){ // 2*(15-487)
                        currentText = currentText.substring(0, currentText.length() - 1);
                        display.setText(currentText);
                    }
                }
                

                // Գործողություններ    
                else if (value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/")) {
                    if (!display.getText().isEmpty()) {

                        // firstNumber[0] = Double.parseDouble(display.getText());  2+7 - (12*3)
                        // operation[0] = value;

                        display.setText(display.getText() + value);

                    }
                }

                // =//       2+(4-1*3) --->. indexOf("(")  --> 2
    //                                                                        --->. indexOf(")")  --> 8
    
    //                                                                        substring(2, 8)
    //                                                                                 result - Tiv    
    //                                                                                   2+1

                else if (value.equals("=")) {
                      String expression = display.getText();

                    if( expression.contains("(") && expression.contains(")")){
                        int openIndex = expression.indexOf("(");
                        int closeIndex = expression.indexOf(")");

                        String inside = expression.substring(openIndex + 1, closeIndex); // 5-6

                        double insideResult = calculate(inside);
                        expression = expression.replace("(" + inside + ")", String.valueOf(insideResult));
                    }







                      double result = calculate(expression);
                      display.setText(String.valueOf(result));


                    // if (!display.getText().isEmpty()) {

                    //     double secondNumber = Double.parseDouble(display.getText());

                    //     double result = 0;

                    //     switch (operation[0]) {

                    //         case "+":
                    //             result = firstNumber[0] + secondNumber;
                    //             break;

                    //         case "-":
                    //             result = firstNumber[0] - secondNumber;
                    //             break;

                    //         case "*":
                    //             result = firstNumber[0] * secondNumber;
                    //             break;

                    //         case "/":

                    //             if (secondNumber != 0) {

                    //                 result = firstNumber[0] / secondNumber;

                    //             } else {

                    //                 display.setText("Սխալ");
                    //                 return;
                    //             }

                    //             break;
                    //     }

                    //     display.setText(String.valueOf(result));
                    // }
                }
            });
        }

        frame.setLayout(new BorderLayout(10, 10));

        frame.add(display, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
