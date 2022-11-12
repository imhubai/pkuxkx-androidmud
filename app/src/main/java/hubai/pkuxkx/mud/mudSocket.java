package hubai.pkuxkx.mud;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class mudSocket {
    private Socket socket;
    private OutputStream out;
    private InputStream in;
    public int serverStatus = 1;
    private String charest = "UTF-8";

    public mudSocket() {
    }

    public boolean connect(String IP, int Port) {
        boolean isConnect = false;
        charest = (Port == 8080) ? "UTF-8" : "GBK";
        try {
            if (socket == null || !socket.isConnected()) {
                socket = new Socket();
            }
            SocketAddress socketaddress = new InetSocketAddress(IP, Port);
            socket.connect(socketaddress, 5000);
            if (socket.isConnected()) {
                isConnect = true;
                //连接成功
            }
        } catch (IOException e) {
            e.printStackTrace();
            //无权限或无网络或服务器断开
        }
        return isConnect;
    }

    public void sendMsg(String msg) {
        try {
            if (out == null) {
                out = socket.getOutputStream();
            }
            out.write(msg.getBytes(charest));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String rcvMsg() {
        //byte[] msg = new byte[1024*4];
        int msg_len;
        String Msg = null;
        char[] cbuf = new char[1024 * 4];

        try {
            if (in == null && serverStatus != -1) {
                in = socket.getInputStream();
            }

            InputStreamReader inr = new InputStreamReader(in, charest);
            msg_len = inr.read(cbuf);
            if (msg_len == -1) {
                serverStatus = msg_len;
                //close
                disconnect();
                return null;
            }
            Msg = new String(cbuf, 0, msg_len);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Msg;
    }

    public void closeSocket() {
        try {
            if (in != null) {
                in.close();
                in = null;
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (socket != null) {
                socket.close();
                socket = null;
            }
            serverStatus = -1;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        closeSocket();
    }
}
