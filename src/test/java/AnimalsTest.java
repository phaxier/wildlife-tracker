import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class AnimalsTest {

    @Before
    public void setUp(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "kos","210");
    }

    @After
    public void tearDown(){
        try(Connection con = DB.sql2o.open()){
            String deleteAnimalsQuery = "DELETE FROM animals*;";
            String deleteSightingsQuery = "DELETE FROM sightings*;";
            con.createQuery(deleteAnimalsQuery).executeUpdate();
            con.createQuery(deleteSightingsQuery).executeUpdate();
        }
    }


    @Test
    public void animal_instantiatesCorrectly_false() {
        Animals testAnimals = new Animals("Tortoise");
        assertEquals(true, testAnimals instanceof Animals);
    }

    @Test
    public void getName_animalInstantiatesWithName_Tortoise() {
        Animals testAnimals = new Animals("Tortoise");
        assertEquals("Tortoise", testAnimals.getName());
    }

    @Test
    public void equals_returnsTrueIfNameIsTheSame_false() {
        Animals firstAnimals = new Animals("Tortoise");
        Animals anotherAnimals = new Animals("Tortoise");
        assertTrue(firstAnimals.equals(anotherAnimals));
    }

    @Test
    public void save_assignsIdToObjectAndSavesObjectToDatabase() {
        Animals testAnimals = new Animals("Tortoise");
        testAnimals.save();
        Animals savedAnimals = Animals.all().get(0);
        assertEquals(testAnimals.getId(), savedAnimals.getId());
    }

    @Test
    public void all_returnsAllInstancesOfAnimals_false() {
        Animals firstAnimals = new Animals("Tortoise");
        firstAnimals.save();
        Animals secondAnimals = new Animals("Fisi");
        secondAnimals.save();
        assertEquals(true, Animals.all().get(0).equals(firstAnimals));
        assertEquals(true, Animals.all().get(1).equals(secondAnimals));
    }

    @Test
    public void find_returnsAnimalsWithSameId_secondAnimals() {
        Animals firstAnimals = new Animals("Tortoise");
        firstAnimals.save();
        Animals secondAnimals = new Animals("Fisi");
        secondAnimals.save();
        assertEquals(Animals.find(secondAnimals.getId()), secondAnimals);
    }

    @Test
    public void delete_deletesAnimalsFromDatabase_0() {
        Animals testAnimals = new Animals("Tortoise");
        testAnimals.save();
        testAnimals.delete();
        assertEquals(0, Animals.all().size());
    }

    public void updateName_updatesAnimalsNameInDatabase_String() {
        Animals testAnimals = new Animals("Tortoise");
        testAnimals.save();
        testAnimals.updateName("Hyena");
        assertEquals("Hyena", testAnimals.getName());
    }

    @Test
    public void find_returnsNullWhenNoAnimalsFound_null() {
        assertTrue(Animals.find(999) == null);
    }

}