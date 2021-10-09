import java.awt.*;
import java.io.*;
import javax.swing.JFileChooser;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException{
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        String documents_path = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
        dialog.setDirectory(documents_path + "javaflashplayer\\Files\\");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String swffile = dialog.getFile();
        System.out.println(swffile + " chosen.");
        PrintWriter writer = new PrintWriter(documents_path + "\\javaflashplayer\\Runtime\\Data\\recents.emubackup");
        writer.print("");
        writer.print(swffile);
        writer.close();
        System.out.println(documents_path);
        Runtime.getRuntime().exec(new String[]{documents_path + "\\javaflashplayer\\Runtime\\start.bat" , "-p", swffile});
        System.exit(0);
    }
}
