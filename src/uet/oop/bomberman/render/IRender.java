package uet.oop.bomberman.render;

import javafx.scene.canvas.GraphicsContext;

public interface IRender {
    void render(GraphicsContext gc);

    void update();
}
