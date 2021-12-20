package formula;

public abstract class BinaryOperation implements Formula {
    Formula leftMember, rightMember;

    @Override
    public boolean isConstant() {
        return leftMember.isConstant() && rightMember.isConstant();
    }

    public Formula getLeftMember() {
        return leftMember;
    }

    public Formula getRightMember() {
        return rightMember;
    }
}