package ilijan.coolculator;

import android.view.View;
import android.widget.Button;

import java.util.List;

/**
 * Created by ilijan on 10.09.15..
 */
public class InputButtonsSetter {
    private static final String DOT_SYMBOL = ".";
    private static final String NEGATIVE_SIGN = "-";

    private CalculatorStatus calculatorStatus;
    private List<Button> digits;
    private Button btnDot;
    private Button btnClear;
    private Button btnSign;
    private Button btnEquals;

    public InputButtonsSetter setStatus(CalculatorStatus status) {
        this.calculatorStatus = status;
        return this;
    }

    public InputButtonsSetter setDigits(List<Button> digits) {
        this.digits = digits;
        return this;
    }

    public InputButtonsSetter setDot(Button btnDot) {
        this.btnDot = btnDot;
        return this;
    }

    public InputButtonsSetter setClear(Button btnClear) {
        this.btnClear = btnClear;
        return this;
    }

    public InputButtonsSetter setSign(Button btnSign) {
        this.btnSign = btnSign;
        return this;
    }

    public InputButtonsSetter setEquals(Button btnEquals) {
        this.btnEquals = btnEquals;
        return this;
    }

    private void setDigitListeners() {
        for (final Button button : digits) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (calculatorStatus.inputFinished) {
                        calculatorStatus.display.setText("");
                        calculatorStatus.inputFinished = false;
                    }
                    String currentText = calculatorStatus.display.getText().toString();
                    calculatorStatus.display.setText(currentText + button.getText());
                }
            });
        }
    }

    private void setDotListener() {
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calculatorStatus.inputFinished) {
                    calculatorStatus.display.setText("0.");
                    calculatorStatus.inputFinished = false;
                    return;
                }

                if (calculatorStatus.display.getText().toString().contains(DOT_SYMBOL)) {
                    return;
                }
                calculatorStatus.display.setText(calculatorStatus.display.getText() + DOT_SYMBOL);
            }
        });
    }

    private void setClearListener() {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorStatus.setResult(0);
                calculatorStatus.firstOperand = 0;
                calculatorStatus.nextOperation = null;
            }
        });
    }

    private void setSignListener() {
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calculatorStatus.inputFinished) {
                    calculatorStatus.inputFinished = false;
                    calculatorStatus.display.setText("0");
                }
                if (calculatorStatus.display.getText().toString().startsWith(NEGATIVE_SIGN)) {
                    calculatorStatus.display.setText(
                            calculatorStatus.display.getText().toString()
                                    .substring(1, calculatorStatus.display.getText().length()));
                } else {
                    calculatorStatus.display.setText(NEGATIVE_SIGN + calculatorStatus.display.getText());
                }
            }
        });
    }

    private void setEqualsListener() {
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double number = calculatorStatus.getNumber();
                if (calculatorStatus.nextOperation != null) {
                    double result = calculatorStatus
                            .nextOperation.perform(calculatorStatus.firstOperand, number);
                    calculatorStatus.setResult(result);
                    calculatorStatus.nextOperation = null;
                } else {
                    calculatorStatus.setResult(number);
                }
            }
        });

    }

    public void set() {
        setDigitListeners();
        setDotListener();
        setSignListener();
        setClearListener();
        setEqualsListener();
    }
}
