import java.io.IOException;
import java.net.*;

public class Cliente {

    // Se escogerá el puerto 3333 como puerto arbitrario para la conexión.
    private DatagramSocket Socket;
    private DatagramPacket Paquete;
    private InetAddress Dirección;
    private String Mensaje;
    private byte[] MensajeBytes;

    public void ejecutar() {
        try {
            conectar();
            generarMensaje();
            enviarMensaje();
        } catch (Exception e) {
            System.err.println(e.getClass());
            System.err.println(e.getMessage());
        } finally {
            if (!continuar()) ejecutar();
        }
    }

    private void conectar() throws SocketException, UnknownHostException {
        this.Socket = new DatagramSocket();
        this.Dirección = InetAddress.getByName("localhost");
    }

    private void generarMensaje() {
        System.out.println("Ingrese el texto que desee enviar:");
        // Se ingresa el texto, luego, se pasa a bytes.
        this.Mensaje = Utilidades.validarString();
        this.MensajeBytes = this.Mensaje.getBytes();
    }

    private void enviarMensaje() throws IOException {
        this.Paquete = new DatagramPacket(this.MensajeBytes,
                this.Mensaje.length(),
                this.Dirección,
                3333);
        this.Socket.send(this.Paquete);
    }

    private boolean continuar() {
        System.out.println("Escribe \"SALIR\" para salir del programa.");
        return Utilidades.validarString().equals("SALIR");
    }

}
