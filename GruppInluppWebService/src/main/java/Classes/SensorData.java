package Classes;

import java.io.Serializable;
import java.util.Date;

public class SensorData implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private float temperature;
    private float humidity;
    private Date created;

    public SensorData() {
    }

    public SensorData(float temperature) {

        this.temperature = temperature;

    }

    public SensorData(float temperature, float humidity, Date created) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.created = created;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
