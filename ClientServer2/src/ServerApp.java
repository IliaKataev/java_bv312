import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerApp {
    private JFrame frame;
    private final File imageDir = new File("images");
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> fileList;
    private ServerSocket serverSocket;
    private final ExecutorService pool = Executors.newCachedThreadPool();
    private volatile boolean running = true;

    private int port = 9000;

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            try{
                new ServerApp().start();
            } catch (IOException e){
                e.printStackTrace();
            }
        });
    }

    private void start() throws IOException {
        if (!imageDir.exists()) imageDir.mkdirs();
        loadExistingFiles();

        frame = new JFrame("Image Получатель Сервер");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // увеличим размер, чтобы было место для предпросмотра
        frame.setLayout(new BorderLayout());

        // Левая часть: список файлов
        fileList = new JList<>(listModel);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(fileList);

        // Правая часть: предпросмотр изображения
        JLabel previewLabel = new JLabel("Preview", SwingConstants.CENTER);
        JScrollPane previewScroll = new JScrollPane(previewLabel);

        // Разделяем левую и правую части
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, previewScroll);
        splitPane.setDividerLocation(250);
        frame.add(splitPane, BorderLayout.CENTER);

        // Внизу: лог-сообщения
        JTextArea logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setPreferredSize(new Dimension(frame.getWidth(), 100));
        frame.add(logScroll, BorderLayout.SOUTH);

        // Верхняя панель: порт и кнопки
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Port: "));
        JTextField portField = new JTextField(String.valueOf(port), 6);
        top.add(portField);

        JButton startBtn = new JButton("Start");
        JButton stopBtn = new JButton("Stop");
        stopBtn.setEnabled(false);
        top.add(startBtn);
        top.add(stopBtn);

        frame.add(top, BorderLayout.NORTH);

        // Обработчики кнопок
        startBtn.addActionListener(e -> {
            try {
                port = Integer.parseInt(portField.getText().trim());
                startServer();
                logArea.append("Server started on port " + port + "\n");
                startBtn.setEnabled(false);
                stopBtn.setEnabled(true);
                portField.setEnabled(false);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Can't start server: " + ex.getMessage());
            }
        });

        stopBtn.addActionListener(e -> {
            stopServer();
            logArea.append("Server stopped\n");
            startBtn.setEnabled(true);
            stopBtn.setEnabled(false);
            portField.setEnabled(true);
        });

        // Двойной клик по файлу — показать предпросмотр
        fileList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String name = fileList.getSelectedValue();
                    if (name != null) {
                        File f = new File(imageDir, name);
                        if (f.exists()) {
                            ImageIcon icon = new ImageIcon(f.getAbsolutePath());
                            // масштабирование под JLabel
                            Image img = icon.getImage();
                            int w = previewScroll.getWidth() - 20;
                            int h = previewScroll.getHeight() - 20;
                            double rw = (double) w / img.getWidth(null);
                            double rh = (double) h / img.getHeight(null);
                            double r = Math.min(rw, rh);
                            Image scaled = img.getScaledInstance(
                                    (int) (img.getWidth(null) * r),
                                    (int) (img.getHeight(null) * r),
                                    Image.SCALE_SMOOTH
                            );
                            previewLabel.setIcon(new ImageIcon(scaled));
                            previewLabel.setText(null);
                        }
                    }
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void startServer() throws IOException {
        serverSocket = new ServerSocket(port);
        pool.submit(() -> {
            while(running){
                try{
                    Socket client = serverSocket.accept();
                    pool.submit(new ClientHandler(client, imageDir, listModel));
                } catch (IOException e) {
                    if(running) e.printStackTrace();
                }
            }
        });
        JOptionPane.showMessageDialog(frame, "Server started on port " + port);
    }

    private void stopServer(){
        running = false;
        try{
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e){}
        pool.shutdownNow();
        JOptionPane.showMessageDialog(frame, "Server stopped");
    }

    private void loadExistingFiles(){
        String[] names = imageDir.list();
        if(names != null){
            for (String n : names) listModel.addElement(n);
        }
    }

    private void openSelectedImage(){
        String name = fileList.getSelectedValue();
        if(name == null) return;
        File f = new File(imageDir, name);
        if(!f.exists()){
            JOptionPane.showMessageDialog(frame, "File Not Found");
            return;
        }

        JFrame imgFrame = new JFrame(name);
        imgFrame.setSize(600, 600);
        imgFrame.setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon(f.getAbsolutePath());
        JLabel lbl = new JLabel(icon);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(lbl);
        imgFrame.add(scrollPane, BorderLayout.CENTER);

        imgFrame.setLocationRelativeTo(null);
        imgFrame.setVisible(true);

    }
}
