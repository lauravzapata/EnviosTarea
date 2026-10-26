package co.edu.uniquindio.poo.model;

public class EnvioExpress extends EnvioNacional {
    private byte cantidadHora;

    public EnvioExpress(String codigo, String destinatario, double peso, String ciudad, boolean domicilio) {
        super(codigo, destinatario, peso, ciudad, domicilio);
    }

    public byte getCantidadHora() {
        return cantidadHora;
    }

    public void setCantidadHora(byte cantidadHora) {
        this.cantidadHora = cantidadHora;
    }

    @Override
    public double costoEnvio() {
            double costo = super.costoEnvio() * 1.30;
            if (cantidadHora <= 24) {
                costo += 5000;
            }
            return costo;
    }
}
