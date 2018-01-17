public enum CarType {
    Truck(3), Van(2), Regular(1);

    private int numVal;

    CarType(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }


}
