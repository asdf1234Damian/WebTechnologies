package JavaApplets;

import java.applet.Applet;
import java.awt.Graphics;

public class AppletPerms extends Applet {
    @Override
    public void paint(Graphics g) {
        g.drawString("Hello world!", 50, 25);
    }
}
