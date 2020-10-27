package views.console.artefact;
import helpers.TextColors;
import models.artefact.Artefact;

public class ArtefactView {
    public void PrintArtefact(Artefact artefact){
        System.out.println(TextColors.ANSI_YELLOW + "Manna from heaven... You have received a " + artefact + "." + TextColors.ANSI_RESET);
        System.out.println(TextColors.ANSI_BLUE + "[K]" + TextColors.ANSI_RESET + " - [K]eep");
        System.out.println(TextColors.ANSI_BLUE + "[T]" + TextColors.ANSI_RESET + " - [T]oss");
    }
}
