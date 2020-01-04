package tech.mattharris.java;

import org.jetbrains.annotations.NotNull;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.IOException;

public final class App extends JFrame {

    /**
     * This is the main application
     */
    private static final long serialVersionUID = 1L;

    private App() throws IOException {
        PropReader properties = new PropReader();
        int gridSize = Integer.parseInt(properties.grid);
        int numOfItems = gridSize * gridSize;
        JLabel[] imageLabelAr = new JLabel[numOfItems];
        List<String> files = getFiles(properties.location);
        Container p = this.getContentPane();

        for (int i = 0; i < numOfItems; i++) {
            imageLabelAr[i] = new JLabel(new StretchIcon(files.get(i)));
            p.add(imageLabelAr[i]);
        }

        this.setLayout(new GridLayout(gridSize, gridSize));
        this.addKeyListener(new MKeyListener());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setTitle("Sexy");
        this.setResizable(true);

        p.setBackground(Color.BLACK);

        Timer t = new Timer(Integer.parseInt(properties.speed), new ActionListener() {
            int x = numOfItems;

            public void actionPerformed(ActionEvent e) {
                x++;
                imageLabelAr[x % numOfItems].setIcon(new StretchIcon(files.get(x % files.size())));
            }
        });
        t.start();

        this.setVisible(true);
    }

    static class MKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            System.exit(0);
        }
    }

    @NotNull
    public static List<String> getFiles(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        List<String> files = new ArrayList<>();

        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.getName().toLowerCase().endsWith("gif") || file.getName().toLowerCase().endsWith("png")
                    || file.getName().toLowerCase().endsWith("jpg") || file.getName().toLowerCase().endsWith("jpeg")) {
                files.add(file.getAbsolutePath());
            } else {
                System.out.println(file.getName().toLowerCase());
            }
        }

        Collections.shuffle(files);

        return files;
    }

    public static void main(String[] args) throws IOException {
        new App();
    }
}
