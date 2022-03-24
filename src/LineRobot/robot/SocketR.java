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

    private String message;
    private InetAddress mcast;
    private static MulticastSocket ms;

    public SocketR() {
        try {
            ms = new MulticastSocket();
            mcast  = InetAddress.getByName("225.1.1.1");
        } catch (IOException e) {e.printStackTrace();}
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void sendMessage() throws IOException{
        DatagramPacket dp  = new DatagramPacket(message.getBytes(),message.length(), mcast, PORT);
        ms.send(dp);
    }

    @Override
    public void close() throws IOException {
        ms.close();
    }

    public static void main(String[] args) {

        try (SocketR socketR = new SocketR();) {
            socketR.setMessage("testa");
            while(true){
                socketR.sendMessage();
            }
        } catch (Exception e) {}
    }
}
