package SharedClass;

import java.io.Serializable;

public class Car implements Serializable {
    private static final long serialVersionUID = 1359344L;
    private String registration;
    private int year;
    private String color1,color2,color3;
    private String maker, model;
    private int price;

    Car(){

    }
    Car(String registration, int year, String color1, String color2, String color3, String maker, String model, int price){
        this.registration = registration;this.year = year;
        this.color1 = color1;this.color2 = color2;this.color3 = color3;
        this.maker = maker;this.model = model;
        this.price = price;

    }

    @Override
    public String toString(){
        return(registration+','+year+','+color1+','+color2+','+color3+','+maker+','+model+','+price);
    }

    public void DisplayInfo(){
        System.out.println("Registration No: "+ registration);
        System.out.println("Year:   "+ year);
        System.out.println("Colors: "+ color1 + ','+ color2+ ',' + color3);
        System.out.println("Make:  "+ maker);
        System.out.println("Car model:  " + model);
        System.out.println("Price: " + price + "$");
    }

    public String getRegistration(){
        return registration;
    }

    public String getMaker(){
        return maker;
    }

    public String getModel(){
        return model;
    }
}

