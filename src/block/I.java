package block;


public class I extends Tetromino {
    public I(int x,int y) {
        super(x,y);
        statusNum=2;
        color=1;
    }

    @Override
    public void setCells(int x,int y) {    //更新各个旋转状态下 整体方块的形状信息。   //各方块主要不同的地方

        cells[0][0].setLocation(x, y - 1);
        cells[0][1].setLocation(x, y);
        cells[0][2].setLocation(x, y + 1);
        cells[0][3].setLocation(x, y + 2);

        cells[1][0].setLocation(x - 1, y);
        cells[1][1].setLocation(x, y);
        cells[1][2].setLocation(x + 1, y);
        cells[1][3].setLocation(x + 2, y);
    }


}