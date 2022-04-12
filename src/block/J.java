package block;

public class J extends Tetromino{
    public J(int x, int y) {
        super(x,y);
        statusNum=4;
        color=2;
    }

    @Override
    public void setCells(int x, int y) {
        cells[0][0].setLocation(x-1, y-1);          // *
        cells[0][1].setLocation(x, y);                    // * * *
        cells[0][2].setLocation(x, y -1);
        cells[0][3].setLocation(x, y +1);

        cells[1][0].setLocation(x-1, y);               // * *
        cells[1][1].setLocation(x, y);                    // *
        cells[1][2].setLocation(x + 1, y);             // *
        cells[1][3].setLocation(x -1, y+1);

        cells[2][0].setLocation(x+1, y+1);          // * * *
        cells[2][1].setLocation(x, y);                    //     *
        cells[2][2].setLocation(x , y-1);
        cells[2][3].setLocation(x , y+1);

        cells[3][0].setLocation(x-1, y);                //   *
        cells[3][1].setLocation(x, y);                     //   *
        cells[3][2].setLocation(x +1, y);               // * *
        cells[3][3].setLocation(x +1, y-1);
    }
}
