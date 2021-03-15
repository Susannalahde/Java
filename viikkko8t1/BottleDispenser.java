package com.example.viikkko8t1;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author susannalahde
 */


public class BottleDispenser {

    private int bottles;
    ArrayList<Bottle> bottle_array = new ArrayList<Bottle>();
    private double money;
    private String actionText;
    private Bottle receipt = null;

    //Tehdään singleton
    private static BottleDispenser bd = new BottleDispenser();

    private BottleDispenser() {

        bottles = 5;
        money = 0;
        Bottle bullo = new Bottle("Pepsi Max","Pepsi",0.3,0.5,1.8);
        bottle_array.add(bullo);
        bullo = new Bottle("Pepsi Max","Pepsi",0.3,1.5,2.2);
        bottle_array.add(bullo);
        bullo = new Bottle("Coca-Cola Zero","Coca-Cola",0.3,0.5,2.0);
        bottle_array.add(bullo);
        bullo = new Bottle("Coca-Cola Zero","Coca-Cola",0.3,1.5,2.5);
        bottle_array.add(bullo);
        bullo = new Bottle("Fanta Zero","Fanta",0.3,0.5,1.95);
        bottle_array.add(bullo);
    }

    public static BottleDispenser getInstance() {

        return bd;
    }

    //tällä voidaan näyttää rahan määrä koneessa
    public String moneyInDispenser() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(money);
    }

    public String addMoney(int amount) {
        money += amount;
        actionText = "Klink! Added "+amount+"$ !";
        return actionText;
    }

    public String buyBottle(int valinta) {
        int index = valinta - 1;
        if (bottles == 0) {
            actionText = "No bottles left, sorry :D";
        }

        else if (money < bottle_array.get(index - 1).getPrice()){
            actionText = "Add money first!";
        }
        else{

            money -= bottle_array.get(index - 1).getPrice();
            actionText = "KACHUNK! "+bottle_array.get(index - 1).getName()+" came out of the dispenser!";
            receipt = bottle_array.get(index - 1);
            bottle_array.remove(index -1);
        }
        return actionText;
    }


    public String returnMoney() {
        if(money > 0){
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            actionText = "Klink klink. Money came out! you got "+ decimalFormat.format(money)+"$ back";
            money = 0;
        }
        else{
            actionText = "Klink klink. All money gone!";
        }
        return actionText;



    }
    public String listBottles() {
        int i = 0;
        actionText = "";
        for(Bottle bottle : bottle_array) {
            i++;
            actionText += i+". Name: "+bottle.getName()+"\n\tSize: "+bottle.getSize()+"\tPrice: "+bottle.getPrice()+"\n\n";
        }
        return actionText;
    }
    public String getReceipt() {
        if (receipt == null){
            return "";
        }
        actionText = "---------Receipt---------\n"+
                "item: "+receipt.getName()+"\n"+
                "manufacturer: "+receipt.getManufacturer()+"\n"+
                "size: "+receipt.getSize()+"\n"+
                "price: "+receipt.getPrice()+"\n"+
                "total energy: "+receipt.getEnergy()+"\n"+
                "-------------------------\n\n";
        return actionText;
    }
}
