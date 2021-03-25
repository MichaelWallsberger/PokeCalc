package Classes.Animation;

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Animate a shake effect on the given node
 *
 * Port of Shake from Animate.css http://daneden.me/animate by Dan Eden
 *
 * {@literal @}keyframes shake {
 * 	0%, 100% {transform: translateX(0);}
 * 	10%, 30%, 50%, 70%, 90% {transform: translateX(-10px);}
 * 	20%, 40%, 60%, 80% {transform: translateX(10px);}
 * }
 *
 * @author Jasper Potts
 */
public class ShakeTransition extends Transition {

    private final Interpolator WEB_EASE = Interpolator.SPLINE(0.25, 0.1, 0.25, 1);
    private final Timeline timeline;
    private final Node node;
    private boolean oldCache = false;
    private CacheHint oldCacheHint = CacheHint.DEFAULT;
    private final boolean useCache = true;
    private final DoubleProperty x = new SimpleDoubleProperty();

    public ShakeTransition(final Node node) {
        this.node=node;

        statusProperty().addListener((ov, t, newStatus) -> {
            if (newStatus == Status.RUNNING) {
                starting();
            } else {
                stopping();
            }
        });

        this.timeline= new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(x, 0, WEB_EASE)),
                new KeyFrame(Duration.millis(100), new KeyValue(x, -10, WEB_EASE)),
                new KeyFrame(Duration.millis(200), new KeyValue(x, 10, WEB_EASE)),
                new KeyFrame(Duration.millis(300), new KeyValue(x, -10, WEB_EASE)),
                new KeyFrame(Duration.millis(400), new KeyValue(x, 10, WEB_EASE)),
                new KeyFrame(Duration.millis(500), new KeyValue(x, -10, WEB_EASE)),
                new KeyFrame(Duration.millis(600), new KeyValue(x, 10, WEB_EASE)),
                new KeyFrame(Duration.millis(700), new KeyValue(x, -10, WEB_EASE)),
                new KeyFrame(Duration.millis(800), new KeyValue(x, 10, WEB_EASE)),
                new KeyFrame(Duration.millis(900), new KeyValue(x, -10, WEB_EASE)),
                new KeyFrame(Duration.millis(1000), new KeyValue(x, 0, WEB_EASE))
        );


        x.addListener((ob,n,n1)->(node).setTranslateX(n1.doubleValue()));

        setCycleDuration(Duration.seconds(1));
        setDelay(Duration.seconds(0.2));
    }

    protected final void starting() {
        if (useCache) {
            oldCache = node.isCache();
            oldCacheHint = node.getCacheHint();
            node.setCache(true);
            node.setCacheHint(CacheHint.SPEED);
        }
    }

    protected final void stopping() {
        if (useCache) {
            node.setCache(oldCache);
            node.setCacheHint(oldCacheHint);
        }
    }

    @Override
    protected void interpolate(double d) {
        timeline.playFrom(Duration.seconds(d));
        timeline.stop();
    }
}
