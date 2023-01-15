package pl.javastart.task.services;

public class CardPhoneContract extends Contract {
    private double smsFee;
    private double mmsFee;
    private double minuteCallFee;

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
    protected boolean checkCallAvailability(int seconds) {
        double feeForSecond = minuteCallFee / 60;
        if (getCreditBalance() >= feeForSecond) {
            setCreditBalance(getCreditBalance() - (feeForSecond * seconds));
            setVoiceEventsTime(getVoiceEventsTime() + seconds);
            return true;
        }
        return false;
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
