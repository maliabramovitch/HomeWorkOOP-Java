import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class MyScrollPane extends JPanel implements AdjustmentListener, ComponentListener{


    private LayoutManager border;
    private JPanel centerPanel;
    private JScrollBar vertical;
    private JScrollBar horizontal;
    private JPanel app;

    private int maxXValue;
    private int maxYValue;
    private boolean containsVertical;
    private boolean containsHorizontal;

    public MyScrollPane(Component p) {
        if (p instanceof JFrame) {
            app = (JPanel) ((JFrame) p).getContentPane();
        }
        else if(p instanceof JPanel) {
            app = (JPanel) p;
        }
        panelInitiator();
    }


    private void panelInitiator() {
        app.setSize(app.getPreferredSize());
        centerPanel = new JPanel();
        centerPanel.add(app);

        centerPanel.setPreferredSize(app.getSize());
        centerPanel.setLayout(null);
        //CREATING THE MAINPANEL WITH ITS LAYOUTMANAGER
        border = new BorderLayout();
        this.setLayout(border);

        //CREATING THE SCROLLERS
        vertical = new JScrollBar(Adjustable.VERTICAL);
        horizontal = new JScrollBar(Adjustable.HORIZONTAL);


        //GIVING THE MAIN PANEL ITS SIZE
        Dimension mainPanelSize = new Dimension(centerPanel.getWidth() + vertical.getWidth(), centerPanel.getHeight() + horizontal.getHeight());
        this.setPreferredSize(mainPanelSize);

        //ADDING THE CENTER PANEL
        this.add(centerPanel, BorderLayout.CENTER);

        //ADDING THE LISTENERS TO THE SCROLLERS AND ADDING THEM
        vertical.addAdjustmentListener(this);
        //vertical.setMinimum(-centerPanel.getHeight());
        this.add(vertical, BorderLayout.WEST);

        horizontal.addAdjustmentListener(this);
        //horizontal.setMinimum(-centerPanel.getWidth());
        this.add(horizontal, BorderLayout.SOUTH);

        this.addComponentListener(this);
    }


    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {



        //we had a weird issue here where the max was 90, even after we tried to change it
        int criticalX = ((horizontal.getValue() * maxXValue) / (horizontal.getMaximum()-10));
        int criticalY = ((vertical.getValue() * maxYValue) / (vertical.getMaximum()-10));

        app.setLocation( criticalX, criticalY);
    }


    @Override
    public void componentResized(ComponentEvent e) {
        vertical.setMaximum(this.getHeight());
        horizontal.setMaximum(this.getWidth());

        if (comparableWidth() >= app.getWidth()) {
            this.remove(horizontal);
            maxYValue = -app.getHeight() + this.getHeight();
            containsHorizontal = false;
        }
        else {
            this.add(horizontal, BorderLayout.SOUTH);
            maxYValue = -app.getHeight() + this.getHeight() - horizontal.getHeight();
            containsHorizontal = true;
        }


        if (comparableHeight() >= app.getHeight()) {
            this.remove(vertical);
            maxXValue = -app.getWidth() + this.getWidth();
            containsVertical = false;
        }
        else {
            this.add(vertical, BorderLayout.WEST);
            maxXValue = -app.getWidth() + this.getWidth() - vertical.getWidth();
            containsVertical = true;
        }


        if (!containsHorizontal && !containsVertical) {
            maxXValue = -app.getWidth() + this.getWidth();
            maxYValue = -app.getHeight() + this.getHeight();

        }
    }

    private int comparableHeight() {
        if(containsHorizontal) {
            return (this.getHeight() - horizontal.getHeight());
        }
        else {
            return this.getHeight();
        }
    }

    private int comparableWidth() {
        if(containsVertical) {
            return (this.getWidth() - vertical.getWidth());
        }
        else {
            return this.getWidth();
        }
    }


    @Override
    public void componentMoved(ComponentEvent e) {}


    @Override
    public void componentShown(ComponentEvent e) {}


    @Override
    public void componentHidden(ComponentEvent e) {}
}

