package Classes;

import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;

public class AutoBoxComplete<T> {
    private final ComboBox<T> cmb;
    private String filter = "";
    private boolean addSpace = false;
    private final ObservableList<T> originalItems;

    public AutoBoxComplete(ComboBox<T> cmb) {
        this.cmb = cmb;
        originalItems = FXCollections.observableArrayList(cmb.getItems());
        cmb.setTooltip(new Tooltip());
        cmb.setOnKeyPressed(this::handleOnKeyPressed);
        cmb.setOnHidden(this::handleOnHiding);

        setSkinForNoSpaceBarClose();
    }

    public void handleOnKeyPressed(KeyEvent e) {
        ObservableList<T> filteredList = FXCollections.observableArrayList();
        KeyCode code = e.getCode();

        // add Letter
        filter += e.getText();

        // do nothing
        if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.UP
                || e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.LEFT
                || e.isControlDown() || e.getCode() == KeyCode.HOME
                || e.getCode() == KeyCode.END || e.getCode() == KeyCode.TAB) {
            return;
        }

        // remove one Letter
        if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
            filter = filter.substring(0, filter.length() - 1);
            cmb.getItems().setAll(originalItems);
        }

        // empty String when ESC is pressed
        if (code == KeyCode.ESCAPE) {
            filter = "";
        }

        if (filter.length() == 0) {
            filteredList = originalItems;
            cmb.getTooltip().hide();
        } else {
            Stream<T> items = cmb.getItems().stream();
            String txtUsr = filter.toLowerCase();

            // Filtered List
            items.filter(el -> el.toString().toLowerCase().contains(txtUsr)).forEach(filteredList::add);

            // Tooltip
            cmb.getTooltip().setText(txtUsr);
            Window stage = cmb.getScene().getWindow();
            double posX = stage.getX() + cmb.localToScene(cmb.getBoundsInLocal()).getMinX();
            double posY = stage.getY() + cmb.localToScene(cmb.getBoundsInLocal()).getMinY();
            cmb.getTooltip().show(stage, posX, posY);

            cmb.show();
        }

        EventHandler<ActionEvent> action = cmb.getOnAction();
        cmb.setOnAction(null);
        cmb.getItems().setAll(filteredList);
        cmb.setOnAction(action);
    }

    public void handleOnHiding(Event e) {
        EventHandler<ActionEvent> action = cmb.getOnAction();
        cmb.setOnAction(null);

        filter = "";
        cmb.getTooltip().hide();
        T s = cmb.getSelectionModel().getSelectedItem();
        cmb.getItems().setAll(originalItems);
        cmb.getSelectionModel().select(s);

        cmb.setOnAction(action);
    }

    public void setSkinForNoSpaceBarClose() {
        ComboBoxListViewSkin<T> comboBoxListViewSkin = new ComboBoxListViewSkin<T>(cmb);
        comboBoxListViewSkin.getPopupContent().addEventFilter(KeyEvent.ANY, (event) -> {
            // add space
            if (KeyEvent.KEY_PRESSED.getName().equals("KEY_PRESSED") && event.getText().equals(" ")) {
                addSpace = !addSpace;
                if (addSpace) handleOnKeyPressed(event);
            }

            if( event.getCode() == KeyCode.SPACE) {
                event.consume();
            }
        });
        cmb.setSkin(comboBoxListViewSkin);
    }
}