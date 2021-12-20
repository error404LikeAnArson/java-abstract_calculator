package formula;

public abstract class Monomial extends Polynomial {
    Double coef;
    Integer degree;

    Monomial(Formula member) {
        super(member);
    }

    public Double getCoef() {
        return coef;
    }

    public Integer getDegree() {
        return degree;
    }

}
