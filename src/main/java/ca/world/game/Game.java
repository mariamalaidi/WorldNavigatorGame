package ca.world.game;

import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 160; // width of the map
    public static final int HEIGHT = WIDTH/12*9; //height = width / resolution because it's a box
    public static final int SCALE = 3; //allow us to scale the game
    public static final String NAME = "WORLD NAVIGATOR";
    private JFrame frame;
    private boolean running = false;

    public Game(){
        setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH* SCALE, HEIGHT*SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT*SCALE));
        frame = new JFrame(NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public synchronized void start(){
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop(){
        running = false;
    }
    @Override
    public void run() {
        while(running){
            System.out.println("Hello world");
        }
    }

    public static void main(String[] args) {
        new Game().start();
    }
}
