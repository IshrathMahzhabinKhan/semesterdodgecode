package semesterdodge;

import javax.swing.*;
import java.awt.*;

public class AssetLoader {
    public static Image load(String path) {
        return new ImageIcon(path).getImage();
    }
}
