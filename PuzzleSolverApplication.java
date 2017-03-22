
public class PuzzleSolverApplication {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Puzzle pz = new Puzzle();
        int i;
        for (i = 0; i <= 14; i++) {
            try {
                pz.solve(i);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("not found");
            }
        }

    }
}
