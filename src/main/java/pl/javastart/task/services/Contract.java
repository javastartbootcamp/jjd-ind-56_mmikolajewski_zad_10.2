package pl.javastart.task.services;

abstract class Contract {
    private int smsAmount;
    private int mmsAmount;
    private int voiceEventsTime;
    private double creditBalance;

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

    public int getVoiceEventsTime() {
        return voiceEventsTime;
    }

    protected void setVoiceEventsTime(int voiceEventsTime) {
        this.voiceEventsTime = voiceEventsTime;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    protected void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    protected abstract boolean checkSmsAvailability();

    protected abstract boolean checkCallAvailability(int seconds);

    protected abstract boolean checkMmsAvailability();

    public void printAccountState() {
        System.out.println("=== STAN KONTA ===");
        System.out.println("Wysłanych SMSów:" + smsAmount + "\n"
                + "Wysłanych MMSów:" + mmsAmount + "\n"
                + "Liczba sekund rozmowy: " + voiceEventsTime + "\n"
                + "Na koncie zostało: " + creditBalance);
    }
}