package formula;
import java.lang.Math;


public class Sine extends Function {
    Sine(Formula member) {
        super(member);
    }

    public double eval(double xValue) {
        return Math.sin(member.eval(xValue));
    }

    public String toString() {
        return "sin(" + member.toString() + ")";
    }

    public Formula derivative() {
        return new Multiplication(member.derivative(), new Cosine(member));
    }

    @Override
    public Formula simplifiedFormula() {
        if (isConstant())
            return new Constant(new Sine(member.simplifiedFormula()).eval(0));
        return this;
    }
}