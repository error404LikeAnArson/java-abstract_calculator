package formula;

public class Addition extends BinaryOperation {
    public static final Constant NEUTRAL = new Constant(0);

    public Addition (Formula leftMember, Formula rightMember) {
        this.leftMember = leftMember.simplifiedFormula();
        this.rightMember = rightMember.simplifiedFormula();
    }

    public double eval(double xValue) {
        return leftMember.eval(xValue) + rightMember.eval(xValue);
    }

    public String toString() {
        return "(" + leftMember.toString() + ")+(" + rightMember.toString() + ")";
    }

    public Formula derivative() {
        return new Addition(leftMember.derivative(), rightMember.derivative());
    }

    @Override
    public Formula simplifiedFormula() {
        if (leftMember.isConstant())
            if (leftMember.eval(0) == NEUTRAL.eval(0))
                return rightMember;
        if (rightMember.isConstant())
            if (rightMember.eval(0) == NEUTRAL.eval(0))
                return leftMember;
        if (isConstant())
            return new Constant(new Addition(leftMember.simplifiedFormula(), rightMember.simplifiedFormula()).eval(0));

        return this;
    }
}
