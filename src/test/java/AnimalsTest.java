import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class AnimalsTest {

    @Test
    public void animal_instantiatesCorrectly_false() {
        Animals testAnimal = new Animals("Hyena","okay", "young", 1);
        assertEquals(true, testAnimal instanceof Animals);
    }
    @Test
    public void Animals_instantiatesWithname_String() {
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
        Animals testanimal = new Animals("Hyena", "okay", "young", 1);
        Animals testanimal = new Animals("Hyena", "okay", "young", 1);
        assertTrue(testanimal.equals(testanimal));
    }
    }
