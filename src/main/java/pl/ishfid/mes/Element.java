package pl.ishfid.mes;

/**
 * Created by ishfid on 30.10.16.
 */
public class Element {
    private int id;
    // nodes id
    private int id1, id2;
    //characteristics of node
    private double s, k, l;

    private double[][] h, p;

    public Element(int id) {
        this.id = id;
        this.l = .0;
        this.s = .0;
        this.k = .0;
        this.h = new double[2][2];
        this.p = new double[1][2];
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public double[][] getP() {
        return p;
    }

    public double[][] getH() {
        return h;
    }

    public double getS() {
        return s;
    }

    public void setS(double s) {
        this.s = s;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getL() {
        return l;
    }

    public void setL(double l) {
        this.l = l;
    }
}
