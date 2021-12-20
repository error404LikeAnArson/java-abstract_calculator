package formula;

public class Subtraction extends BinaryOperation {
    public final static Constant RIGHT_NEUTRAL = new Constant(0);

    public Subtraction (Formula leftMember, Formula rightMember) {
        this.leftMember = leftMember.simplifiedFormula();
        this.rightMember = rightMember.simplifiedFormula();
    }
    public double eval(double xValue) {
        return leftMember.eval(xValue) - rightMember.eval(xValue);
    }

    public String toString() {
        return "(" + leftMember.toString() + ")-(" + rightMember.toString() + ")";
    }

    public Formula derivative() {
        return new Subtraction(leftMember.derivative(), rightMember.derivative());
    }

    @Override
    public Formula simplifiedFormula() {
        if (rightMember.isConstant())
            if (rightMember.eval(0) == RIGHT_NEUTRAL.eval(0))
                return leftMember;
        if (isConstant())
            return new Constant(new Subtraction(leftMember.simplifiedFormula(), rightMember.simplifiedFormula()).eval(0));
        return this;
    }
}
