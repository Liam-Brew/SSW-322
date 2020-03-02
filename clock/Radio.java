package clock;

public class Radio {
    // Data fields
    private int volume = 0;
    private boolean active = false;
    private double frequency = 0.0;
    private String stationLabel = null;

    // Methods
    public void setStation(double station) {
        if ((station >= 88.1) && (station <= 108)) {
            this.frequency = station;
            this.stationLabel = "FM";
        } else if ((station >= 540) && (station <= 1600)) {
            this.frequency = station;
            this.stationLabel = "AM";
        } else {
            throw new IllegalArgumentException("Error: invalid radio frequency!");
        }
    }

    public void setVolume(int volume) {
        if (volume < 0 || volume > 10) {
            throw new IllegalArgumentException("Error: invalid volume!");
        }
        this.volume = volume;
    }

    public boolean getActive(){
        return active;
    }

    public void turnOn(){
        if(getActive()){
            throw new IllegalStateException("Error: radio already on!");
        }
        this.active = !this.active;
        System.out.println("The radio was turned on and is playing " + frequency + " " + stationLabel + ".");
    }

    public void turnOff(){
        if(!getActive()){
            throw new IllegalStateException("Error: radio already off!");
        }
        this.active = !this.active;
        System.out.println("The radio was turned off.");
    }

}