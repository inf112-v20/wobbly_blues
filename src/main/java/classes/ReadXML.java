package classes;


import java.io.*;

public class ReadXML {

    private File file;

    public ReadXML(String filename) {
        file = new File(filename);
    }

    public int[][][] readFile(int x, int y, int z) {
        int[][][] result = new int[x][y][z];

        BufferedReader reader = null;


        try {
            reader = new BufferedReader(new FileReader(file));
            String text;

            //i = row index, j = column index, k = cell index
            int i = 0, j = 0, k = 0;
            boolean read = false;
            while ((text = reader.readLine()) != null) {
                if (text.contains("<data")) {
                    read = true;
                    i = 0;
                    j = 0;
                    continue;
                }
                if (text.contains("</data")) {
                    read = false;
                    k++;
                }
                if(read) {
                    String[] line = text.split(",");
                    for (String str : line) {
                        result[i][j][k] = Integer.parseInt(str);
                        j++;
                    }
                    i++;
                    j = 0;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }

        //print out the list
        return result;
    }
}
