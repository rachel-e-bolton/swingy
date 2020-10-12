package models.character.villains;

import java.util.Random;

public enum VillainClass {
    NaughtyBoy,
    PsychoticPatient,
    Executioner;

    public static VillainClass getVillainRandomClass() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
