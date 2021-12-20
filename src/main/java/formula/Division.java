package formula;

public class Division extends BinaryOperation {
    public final static Constant RIGHT_NEUTRAL = new Constant(1);
    public static final Constant LEFT_ANNULATOR = new Constant(0);

    public Division (Formula leftMember, Formula rightMember) {
        if (rightMember instanceof Constant)
            if (rightMember.eval(0) == 0.0)
                throw new ArithmeticException("division par zero");
        this.leftMember = leftMember.simplifiedFormula();
        this.rightMember = rightMember.simplifiedFormula();
    }

    public double eval(double xValue) {
        if (rightMember.eval(xValue) == 0.0)
            System.out.println("attention division par zero");
        return leftMember.eval(xValue) / rightMember.eval(xValue);
    }

    public String toString() {
        return "(" + leftMember.toString() + ")/(" + rightMember.toString() + ")";
    }

    public Formula derivative() {
        return new Division(new Subtraction(new Multiplication(leftMember.derivative(), rightMember), new Multiplication(leftMember, rightMember.derivative())), new Multiplication(rightMember, rightMember));
    }

    @Override
    public Formula simplifiedFormula() {
        if (rightMember.isConstant())
            if (leftMember.eval(0) == LEFT_ANNULATOR.eval(0))
                 return new Constant(0);
        if (rightMember.isConstant())
            if (rightMember.eval(0) == RIGHT_NEUTRAL.eval(0))
                return leftMember;
        if (isConstant())
            return new Constant(new Division(leftMember.simplifiedFormula(), rightMember.simplifiedFormula()).eval(0));
        return this;
    }
}
