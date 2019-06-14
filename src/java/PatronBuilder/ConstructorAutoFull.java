package PatronBuilder;

/**
 *
 * @author Elvis-
 */
public class ConstructorAutoFull extends BuilderAuto {

    public ConstructorAutoFull() {
    }

    // ------------------------------
    @Override
    public void construirMotor() {
        this.auto.setTipo("full");
        this.auto.setMotor("Motor de potencia alta");
    }

    // ------------------------------
    @Override
    public void construirCarroceria() {
        this.auto.setCarroceria("Carrocería de alta protección");
    }

    // ------------------------------
    @Override
    public void construirAireAcondicionado() {
        this.auto.setAireAcond(true);
    }

    // ------------------------------
    @Override
    public void construirVentanillas() {
        this.auto.setVentanillasElectricas(true);
    }
}
