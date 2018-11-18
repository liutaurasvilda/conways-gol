package io.github.liutaurasvilda.gol.guifx;

import io.github.liutaurasvilda.gol.Location;
import io.github.liutaurasvilda.gol.World;
import io.github.liutaurasvilda.gol.Cell;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.function.Supplier;


/**
 * Canvas which draws the worldSupplier and allows cells to be
 * toggled
 */
public class GraphicDisplay extends Canvas {

    private final Supplier<World> worldSupplier;
    private final String startupMessage;
    private final Supplier<CShape> cShapeSupplier;
    private final EventHandler<MouseEvent> mouseHandler;
    private Runnable displayMethod;

    public GraphicDisplay(Supplier<World> worldSupplier, String startupMessage, Supplier<CShape> cShapeSupplier) {
        this.worldSupplier = worldSupplier;
        this.startupMessage = startupMessage;
        this.cShapeSupplier = cShapeSupplier;
        mouseHandler = this::toggleCell;
        displayMethod = this::startupMessage;
        addEventHandler(MouseEvent.MOUSE_CLICKED, mouseHandler);
    }


    /**
     * invoke currently set display method
     */
    public void display() {
        displayMethod.run();
    }

    public void disableClick() {
        removeEventHandler(MouseEvent.MOUSE_CLICKED, mouseHandler);
    }

    /**
     * mouse click handler, toggle state of cell clicked on
     *
     * @param mouseEvent
     */
    public void toggleCell(MouseEvent mouseEvent) {
        displayMethod = this::drawGrid;
        World world = worldSupplier.get();
        double w = world.getSize();
        double h = world.getSize();
        double dx = getWidth() / w;
        double dy = getHeight() / h;
        int x = (int) (mouseEvent.getX() / dx);
        int y = (int) (mouseEvent.getY() / dy);
        Location start = Location.of(x, y);
        for (Location delta : cShapeSupplier.get().getLocations()) {
            Location where = world.add(start,delta);
            Cell cell = world.at(where);
            if (cell == Cell.DEAD) {
                world.aliveAt(where);
            } else {
                assert cell == Cell.ALIVE;
                world.deadAt(where);
            }
        }
        display();
    }

    /**
     * show startup message
     */
    public void startupMessage() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, getWidth(), getHeight());
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(
                startupMessage,
                Math.round(getWidth() / 2),
                Math.round(getHeight() / 2)
        );
    }

    /**
     * display the worldSupplier
     */
    public void drawGrid() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, getWidth(), getHeight());
        gc.setFill(Color.RED);
        World world = worldSupplier.get();
        double w = world.getSize();
        double h = world.getSize();
        double dx = getWidth() / w;
        double dy = getHeight() / h;

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                Cell cell = world.at(Location.of(x, y));
                if (cell == Cell.ALIVE) {
                    double sx = x * dx;
                    double sy = y * dy;
                    gc.fillRoundRect(sx, sy, dx, dy, dx / 2, dy / 2);
                }
            }
        }
    }
}
