import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class ClientApp {
    private JFrame frame;
    private JLabel previewLabel;
    private File selectedFile;
    private  JTextField hostField;
    private  JTextField portField;

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new ClientApp().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("Image Sender Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        hostField = new JTextField("localhost", 12);
        portField = new JTextField("9000", 6);
        top.add(new JLabel("Host:"));
        top.add(hostField);
        top.add(new JLabel("Port:"));
        top.add(portField);
        frame.add(top, BorderLayout.NORTH);

        previewLabel = new JLabel("No image selected", SwingConstants.CENTER);
        previewLabel.setPreferredSize(new Dimension(400, 250));
        frame.add(previewLabel, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton chooseBtn = new JButton("Choose image...");
        JButton sendBtn = new JButton("Send");
        sendBtn.setEnabled(false);
        bottom.add(chooseBtn);
        bottom.add(sendBtn);
        frame.add(bottom, BorderLayout.SOUTH);

        chooseBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int res = chooser.showOpenDialog(frame);
            if (res == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                showPreview(selectedFile);
                sendBtn.setEnabled(true);
            }
        });

        sendBtn.addActionListener(e -> {
            if (selectedFile == null) return;
            sendBtn.setEnabled(false);
            new Thread(() -> {
                try {
                    sendFile(selectedFile, hostField.getText().trim(), Integer.parseInt(portField.getText().trim()));
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(frame, "File sent successfully");
                        sendBtn.setEnabled(true);
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                        sendBtn.setEnabled(true);
                    });
                }
            }).start();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void showPreview(File f){
        ImageIcon icon = new ImageIcon(f.getAbsolutePath());
        Image img = icon.getImage();
        int w =400;
        int h = 250;
        double rw = (double)w/img.getWidth(null);
        double rh = (double)h/img.getHeight(null);

        // 1600x1200 400x250 rw 400/1600 = 0.25 250/1200 = 0.208

        double r = Math.min(rw, rh);

        Image scaled = img.getScaledInstance(
                (int)(img.getWidth(null)*r),
                (int)(img.getHeight(null)*r),
                Image.SCALE_SMOOTH);

        previewLabel.setIcon(new ImageIcon(scaled));
        previewLabel.setText(null);
    }

    private void sendFile(File file, String host, int port) throws IOException{
        try(Socket socket = new Socket(host, port);
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            FileInputStream fis = new FileInputStream(file)){

            dos.writeUTF(file.getName());
            dos.writeLong(file.length());
             byte[] buffer = new byte[8192];
             int read;

             while((read = fis.read(buffer)) != 1){
                 dos.write(buffer, 0, read);
             }
             dos.flush();

             try(DataInputStream dis = new DataInputStream(socket.getInputStream())){
                 if(dis.available() > 0){
                     boolean ok = dis.readBoolean();
                     System.out.println("Server answer:" + ok);
                 }
             }catch (IOException ignored){}
        }
    }
}
