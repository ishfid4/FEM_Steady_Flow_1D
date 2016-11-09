package pl.ishfid.mes;

import pl.ishfid.mes.models.Element;

import java.util.List;

/**
 * Created by ishfid on 31.10.16.
 */
public class SystemOfEquations {
    private double[][] globalHmatrix;
    private double[] globalPvector;
    private double[] temperatureVector;

    public SystemOfEquations(int nodeCount) {
        this.globalHmatrix = new double[nodeCount][nodeCount];
        for(int i = 0; i < nodeCount; ++i){
            for(int j = 0; j < nodeCount; ++j){
                this.globalHmatrix[i][j] = 0;
            }
        }

        this.globalPvector = new double[nodeCount];
        this.temperatureVector = new double[nodeCount];
        for (int i = 0; i < nodeCount; ++i){
            this.globalPvector[i] = 0;
            this.temperatureVector[i] = 0;
        }
    }

    public void calculateGlobalH(List<Element> elementList){
        for (Element element: elementList) {
            this.globalHmatrix[element.getId1()-1][element.getId1()-1] += element.getH()[0][0];
            this.globalHmatrix[element.getId1()-1][element.getId2()-1] += element.getH()[0][1];
            this.globalHmatrix[element.getId2()-1][element.getId1()-1] += element.getH()[1][0];
            this.globalHmatrix[element.getId2()-1][element.getId2()-1] += element.getH()[1][1];
        }
    }

    public void calculateGlobalP(List<Element> elementList){
        for (Element element: elementList) {
            this.globalPvector[element.getId1()-1] += element.getP()[0];
            this.globalPvector[element.getId2()-1] += element.getP()[1];
        }
    }

    public void showHandP(){
        System.out.println("H matrix");
        for (int i = 0; i < this.globalHmatrix.length; ++i){
            for (int j = 0; j < this.globalHmatrix.length; ++j){
                System.out.print(this.globalHmatrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("P vector"); //odwrotnie wiersz z kolmna??
        for (int j = 0; j < this.globalHmatrix.length; ++j){
            System.out.print(this.globalPvector[j] + " ");
        }
        System.out.println();

    }

    public double[][] getGlobalHmatrix() {
        return globalHmatrix;
    }

    public double[] getGlobalPvector() {
        return globalPvector;
    }

    public void setTemperatureVector(double[] temperatureVector) {
        this.temperatureVector = temperatureVector;
    }

    public double[] getTemperatureVector() {
        return temperatureVector;
    }
}
