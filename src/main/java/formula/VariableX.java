package formula;

public class VariableX implements Formula {
    public double eval(double xValue) {
        return xValue;
    }

    public String toString() {
        return "x";
    }

    public Formula derivative() {
        return new Constant(1);
    }

    @Override
    public boolean isConstant() {
        return false;
    }

    @Override
    public Formula simplifiedFormula() {
        return this;
    }
}
