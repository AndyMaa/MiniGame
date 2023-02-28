package minigame.core.server;

import minigame.core.net.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public final class Connection {
    private final Socket socket;
    private final ObjectOutputStream os;
    private final ObjectInputStream is;
    public Connection(Socket socket) throws IOException {
        this.socket=socket;
        os=new ObjectOutputStream(socket.getOutputStream());
        is=new ObjectInputStream(socket.getInputStream());
    }
    public void close(){
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writePacket(Packet packet){
        try {
            os.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Packet readPacket() throws IOException, ClassNotFoundException {
        return ((Packet) is.readObject());
    }
}
