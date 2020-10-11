package models.character.villains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class VillainNames {
    static final List<String> _villainNames = new ArrayList<>(Arrays.asList(
            "Sami Silva AKA Awful-Monkey",
            "Sebastian Veda AKA Doctor Awful",
            "Sonny Serna AKA Mean-Man",
            "Scott Saavedra AKA Agent Bad",
            "William Davis AKA Captain Proud",
            "Ivanka",
            "Mohammed Martin",
            "Doctor Forgotten",
            "Phil",
            "Uncle Joe",
            "Dance Monkey"
    ));

    public static String getRandomVillainName() {
        Random random = new Random();
        int randomName = random.nextInt(_villainNames.size());
        return _villainNames.get(randomName);
    }
}
