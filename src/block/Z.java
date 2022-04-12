package block;

public class Z extends Tetromino{
    public Z(int x, int y) {
        super(x,y);
        statusNum=2;
        color=7;
    }

    @Override
    public void setCells(int x, int y) {
        cells[0][0].setLocation(x, y+1);        //    * *
        cells[0][1].setLocation(x, y);             //      * *
        cells[0][2].setLocation(x -1, y);
        cells[0][3].setLocation(x-1, y-1);

        cells[1][0].setLocation(x-1, y);
        cells[1][1].setLocation(x, y);              //    *
        cells[1][2].setLocation(x , y-1);       //   * *
        cells[1][3].setLocation(x + 1, y-1);  //  *

    }

}
