package formula;

public class Exponentiation extends BinaryOperation {
    public static final Constant NEUTRAL = new Constant(1);
    public static final Constant ANNULATOR = new Constant(0);

    public Exponentiation (Formula leftMember, Formula rightMember) {
        this.leftMember = leftMember.simplifiedFormula();
        this.rightMember = rightMember.simplifiedFormula();
    }
    public double eval(double xValue) {
        return Math.pow(leftMember.eval(xValue), rightMember.eval(xValue));
    }

    public String toString() {
        return "(" + leftMember.toString() + ")^(" + rightMember.toString() + ")";
    }

    public Formula derivative() {
        return new Multiplication(this, (new Multiplication(rightMember, new Logarithm(leftMember))).derivative());
    }

    @Override
    public Formula simplifiedFormula() {
        if (leftMember.isConstant()) {
            if (leftMember.eval(0) == NEUTRAL.eval(0))
                return leftMember;
            if (leftMember.eval(0) == ANNULATOR.eval(0) && rightMember.eval(0) > 0)
                return new Constant(0);
        }
        if (rightMember.isConstant()) {
            if (rightMember.eval(0) == NEUTRAL.eval(0))
                return leftMember;
            if (rightMember.eval(0) == ANNULATOR.eval(0))
                return new Constant(1);
        }
        if (isConstant())
            return new Constant(new Exponentiation(leftMember.simplifiedFormula(), rightMember.simplifiedFormula()).eval(0));

        return this;
    }
}
