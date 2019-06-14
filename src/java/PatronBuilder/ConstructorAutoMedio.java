package PatronBuilder;
/**
 *
 * @author Elvis-
 */
public class ConstructorAutoMedio extends BuilderAuto
{
    public ConstructorAutoMedio() {
    }
    // ------------------------------
    @Override
     public void construirMotor() {
        this.auto.setMotor( "Motor de potencia media" );
    }
    // ------------------------------
    @Override
     public void construirCarroceria() {
        this.auto.setCarroceria( "Carrocería de protección media" );
    }
    // ------------------------------
    @Override
     public void construirAireAcondicionado() {
        this.auto.setAireAcond( false );
    }
    // ------------------------------
    @Override
     public void construirVentanillas() {
        this.auto.setVentanillasElectricas( true );
    }
}
