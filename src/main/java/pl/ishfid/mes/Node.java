package pl.ishfid.mes;

/**
 * Created by ishfid on 30.10.16.
 */
public class Node {
    private int id;
    private double temperature, x;
    private int boundaryCondition;

    public Node(int id) {
        this.id = id;
        this.boundaryCondition = 0;
        this.temperature = .0;
        this.x = .0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getBoundaryCondition() {
        return boundaryCondition;
    }

    public void setBoundaryCondition(int boundaryCondition) {
        this.boundaryCondition = boundaryCondition;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
