package pl.ishfid.mes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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
            for (int i = 0; i < tmpInt; ++i){
                nodeList.get(scanner.nextInt()-1).setBoundaryCondition(scanner.nextInt());
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

            // Temperature from nodes to file
            for (int i = 0; i < nodeList.size(); ++i) {
               out.printf("%f",nodeList.get(i).getTemperature());
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
