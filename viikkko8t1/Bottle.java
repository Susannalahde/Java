package com.example.viikkko8t1;

/**
 *
 * @author susannalahde
 */
public class Bottle {
    private String name;
    private String manufacturer;
    private double total_energy;
    private double pullokoko;
    private double pullohinta;
    public Bottle(String bname, String manuf, double totE,double size, double hinta){
        name = bname;
        manufacturer = manuf;
        total_energy = totE;
        pullokoko = size;
        pullohinta = hinta;
    }
    public String getName(){
        return name;
    }

    public String getManufacturer(){ return manufacturer; }

    public double getSize(){
        return pullokoko;
    }

    public double getPrice() { return pullohinta; }

    public double getEnergy(){
        return total_energy;
    }
}
