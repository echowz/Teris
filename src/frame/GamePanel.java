package frame;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GamePanel extends JPanel {
    public static int[][] graph;        //地图的二维数组映射
    public static Color[] colors;       //7种方块7种颜色，便于区分和识别。
    public GamePanel()
    {
        init();
        setBackground(Color.CYAN);          //背景色设为淡蓝 方格填充后作为淡蓝色网格线
        setBounds(new Rectangle(0,0,MainFrame.width*2/3,MainFrame.height));//设置panel区域的长宽和位置，为整个框体的左三分之二处
        setVisible(true);
    }
    public void init() //初始化辅助参数，颜色集，地图等。
    {
        colors=new Color[]{ //初始化颜色数组
                Color.white,    //默认静态final变量，返回的是白色的Color对象
                new Color(0,240,240),
                new Color(0,0,240),
                new Color(240,160,0),
                new Color(240,240,0),
                new Color(0,240,0),
                new Color(160,0,240),
                new Color(240,0,0)};
        graph = new int[25][15];    //初始化地图大小

        for(int i=0;i<=22;i++)
        Arrays.fill(graph[i], -1);  //先全部初始化-1，再将合法区域初始化为0

        for(int i=1;i<=20;i++)      //地图的合法区域为x：1-20  y:1-10;
            for(int j=1;j<=10;j++)
                graph[i][j]=0;          //经初始化，-1为出界。
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        for(int i=1;i<=20;i++)
            for(int j=1;j<=10;j++)
            {
                g.setColor(colors[graph[i][j]]);
                int x=MainFrame.width/40+(j-1)*MainFrame.width/16;
                int y=MainFrame.width/20+(i-1)*MainFrame.width/16;
                int width=MainFrame.width/16-2,height=MainFrame.width/16-2;
                g.fillRect(x,y,width,height);
            }
    }

    public int coveringListener()  //检测当前覆盖满的层，处理其消失，并将消失的数量返回。
    {
        boolean res=false;
        int sumLine=0;
        int startLine=0;
        for(int i=20;i>=1;i--)
        {
            if(isCovering(graph[i]))
            {
                startLine=i;
                res=true;
                sumLine++;
            }
            else if(res) break;
        }
        if(sumLine>0) {
            for(int i=startLine+sumLine-1;i>1;i--){
                System.arraycopy(graph[Math.max(1,i - sumLine)], 1, graph[i], 1, 10);
            }
        }
        return sumLine*sumLine;
    }
    public boolean isCovering(int[] g) {        //当前层是否满
        for(int i=1;i<=10;i++)
         if(g[i]==0) return false;
        return true;
    }
}

