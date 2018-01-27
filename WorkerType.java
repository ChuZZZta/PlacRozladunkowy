public enum WorkerType {
    Human(1), Trolley(2);

    private int numVal;

    WorkerType(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }


}