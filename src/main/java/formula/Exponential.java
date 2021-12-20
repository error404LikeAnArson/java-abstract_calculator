package formula;
import java.lang.Math;

public class Exponential extends Function {
    public Exponential (Formula member) {
        super(member);
    }
    public double eval(double xValue) {
        return Math.exp(member.eval(xValue));
    }

    public String toString() {
        return "exp(" + member.toString() + ")";
    }

    public Formula derivative() {
        return new Multiplication(member.derivative(), new Exponential(member));
    }

    @Override
    public Formula simplifiedFormula() {
        if (isConstant())
            return new Constant(new Exponential(member.simplifiedFormula()).eval(0));
        return this;
    }
}