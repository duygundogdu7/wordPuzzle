
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle {

    private static final String fileName = "puzzle.txt";
    private char[][] charMatrix;
    private int row;
    private int col;
    private int ret;
    private ArrayList<String> wordList = new ArrayList<String>();

    public Puzzle() {
        Scanner in = null;
        File inputFile = new File(fileName);
        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        row = in.nextInt();
        col = in.nextInt();
        charMatrix = new char[row][col];
        readMatrix(in);
        readWordList(in);
        ret = 0;
    }

    private void readWordList(Scanner in) {
        // TODO Auto-generated method stub
        String line;
        while (in.hasNext()) {
            line = in.next();
            wordList.add(line);
        }
    }

    private void readMatrix(Scanner in) {
        // TODO Auto-generated method stub
        int i = 0, j = 0;
        String line;
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                line = in.next();
                charMatrix[i][j] = line.charAt(0);
            }
        }
    }

    public void printMatrix() {
        int i = 0, j = 0;
        String line;
        System.out.println("Char Matirx");
        System.out.println("Row:" + row + " Col:" + col + "\n");
        System.out.print(" ");
        for (i = 0; i < row; i++) {
            System.out.print(i % 10);
        }
        System.out.println();
        for (i = 0; i < row; i++) {
            System.out.print(i % 10);
            for (j = 0; j < col; j++) {
                System.out.print(charMatrix[i][j]);
            }
            System.out.println();
        }
    }

    public void printWords() {
        // TODO Auto-generated method stub
        for (String a : wordList) {
            System.out.println(a);
        }
    }

    public void solve(int num) {
        int i = 0, j = 0, k = 0, prow = 0, pcol = 0, c = 0, d = 0, g, flag = -1, flag1 = 1;
        String myWord;
        myWord = wordList.get(num);
        int size = myWord.length();
        Point[] fpoint = new Point[size];
        Point startPoint = new Point();
        for (g = 0; g < size; g++) {
            fpoint[g] = new Point();

        }
        fpoint = findTwo(myWord, startPoint, fpoint);
        while (flag == -1) {
            if (fpoint[0].x == fpoint[1].x && fpoint[0].y == fpoint[1].y - 1) {
                for (c = 2; c < size; c++) {
                    if (fpoint[c - 1].y != col - 1 && charMatrix[fpoint[c - 1].x][fpoint[c - 1].y + 1] == myWord.charAt(c)) {//sağ
                        fpoint[c].x = fpoint[c - 1].x;
                        fpoint[c].y = fpoint[c - 1].y + 1;
                        if (c == size - 1) {
                            flag = 0;
                        }
                    } else {
                        fpoint = findTwo(myWord, startPoint, fpoint);
                        fpoint[0] = fpoint[0];
                        fpoint[1] = fpoint[1];
                        break;
                    }
                }
                //    System.out.println("SAĞ");
            } else if (fpoint[0].x == fpoint[1].x && fpoint[0].y == fpoint[1].y + 1) {
                for (c = 2; c < size; c++) {
                    if (fpoint[c - 1].y != 0 && charMatrix[fpoint[c - 1].x][fpoint[c - 1].y - 1] == myWord.charAt(c)) {
                        fpoint[c].x = fpoint[c - 1].x;
                        fpoint[c].y = fpoint[c - 1].y - 1;
                        if (c == size - 1) {
                            flag = 0;
                        }
                    } else {
                        fpoint = findTwo(myWord, startPoint, fpoint);

                        fpoint[0] = fpoint[0];
                        fpoint[1] = fpoint[1];
                        break;
                    }
                }
                //    System.out.println("SOL");
            } else if (fpoint[0].x == fpoint[1].x + 1 && fpoint[0].y == fpoint[1].y) {
                for (c = 2; c < size; c++) {
                    if (fpoint[c - 1].x != 0 && charMatrix[fpoint[c - 1].x - 1][fpoint[c - 1].y] == myWord.charAt(c)) {
                        fpoint[c].x = fpoint[c - 1].x - 1;
                        fpoint[c].y = fpoint[c - 1].y;
                        if (c == size - 1) {
                            flag = 0;
                        }
                    } else {
                        fpoint = findTwo(myWord, startPoint, fpoint);

                        fpoint[0] = fpoint[0];
                        fpoint[1] = fpoint[1];
                        break;
                    }
                }
                //   System.out.println("ÜST");
            } else if (fpoint[0].x == fpoint[1].x + 1 && fpoint[0].y == fpoint[1].y + 1) {
                for (c = 2; c < size; c++) {
                    if (fpoint[c - 1].x != 0 && fpoint[c - 1].y != 0 && charMatrix[fpoint[c - 1].x - 1][fpoint[c - 1].y - 1] == myWord.charAt(c)) {
                        fpoint[c].x = fpoint[c - 1].x - 1;
                        fpoint[c].y = fpoint[c - 1].y - 1;
                        if (c == size - 1) {
                            flag = 0;
                        }
                    } else {
                        fpoint = findTwo(myWord, startPoint, fpoint);

                        fpoint[0] = fpoint[0];
                        fpoint[1] = fpoint[1];
                        break;
                    }
                }
                //   System.out.println("SOL ÜST");
            } else if (fpoint[0].x == fpoint[1].x + 1 && fpoint[0].y == fpoint[1].y - 1) {
                for (c = 2; c < size; c++) {
                    if (fpoint[c - 1].x != 0 && fpoint[c - 1].y != col - 1 && charMatrix[fpoint[c - 1].x - 1][fpoint[c - 1].y + 1] == myWord.charAt(c)) {
                        fpoint[c].x = fpoint[c - 1].x - 1;
                        fpoint[c].y = fpoint[c - 1].y + 1;
                        if (c == size - 1) {
                            flag = 0;
                        }
                    } else {
                        fpoint = findTwo(myWord, startPoint, fpoint);

                        fpoint[0] = fpoint[0];
                        fpoint[1] = fpoint[1];
                        break;
                    }
                }
                //   System.out.println("SAĞ ÜST");
            } else if (fpoint[0].x == fpoint[1].x - 1 && fpoint[0].y == fpoint[1].y) {
                for (c = 2; c < size; c++) {
                    if (fpoint[c - 1].x != row - 1 && charMatrix[fpoint[c - 1].x + 1][fpoint[c - 1].y] == myWord.charAt(c)) {
                        fpoint[c].x = fpoint[c - 1].x + 1;
                        fpoint[c].y = fpoint[c - 1].y;
                        if (c == size - 1) {
                            flag = 0;
                        }
                    } else {
                        fpoint = findTwo(myWord, startPoint, fpoint);

                        fpoint[0] = fpoint[0];
                        fpoint[1] = fpoint[1];
                        break;
                    }
                }
                //  System.out.println("ALT");
            } else if (fpoint[0].x == fpoint[1].x - 1 && fpoint[0].y == fpoint[1].y + 1) {
                for (c = 2; c < size; c++) {
                    if (fpoint[c - 1].x != row - 1 && fpoint[c - 1].y != 0 && charMatrix[fpoint[c - 1].x + 1][fpoint[c - 1].y - 1] == myWord.charAt(c)) {
                        fpoint[c].x = fpoint[c - 1].x + 1;
                        fpoint[c].y = fpoint[c - 1].y - 1;
                        if (c == size - 1) {
                            flag = 0;
                        }
                    } else {
                        fpoint = findTwo(myWord, startPoint, fpoint);
                        fpoint[0] = fpoint[0];
                        fpoint[1] = fpoint[1];
                        break;
                    }

                }
                //   System.out.println("SOL ALT");
            } else if (fpoint[0].x == fpoint[1].x - 1 && fpoint[0].y == fpoint[1].y - 1) {
                for (c = 2; c < size; c++) {
                    if (fpoint[c - 1].x != row - 1 && fpoint[c - 1].y != col - 1 && charMatrix[fpoint[c - 1].x + 1][fpoint[c - 1].y + 1] == myWord.charAt(c)) {
                        fpoint[c].x = fpoint[c - 1].x + 1;
                        fpoint[c].y = fpoint[c - 1].y + 1;
                        if (c == size - 1) {
                            flag = 0;
                        }
                    } else {
                        fpoint = findTwo(myWord, startPoint, fpoint);

                        fpoint[0] = fpoint[0];
                        fpoint[1] = fpoint[1];
                        break;
                    }
                }
                //   System.out.println("SAĞ ALT");
            }
            flag1 = 1;
            for (d = 0; d < size; d++) {
                if (fpoint[d].x == 0 && fpoint[d].y == 0) {
                    flag1 = 0;
                }
            }
            if (flag1 == 1) {
                System.out.print(myWord + ": ");
                for (d = 0; d < size; d++) {
                    fpoint[d].print();
                }
                System.out.println();
            }
        }

    }

    private Point findFirst(String myWord, Point startPoint) {
        ret = 0;
        int j, k, prow, pcol;
        Point point = new Point();
        point.x = -1;
        point.y = -1;
        if (startPoint.y < 14) {
            prow = startPoint.x;
            pcol = startPoint.y + 1;
        } else {
            prow = startPoint.x + 1;
            pcol = 0;
        }
        for (j = prow; j < row; j++, pcol = 0) {
            for (k = pcol; k < col; k++) {
                if (charMatrix[j][k] == myWord.charAt(0)) {
                    point.x = j;
                    point.y = k;
                    return point;
                }
            }
        }
        return point;
    }

    private Point[] findSecondArray(String myWord, Point fpoint) {
        int i;
        Point[] point = new Point[8];
        for (i = 0; i < 8; i++) {
            point[i] = new Point();
            point[i].x = -1;
            point[i].y = -1;
        }
        int flag = 0, k = 0;
        if (fpoint.x != row - 1) {
            if (charMatrix[fpoint.x + 1][fpoint.y] == myWord.charAt(1)) {//alt
                point[k].x = fpoint.x + 1;
                point[k].y = fpoint.y;
                k++;
            }
        }
        if (fpoint.y != col - 1) {
            if (charMatrix[fpoint.x][fpoint.y + 1] == myWord.charAt(1)) {//sağ
                point[k].x = fpoint.x;
                point[k].y = fpoint.y + 1;
                k++;
            }
        }
        if (fpoint.x != row - 1 && fpoint.y != col - 1) {
            if (charMatrix[fpoint.x + 1][fpoint.y + 1] == myWord.charAt(1)) {//sağ alt
                point[k].x = fpoint.x + 1;
                point[k].y = fpoint.y + 1;
                k++;
            }
        }
        if (fpoint.x != row - 1 && fpoint.y != 0) {
            if (charMatrix[fpoint.x + 1][fpoint.y - 1] == myWord.charAt(1)) {//sol alt
                point[k].x = fpoint.x + 1;
                point[k].y = fpoint.y - 1;
                k++;
            }
        }
        if (fpoint.y != 0) {
            if (charMatrix[fpoint.x][fpoint.y - 1] == myWord.charAt(1)) {//sol
                point[k].x = fpoint.x;
                point[k].y = fpoint.y - 1;
                k++;
            }
        }
        if (fpoint.x != 0 && fpoint.y != col - 1) {
            if (charMatrix[fpoint.x - 1][fpoint.y + 1] == myWord.charAt(1)) {//sağ üst
                point[k].x = fpoint.x - 1;
                point[k].y = fpoint.y + 1;
                k++;
            }
        }
        if (fpoint.x != 0 && fpoint.y != 0) {
            if (charMatrix[fpoint.x - 1][fpoint.y - 1] == myWord.charAt(1)) {//sol üst
                point[k].x = fpoint.x - 1;
                point[k].y = fpoint.y - 1;
                k++;
            }
        }
        if (fpoint.x != 0) {
            if (charMatrix[fpoint.x - 1][fpoint.y] == myWord.charAt(1)) {//üst
                point[k].x = fpoint.x - 1;
                point[k].y = fpoint.y;
                k++;
            }
        }
        return point;
    }

    Point[] findTwo(String myWord, Point startPoint, Point[] point) {
        do {
            if (ret < 8) {
                point[1] = findSecond(myWord, point[0]);
                ret++;
            } else {
                startPoint.x = point[0].x;
                startPoint.y = point[0].y;
                ret = 0;
                point[0] = findFirst(myWord, startPoint);
                point[1] = findSecond(myWord, point[0]);
            }

        } while (point[1].x == -1 && point[1].y == -1);
        return point;
    }

    private Point findSecond(String myWord, Point point) {

        int i;
        Point[] pointArr = new Point[8];
        for (i = 0; i < 8; i++) {
            pointArr[i] = new Point();
        }
        pointArr = findSecondArray(myWord, point);
        return pointArr[ret];
    }
}
