package models.artefact;

import java.util.Random;

public enum Artefact {
    Weapon,
    Armor,
    Helm;

    public static Artefact getRandomArtefact() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
