package ilijan.coolculator;

import android.view.View;
import android.widget.Button;

import java.util.Map;

/**
 * Created by ilijan on 10.09.15..
 */
public class OperationButtonsSetter {

    private CalculatorStatus calculatorStatus;
    private Map<Button, OperationProperties> operationButtons;

    public OperationButtonsSetter setStatus(CalculatorStatus status) {
        this.calculatorStatus = status;
        return this;
    }

    public OperationButtonsSetter setButtons(Map<Button, OperationProperties> operationButtons) {
        this.operationButtons = operationButtons;
        return this;
    }

    public void set() {
        setOperationButtonsListeners();
    }

    private void setOperationButtonsListeners() {
        for(Button button : operationButtons.keySet()) {
            final OperationProperties operationProperties = operationButtons.get(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    evaluateOperation(operationProperties);
                }
            });
        }
    }

    private void evaluateOperation(OperationProperties operationProperties) {
        try {
            double number = calculatorStatus.getNumber();
            if (calculatorStatus.nextOperation == null) {
                calculatorStatus.firstOperand = number;
            } else {
                double result = calculatorStatus.nextOperation
                        .perform(calculatorStatus.firstOperand, number);
                calculatorStatus.firstOperand = result;
                calculatorStatus.setResult(result);
            }
            calculatorStatus.nextOperation = operationProperties.operation;
        } catch (Exception ex) {
            calculatorStatus.setResult(0);
            calculatorStatus.display.setText("Error");
        }
    }
}
