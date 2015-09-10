package ilijan.coolculator;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.DecimalFormat;

/**
 * Created by ilijan on 10.09.15..
 */
public class CalculatorStatus {
    double firstOperand;
    Operation nextOperation;
    boolean inputFinished = true;

    TextView display;
    ToggleButton invertButton;
    ImageView fiveLogo;

    public CalculatorStatus(TextView display, ImageView fiveLogo, ToggleButton invertButton) {
        this.display = display;
        this.fiveLogo = fiveLogo;
        this.invertButton = invertButton;
        fiveLogo.setVisibility(View.INVISIBLE);
    }

    void setResult(double result) {
        inputFinished = true;

        DecimalFormat decimalFormat = new DecimalFormat("0.##########");
        display.setText(decimalFormat.format(result));

        if (display.getText().toString().equals("5")) {
            display.setVisibility(View.INVISIBLE);
            fiveLogo.setVisibility(View.VISIBLE);
            fiveLogo.setAlpha(0f);
            fiveLogo.animate().alpha(1f).setDuration(800).start();

            return;
        }

        fiveLogo.setVisibility(View.INVISIBLE);
        display.setVisibility(View.VISIBLE);
    }

    public double getNumber() {
        inputFinished = true;
        return Double.parseDouble(display.getText().toString());
    }
}
