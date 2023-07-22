package vehicles.engines;

//TODO: add javadoc
class UnsupportedEngineTypeException extends Exception {
    protected UnsupportedEngineTypeException() {
        super("Unsupported engine type!");
    }
}