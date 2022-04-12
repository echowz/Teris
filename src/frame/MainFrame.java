package frame;
import javax.swing.*;
import java.util.Objects;

public class MainFrame extends JFrame {     //主框体程序
    public static final int height=800;
    public static final int width=height*3/4;


    MainFrame()
    {
        setSize(width+20,height+40);
        setTitle("俄罗斯方块");
        setLayout(null);
        setLocationRelativeTo(null);    //程序设置为显示再屏幕中心（默认会显示在左上角）
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭窗体则结束程序
        setVisible(true);   //设置为可视
        setResizable(false);

        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("img/T.png"))).getImage());
    }
}
