package pl.javastart.task.services;

abstract class Contract {
    protected int smsAmount;
    protected int mmsAmount;
    protected int callInSeconds;
    protected double creditBalance;

    public int getSmsAmount() {
        return smsAmount;
    }

    protected void setSmsAmount(int smsAmount) {
        this.smsAmount = smsAmount;
    }

    public int getMmsAmount() {
        return mmsAmount;
    }

    protected void setMmsAmount(int mmsAmount) {
        this.mmsAmount = mmsAmount;
    }

    public int getCallInSeconds() {
        return callInSeconds;
    }

    protected void setCallInSeconds(int callInSeconds) {
        this.callInSeconds = callInSeconds;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    protected void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    protected abstract boolean sendSms();

    protected abstract int availableCallSeconds(int seconds);

    protected abstract boolean sendMms();

    public void printAccountState() {
        System.out.println("=== STAN KONTA ===");
        System.out.println("Wysłanych SMSów:" + smsAmount + "\n"
                + "Wysłanych MMSów:" + mmsAmount + "\n"
                + "Liczba sekund rozmowy: " + callInSeconds + "\n"
                + "Na koncie zostało: " + creditBalance);
    }
}