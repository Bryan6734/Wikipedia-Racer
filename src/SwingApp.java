import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;

public class SwingApp implements ActionListener {

    private JFrame mainFrame;
    private JTextArea startURLTextArea;
    private JTextArea endURLTextArea;
    private JPanel submitPanel;
    private JScrollPane outputScrollPane;
    private JTextArea outputTextArea;
    private int WIDTH=800;
    private int HEIGHT=700;

    public SwingApp(){
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingApp swingParser = new SwingApp();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Bryan Sukidi's Wikipedia Racer");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(4, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        startURLTextArea = new JTextArea("Enter start URL here");
        startURLTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        startURLTextArea.setLineWrap(true);
        startURLTextArea.setWrapStyleWord(true);

        endURLTextArea = new JTextArea("Enter end URL here");
        endURLTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        endURLTextArea.setLineWrap(true);
        endURLTextArea.setWrapStyleWord(true);



        submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());

        outputTextArea = new JTextArea(0, JLabel.CENTER);
        outputTextArea.setEditable(false);
        outputTextArea.setLineWrap(true);

        outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        handleEvents();

        mainFrame.add(startURLTextArea);
        mainFrame.add(endURLTextArea);
        mainFrame.add(submitPanel);
        mainFrame.add(outputScrollPane);
//        mainFrame.add(outputTextArea);

        mainFrame.setVisible(true);

    }

    private void handleEvents(){

        JButton scrapeButton = new JButton("Find path");
        scrapeButton.setActionCommand("path");
        scrapeButton.addActionListener(new SwingApp.ButtonClickListener());
        submitPanel.add(scrapeButton);

    }


    private ArrayList<String> findPath(String startURL, String endURL){
        WikiRacer wikiRacer = new WikiRacer();
        boolean found = wikiRacer.searchDFS(startURL, endURL, 0);

        ArrayList<String> path = new ArrayList<>();

        if (found){
            path = wikiRacer.getPath();
        }

        return path;

    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("path")) {
                String myStartURL = startURLTextArea.getText();
                outputTextArea.setText("");

                String myEndURL = endURLTextArea.getText();

                ArrayList<String> links = findPath(myStartURL, myEndURL);

                for (int i = 0; i < links.size(); i++){
                    outputTextArea.append(i + " | " + links.get(i) + "\n");
                }

            }
        }
    }
}
