package manzano.wilson.multiapp.Objects;

/**
 * Created by User on 14/11/2017.
 */

public class User {
    private String EMAIL;
    private String PASSWORD;
    private String ID;
    private String NIC;
    private String DIRECCION;
    private String BARRIO;
    private int ESTRATO;
    private String SALDO;
    private String LATITUD;
    private String LONGITUD;
    private String MEDIDOR;
    private String CIUDAD;
    private String TITULAR;


    public User(String email, String password) {
        this.EMAIL = email;
        this.PASSWORD = password;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCIUDAD() {
        return CIUDAD;
    }

    public void setCIUDAD(String CIUDAD) {
        this.CIUDAD = CIUDAD;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getBARRIO() {
        return BARRIO;
    }

    public void setBARRIO(String BARRIO) {
        this.BARRIO = BARRIO;
    }

    public int getESTRATO() {
        return ESTRATO;
    }

    public void setESTRATO(int ESTRATO) {
        this.ESTRATO = ESTRATO;
    }

    public String getSALDO() {
        return SALDO;
    }

    public void setSALDO(String SALDO) {
        this.SALDO = SALDO;
    }

    public String getLATITUD() {
        return LATITUD;
    }

    public void setLATITUD(String LATITUD) {
        this.LATITUD = LATITUD;
    }

    public String getLONGITUD() {
        return LONGITUD;
    }

    public void setLONGITUD(String LONGITUD) {
        this.LONGITUD = LONGITUD;
    }

    public String getMEDIDOR() {
        return MEDIDOR;
    }

    public void setMEDIDOR(String MEDIDOR) {
        this.MEDIDOR = MEDIDOR;
    }


    public String getEmail() {
        return EMAIL;
    }

    public void setEmail(String email) {
        this.EMAIL = email;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public void setPassword(String password) {
        this.PASSWORD = password;
    }

    public String getId() {
        return ID;
    }

    public void setId(String id) {
        this.ID = id;
    }


    public String getTITULAR() {
        return TITULAR;
    }

    public void setTITULAR(String TITULAR) {
        this.TITULAR = TITULAR;
    }
}
