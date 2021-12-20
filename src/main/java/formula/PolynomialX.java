package formula;

import java.util.Map;
import java.util.TreeMap;

public class PolynomialX extends Polynomial {
    private Formula formula;

    public PolynomialX (Formula member, Map<Integer, Double> map) {
        super(member);
        this.map = map;
        formula = new Constant(0);
        order = 0;
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            if (entry.getKey() > order)
                order = entry.getKey();
            formula = new Addition(formula, new MonomialX(member, entry.getValue(), entry.getKey()));
        }
    }

    public PolynomialX (Formula member, MonomialX[] monomials) {
        super(member);
        map = new TreeMap<Integer, Double>();
        for (MonomialX monomial: monomials) {
            map.put(monomial.getDegree(), monomial.getCoef());
        }

        formula = new Constant(0);
        order = 0;
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            if (entry.getKey() > order)
                order = entry.getKey();
            formula = new Addition(formula, new MonomialX(member, entry.getValue(), entry.getKey()));
        }
    }

    public double eval(double xValue) {
        return formula.eval(xValue);
    }

    public String toString() {
        return formula.toString();
    }

    public Formula derivative() {
        return formula.derivative();
    }

    @Override
    public Formula simplifiedFormula() {
        return formula.simplifiedFormula();
    }
}
