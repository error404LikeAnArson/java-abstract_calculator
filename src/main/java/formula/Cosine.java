package formula;
import java.lang.Math;


public class Cosine extends Function {
    public Cosine(Formula member) {
        super(member);
    }

    public double eval(double xValue) {
        return Math.cos(member.eval(xValue));
    }

    public String toString() {
        return "cos(" + member.toString() + ")";
    }

    public Formula derivative() {
        return new Multiplication(member.derivative(), new Opposite(new Sine(member)));
    }

    @Override
    public Formula simplifiedFormula() {
        if (isConstant())
            return new Constant(new Cosine(member.simplifiedFormula()).eval(0));
        return this;
    }
}