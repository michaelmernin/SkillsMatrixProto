package prototype_skills.prototypeskills.Visuals;

import javax.swing.*;
import java.awt.*;



public class GraphViewer {

    JFrame frame;





    public SimpleGraphViewer1(Dimension size, String title) {
        view = createGraph2DView();
        frame = createApplicationFrame(size, title, view);
    }

    private Graphics2D createGraph2DView() {
        Graphics2D view = new Graph
        return view;
    }

    /** Creates a JFrame that will show the demo graph. */
    private JFrame createApplicationFrame(Dimension size, String title,
                                          JComponent view) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(size);
        // Add the given view to the panel.
        panel.add(view, BorderLayout.CENTER);
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        return frame;
    }

    public void show() { frame.setVisible(true); }

    public static void main(String[] args) {
        SimpleGraphViewer1 sgv =
                new SimpleGraphViewer1(new Dimension(400, 200),
                        SimpleGraphViewer1.class.getName());
        sgv.show();
    }


}
