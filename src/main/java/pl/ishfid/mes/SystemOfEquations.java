package pl.ishfid.mes;

import java.util.List;

/**
 * Created by ishfid on 31.10.16.
 */
public class SystemOfEquations {
    private double[][] globalHmatrix;
    private double[][] globalPvector;
    private double[][] temperatureVector;

    public SystemOfEquations(int nodeCount) {
        this.globalHmatrix = new double[nodeCount][nodeCount];
        for(int i = 0; i < nodeCount; ++i){
            for(int j = 0; j < nodeCount; ++j){
                this.globalHmatrix[i][j] = 0;
            }
        }

        this.globalPvector = new double[1][nodeCount];
        this.temperatureVector = new double[1][nodeCount];
        for (int i = 0; i < nodeCount; ++i){
            this.globalPvector[0][i] = 0;
            this.temperatureVector[0][i] = 0;
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
            this.globalPvector[0][element.getId1()-1] += element.getP()[0][0];
            this.globalPvector[0][element.getId2()-1] += element.getP()[0][1];
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

        System.out.println("P vector");
        for (int j = 0; j < this.globalHmatrix.length; ++j){
            System.out.print(this.globalPvector[0][j] + " ");
        }
        System.out.println();

    }
}
