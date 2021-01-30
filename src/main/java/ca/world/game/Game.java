package ca.world.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 160; // width of the map
    public static final int HEIGHT = WIDTH/12*9; //height = width / resolution because it's a box
    public static final int SCALE = 3; //allow us to scale the game
    public static final String NAME = "WORLD NAVIGATOR";
    private JFrame frame;
    private boolean running = false;
    private int tickCount = 0;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

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
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D/60D;
        int ticks = 0;
        int frames = 0 ;
        long lastTimer = System.currentTimeMillis();
        double delta = 0 ;
        while(running){
            long now = System.nanoTime();
            delta+=(now - lastTime)/nsPerTick;
            lastTime = now;
            boolean shouldRender = false;
            while(delta >= 1){
                ticks++;
                tick();
                delta-=1;
                shouldRender = true;
            }
            if(shouldRender) {
                frames++;
                render();
            }
            if(System.currentTimeMillis() - lastTimer >= 1000){
                lastTimer += 1000;
                System.out.println(frames+" , "+ticks);
                frames = 0 ;
                ticks = 0 ;
            }
        }
    }

    public void tick(){
        tickCount++;
        for(int i = 0 ; i < pixels.length ; i++){
            pixels[i] = i+tickCount;
        }
    }

    public void render(){
        BufferStrategy bufferStrategy = getBufferStrategy();
        if(bufferStrategy == null){
            createBufferStrategy(3);
            return ;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,getWidth(), getHeight());
        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        graphics.dispose();
        bufferStrategy.show();
    }
    public static void main(String[] args) {
        new Game().start();
    }
}

