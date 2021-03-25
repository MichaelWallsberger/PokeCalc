package Classes.Animation;

import Intefaces.Animation;
import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BorderShadow implements Animation {
    private final Timeline timeline;

    public BorderShadow(final Node node) {

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.CYAN);
        shadow.setSpread(0.5);

        this.timeline= new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(shadow.radiusProperty(), 0)),
                new KeyFrame(Duration.seconds(0.25), new KeyValue(shadow.radiusProperty(), 20))
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(2);

        node.setEffect(shadow);
        timeline.setOnFinished(evt -> node.setEffect(null));
        timeline.play();
    }

    public void playFromStart() {
        timeline.play();
    }
}
