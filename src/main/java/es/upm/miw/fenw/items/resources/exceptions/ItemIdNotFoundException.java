package es.upm.miw.fenw.items.resources.exceptions;

public class ItemIdNotFoundException extends Exception {
    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Id de item no encontrado";

    public ItemIdNotFoundException() {
        this("");
    }

    public ItemIdNotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
