package pl.ishfid.mes;

import org.apache.commons.math3.linear.*;
import pl.ishfid.mes.models.Element;
import pl.ishfid.mes.models.Node;
import pl.ishfid.mes.models.Wireframe;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ishfid on 30.10.16.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        SystemOfEquations systemOfEquations;
        Wireframe wireframe = new Wireframe();
        List<Node> nodeList = new ArrayList<Node>();
        List<Element> elementList = new ArrayList<Element>();
        DecompositionSolver solver;
        RealMatrix coefficients;
        RealVector constans;

        FileInputStream in = null;
        PrintWriter out = null;
        Scanner scanner;

        try {
            int tmpInt;

            in = new FileInputStream("input.txt");
            out = new PrintWriter("output.txt");
            scanner = new Scanner(in);

            // Creating nodes
            tmpInt = scanner.nextInt();
            for (int i = 0; i < tmpInt; ++i){
                Node node = new Node(i);
                node.setX(scanner.nextDouble());
                nodeList.add(node);
            }
            // Setting boundary condition flag for nodes
            tmpInt = scanner.nextInt();
            int tmpNodeId, tmpBC;
            for (int i = 0; i < tmpInt; ++i){
                tmpNodeId = scanner.nextInt();
                tmpBC = scanner.nextInt();
                nodeList.get(tmpNodeId-1).setBoundaryCondition(tmpBC);
//                nodeList.get(scanner.nextInt()-1).setBoundaryCondition(scanner.nextInt());
            }
            // Creating elements
            tmpInt = scanner.nextInt();
            for (int i = 0; i < tmpInt; ++i){
                Element element = new Element(scanner.nextInt());
                element.setId1(scanner.nextInt());
                element.setId2(scanner.nextInt());
                element.setS(scanner.nextDouble());
                element.setK(scanner.nextDouble());
                element.setL(scanner.nextDouble());
                elementList.add(element);
            }
            // Setting wireframe
            wireframe.setElementsCount(tmpInt);
            wireframe.setNodeCount(tmpInt+1);
            wireframe.setAlpha(scanner.nextDouble());
            wireframe.setQ(scanner.nextDouble());
            wireframe.setAmbientTemp(scanner.nextDouble());

            // Calculating H matrix, tension vector P
            for (Element element: elementList) {
                element.calculateH(nodeList,wireframe);
                element.calculateP(nodeList,wireframe);
            }

            // Creating global H matrix, and global tension vector P
            systemOfEquations = new SystemOfEquations(wireframe.getNodeCount());
            systemOfEquations.calculateGlobalH(elementList);
            systemOfEquations.calculateGlobalP(elementList);

            systemOfEquations.showHandP();

            // Solving linear equation system
            coefficients = new Array2DRowRealMatrix(systemOfEquations.getGlobalHmatrix(), false);
            solver = new LUDecomposition(coefficients).getSolver();

            constans = new ArrayRealVector(systemOfEquations.getGlobalPvector(), false);
            constans = constans.mapMultiply(-1);
            systemOfEquations.setTemperatureVector(solver.solve(constans).toArray());
            
            // Setting temperatures to the nodes
            for(int i = 0; i < systemOfEquations.getTemperatureVector().length; ++i){
                nodeList.get(i).setTemperature(systemOfEquations.getTemperatureVector()[i]);
            }

            // Temperature from nodes to file
            System.out.println("\nTemperature values: ");
            for (Node node: nodeList) {
                System.out.println(node.getTemperature());
                out.printf("%f",node.getTemperature());
                out.println();
            }

        }finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
