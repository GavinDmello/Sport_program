import java.io.*;

public class ski{

    /**
     * @param args the command line arguments
     */
    static boolean haspassed = false;
    static String newpath = "";
    static int newcount = 0;
    static int slope = 0;
    static int count = 0;
    static int rows = 1000;
    static int reslen = 0;
    static int resdep = 0;
    static int cols = 1000;
    static int data[][] = new int[rows][cols];
    static int state = 0;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FileReader fr = new FileReader("C:\\Users\\Gavin\\Documents\\NetBeansProjects\\JavaApplication1\\src\\javaapplication1\\data.txt");
        BufferedReader br = new BufferedReader(fr);
        String temp = br.readLine();

        String t[] = temp.split(" ");

        String tdata[] = new String[rows];
        String te[] = new String[rows];

        for (int i = 0; i < rows; i++) {
            temp = br.readLine();
            String in[] = temp.split(" ");
            for (int j = 0; j < in.length; j++) {
                data[i][j] = Integer.parseInt(in[j]);
            }
        }

        int rowpos = 0;
        int colpos = 0;
        int newslope = 0;
        for (int i = 0; i <= rows - 1; i++) {
            for (int j = 0; j <= cols - 1; j++) {
                newpath = "";
                rowpos = i;
                colpos = j;

                calc(i, j, rowpos, colpos, newslope);

            }

        }

    }
//crawling code

    public static void calc(int i, int j, int rowpos, int colpos, int newslope) {

        haspassed = false;
        state++;

        //checking the boundaries
        if ((i <= rows - 1 && j <= cols - 1)) {

            //down exists
            if ((i + 1) <= rows - 1) {
                if (data[i][j] > data[i + 1][j]) {
                    if (haspassed == true) {

                        if (state > newcount) {
                            newcount = state - 1;
                        } else {
                            newcount = 0;
                        }
                    }
                    newcount++;

                    calc(i + 1, j, rowpos, colpos, newslope);
                    haspassed = true;

                }
            }

            //right exists
            if ((j + 1) <= cols - 1) {
                if (data[i][j] > data[i][j + 1]) {
                    if (haspassed == true) {

                        if (state > newcount) {
                            newcount = state - 1;
                        } else {
                            newcount = 0;
                        }
                    }
                    newcount++;

                    calc(i, j + 1, rowpos, colpos, newslope);
                    haspassed = true;

                }
            }

            //left exists
            if ((j - 1) >= 0) {
                if (data[i][j] > data[i][j - 1]) {
                    if (haspassed == true) {

                        if (state > newcount) {
                            newcount = state - 1;
                        } else {
                            newcount = 0;
                        }
                    }
                    newcount++;

                    calc(i, j - 1, rowpos, colpos, newslope);
                    haspassed = true;

                }

            }

            //up exists
            if ((i - 1) >= 0) {
                if (data[i][j] > data[i - 1][j]) {
                    if (haspassed == true) {

                        if (state > newcount) {
                            newcount = state - 1;
                        } else {
                            newcount = 0;
                        }
                    }
                    newcount++;

                    calc(i - 1, j, rowpos, colpos, newslope);
                    haspassed = true;

                }

            }
            //nothing exists
            newslope = data[rowpos][colpos] - data[i][j];
            if (newcount > count) {
                count = newcount;
                slope = newslope;
                resdep = slope;
                reslen = count + 1;

                System.out.println("depth:" + resdep);
                System.out.println("length:" + reslen);
            }

            if (newcount == count) {
                if (newslope > slope) {
                    slope = newslope;
                    count = newcount;
                    resdep = slope;
                    reslen = count + 1;

                    System.out.println("depth:" + resdep);
                    System.out.println("length:" + reslen);
                }
            }

            newcount = 0;

            //newpath = "";
            state--;

        }
    }

}
