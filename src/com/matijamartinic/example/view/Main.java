package com.matijamartinic.example.view;

import com.matijamartinic.game2048.Game2048;
import com.matijamartinic.game2048.MoveDirection;
import com.matijamartinic.game2048.calculator.RotateCalculator;
import com.matijamartinic.util.Arrays;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args)
    {
        JFrame jFrame = new JFrame("wtf");

        Game2048 game2048 = new Game2048(new RotateCalculator(new Arrays()));

        jFrame.setSize(400,400);
        GridLayout gridLayout = new GridLayout(4, 4);
        jFrame.setLayout(gridLayout);

        jFrame.setVisible(true);
        draw(jFrame, game2048.getCurrentGameState());

        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        jFrame.setFocusable(true);
        jFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("STISNUO" + e.getKeyCode());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        game2048.move(MoveDirection.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        game2048.move(MoveDirection.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        game2048.move(MoveDirection.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        game2048.move(MoveDirection.RIGHT);
                        break;
                }
                draw(jFrame, game2048.getCurrentGameState());
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }

    public static void draw(JFrame jFrame, int[][] state)
    {
        jFrame.getContentPane().removeAll();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String value = state[i][j] == 0? "": "" + (int) Math.pow(2, state[i][j]);
                Component component = jFrame.add(new TextField(value));
                component.setFocusable(false);
            }
        }
        jFrame.getContentPane().revalidate();
        jFrame.getContentPane().repaint();
    }

}
