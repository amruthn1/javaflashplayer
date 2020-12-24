import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import java.io.*;
import java.util.*;
public class Launcher {
    public static void main(String[]  args) throws IOException{
        JOptionPane.showMessageDialog(null, "Note: If the player prompts an error, hit dismiss all and the file will play normally.");
        JFrame container = new JFrame("Launcher");
        JPanel panel = new JPanel();
        container.setResizable(false);
        panel.setBounds(40,80,1100,600);
        JButton quickstart = new JButton("Quick Start");
        quickstart.setBounds(-100,100,600,600);
        quickstart.setBackground(Color.gray);
        JButton clearquicksave = new JButton("Delete Quick Save");
        clearquicksave.setBounds(25,100,600,600);
        clearquicksave.setBackground(Color.gray);
        JButton choosefile = new JButton("Choose An SWF File");
        choosefile.setBounds(50,100,600,600);
        choosefile.setBackground(Color.gray);
        panel.add(quickstart);
        panel.add(clearquicksave);
        panel.add(choosefile);
        container.add(panel);
        container.setSize(1100,300);
        container.setLayout(null);
        container.setVisible(true);
        quickstart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String documents_path = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
                System.out.println("clicked quickstart");
                try {
                    File quickdata = new File(documents_path + "\\JavaApp\\Runtime\\Data\\recents.emubackup");
                    BufferedReader br = new BufferedReader(new FileReader(quickdata));
                    String st;
                    while ((st = br.readLine()) != null){
                        System.out.println(st);
                        if (st.contains("NULL")){
                            System.out.println("Nothing in quicksave");
                            JOptionPane.showMessageDialog(null, "To use the quicksave feature, the player had to have been used before.");

                        } else {
                            Runtime.getRuntime().exec(new String[]{documents_path + "\\JavaApp\\Runtime\\start.bat" , "-p", st});
                            container.dispose();
                        }
                    }
                } catch (Exception exception){
                    System.out.println("Couldn't read file");
                }

            }
        });
        clearquicksave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("clicked clearquicksave");
                    String documents_path = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
                    PrintWriter writer = new PrintWriter(documents_path + "\\JavaApp\\Runtime\\Data\\recents.emubackup");
                    writer.print("NULL");
                    writer.close();
                } catch (Exception filenotfound){
                    System.out.println("filenotfound");
                }
            }
        });
        choosefile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked choosefile");
                container.dispose();
                try{
                    Main.main(new String[0]);
                } catch (Exception error){
                    System.out.println("Couldn't initialize method");
                }
            }
        });

    }
}