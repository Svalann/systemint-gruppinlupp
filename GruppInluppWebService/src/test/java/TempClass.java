
import java.io.Serializable;

public class TempClass implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private float temperature;
    private float humidity;

    public TempClass() {
    }

    public TempClass(float temperature) {

        this.temperature = temperature;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
    
}
