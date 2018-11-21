package io.github.liutaurasvilda.gol.gui;

import io.github.liutaurasvilda.gol.Location;
import io.github.liutaurasvilda.gol.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

final class GoL {

    private World world;
    private Timer timer;

    GoL() {
        buildWorld();
        buildGUI();
    }

    private void buildWorld() {
        world = World.empty();
        world.setSize(30);
        world.setLivingAt(Location.of(4, 5));
        world.setLivingAt(Location.of(5, 6));
        world.setLivingAt(Location.of(6, 4));
        world.setLivingAt(Location.of(6, 5));
        world.setLivingAt(Location.of(6, 6));
    }

    private void buildGUI() {
        supportMacOSX();
        JFrame frame = new JFrame("Conway's Game of Life 1.0");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(world.getSize(), world.getSize()));
        frame.add(panel);

        addButtonsTo(panel);

        frame.pack();
        frame.setVisible(true);

        ActionListener worldGenerationListener = e -> {
            update(panel);
            if (world.hasPopulation()) {
                world = world.nextGeneration();
            } else {
                timer.stop();
            }
        };
        timer = new Timer(100, worldGenerationListener);
        timer.setInitialDelay(0);
        timer.start();
    }

    private void supportMacOSX() {
        if ("Mac OS X".equals(System.getProperty("os.name"))) {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException |
                    IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        }
    }

    private void addButtonsTo(JPanel panel) {
        for (int i = 0; i < Math.pow(world.getSize(), 2); i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(20, 20));
            button.setBackground(Color.GRAY);
            button.setOpaque(true);
            panel.add(button);
        }
    }

    private void update(JPanel panel) {
        String[] cells = world.toString().replace("\n", "").split("");
        for (int i = 0; i < Math.pow(world.getSize(), 2); i++) {
            JButton button = (JButton)panel.getComponent(i);
            if (cells[i].equals("0")) {
                button.setBackground(Color.ORANGE);
            } else {
                button.setBackground(Color.GRAY);
            }
        }
    }
}
