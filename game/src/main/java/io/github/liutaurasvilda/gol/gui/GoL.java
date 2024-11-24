package io.github.liutaurasvilda.gol.gui;

import io.github.liutaurasvilda.gol.Location;
import io.github.liutaurasvilda.gol.World;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

final class GoL {

    private static final int WORLD_SIZE = 30;

    private World world;
    private Timer timer;

    GoL() {
        buildWorld();
        buildGUI();
    }

    private void buildWorld() {
        List<Location> seed = List.of(
                Location.of(4, 5),
                Location.of(5, 6),
                Location.of(6, 4),
                Location.of(6, 5),
                Location.of(6, 6)
        );
        world = World.generation(seed, WORLD_SIZE);
    }

    private void buildGUI() {
        supportMacOSX();
        JFrame frame = new JFrame("Conway's Game of Life 1.0");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(WORLD_SIZE, WORLD_SIZE));
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
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addButtonsTo(JPanel panel) {
        for (int i = 0; i < Math.pow(WORLD_SIZE, 2); i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(20, 20));
            button.setBackground(Color.GRAY);
            button.setOpaque(true);
            panel.add(button);
        }
    }

    private void update(JPanel panel) {
        String[] cells = world.toString().replace("\n", "").split("");
        for (int i = 0; i < Math.pow(WORLD_SIZE, 2); i++) {
            JButton button = (JButton) panel.getComponent(i);
            if (cells[i].equals("0")) {
                button.setBackground(Color.ORANGE);
            } else {
                button.setBackground(Color.GRAY);
            }
        }
    }
}
