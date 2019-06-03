import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class AnimalsTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();


    @Test
    public void animal_instantiatesCorrectly_false() {
        Animals testAnimal = new Animals("Hyena","okay", "young", 1);
        assertEquals(true, testAnimal instanceof Animals);
    }
    @Test
    public void Animals_instantiatesWithName_String() {
        Animals testAnimal = new Animals("Hyena","okay", "young", 1);
        assertEquals("Hyena", testAnimal.getName());
    }

    @Test
    public void client_instantiatesWithNumber_Integer() {
        Animals newAnimal = new Animals("Hyena", "okay", "young", 1);
        assertEquals(1, newAnimal.getAnimalId());
    }

    @Test
    public void equals_returnsTrueIfTwoObjectsAreSame_true() {
        Animals testmonkey = new Animals("Hyena", "okay", "young", 1);
        Animals testanimal = new Animals("Hyena", "okay", "young", 1);
        assertTrue(testmonkey.equals(testanimal));
    }

    @Test
    public void equals_returnsTrueIfNameAndAnimalIdAreSame_true() {
        Animals testanimal = new Animals("Hyena", "okay", "young", 1);
        Animals anotheranimal = new Animals("Hyena", "okay", "young", 1);
        assertTrue(testanimal.equals(anotheranimal));
    }


    @Test
    public void save_assignsIdToAnimal() {
        Animals testanimal = new Animals("Hyena", "okay", "young", 1);
        testanimal.save();
        Animals savedanimal = Animals.all().get(0);
        assertEquals(savedanimal.getAnimalId(), testanimal.getAnimalId());
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animals firstanimal = new Animals("Hyena", "okay", "young", 1);
        firstanimal.save();
        Animals secondanimal = new Animals("Hyena", "okay", "young", 1);
        secondanimal.save();
        assertEquals(true, Animals.all().get(0).equals(firstanimal));
        assertEquals(true, Animals.all().get(1).equals(secondanimal));
    }

    @Test
    public void find_returnsAnimalWithSameid_secondAnimal() {
        Animals firstanimal = new Animals("Hyena", "okay", "young", 1);
        firstanimal.save();
        Animals secondanimal = new Animals("Hyena", "okay", "young", 1);
        secondanimal.save();
        assertEquals(Animals.find(secondanimal.getAnimalId()), secondanimal);

    }

    @Test
    public void getAnimal_retrievesAllAnimalsFromDatabase_animalList() {
        Animals testanimal = new Animals("Hyena", "okay", "young", 1);
        testanimal.save();
        Animals firstanimal = new Animals("monkey", "nice", "adult, 2", testanimal.getAnimalId());
        firstanimal.save();
        Animals secondanimal = new Animals("monkey", "nice", "adult, 2", testanimal.getAnimalId());
        secondanimal.save();
        Animals[] animals = new Animals[]{firstanimal, secondanimal};
        assertTrue(testanimal.getanimal().containsAll(Arrays.asList(animals)));

    }
    }
