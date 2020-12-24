import java.awt.*;
import java.io.*;
import javax.swing.JFileChooser;
import java.io.IOException;
import javax.swing.*;
public class Main {

    public static void main(String[] args) throws IOException{
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String swffile = dialog.getFile();
        System.out.println(swffile + " chosen.");
        String documents_path = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
        PrintWriter writer = new PrintWriter(documents_path + "\\JavaApp\\Runtime\\Data\\recents.emubackup");
        writer.print("");
        writer.print(swffile);
        writer.close();
        System.out.println(documents_path);
        Runtime.getRuntime().exec(new String[]{documents_path + "\\JavaApp\\Runtime\\start.bat" , "-p", swffile});
    }
}
