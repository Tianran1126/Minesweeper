import java.util.ArrayList;
import java.util.List;

public class BombSquare extends GameSquare {
    private GameBoard board;// Object reference to the GameBoard this square is part of.
    private boolean hasBomb;
    private boolean reveal=false;
    private boolean show=false;
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
        else if(this.GetBombs()==0)  {
            Fill(this);
        }
        else{
            display(GetBombs(),this);
        }
        reveal=true;
    }

    @Override
    public void rightClicked() {
        if (!flag&&!reveal) {
            setImage("images/flag.png");
            flag = true;
        } else if(!reveal){
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
    //Display image Icon
    public void display(int count,BombSquare square) {
        String Count = String.valueOf(count);
        Count = "images/" + Count + ".png";
        square.setImage(Count);
        square.show=true;
    }
  //return surrounding bombs
    public int GetBombs(){
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

    private void Fill(BombSquare square){
        if(square==null) return;
       if(square.GetBombs()==0&&square.show==false) {
           display(square.GetBombs(), square);
           Fill((BombSquare) board.getSquareAt(square.getXLocation() + 1, square.getYLocation()));
           Fill((BombSquare) board.getSquareAt(square.getXLocation() - 1, square.getYLocation()));
           Fill((BombSquare) board.getSquareAt(square.getXLocation() , square.getYLocation()-1));
           Fill((BombSquare) board.getSquareAt(square.getXLocation() , square.getYLocation()+1));
       }
       else{
           return;
       }
    }



}


