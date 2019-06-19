package PatronBuilder;

/**
 *
 * @author Elvis-
 */
public class ConstructorAutoBase extends BuilderAuto {
    
    @Override
    public void construirTipo() {
        this.auto.setTipo("base");
    }
    
    @Override
    public void construirMotor() {
        this.auto.setMotor("Motor de potencia mínima");
    }
    
    @Override
    public void construirCarroceria() {
        this.auto.setCarroceria("Carrocería de baja protección");
    }
    
    @Override
    public void construirAireAcondicionado() {
        this.auto.setAireAcond(false);
    }
    
    @Override
    public void construirVentanillas() {
        this.auto.setVentanillasElectricas(false);
    }

    @Override
    public void calcularPeso() {
        this.auto.setPeso(600);
    }

}
