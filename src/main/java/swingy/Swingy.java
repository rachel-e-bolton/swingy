package swingy;

import models.character.villains.VillainClass;
import models.character.villains.VillainNames;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Set;
import java.util.logging.Level;

public class Swingy {
    public static void main(String[] args) throws Exception {

        //Don't log to the stdout
        //java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        //Setting database connection
        //Class.forName("org.sqlite.JDBC");
        //String url = "jdbc:sqlite:swingy.db";
        //try {
        //    Connection connection = DriverManager.getConnection(url);
        // } catch (Exception e) {
        //    System.out.println(e.getMessage());
        //}

        //Validator instantiation
        //Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

//        Test test = new Test();

        //Error checking on specific class based on class level annotation
//        Set<ConstraintViolation<Test>> errors = validator.validate(test);
//        System.out.println(errors.size());
    }
}
