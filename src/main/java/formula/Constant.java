package formula;

import java.util.TreeMap;

public class Constant extends Monomial {
    public Constant (double value) {
        super(new VariableX());
        coef = value;
        degree = 0;
        order = 0;
        map = new TreeMap<Integer, Double>();
        map.put(0, value);
    }

    public double eval(double xValue) {
        return coef;
    }

    public String toString() {
        return Double.toString(coef);
    }

    public Formula derivative() {
        return new Constant(0);
    }

    @Override
    public boolean isConstant() {
        return true;
    }

    @Override
    public Formula simplifiedFormula() {
            return this;
    }

    public boolean isPositive() {
        return eval(0) >= 0;
    }
}
