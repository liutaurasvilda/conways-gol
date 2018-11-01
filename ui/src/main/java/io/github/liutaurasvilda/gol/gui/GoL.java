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
        world.setSize(50);
        world.aliveAt(Location.of(4, 5));
        world.aliveAt(Location.of(5, 6));
        world.aliveAt(Location.of(6, 4));
        world.aliveAt(Location.of(6, 5));
        world.aliveAt(Location.of(6, 6));
    }

    private void buildGUI() {
        JFrame frame = new JFrame("Conway's Game of Life 1.0");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int rows = world.getSize();
        int columns = world.getSize();

        JPanel panel = new JPanel(new GridLayout(rows, columns));
        frame.add(panel);

        for (int i = 0; i < rows * columns; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(20, 20));
            button.setBackground(Color.GRAY);
            button.setOpaque(true);
            panel.add(button);
        }

        frame.pack();
        frame.setVisible(true);

        ActionListener worldGenerationListener = e -> {
            if (world.hasPopulation()) {
                String[] w = world.toString().replace("\n", "").split("");
                for (int i = 0; i < rows * columns; i++) {
                    JButton button = (JButton)panel.getComponent(i);
                    if (w[i].equals("0")) {
                        button.setBackground(Color.ORANGE);
                    } else {
                        button.setBackground(Color.GRAY);
                    }
                }
                world = world.nextGeneration();
            }
        };
        Timer t = new Timer(100, worldGenerationListener);
        t.setInitialDelay(0);
        t.start();
    }
}
