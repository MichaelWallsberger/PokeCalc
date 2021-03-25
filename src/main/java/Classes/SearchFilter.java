package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

public class SearchFilter<T> {
    public ObservableList<T> searchFor(KeyEvent e, VBox Container, TextField SearchField, ObservableList<T> list) {
        ObservableList<T> filteredList = FXCollections.observableArrayList();
        KeyCode code = e.getCode();

        // Empty List
        Container.getChildren().clear();

        // search String
        String filter = SearchField.getText();

        // empty String when ESC is pressed
        if (code == KeyCode.ESCAPE) {
            filter = "";
            SearchField.setText("");
        }

        if (filter.length() == 0 || SearchField.getText().length() == 0) {
            return null;
        }
        // create Filtered List
        else {
            String txtUsr = filter.toLowerCase();

            list.stream().filter(el -> el.toString().toLowerCase().contains(txtUsr)).forEach(filteredList::add);
            return filteredList;
        }
    }

}
