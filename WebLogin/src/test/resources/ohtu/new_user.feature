Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation successful with correct username and password
        Given new user is selected
        When  correct username "eero" and password "eerokuurne2" and password confirmation "eerokuurne2" are given
        Then  user is registered

    Scenario: creation fails with too short username and valid passord
        Given new user is selected
        When  incorrect username "ee" and password "eerokuurne2" and password confirmation "eerokuurne2" are given
        Then  user is not created and error "username should have at least 3 characters" is reported  

    Scenario: creation fails with correct username and too short password
        Given new user is selected
        When  correct username "eero" and incorrect password "eroku1" and password confirmation "eroku1" are given
        Then  user is not created and error "password should have at least 8 characters" is reported

    Scenario: creation fails with correct username and password consisting of letters
        Given new user is selected
        When  correct username "eero" and incorrect password "eerokuurne" and password confirmation "eerokuurne" are given
        Then  user is not created and error "password can not contain only letters" is reported 

    Scenario: creation fails with already taken username and valid pasword
        Given new user is selected
        When  taken username "jukka" and password "eerokuurne2" and password confirmation "eerokuurne2" are given
        Then  user is not created and error "username is already taken" is reported 

    Scenario: creation fails when password and password confirmation do not match
        Given new user is selected
        When  correct username "eero" and password "eerokuurne2" and different password confirmation "eerokuurne3" are given
        Then  user is not created and error "password and password confirmation do not match" is reported

    Scenario: user can login with succesfully generated account
        Given user with username "liisa" with password "salainen1" is succesfully created
        And   login is selected
        When  correct username "liisa" and password "salainen1" are given
        Then  user is logged in

    Scenario: user can not login with account that is not succesfully created
        Given user with username "aa" and password "bad" is unsuccesfully created
        And   login is selected
        When  incorrect username "aa" and incorrect password "bad" are given
        Then  user is not logged in and error message is given
