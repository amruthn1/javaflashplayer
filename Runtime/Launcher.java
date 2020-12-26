import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Launcher {
    public static void main(String[] args) throws IOException {
        JOptionPane.showMessageDialog(null, "Note: If the player prompts an error, hit dismiss all and the file will play normally.");
        JFrame container = new JFrame("Launcher");
        container.setSize(800, 800);
        JPanel panel = new JPanel();
        container.setResizable(false);
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        panel.setBounds(40, 80, 1100, 600);
        JButton quickstart = new JButton("Quick Start");
        quickstart.setBounds(-100, 100, 600, 600);
        quickstart.setBackground(Color.gray);
        JButton clearquicksave = new JButton("Delete Quick Save");
        clearquicksave.setBounds(25, 100, 600, 600);
        clearquicksave.setBackground(Color.gray);
        JButton choosefile = new JButton("Choose An SWF File");
        choosefile.setBounds(50, 100, 600, 600);
        choosefile.setBackground(Color.gray);
        JButton search = new JButton("Search For SWF Files");
        search.setBounds(50, 100, 600, 600);
        search.setBackground(Color.gray);
        panel.add(quickstart);
        panel.add(clearquicksave);
        panel.add(choosefile);
        panel.add(search);
        container.add(panel);
        container.setSize(1100, 300);
        container.setLayout(null);
        container.setVisible(true);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked");
                JFrame search = new JFrame("Search For SWF Files");
                JPanel u = new JPanel();
                search.setLayout(null);
                search.setResizable(true);
                Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
                int height = screensize.height * 1 / 4;
                int width = screensize.width * 1 / 3;
                search.setPreferredSize(new Dimension(width, height));
                search.getContentPane().setLayout(new FlowLayout());
                JTextField field = new JTextField("Search");
                search.getContentPane().add(field);
                search.pack();
                JLabel dropdown = new JLabel("Once you hit search, the options will appear in the dropdown.");
                dropdown.setVisible(false);
                search.setVisible(true);
                JButton startsearch = new JButton("Search");
                search.getContentPane().add(startsearch);
                search.getContentPane().add(dropdown);
                startsearch.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String x = field.getText();
                        String y = x.toLowerCase();
                        String z = y.replace(" ", "-");
                        System.out.println(z);
                        String documents_path = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
                        File file = new File(documents_path + "\\JavaApp\\Runtime\\Data\\archive.xml");
                        Scanner in = null;
                        String[] results;
                        String[] parsed;
                        List<String> dropdownitems = new ArrayList<String>();
                        try {
                            in = new Scanner(file);
                            while (in.hasNext()) {
                                String line = in.nextLine();
                                if (line.contains(z)) {
                                    String[] temp = line.split("source");
                                    Pattern p = Pattern.compile(".*\\\"(.*)\\\".*");
                                    Matcher m = p.matcher(temp[0]);
                                    if (m.find()) {
                                        dropdown.setVisible(true);
                                        dropdownitems.add(m.group(1));
                                    }
                                }
                            }
                            String[] arrayList = new String[dropdownitems.size()];
                            dropdownitems.toArray(arrayList);
                            final JComboBox<String> cb = new JComboBox<>(arrayList);
                            cb.setVisible(true);
                            search.add(cb);
                            JButton confirm = new JButton("Choose");
                            search.add(confirm);
                            cb.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JComboBox<String> combo = (JComboBox<String>) e.getSource();
                                    String selected = (String) combo.getSelectedItem();
                                    confirm.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                System.out.println(selected);
                                                String url = "https://archive.org/download/armorgames/" + selected;
                                                ReadableByteChannel readChannel = Channels.newChannel(new URL(url).openStream());
                                                FileOutputStream fileOS = new FileOutputStream(documents_path + "\\JavaApp\\Files\\" + selected);
                                                FileChannel writeChannel = fileOS.getChannel();
                                                search.dispose();
                                                JOptionPane.showMessageDialog(null, "The file will be downloaded in the background. The amount of time it will take will depend on your internet connection.");
                                                writeChannel
                                                        .transferFrom(readChannel, 0, Long.MAX_VALUE);
                                            } catch (MalformedURLException exception) {
                                                System.out.println("Uncaught Exception");
                                            } catch (IOException error) {
                                                System.out.println("IOException Error");
                                            }
                                        }
                                    });

                                }
                            });
                        } catch (FileNotFoundException exception) {
                            JOptionPane.showMessageDialog(null, "The requested SWF file was not found.");
                        }
                    }
                });
            }
        });
        quickstart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String documents_path = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
                System.out.println("clicked quickstart");
                try {
                    File quickdata = new File(documents_path + "\\JavaApp\\Runtime\\Data\\recents.emubackup");
                    BufferedReader br = new BufferedReader(new FileReader(quickdata));
                    String st;
                    while ((st = br.readLine()) != null) {
                        System.out.println(st);
                        if (st.contains("NULL")) {
                            System.out.println("Nothing in quicksave");
                            JOptionPane.showMessageDialog(null, "To use the quicksave feature, the player had to have been used before.");

                        } else {
                            Runtime.getRuntime().exec(new String[]{documents_path + "\\JavaApp\\Runtime\\start.bat", "-p", st});
                            container.dispose();
                        }
                    }
                } catch (Exception exception) {
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
                } catch (Exception filenotfound) {
                    System.out.println("filenotfound");
                }
            }
        });
        choosefile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked choosefile");
                container.dispose();
                try {
                    Main.main(new String[0]);
                } catch (Exception error) {
                    System.out.println("Couldn't initialize method");
                }
            }
        });

    }
}