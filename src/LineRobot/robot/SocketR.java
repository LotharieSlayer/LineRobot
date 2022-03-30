package LineRobot.robot;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Socket qui se connectera à chaque Serveur de chaque robot.
 */

 //emetteur
public class SocketR implements Closeable{
    
    private final static int PORT = 1312;

    private InetAddress mcast;
    private static MulticastSocket ms;

    public SocketR() {
            try {
                ms = new MulticastSocket();
                mcast  = InetAddress.getByName("225.1.1.1");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void sendMessage(String message) {
        String trame = "lineRobot:"+message;
        DatagramPacket dp  = new DatagramPacket(trame.getBytes(),trame.length(), mcast, PORT);
        try {
            ms.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        ms.close();
    }
}
