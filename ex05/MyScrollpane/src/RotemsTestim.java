import javax.swing.*;
import java.awt.*;
import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class RotemsTestim {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        Calculator c = new Calculator();
        MyScrollPane m = new MyScrollPane(c);
        f.add(m);
        f.setDefaultCloseOperation(3);
        f.setPreferredSize(new Dimension(200,200));
        f.pack();
        c.setVisible(false);
        f.setVisible(true);
    }
}
