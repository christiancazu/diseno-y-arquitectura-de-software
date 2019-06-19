package PatronBuilder;

/**
 *
 * @author Elvis-
 */
public class ConstructorAutoMedio extends BuilderAuto {
  
    @Override
    public void construirTipo() {
        this.auto.setTipo("medio");
    }

    @Override
    public void construirMotor() {
        this.auto.setMotor("Motor de potencia media");
    }
    
    @Override
    public void construirCarroceria() {
        this.auto.setCarroceria("Carrocería de protección media");
    }
    
    @Override
    public void construirAireAcondicionado() {
        this.auto.setAireAcond(false);
    }
    
    @Override
    public void construirVentanillas() {
        this.auto.setVentanillasElectricas(true);
    }
    
    @Override
    public void calcularPeso() {
        this.auto.setPeso(800);
    }

}
