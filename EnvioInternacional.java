package co.edu.uniquindio.poo.model;

public class EnvioInternacional extends Envio {
    private String pais;
    private boolean seguro;


    public EnvioInternacional(String codigo, String destinatario, double peso, String pais, boolean seguro) {
        super(codigo, destinatario, peso);
        this.pais = pais;
        this.seguro = seguro;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean isSeguro() {
        return seguro;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

    @Override
    public double costoEnvio() {
        if(seguro== true){
            return 35000+(getPeso()*9000);
        }else{
            return 15000+(getPeso()*9000);
        }

    }
}
