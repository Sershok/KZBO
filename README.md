## I created 
a program thanks to which you can enter the application and check which users are already registered or can be registered.

## Сontrollers
 AuthController

login to the application by entering personal data (data validation is also performed here)

 MainController

main page where you can look at existing users

 SingUpController

possibility to register a user

## Database
ConnectingDB

connection to the database through the configuration file

UserRepository

interaction with the database and the user model

## Exception and Validation

ValidationException

own class for exception handling

Fields Validation

validation of all incoming information takes place in this class