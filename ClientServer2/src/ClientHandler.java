import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler implements Runnable{
    private final Socket socket;
    private final File imagesDir;
    private final DefaultListModel<String> listModel;
    ClientHandler(Socket s, File imagesDir, DefaultListModel<String> listModel) {
        this.socket = s;
        this.imagesDir = imagesDir;
        this.listModel = listModel;
    }


    public void run() {
        try(DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()))){
            String originalName = dis.readUTF();
            long size = dis.readLong();
            String savedName = makeName(originalName);
            File outFile = new File(imagesDir, savedName);
            try(FileOutputStream fos = new FileOutputStream(outFile)){
                byte[] buffer = new byte[8192];
                long remaining = size;
                while (remaining > 0){
                    int read = dis.read(buffer, 0, (int)Math.min(buffer.length, remaining));
                    if(read == -1) throw new EOFException("End of stream error");
                    fos.write(buffer, 0, read);
                    remaining -= read;
                }
                fos.flush();
            }

            try{
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeBoolean(true);
                dos.flush();
            } catch (IOException ex){}

            SwingUtilities.invokeLater(() -> listModel.addElement(savedName));
        }catch(IOException e){
            e.printStackTrace();
        } finally {
            try{
                socket.close();
            } catch (IOException ignored){}
        }
    }

    public String makeName(String orig){
        String time = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
        String safe = orig.replace("[^a-zA-Z0-9._-]", "_");
        return time + "_" + safe;
    }
}
