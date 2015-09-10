package ilijan.coolculator;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import java.util.Map;

/**
 * Created by ilijan on 10.09.15..
 */
public class FunctionButtonsSetter {

    private CalculatorStatus calculatorStatus;
    private Map<Button, FunctionProperties> functionButtons;

    public FunctionButtonsSetter setStatus(CalculatorStatus status) {
        this.calculatorStatus = status;
        return this;
    }

    public FunctionButtonsSetter setButtons(Map<Button, FunctionProperties> functionButtons) {
        this.functionButtons = functionButtons;
        return this;
    }

    public void set() {
        setLabels();
        setFunctionButtonsListeners();
        setInvertButtonListener();
    }

    private void setFunctionButtonsListeners() {
        for(Button button : functionButtons.keySet()) {
            final FunctionProperties properties = functionButtons.get(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    evaluateFunction(properties);
                }
            });
        }
    }

    private void evaluateFunction(FunctionProperties properties) {
        try {
            double number = Double.parseDouble(calculatorStatus.display.getText().toString());

            Function functionToUse =
                    calculatorStatus.invertButton.isChecked() ?
                            properties.inverse : properties.function;

            double result = functionToUse.evaulate(number);
            calculatorStatus.setResult(result);
        } catch (Exception ex) {
            calculatorStatus.setResult(0);
            calculatorStatus.display.setText("Error");
        }
    }

    private void setInvertButtonListener() {
        calculatorStatus.invertButton.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        setLabels();
                        setInvertButtonColor();
                    }
                });
    }

    private void setLabels() {
        for(Button button : functionButtons.keySet()) {
            FunctionProperties properties = functionButtons.get(button);
            if (calculatorStatus.invertButton.isChecked()) {
                button.setText(properties.inverseSymbol);
                calculatorStatus.invertButton.setTextColor(Color.RED);
            } else {
                button.setText(properties.symbol);
                calculatorStatus.invertButton.setTextColor(Color.WHITE);
            }
        }
    }

    private void setInvertButtonColor() {
        if (calculatorStatus.invertButton.isChecked()) {
            calculatorStatus.invertButton.setTextColor(Color.RED);
        } else {
            calculatorStatus.invertButton.setTextColor(Color.WHITE);
        }
    }
}
