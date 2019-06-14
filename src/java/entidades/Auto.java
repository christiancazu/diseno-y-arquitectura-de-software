package entidades;
/**
 *
 * @author Elvis-
 */
public class Auto
{
    private String motor = "";
    private String carroceria = "";
    private Boolean ventanillasElectricas = false;
    private Boolean aireAcond = false;
    // -------------------------------------------
    public Auto() {
    }
    // -------------------------------------------
    public String getMotor() {
        return this.motor;
    }
    // -------------------------------------------
    public void setMotor(String motor) {
        this.motor = motor;
    }
    // -------------------------------------------
    public String getCarroceria() {
        return this.carroceria;
    }
    // -------------------------------------------
    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }
    // -------------------------------------------
    public Boolean getVentanillasElectricas() {
        return ventanillasElectricas;
    }
    // -------------------------------------------
    public void setVentanillasElectricas(Boolean ventanillasElectricas) {
        this.ventanillasElectricas = ventanillasElectricas;
    }
    // -------------------------------------------
    public Boolean getAireAcond() {
        return aireAcond;
    }
    // -------------------------------------------
    public void setAireAcond(Boolean aireAcond) {
        this.aireAcond = aireAcond;
    }
}
