package imageviewer;

import imageviewer.architecture.Command;
import imageviewer.architecture.ImageDisplay;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private final Map<String,Command> commands = new HashMap<>();
    private ImagePanel imageDisplay;
    private Component prev;
    private Component next;

    public static MainFrame create() throws IOException {
        return new MainFrame();
    }

    private MainFrame() throws IOException {
        this.setTitle("Image Viewer");
        this.setSize(800,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(imageDisplay = imagePanel());
        JPanel tools=new JPanel();
                tools.setLayout(new FlowLayout());
        this.prev=prevButton();
        this.next=nextButton();
        tools.add("prev", this.prev);
        tools.add("next", this.next);
        prev.setVisible(false);
        next.setVisible(false);
        Dimension minSize = new Dimension(10, 10);
        Dimension prefSize = new Dimension(10, 10);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);
        Box.Filler filler = new Box.Filler(minSize, prefSize, maxSize);
        tools.add("fill",filler);
        this.add(tools,BorderLayout.NORTH);
        tools.addMouseMotionListener(new MouseAdapter() {
    public void mouseMoved(MouseEvent e) {
         prev.setVisible((e.getY() <= 25));
         next.setVisible((e.getY() <= 25));
     }
    });
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                  commands.get("refresh").execute();
            }
        });;
    }
    
   
    void start() {
        this.setVisible(true);
    }
    
    public void add(String name, Command command) {
        commands.put(name, command);
    }

    
    private ImagePanel imagePanel()  {
        return new ImagePanel();
    }

    private Component nextButton() {
        final JButton button = new JButton(">");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                commands.get("next").execute();
            }
        });
        return button;
    }

    private Component prevButton() {
        final JButton button = new JButton("<");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                commands.get("prev").execute();
            }
        });
        return button;
    }

    ImageDisplay imageDisplay() {
        return imageDisplay;
    }
    
    
}
