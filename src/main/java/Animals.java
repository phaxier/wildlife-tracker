
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;



public class Animals {

    public Animals(String name, String health, String age, int animalId) {
        this.name = name;
        this.health = health;
        this.age = age;
        this.animalId = animalId;
    }

    public String name;
    public String health;
    public String age;
    public int animalId;


    public String getName() {
        return name;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    public int getAnimalId() {
        return animalId;
    }


    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, health,age, animalid) VALUES (:name, :AnimalId)";
            this.animalId = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("animalId", this.animalId)
                    .executeUpdate()
                    .getKey();
        }
    }

    @Override
    public boolean equals(Object otherAnimals) {
        if (!(otherAnimals instanceof Animals)) {
            return false;
        } else {
            Animals newanimal = (Animals) otherAnimals;
            return this.getName().equals(newanimal.getName()) &&
                    this.getAnimalId() == newanimal.getAnimalId();
        }

        public static List<Animals> all () {
            String sql = "SELECT * FROM animals";
            try (Connection con = DB.sql2o.open()) {
                return con.createQuery(sql).executeAndFetch(Animals.class);x
            }
        }

    }

    public static Animals find (int id) {
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM animals where id=:id";
            Animals animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animals.class);
            return animal;
        }
    }


}
