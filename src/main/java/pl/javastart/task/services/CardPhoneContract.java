package pl.javastart.task.services;

public class CardPhoneContract extends Contract {
    protected double smsFee;
    protected double mmsFee;
    protected double minuteCallFee;

    public CardPhoneContract(double balance, double smsFee, double mmsFee, double minuteCallFee) {
        super.setCreditBalance(balance);
        this.smsFee = smsFee;
        this.mmsFee = mmsFee;
        this.minuteCallFee = minuteCallFee;
    }

    public double getSmsFee() {
        return smsFee;
    }

    protected void setSmsFee(double smsFee) {
        this.smsFee = smsFee;
    }

    public double getMmsFee() {
        return mmsFee;
    }

    protected void setMmsFee(double mmsFee) {
        this.mmsFee = mmsFee;
    }

    public double getMinuteCallFee() {
        return minuteCallFee;
    }

    protected void setMinuteCallFee(double minuteCallFee) {
        this.minuteCallFee = minuteCallFee;
    }

    @Override
    protected boolean checkSmsAvailability() {
        if (getCreditBalance() >= smsFee) {
            setCreditBalance(getCreditBalance() - smsFee);
            setSmsAmount(getSmsAmount() + 1);
            return true;
        }
        return false;
    }

    @Override
    protected void consumeData(int seconds) {
        double feeForSecond = minuteCallFee / 60;
        creditBalance = creditBalance - (feeForSecond * seconds);
        voiceEventsTime = voiceEventsTime + seconds;
    }

    @Override
    protected int availableCallSeconds(int seconds) {
        double feeForSecond = minuteCallFee / 60;
        if (creditBalance >= (feeForSecond * seconds)) {
            return seconds;
        }
        if (creditBalance <= (feeForSecond * seconds)) {
            return (int) (creditBalance / feeForSecond);
        }
        return 0;
    }

    @Override
    protected boolean checkMmsAvailability() {
        if (getCreditBalance() >= mmsFee) {
            setCreditBalance(getCreditBalance() - mmsFee);
            setMmsAmount(getMmsAmount() + 1);
            return true;
        }
        return false;
    }

    @Override
    public void printAccountState() {
        super.printAccountState();
    }
}
