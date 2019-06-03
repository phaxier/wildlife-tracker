import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DateFormat;
import java.util.Date;

public class SightingsTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sighting_instantiatesCorrectly_true() {
        Animals testAnimals = new Animals("Tortoise");
        testAnimals.save();
        Sightings testSightings = new Sightings(testAnimals.getId(), "54.542514, -111.849520", "Ranger Muchai");
        assertEquals(true, testSightings instanceof Sightings);
    }

    @Test
    public void equals_returnsTrueIfLocationAndDescriptionAreSame_true() {
        Animals testAnimals = new Animals("Tortoise");
        testAnimals.save();
        Sightings testSightings = new Sightings(testAnimals.getId(), "54.542514, -111.849520", "Ranger Muchai");
        Sightings anotherSightings = new Sightings(testAnimals.getId(), "54.542514, -111.849520", "Ranger Muchai");
        assertTrue(testSightings.equals(anotherSightings));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sightings() {
        Animals testAnimals = new Animals("Tortoise");
        testAnimals.save();
        Sightings testSightings = new Sightings (testAnimals.getId(), "54.542514, -111.849520", "Ranger Muchai");
        testSightings.save();
        assertEquals(true, Sightings.all().get(0).equals(testSightings));
    }

    @Test
    public void all_returnsAllInstancesOfSightings_true() {
        Animals testAnimals = new Animals("Tortoise");
        testAnimals.save();
        Sightings testSightings = new Sightings (testAnimals.getId(), "54.98538, -111.9294965", "Ranger Muchai");
        testSightings.save();
        Animals secondTestAnimals = new Animals("Badger");
        secondTestAnimals.save();
        Sightings secondTestSightings = new Sightings (testAnimals.getId(), "54.542514, -111.849520", "Ranger Kemboi");
        secondTestSightings.save();
        assertEquals(true, Sightings.all().get(0).equals(testSightings));
        assertEquals(true, Sightings.all().get(1).equals(secondTestSightings));
    }

    @Test
    public void find_returnsSightingsWithSameId_secondSightings() {
        Animals testAnimals = new Animals("Tortoise");
        testAnimals.save();
        Sightings testSightings = new Sightings (testAnimals.getId(), "54.542514, -111.849520", "Ranger Muchai");
        testSightings.save();
        Animals secondTestAnimals = new Animals("Badger");
        secondTestAnimals.save();
        Sightings secondTestSightings = new Sightings (testAnimals.getId(), "54.542514, -111.849520", "Ranger Kemboi");
        secondTestSightings.save();
        assertEquals(Sightings.find(secondTestSightings.getId()), secondTestSightings);
    }

    @Test
    public void find_returnsNullWhenNoAnimalsFound_null() {
        assertTrue(Animals.find(999) == null);
    }

}
