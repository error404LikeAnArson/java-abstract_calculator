package formula;

public abstract class Function implements Formula {
    Formula member;

    Function(Formula member) {
        this.member = member;
    }

    public Formula getMember() {
        return member;
    }

    @Override
    public boolean isConstant() {
        return member.isConstant();
    }
}
