package app.validation;

public interface Validator<E> {
    void validate(E entity) throws ValidationException;
}