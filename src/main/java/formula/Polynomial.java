package formula;

import java.util.Map;

public abstract class Polynomial extends Function {
    Map<Integer, Double> map;
    Integer order;

    Polynomial(Formula member) {
        super(member);
        order = 0;
    }

    public Integer getOrder() {
        return order;
    }

    public Map<Integer, Double> getMap() {
        return map;
    }

    @Override
    public boolean isConstant() {
        return order == 0 || member.isConstant();
    }

    public MonomialX getMonomialX(Integer degree) {
        if (map.containsKey(degree))
            return new MonomialX(member, map.get(degree), degree);
        else
            return new MonomialX(member,0.0, 0);
    }

    public MonomialX[] getMonomialsX() {
        MonomialX[] monomialsX = new MonomialX[map.size()];
        int i = 0;
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            monomialsX[i] = getMonomialX(entry.getKey());
            i++;
        }
        return monomialsX;
    }
}
