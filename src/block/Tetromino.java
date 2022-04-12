package block;

import frame.Menu;

import java.util.Random;

public abstract class  Tetromino {
    public int color;   //颜色
    public int x;       //当前方块的坐标
    public int y;
    public int status;  //当前方块处于的旋转状态为
    public int statusNum=4; //当前方块一定有几个状态
    public static int currentTetromino=new Random().nextInt(7);//当前选定的方块，通过随机数选定。
    public Cell[][] cells;  //当前方块的i个状态，每个状态4个小方块，所记录的小方块信息。
        //旋转时将在第一维，不同的状态之间切换； 移动时将在第二维，对当前状态下的4个方块进行遍历，查看是否可以移动，和修改其位置信息。

    public void move(int dir) {  //0左 1右 2下
        setColor(0);
        for(int j=0;j<4;j++)
            if(cells[status%statusNum][j].isBorder(dir)) {
                setColor(color);
                return;
            }
        if (dir == 0) y--;
        else if(dir==1) y++;
        else if(dir==2) x++;

        setCells(x,y);
        setColor(color);

    }

    public void spin() {        //旋转
        setColor(0);                    //实现逻辑为先假设旋转，先移除旧的，再切换状态，然后判断旋转后的方块所处位置是否合法（全为0）。如果合法则应用填色，不合法则恢复
        status++;
        for (int j = 0; j < 4; j++) {   //所有小方块合法才改变。
            if (!cells[status%statusNum][j].legal()) {
                status--;

                setColor(color);
                return;
            }
        }
        setColor(color);
    }

    public void setColor(int color) {  //分别设置4个小方块所处位置的颜色
        for (int j = 0; j < 4; j++) {
            cells[status%statusNum][j].setColor(color);
        }
    }

    public boolean isBottom(){      //如果有方块在即将向下运动时触碰底部，则判断为触底/
        setColor(0);
        for(int j=0;j<4;j++)
            if(cells[status%statusNum][j].isBorder(2)) {
                setColor(color);
                return true;
            }
        return false;
    }
    public boolean init()
    {
        cells = new Cell[statusNum][4];
        for (int i = 0; i < statusNum; i++)
            for (int j = 0; j < 4; j++)
                cells[i][j] = new Cell();
        setCells(x,y);
            for(int i=0;i<statusNum;i++)
                if(!cells[status%2][i].legal())
                    return false;

        setColor(color);
        return true;
    }

    public abstract void setCells(int x,int y);


    public Tetromino(int x,int y) {
        this.x=x;
        this.y=y;
    }
    public  static Tetromino nextTetromino(int x, int y){  //创建对象的静态方法，返回一个实现类，如果无法构造则返回null
        Tetromino tetromino=new I(x,y);
        Random random = new Random();
        int temp = random.nextInt(7);
        Menu.setCurrentPicture(temp);
        switch (currentTetromino) {
            case 0:
                tetromino = new I(2, 5);
                break;
            case 1:
                tetromino = new J(2, 5);
                break;
            case 2:
                tetromino = new L(2, 5);
                break;
            case 3:
                tetromino = new O(2, 5);
                break;
            case 4:
                tetromino = new S(2, 5);
                break;
            case 5:
                tetromino = new T(2, 5);
                break;
            case 6:
                tetromino = new Z(2, 5);
                break;
        }
        currentTetromino=temp;
        if(tetromino.init()) return tetromino;
        else return null;
    }
}
