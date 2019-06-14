package PatronBuilder;

/**
 *
 * @author Elvis-
 */
public class Director
{
    public Director() {
    }
    // --------------------------
     public void construir( BuilderAuto builder )
    {
        builder.crearNuevoCoche();
        builder.construirMotor();
        builder.construirCarroceria();
        builder.construirVentanillas();
        builder.construirAireAcondicionado();
    }
}
