package block;
import frame.GamePanel;

public class Cell {
    public int x;
    public int y;
    public void setLocation(int x,int y) {
        this.x = x;
        this.y = y;
    }
    public void setColor(int color) {       //给当前小方块所在位置上色
        GamePanel.graph[Math.max(0,x)][Math.max(0,y)]=color;
    }

    public boolean isBorder(int dir)    //参数 0向左移动，1向右移动 2向下 通过该小方块的移动判断整体移动后是否会撞击边界或其他方块 作为碰撞判定的一部分
    {
        if(dir==0) return GamePanel.graph[x][y-1] != 0;
        else if(dir==1) return GamePanel.graph[x][y+1]!=0;
        else if(dir==2) return GamePanel.graph[x+1][y]!=0;
        return true;
    }

    public boolean legal(){     //判断该方块当前位置是否合法。    //合法返回真
        if(x <1 || x > 20 || y <1 || y > 10)return false;
        return GamePanel.graph[x][y]==0;
    }
}
