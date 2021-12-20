package formula;

import java.util.Map;

public class Opposite extends MonomialX {
    public Opposite (Formula member) {
        super(member, -1.0, 1);
    }

    public String toString() {
        return "(-(" + member.simplifiedFormula().toString() + "))";
    }

    @Override
    public Formula simplifiedFormula() {
        if (isConstant())
            return new Constant(new Opposite(member.simplifiedFormula()).eval(0));

        if (member instanceof Polynomial) {
            Map<Integer, Double> map = ((Polynomial) member).getMap();
            for (Map.Entry<Integer, Double> entry : map.entrySet())
                map.replace(entry.getKey(), -1.0 * entry.getValue());
            return new PolynomialX(member, map).simplifiedFormula();
        }
        return this;
    }
}
