package pl.ishfid.mes;

import java.util.List;

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

    public void calculateH(List<Node> nodeList, Wireframe wireframe){
        double c;
        c = (this.s*this.k)/this.l;

        for(int i = 0; i < 2; ++i){
            for (int j = 0; j < 2; ++j){
                if(i != j) {
                    this.h[i][j] = -c;
                } else {
                    this.h[i][j] = c;
                }
            }
        }

        // boundary condition 2 -> convection
        if (nodeList.get(id1-1).getBoundaryCondition() == 2){
            this.h[0][0] += wireframe.getAlpha() * this.s;
        }
        if (nodeList.get(id2-1).getBoundaryCondition() == 2){
            this.h[1][1] += wireframe.getAlpha() * this.s;
        }
    }

    public void calculateP(List<Node> nodeList, Wireframe wireframe){
        // boundary condition 2 -> stream
        if (nodeList.get(id1-1).getBoundaryCondition() == 1){
            this.p[0][0] = this.s * wireframe.getQ();
        } else if (nodeList.get(id1-1).getBoundaryCondition() == 2) {
            this.p[0][0] = -(this.s * wireframe.getAlpha()*wireframe.getAmbientTemp());
        } else {
            this.p[0][0] = 0;
        }

        if (nodeList.get(id2-1).getBoundaryCondition() == 1){
            this.p[0][1] = this.s * wireframe.getQ();
        } else if (nodeList.get(id2-1).getBoundaryCondition() == 2) {
            this.p[0][1] = -(this.s * wireframe.getAlpha()*wireframe.getAmbientTemp());
        } else {
            this.p[0][1] = 0;
        }
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
