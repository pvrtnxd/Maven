package me.lebedamm.budgetapp.exception;

public class ValException extends RuntimeException {
    public  ValException ( String entity) {
        super("Ошибка валидации сущности: " + entity);
    }
}
