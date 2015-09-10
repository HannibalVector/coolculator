package ilijan.coolculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    CalculatorStatus calculatorStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView display = (TextView) findViewById(R.id.display);
        ImageView fiveLogo = (ImageView) findViewById(R.id.five_logo);
        ToggleButton invertButton = (ToggleButton) findViewById(R.id.btnInvert);

        calculatorStatus = new CalculatorStatus(display, fiveLogo, invertButton);
        setInputButtons();
        setFunctionButtons();
        setOperationButtons();
    }

    private void setOperationButtons() {
        Map<Button, OperationProperties> operationButtons = new HashMap<>();
        operationButtons.put((Button)findViewById(R.id.btnOperationAdd),
                new OperationProperties(
                        "+",
                        new Operation() {
                            @Override
                            public double perform(double firstOperand, double secondOperand) {
                                return firstOperand + secondOperand;
                            }
                        }
                ));

        operationButtons.put((Button)findViewById(R.id.btnOperationSubtract),
                new OperationProperties(
                        "-",
                        new Operation() {
                            @Override
                            public double perform(double firstOperand, double secondOperand) {
                                return firstOperand - secondOperand;
                            }
                        }
                ));

        operationButtons.put((Button)findViewById(R.id.btnOperationMultiply),
                new OperationProperties(
                        "*",
                        new Operation() {
                            @Override
                            public double perform(double firstOperand, double secondOperand) {
                                return firstOperand * secondOperand;
                            }
                        }
                ));

        operationButtons.put((Button)findViewById(R.id.btnOperationDivide),
                new OperationProperties(
                        "/",
                        new Operation() {
                            @Override
                            public double perform(double firstOperand, double secondOperand) {
                                return firstOperand / secondOperand;
                            }
                        }
                ));

        OperationButtonsSetter operationButtonsSetter = new OperationButtonsSetter();
        operationButtonsSetter
                .setStatus(calculatorStatus)
                .setButtons(operationButtons)
                .set();
    }

    private void setFunctionButtons() {
        Map<Button, FunctionProperties> functionButtons = new HashMap<>();
        functionButtons.put((Button)findViewById(R.id.btnFunctionSqrt),
                new FunctionProperties(
                        "√",
                        "x²",
                        new Function() {
                            @Override
                            public double evaulate(double argument) {
                                return Math.sqrt(argument);
                            }
                        },
                        new Function() {
                            @Override
                            public double evaulate(double argument) {
                                return argument*argument;
                            }
                        }));

        functionButtons.put((Button)findViewById(R.id.btnFunctionSin),
                new FunctionProperties(
                        "sin",
                        "asin",
                        new Function() {
                            @Override
                            public double evaulate(double argument) {
                                return Math.sin(argument);
                            }
                        },
                        new Function() {
                            @Override
                            public double evaulate(double argument) {
                                return Math.asin(argument);
                            }
                        }));

        functionButtons.put((Button) findViewById(R.id.btnFunctionCos),
                new FunctionProperties(
                        "cos",
                        "acos",
                        new Function() {
                            @Override
                            public double evaulate(double argument) {
                                return Math.cos(argument);
                            }
                        },
                        new Function() {
                            @Override
                            public double evaulate(double argument) {
                                return Math.acos(argument);
                            }
                        }));

        functionButtons.put((Button) findViewById(R.id.btnFunctionTan),
                new FunctionProperties(
                        "tan",
                        "atan",
                        new Function() {
                            @Override
                            public double evaulate(double argument) {
                                return Math.tan(argument);
                            }
                        },
                        new Function() {
                            @Override
                            public double evaulate(double argument) {
                                return Math.atan(argument);
                            }
                        }));

        functionButtons.put((Button) findViewById(R.id.btnFunctionLog),
                new FunctionProperties(
                        "log",
                        "exp",
                        new Function() {
                            @Override
                            public double evaulate(double argument) {
                                return Math.log(argument);
                            }
                        },
                        new Function() {
                            @Override
                            public double evaulate(double argument) {
                                return Math.exp(argument);
                            }
                        }));

        FunctionButtonsSetter functionButtonsSetter = new FunctionButtonsSetter();
        functionButtonsSetter
                .setStatus(calculatorStatus)
                .setButtons(functionButtons)
                .set();
    }

    private void setInputButtons() {
        List<Button> digits = new ArrayList<>();
        digits.add((Button)findViewById(R.id.btnNumber0));
        digits.add((Button)findViewById(R.id.btnNumber1));
        digits.add((Button)findViewById(R.id.btnNumber2));
        digits.add((Button)findViewById(R.id.btnNumber3));
        digits.add((Button)findViewById(R.id.btnNumber4));
        digits.add((Button)findViewById(R.id.btnNumber5));
        digits.add((Button)findViewById(R.id.btnNumber6));
        digits.add((Button) findViewById(R.id.btnNumber7));
        digits.add((Button) findViewById(R.id.btnNumber8));
        digits.add((Button) findViewById(R.id.btnNumber9));

        InputButtonsSetter inputButtonsSetter = new InputButtonsSetter();
        inputButtonsSetter
                .setStatus(calculatorStatus)
                .setDigits(digits)
                .setDot((Button) findViewById(R.id.btnDot))
                .setClear((Button) findViewById(R.id.btnClear))
                .setSign((Button) findViewById(R.id.btnSign))
                .setEquals((Button) findViewById(R.id.btnEquals))
                .set();
    }
}
