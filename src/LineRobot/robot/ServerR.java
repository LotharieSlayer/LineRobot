package LineRobot.robot;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * Serveur du robot qui accueillera chaque Socket d'autres robots.
 */

 //receveur
public class ServerR {
    
    private final int PORT = 1312;
    private InetAddress mcast;
    private MulticastSocket ms;

    public ServerR()  {

        try {
            mcast = InetAddress.getByName("225.1.1.1");
            ms = new MulticastSocket(PORT);
            ms.joinGroup(mcast);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        DatagramPacket msg;
        do {
            msg = new DatagramPacket(new byte[512], 512);
            try {
                ms.receive(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!new String(msg.getData()).startsWith("lineRobot:"));
        return new String(msg.getData()).substring("lineRobot:".length());
    }
}
