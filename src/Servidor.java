import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private DataOutputStream Out;
    private DataInputStream In;
    private ServerSocket Socket;
    private Socket SocketCli;
    private String Msj;
    private String MsjEntrada;

    public void ejecutar() {
        try {
            conectarSocket();
            abrirFlujo();
            while (true) {
                recibirMensaje();
                enviarMensaje();
            }

        } catch (Exception e) {
            System.err.println(e.getClass());
            System.err.println(e.getMessage());
        }
    }

    private void conectarSocket() throws IOException {
        this.Socket = new ServerSocket(3333);
        System.out.println("[Socket Conectado]");
        this.SocketCli = this.Socket.accept();
        System.out.println("[Socket Cliente Conectado]");
    }

    private void abrirFlujo() throws IOException {
        this.In = new DataInputStream(this.SocketCli.getInputStream());
        this.Out = new DataOutputStream(this.SocketCli.getOutputStream());
    }

    private void enviarMensaje() throws IOException {
        System.out.println("[Envía tu mensaje]");
        this.Msj = "[Servidor] " + Utilidades.validarString();
        this.Out.writeUTF(this.Msj);
        this.Out.flush();
    }

    private void recibirMensaje() throws IOException {
        System.out.println("[Esperando mensaje]");
        this.MsjEntrada = this.In.readUTF();
        System.out.println(this.MsjEntrada);
    }


}
