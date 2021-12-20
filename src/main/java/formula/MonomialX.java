package formula;

import java.util.TreeMap;

public class MonomialX extends Monomial {
    private Formula formula;

    public MonomialX (Formula member, Double coef, Integer degree) {
        super(member);
        map = new TreeMap<Integer, Double>();
        map.put(degree, coef);
        formula = new Multiplication(new Constant(coef), new Exponentiation(member, new Constant(degree)));
        this.coef = coef;
        this.degree = degree;
        order = degree;
    }

    public double eval(double xValue) {
        return formula.eval(xValue);
    }

    public String toString() {
        return formula.simplifiedFormula().toString();
    }

    public Formula derivative() {
        return formula.derivative();
    }

    @Override
    public Formula simplifiedFormula() {
        return formula.simplifiedFormula();
    }
}
