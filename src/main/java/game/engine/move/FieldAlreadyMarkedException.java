package game.engine.move;

class FieldAlreadyMarkedException extends Throwable {
    FieldAlreadyMarkedException(String s) {
        super(s);
    }
}
