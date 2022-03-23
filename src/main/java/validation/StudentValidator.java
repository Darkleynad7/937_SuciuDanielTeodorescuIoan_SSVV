package validation;

import domain.Student;

public class StudentValidator implements Validator<Student> {

    /**
     * Valideaza un student
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
    public void validate(Student entity) throws ValidationException {
        if(entity.getID() == null){
            throw new ValidationException("Id null!");
        }
        if(entity.getID().equals("")){
            throw new ValidationException("Id empty!");
        }
        if(entity.getNume() == null){
            throw new ValidationException("Nume null!");
        }
        if(entity.getNume().equals("")){
            throw new ValidationException("Nume empty!");
        }
        if(entity.getGrupa() < 0) {
            throw new ValidationException("Grupa negativa!");
        }
        if(entity.getEmail() == null){
            throw new ValidationException("Email null!");
        }
        if(entity.getEmail().equals("")){
            throw new ValidationException("Email empty!");
        }
    }
}
