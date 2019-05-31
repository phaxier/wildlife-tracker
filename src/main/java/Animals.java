
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;



public class Animals {

    public Animals(String name, String health, String age,int animalId){
        this.name = name;
        this.health = health;
        this.age = age;
        this.animalId = animalId;
    }

    public String name;
    public String health;
    public String age;
    public int animalId;



public String getName(){return name;}

public String getHealth(){ return health;}

public String getAge(){ return age;}

public int getAnimalId(){ return animalId;}

}
