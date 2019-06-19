package PatronBuilder;

/**
 *
 * @author Elvis-
 */
public class Director {

    public Director() {
    }

    public void construir(BuilderAuto builder) {
        builder.crearNuevoCoche();
        builder.construirTipo();
        builder.construirMotor();
        builder.construirCarroceria();
        builder.construirVentanillas();
        builder.construirAireAcondicionado();
        builder.calcularPeso();
    }
    
}
