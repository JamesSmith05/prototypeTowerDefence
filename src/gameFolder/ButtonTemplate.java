package gameFolder;

import javax.crypto.spec.PSource;
import javax.swing.*;
import java.awt.*;

public class ButtonTemplate extends JButton {

    public ButtonTemplate(int x, int  y, int width, int height, String text) {
        this.setBounds(x, y, width,  height);
        this.setFont(new Font("Comic Sans", Font.BOLD, 20));
        this.setText(text);
        this.setFocusable(false); // removes focus box around text
        this.setBorder(BorderFactory.createEtchedBorder()); // adds border around box

    }

    public void updateLocation(int x, int  y){
        this.setBounds(x,y,this.getWidth(),this.getHeight());
    }

}
