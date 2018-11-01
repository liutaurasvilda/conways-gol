package io.github.liutaurasvilda.gol.gui;

import javax.swing.*;

final class GoLLauncher {

    private GoLLauncher() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GoL::new);
    }
}
