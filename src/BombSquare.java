import java.util.ArrayList;
import java.util.List;

public class BombSquare extends GameSquare {
    private GameBoard board;// Object reference to the GameBoard this square is part of.
    private boolean hasBomb;
    private boolean flag = false;// True if this squre contains a bomb. False otherwise.

    public static final int MINE_PROBABILITY = 10;

    public BombSquare(int x, int y, GameBoard board) {
        super(x, y, "images/blank.png");
        this.board = board;
        this.hasBomb = ((int) (Math.random() * MINE_PROBABILITY)) ==0;
    }

    @Override
    public void leftClicked() {
        if (hasBomb)
            setImage("images/bomb.png");
        else {
            display(Findneighbour());
        }
    }

    @Override
    public void rightClicked() {
        if (!flag) {
            setImage("images/flag.png");
            flag = true;
        } else if (!hasBomb) {
            setImage("images/blank.png");
            flag = false;
        }
    }

    public boolean isHasBomb() {
        return hasBomb;
    }

    public boolean isFlag() {
        return flag;
    }

    public void display(int count) {
        String Count = String.valueOf(count);
        Count = "images/" + Count + ".png";
        setImage(Count);
    }

    public int Findneighbour() {
        int count = 0;
        int i = getXLocation();
        int j = getYLocation();
        for (int a = i - 1; a <= i + 1; a++) {
            for (int b = j - 1; b <= j + 1; b++) {
                if (board.getSquareAt(a, b) == null || board.getSquareAt(a, b) == this) {
                    System.out.println("Ignore");
                } else {
                    BombSquare square = (BombSquare) board.getSquareAt(a, b);
                    if (square.isHasBomb()) count++;
                }
            }
        }
        return count;
    }
}


