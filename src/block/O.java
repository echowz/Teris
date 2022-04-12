package block;

public class O extends Tetromino {


    public O(int x, int y) {
        super(x, y);
        statusNum = 1;
        color = 4;
    }

    @Override
    public void setCells(int x, int y) {
        cells[0][0].setLocation(x-1, y);
        cells[0][1].setLocation(x-1, y+1);
        cells[0][2].setLocation(x, y );
        cells[0][3].setLocation(x, y + 1);
    }


}
