import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileHandler {
    public static void main(String[] args) throws FileNotFoundException {

        //grab the command line argument (input file)
        String commandLine = args[0];
        String outputFile = "drawMe.txt";

        List<Point> myList = FileHandler.readFile(commandLine); //read the file and store the points in the list
        FileHandler.writeFile(outputFile, FileHandler.processData(myList)); //write the filtered list to the output file
    }

    public static List<Point> processData(List<Point> list) {

        return list.stream()
                .filter(z -> z.getZ() <= 2) // filter points with z cord <= 2
                .map(p -> new Point(p.getX() * 0.5, p.getY() * 0.5, p.getZ())) //scale points x & y by 0.5
                .map(p -> new Point(p.getX() - 150, p.getY() - 37, p.getZ())) //translate points x & y (-150, -37)
                .collect(Collectors.toList());
    }

    public static void writeFile(String fileName, List<Point> list) throws FileNotFoundException {

        //write out the filtered list to output list
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
            String[] lineSplit = line.split(","); //split line into array of components

            //assign each value from array based on index
            double x = Double.parseDouble(lineSplit[0].trim());
            double y = Double.parseDouble(lineSplit[1].trim());
            int z = Integer.parseInt(lineSplit[2].trim());

            //add this line (point) to the list
            list.add(new Point(x, y, z));
        }
        in.close();
        return list;
    }
}


