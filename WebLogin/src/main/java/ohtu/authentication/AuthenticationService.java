package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();
        
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }

        if (usernameIsTooShort(username)) {
            status.addError("username should have at least 3 characters");
        }
        
        if (usernameContainsInvalidCharacters(username)) {
            status.addError("username should have only characters between a-z");
        }
        
        if (passwordIsTooShort(password)) {
            status.addError("password should have at least 8 characters");
        }
        
        if (!passwordContainsSpecialCharacter(password)) {
            status.addError("password can not contain only letters");
        }
        
        if (!passwordConfirmationMatches(password, passwordConfirmation)) {
            status.addError("password and password confirmation do not match");
        }

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }
        
        return status;
    }

    private boolean usernameIsTooShort(String username) {
        if (username.length() < 3) {
            return true;
        }
        return false;
    }
    
    private boolean usernameContainsInvalidCharacters(String username) {
        for (int i = 0; i < username.length(); i++) {
            char ch = username.charAt(i); 
            if (!(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z')) {
                return true;
            }
        }
        return false;
    }
    
    private boolean passwordIsTooShort(String password) {
        if (password.length() < 8) {
            return true;
        }
        return false;
    }
    
    private boolean passwordContainsSpecialCharacter(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (!Character.isLetter(password.charAt(i))) {
                break;
            }
            if (i == password.length() - 1) {
                return false;
            }
        }
        return true;
    }
    
    private boolean passwordConfirmationMatches(String password, String passwordConfirmation) {
        if (password.equals(passwordConfirmation)) {
            return true;
        }
        return false;
    }
}
