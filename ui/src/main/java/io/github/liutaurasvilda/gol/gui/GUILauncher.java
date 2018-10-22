package io.github.liutaurasvilda.gol.gui;

import javax.swing.*;

final class GUILauncher {

    private GUILauncher() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUILauncher::launch);
    }

    private static void launch() {
        JFrame frame = new JFrame("Conway's Game of Life");
        frame.pack();
        frame.setVisible(true);
    }
}
