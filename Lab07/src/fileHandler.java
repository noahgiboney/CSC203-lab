import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class fileHandler {
    public static void main(String[] args) throws FileNotFoundException {

        String commandLine = args[0];
        String outputFile = "Lab07/drawMe.txt";

        List<Point> myList = fileHandler.readFile(commandLine);
        List<Point> newList = fileHandler.processData(myList);
        fileHandler.writeFile(outputFile, newList);
    }

    public static List<Point> processData(List<Point> list) {
        List<Point> filteredList;

        filteredList = list.stream().filter(z -> z.getZ() <= 2)
                .map(p -> new Point(p.getX() * 0.5, p.getY() * 0.5, p.getZ()))
                .map(p -> new Point(p.getX() - 150, p.getY() - 37, p.getZ())).collect(Collectors.toList());

        return filteredList;
    }

    public static void writeFile(String fileName, List<Point> list) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(fileName);

        for(Point i : list){
            out.println(i.getX() + ", " + i.getY() + ", " + i.getZ());
        }
        out.close();
    }
    public static List<Point> readFile(String fileName) throws FileNotFoundException {

        List<Point> list = new ArrayList<>();

        File inFile = new File(fileName);
        Scanner in  = new Scanner(inFile);

        while(in.hasNextLine()) {
            String line = in.nextLine();
            String[] lineSplit = line.split(",");

            double x = Double.parseDouble(lineSplit[0].trim());
            double y = Double.parseDouble(lineSplit[1].trim());
            int z = Integer.parseInt(lineSplit[2].trim());

            list.add(new Point(x, y, z));
        }
        in.close();
        return list;
    }
}


