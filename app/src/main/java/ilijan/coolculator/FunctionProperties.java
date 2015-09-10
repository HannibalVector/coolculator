package ilijan.coolculator;

/**
 * Created by ilijan on 10.09.15..
 */
public class FunctionProperties {
    String symbol;
    String inverseSymbol;
    Function function;
    Function inverse;

    public FunctionProperties(String symbol, String inverseSymbol, Function function, Function inverse) {
        this.symbol = symbol;
        this.inverseSymbol = inverseSymbol;
        this.function = function;
        this.inverse = inverse;
    }
}
