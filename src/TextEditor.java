import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.UndoableEditListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JTextArea textArea;
    JMenuItem newFile, open, save;
    JMenuItem cut, copy, paste, selectAll, close;
    TextEditor(){
        //Initialize frame
        frame = new JFrame();
        //Initialize menu bars and menu
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        //Initialize text area
        textArea = new JTextArea();

        //Initialize menu items for 'File' Menu
        newFile  = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        //Adding Action Listner to menu items
        newFile.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        //Adding menu items to 'File' Menu
        file.add(newFile);
        file.add(open);
        file.add(save);
        //Initialize menu items for 'Edit' Menu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        //Adding Action Listener to menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //Adding menu items to 'Edit' Menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Adding all menus in Menu Bar
        menuBar.add(file);
        menuBar.add(edit);

        //Creating Content Pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        //Adding Text Area to Content Pane
        panel.add(textArea, BorderLayout.CENTER);
        //Adding Scroll feature to text area
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.setJMenuBar(menuBar);
        panel.add(scrollPane);
        frame.add(panel);
        //Setting sizes
        frame.setBounds(100, 100, 800, 500);
        panel.setBounds(0,0, frame.getWidth()-15 , frame.getHeight()-60);
        scrollPane.setBounds(0,0, panel.getWidth()-20 , panel.getHeight()-60);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Text Editor (By Priyansh Bhatnagar)");

    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        System.out.println("Made By Priyansh Bhatnagar");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //For creating new text editor file
        if(e.getSource()==newFile){
            TextEditor newTextEditor = new TextEditor();
        }
        //For opening existing file in editor
        if(e.getSource()==open){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try{
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                    String intermediate = "", output = "";
                    while((intermediate = bufferedReader.readLine())!=null){
                        output += intermediate + "\n";
                    }
                    textArea.setText(output);
                }catch (Exception exception){
                    System.out.println("Cannot open file");
                }
            }
        }
        //For Saving text file
        if(e.getSource()==save){
            JFileChooser fileChooser = new JFileChooser("C:");
            fileChooser.setApproveButtonText("Save");
            int chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    BufferedWriter outfile = null;
                    outfile = new BufferedWriter(new FileWriter(file));
                    textArea.write(outfile);
                    outfile.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        //For cutting to clipboard
        if(e.getSource()==cut){
            textArea.cut();
        }
        //For copying to clipboard
        if(e.getSource()==copy){
            textArea.copy();
        }
        //For pasting from clipboard to text area
        if(e.getSource()==paste){
            textArea.paste();
        }
        //For selecting all text in text area
        if(e.getSource()==selectAll){
            textArea.selectAll();
        }
        //For closing Text Editor
        if(e.getSource()==close){
            System.exit(0);
        }
    }
}