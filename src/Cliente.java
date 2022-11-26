import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {

    private DataOutputStream Out;
    private DataInputStream In;
    private Socket Socket;
    private String Msj;
    private String MsjEntrada;

    public void ejecutar() {
        try {
            conectarSocket();
            abrirFlujo();
            while (true) {
                enviarMensaje();
                recibirMensaje();
            }

        } catch (Exception e) {
            System.err.println(e.getClass());
            System.err.println(e.getMessage());
        }
    }

    private void conectarSocket() throws IOException {
        this.Socket = new Socket("127.0.0.1", 3333);
        System.out.println("[Socket Conectado]");
    }

    private void abrirFlujo() throws IOException {
        this.In = new DataInputStream(this.Socket.getInputStream());
        this.Out = new DataOutputStream(this.Socket.getOutputStream());
    }

    private void enviarMensaje() throws IOException {
        System.out.println("[Envía tu mensaje]");
        this.Msj = "[Cliente] " + Utilidades.validarString();
        this.Out.writeUTF(this.Msj);
        this.Out.flush();
    }

    private void recibirMensaje() throws IOException {
        System.out.println("[Esperando mensaje]");
        this.MsjEntrada = this.In.readUTF();
        System.out.println(this.MsjEntrada);
    }

}
