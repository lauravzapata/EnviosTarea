package co.edu.uniquindio.poo.model;

public class EnvioNacional extends Envio {
    private String ciudad;
    private boolean domicilio;

    public EnvioNacional(String codigo, String destinatario, double peso, String ciudad, boolean domicilio) {
        super(codigo, destinatario, peso);
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public boolean isDomicilio() {
        return domicilio;
    }

    public void setDomicilio(boolean domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public double costoEnvio() {
            if(domicilio){
            return 9000+(getPeso()*2000);
        }else{
               return 6000+(getPeso()*2000) ;
            }
    }
}
