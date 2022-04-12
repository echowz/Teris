package frame;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Menu extends JPanel {
    public static int score;
    public static ArrayList<Image> imageList;
    public static int currentPicture;

    Menu()
    {

//        setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE));
        setBackground(Color.YELLOW);
        setBounds(new Rectangle(MainFrame.width*2/3,0,MainFrame.width/3,MainFrame.height));
        setVisible(true);

        score=0;
        currentPicture=0;
        try {
            System.out.println("当前路径"+getClass().getResource(""));
            imageList=new ArrayList<>();
            imageList.add(ImageIO.read(Objects.requireNonNull(getClass().getResource("img/I.png"))));
            imageList.add(ImageIO.read(Objects.requireNonNull(getClass().getResource("img/J.png"))));
            imageList.add(ImageIO.read(Objects.requireNonNull(getClass().getResource("img/L.png"))));
            imageList.add(ImageIO.read(Objects.requireNonNull(getClass().getResource("img/O.png"))));
            imageList.add(ImageIO.read(Objects.requireNonNull(getClass().getResource("img/S.png"))));
            imageList.add(ImageIO.read(Objects.requireNonNull(getClass().getResource("img/T.png"))));
            imageList.add(ImageIO.read(Objects.requireNonNull(getClass().getResource("img/Z.png"))));
            imageList.add(ImageIO.read(Objects.requireNonNull(getClass().getResource("img/X.gif"))));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(new Color(124,252,0));
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.white);
        g.fillRect(10,10,getWidth()-20,getHeight()-20);     //通过填充矩形绘制边框

        Image img = imageList.get(currentPicture);
        g.drawImage(img,(getWidth()-img.getWidth(null))/2,getHeight()/10,null);
        img=imageList.get(7);
        g.drawImage(img,(getWidth()-img.getWidth(null))/2,getHeight()*3/5,null);


        Graphics2D g2=(Graphics2D)g;    //graphics2D拥有更优秀的平面画图能力
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);   //消除文字抗锯齿
        g2.setFont(new Font("微软雅黑",Font.PLAIN|Font.BOLD,getWidth()/10));//设置文字格式
        g2.setColor(Color.RED); //设置绘制颜色

        g2.drawString("当前得分："+score,getWidth()/10,getHeight()/6+img.getHeight(null));   //绘制文字  当前得分

        g2.setColor(Color.BLACK); //设置绘制颜色
        g2.drawString("即将到来的类型为：",getWidth()/10,getHeight()/15);//绘制文字 下一个类型
        g2.drawString("操作介绍：",getWidth()/10,getHeight()/3);
        g2.drawString("a:左移 d:右移",getWidth()/10,getHeight()*2/5);
        g2.drawString("s:加速 ",getWidth()/10,getHeight()*5/11);
        g2.drawString("w:开启/关闭 悬停 ",getWidth()/10,getHeight()/2);
        g2.drawString("空格:旋转方块 ",getWidth()/10,getHeight()*6/11);

        g2.setFont(new Font("宋体", Font.PLAIN,getWidth()/10));
        g2.setColor(new Color(123,123,123)); //设置绘制颜色

        g2.drawString("商务合作：",getWidth()/10,getHeight()*4/5);
        g2.drawString("qq:1007761282",getWidth()/10,getHeight()*4/5+30);
        g2.drawString("© EchoWZ",getWidth()/10,getHeight()*9/10);
        g2.drawString("All Right Reserved.",getWidth()/10,getHeight()*9/10+30);

    }

    public static void setScore(int score) {
        Menu.score += score*10;
    }

    public static void setCurrentPicture(int num) {
        currentPicture=num;
    }
}
