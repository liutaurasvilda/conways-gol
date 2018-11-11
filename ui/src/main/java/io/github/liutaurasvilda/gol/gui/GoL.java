package io.github.liutaurasvilda.gol.gui;

import io.github.liutaurasvilda.gol.Location;
import io.github.liutaurasvilda.gol.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

final class GoL {

    private World world;

    GoL() {
        buildWorld();
        buildGUI();
    }

    private void buildWorld() {
        world = World.empty();
        world.setSize(30);
        world.aliveAt(Location.of(4, 5));
        world.aliveAt(Location.of(5, 6));
        world.aliveAt(Location.of(6, 4));
        world.aliveAt(Location.of(6, 5));
        world.aliveAt(Location.of(6, 6));
    }

    private void buildGUI() {
        supportMacOSX();
        JFrame frame = new JFrame("Conway's Game of Life 1.0");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(world.getSize(), world.getSize()));
        frame.add(panel);

        fillWithButtons(panel);

        frame.pack();
        frame.setVisible(true);

        ActionListener worldGenerationListener = e -> {
            if (world.hasPopulation()) {
                populateWithAliveCells(panel);
                world = world.nextGeneration();
            } else {
                populateWithEmptyCells(panel);
            }
        };
        Timer t = new Timer(100, worldGenerationListener);
        t.setInitialDelay(0);
        t.start();
    }

    private void supportMacOSX() {
        if ("Mac OS X".equals(System.getProperty("os.name"))) {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillWithButtons(JPanel panel) {
        for (int i = 0; i < Math.pow(world.getSize(), 2); i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(20, 20));
            button.setBackground(Color.GRAY);
            button.setOpaque(true);
            panel.add(button);
        }
    }

    private void populateWithAliveCells(JPanel panel) {
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

    private void populateWithEmptyCells(JPanel panel) {
        for (int i = 0; i < Math.pow(world.getSize(), 2); i++) {
            JButton button = (JButton)panel.getComponent(i);
            button.setBackground(Color.GRAY);
        }
    }
}
