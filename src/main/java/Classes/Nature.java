package Classes;

import com.sun.glass.ui.Application;

public class Nature {
    public String natureName;
    public String StatUp;
    public String StatDown;

    public Nature(String natureName, String statUp, String statDown) {
        this.natureName = natureName;
        StatUp = statUp;
        StatDown = statDown;
    }

    @Override
    public String toString() {
        return natureName;
    }
}
