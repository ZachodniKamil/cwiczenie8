import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Zadanie1 {

    record UserForm(String email, String password, int age) {
        public UserForm {
            if (email == null || email.isBlank()) {
                throw new IllegalArgumentException("email nie moze byc pusty");
            }
            if (password == null || password.isBlank()) {
                throw new IllegalArgumentException("haslo nie moze byc puste");
            }
        }
    }

    static class UserValidator {
        private final List<Predicate<UserForm>> rules = new ArrayList<>();

        public void addRule(Predicate<UserForm> rule) {
            rules.add(rule);
        }

        public boolean isValid(UserForm form) {
            for (Predicate<UserForm> rule : rules) {
                if (!rule.test(form)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        UserValidator validator = new UserValidator();
        validator.addRule(form -> form.email().contains("@"));
        validator.addRule(form -> form.password().length() >= 8);
        validator.addRule(form -> form.age() >= 18);

        UserForm form1 = new UserForm("anna@example.com", "bezpieczne123", 20);
        UserForm form2 = new UserForm("zlyemail", "krotkie", 16);

        System.out.println("form1 poprawny? " + validator.isValid(form1));
        System.out.println("form2 poprawny? " + validator.isValid(form2));
    }
}
