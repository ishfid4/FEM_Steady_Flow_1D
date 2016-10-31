package pl.ishfid.mes;

/**
 * Created by ishfid on 30.10.16.
 */
public class Wireframe {
    private int nodeCount, elementsCount;
    private double alpha, q, ambientTemp;

    public Wireframe() {
        this.nodeCount = 0;
        this.elementsCount = 0;
        this.alpha = .0;
        this.q = .0;
        this.ambientTemp = .0;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }

    public int getElementsCount() {
        return elementsCount;
    }

    public void setElementsCount(int elementsCount) {
        this.elementsCount = elementsCount;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getQ() {
        return q;
    }

    public void setQ(double q) {
        this.q = q;
    }

    public double getAmbientTemp() {
        return ambientTemp;
    }

    public void setAmbientTemp(double ambientTemp) {
        this.ambientTemp = ambientTemp;
    }
}
