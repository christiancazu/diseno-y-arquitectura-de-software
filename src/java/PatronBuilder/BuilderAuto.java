package PatronBuilder;

import entidades.Auto;

/**
 *
 * @author Elvis-
 */
public abstract class BuilderAuto {

    protected Auto auto;
    
    public Auto getAuto() {
        return this.auto;
    }
    
    public void crearNuevoCoche() {
        this.auto = new Auto();
    }
    
    // Métodos que deberán ser construídos por las clases que hereden de ésta
    public abstract void construirTipo();
    
    public abstract void construirMotor();

    public abstract void construirCarroceria();

    public abstract void construirAireAcondicionado();

    public abstract void construirVentanillas();
    
    public abstract void calcularPeso();
    
}
