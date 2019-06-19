package entidades;

import PatronVisitor.Visitable;
import PatronVisitor.Visitor;

/**
 *
 * @author Elvis-
 */
public class Auto implements Visitable {

    private String tipo;
    private String motor;
    private String carroceria;
    private Boolean ventanillasElectricas;
    private Boolean aireAcond;
    private double peso;
    
    public Auto() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getMotor() {
        return this.motor;
    }
    
    public void setMotor(String motor) {
        this.motor = motor;
    }
    
    public String getCarroceria() {
        return this.carroceria;
    }
    
    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }
    
    public Boolean getVentanillasElectricas() {
        return ventanillasElectricas;
    }
    
    public void setVentanillasElectricas(Boolean ventanillasElectricas) {
        this.ventanillasElectricas = ventanillasElectricas;
    }
    
    public Boolean getAireAcond() {
        return aireAcond;
    }
    
    public void setAireAcond(Boolean aireAcond) {
        this.aireAcond = aireAcond;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public double acceptVisitor(Visitor visitor) {
        return visitor.visit(this);
    }

}
