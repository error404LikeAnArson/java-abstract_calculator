package formula;
import java.lang.Math;


public class Logarithm extends Function {
    public Logarithm(Formula member) {
        super(member);
        if (member instanceof Constant)
            if (member.eval(0) <= 0.0)
                throw new ArithmeticException("hors du domaine de definition");
    }

    public double eval(double xValue) {
        if (member.eval(xValue) <= 0.0) {
            System.out.println("hors du domaine de definition: " + Double.toString(xValue));
            System.out.println("hors du domaine de definition: " + Double.toString(xValue));
        }
        return Math.log(member.eval(xValue));
    }

    public String toString() {
        return "ln(" + member.toString() + ")";
    }

    public Formula derivative() {
        return new Division(member.derivative(), member);
    }

    @Override
    public Formula simplifiedFormula() {
        //if (isConstant())
        //    return new Constant(new Logarithm(member.simplifiedFormula()).eval(0));
        return this;
    }
}