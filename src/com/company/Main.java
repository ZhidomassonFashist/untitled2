package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main extends JFrame {
        ///asdasd
    static final int w = 1366;
    static final int h = 768;

    public static void draw(Graphics2D g) {
//Создаем буффер в который рисуем кадр.
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//Рисуем кадр.

        for (int i = 0; i < 16; i++) {
            Render.renderLine(img, 400, 400, (int) (115 * Math.cos(Math.PI * i / (16 / 2)) + 400), (int) (-115 * Math.sin(Math.PI * i / (16 / 2)) + 400));
        }

        g.drawImage(img, 0, 0, null);
    }

    //магический код позволяющий всему работать, лучше не трогать
    public static void main(String[] args) throws InterruptedException {
        Main jf = new Main();
        jf.setSize(w, h);//размер экрана
        jf.setUndecorated(false);//показать заголовок окна
        jf.setTitle("ГнегГнег");
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.createBufferStrategy(2);
//в бесконечном цикле рисуем новый кадр
        while (true) {
            long frameLength = 1000 / 60; //пытаемся работать из рассчета 60 кадров в секунду
            long start = System.currentTimeMillis();
            BufferStrategy bs = jf.getBufferStrategy();
            Graphics2D g = (Graphics2D) bs.getDrawGraphics();
            g.clearRect(0, 0, jf.getWidth(), jf.getHeight());
            draw(g);

            bs.show();
            g.dispose();

            long end = System.currentTimeMillis();
            long len = end - start;
            if (len < frameLength) {
                Thread.sleep(frameLength - len);
            }
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    //Вызывается когда клавиша отпущена пользователем, обработка события аналогична keyPressed
    public void keyReleased(KeyEvent e) {

    }
}

class Render {
    public static void renderLine(BufferedImage img, int x1, int y1, int x2, int y2) {
        if (Math.abs(x1 - x2) > Math.abs(y1 - y2)) {
            if (x1 < x2) {
                for (int i = x1; i <= x2; i++) {
                    int y = ((i - x1) * (y2 - y1) / (x2 - x1)) + y1;
                    img.setRGB(i, y, new Color(0, 0, 0).getRGB());
                }
            }
            if (x1 >= x2) {
                for (int i = x2; i <= x1; i++) {
                    int y = ((i - x1) * (y2 - y1) / (x2 - x1)) + y1;
                    img.setRGB(i, y, new Color(0, 0, 0).getRGB());
                }
            }

        }
        if (Math.abs(y1 - y2) >= Math.abs(x1 - x2)) {
            if (y1 < y2) {
                for (int i = y1; i <= y2; i++) {
                    int x = (((i - y1) * (x2 - x1) / (y2 - y1)) + x1);
                    img.setRGB(x, i, new Color(0, 0, 0).getRGB());

                }
            }
            if (y1 >= y2) {
                for (int i = y2; i <= y1; i++) {
                    int x = ((i - y1) * (x2 - x1) / (y2 - y1)) + x1;
                    img.setRGB(x, i, new Color(0, 0, 0).getRGB());
                }
            }

        }
    }
}