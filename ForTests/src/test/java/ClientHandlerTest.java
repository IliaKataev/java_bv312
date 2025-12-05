
import org.example.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.*;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandlerTest {
    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    private ServerSocket server;
    private Thread serverThread;

    @Before
    public void setUp() throws Exception{
        server = new ServerSocket(6666);
        serverThread = new Thread(() -> {
            try{
                Socket s = server.accept();
                ClientHandler handler = new ClientHandler(s, tmp.getRoot(), new DefaultListModel<>());
                handler.run();
            }catch (IOException e) {
                throw new RuntimeException(e);
            };
        });
        serverThread.start();
    }

    @Test
    public void testReceiveSmallFile() throws Exception{
        int port = server.getLocalPort();
        try(Socket client = new Socket("localhost", port)){
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            String name ="test.bin";
            byte[] data = new byte[]{1,2,3,4,5};
            dos.writeUTF(name);
            dos.writeLong(data.length);
            dos.write(data);
            dos.flush();
        }

        serverThread.join(2000);
        File[] files = tmp.getRoot().listFiles();
        assertNotNull(files);
        assertEquals(1, files.length);
    }

    @After
    public void tearDown() throws Exception{
        server.close();
    }
}
