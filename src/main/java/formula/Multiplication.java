package formula;

public class Multiplication extends BinaryOperation {
    public static final Constant NEUTRAL = new Constant(1);
    public static final Constant ANNULATOR = new Constant(0);

    public Multiplication (Formula leftMember, Formula rightMember) {
        this.leftMember = leftMember.simplifiedFormula();
        this.rightMember = rightMember.simplifiedFormula();
    }
    public double eval(double xValue) {
        return leftMember.eval(xValue) * rightMember.eval(xValue);
    }

    public String toString() {
        return "(" + leftMember.toString() + ")*(" + rightMember.toString() + ")";
    }

    public Formula derivative() {
        return new Addition(new Multiplication(leftMember.derivative(), rightMember), new Multiplication(leftMember, rightMember.derivative()));
    }

    @Override
    public Formula simplifiedFormula() {
        if (leftMember.isConstant()) {
            if (leftMember.eval(0) == NEUTRAL.eval(0))
                return rightMember;
            if (leftMember.eval(0) == ANNULATOR.eval(0))
                return new Constant(0);
        }
        if (rightMember.isConstant()) {
            if (rightMember.eval(0) == NEUTRAL.eval(0))
                return leftMember;
            if (rightMember.eval(0) == ANNULATOR.eval(0))
                return new Constant(0);
        }
        if (isConstant())
            return new Constant(new Multiplication(leftMember.simplifiedFormula(), rightMember.simplifiedFormula()).eval(0));

        if (leftMember instanceof Division)
            return new Division(new Multiplication(((Division) leftMember).getLeftMember(), rightMember).simplifiedFormula(), ((Division) leftMember).getRightMember());

        if (rightMember instanceof Division)
            return new Division(new Multiplication(((Division) rightMember).getLeftMember(), leftMember).simplifiedFormula(), ((Division) rightMember).getRightMember());

        if (leftMember instanceof Monomial && rightMember instanceof Constant)
            return new MonomialX(((Monomial)leftMember).getMember(), ((Monomial) leftMember).getCoef() * rightMember.eval(0), ((Monomial) leftMember).getOrder());

        if (leftMember instanceof Constant && rightMember instanceof Monomial)
            return new MonomialX(((Monomial)leftMember).getMember(), ((Monomial) rightMember).getCoef() * leftMember.eval(0), ((Monomial) rightMember).getOrder());

        if (leftMember instanceof Monomial && rightMember instanceof VariableX)
            return new MonomialX(((Monomial)leftMember).getMember(), ((Monomial) leftMember).getCoef(), ((Monomial) leftMember).getOrder() + 1);

        if (leftMember instanceof VariableX && rightMember instanceof Monomial)
            return new MonomialX(((Monomial)leftMember).getMember(), ((Monomial) rightMember).getCoef(), ((Monomial) rightMember).getOrder() + 1);

        if (leftMember instanceof Monomial && rightMember instanceof Monomial)
            return new MonomialX(((Monomial)leftMember).getMember(), ((Monomial) leftMember).getCoef() * ((Monomial) rightMember).getCoef(),
                    ((Monomial) leftMember).getOrder() + ((Monomial) rightMember).getOrder());

        if (leftMember instanceof PolynomialX) {
            MonomialX[] monomials = ((Polynomial) leftMember).getMonomialsX();
            Formula formula = new Constant(0);
            for (MonomialX monomial: monomials)
                formula = new Addition(formula, new Multiplication(monomial, rightMember).simplifiedFormula());
            return formula;
        }

        if (rightMember instanceof PolynomialX) {
            MonomialX[] monomials = ((Polynomial) rightMember).getMonomialsX();
            Formula formula = new Constant(0);
            for (MonomialX monomial: monomials)
                formula = new Addition(formula, new Multiplication(monomial, leftMember).simplifiedFormula());
            return formula;
        }

        return this;
    }
}
