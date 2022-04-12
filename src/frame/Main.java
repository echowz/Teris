package frame;
import block.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    public static Tetromino select; //当前操控的方块设置为select
    public static Menu menu;
    public static GamePanel gamePanel;
    public static MainFrame mainFrame;
    public static final Object object = new Object();   //用作synchronized 参数，用于线程阻塞，循环下降操作的等待过程。
    public static int pauseTime=500;        //自动下落的间隔时间，用于控制速度，间隔越短下落速度越快。

    public static void main(String[] args) {
        System.out.println("运行开始");
        gamePanel = new GamePanel();
        menu = new Menu();
        mainFrame = new MainFrame();//Mainframe需要在后，因为其中有需要调用到panel的成员的语句

        mainFrame.add(gamePanel);
        mainFrame.add(menu);

        new Thread(()-> {
            while (true) {
                gamePanel.repaint();
                menu.repaint();
            }
        }).start(); //持续刷新画布。
        updateTet();

        Thread downs = new Thread(()->{
            try {
                while (true) {
                    synchronized (object) {
                        down();
                        object.wait(pauseTime/2);       //下落速度
                        object.wait(pauseTime/2);

                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        downs.start();


        mainFrame.addKeyListener(new KeyAdapter() {       //监听玩家操作
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'a':
                    case 'A':
                        select.move(0);
                        break;
                    case 'd':
                    case 'D':
                        select.move(1);
                        break;
                    case 's':
                    case 'S':
                        down();
                        break;
                    case ' ':
                        select.spin();
                        break;
                    case 'w':
                    case 'W':
                        if (downs.isAlive())
                            synchronized (object) {
                                object.notify();       //下落速度
                            }
                        if (pauseTime == 500) pauseTime = 2000000;
                        else pauseTime = 500;
                        break;
                }
                }
            }
        );
    }



    public static synchronized  void down()
    {
        if (!select.isBottom())  //不是底部
            select.move(2);
        else {                  //已经位于底部
            Menu.setScore(gamePanel.coveringListener());   //检测是否覆盖,返回覆盖成功的层数。
            updateTet();    //方块落底则重新创建一个方块
        }
    }

   public static void updateTet() {
       select=Tetromino.nextTetromino(2,5);
       if(select==null) {
           JOptionPane.showMessageDialog(null, "游戏结束 得分为："+Menu.score);
           gamePanel.init();
           Menu.score =0;
           select=Tetromino.nextTetromino(2,5);
           //mainFrame = new MainFrame();//Mainframe需要在后，因为其中有需要调用到panel的成员的语句
       }
   }

}
