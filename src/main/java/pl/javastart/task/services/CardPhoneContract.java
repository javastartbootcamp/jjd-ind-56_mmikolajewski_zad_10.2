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
    protected boolean sendSms() {
        if (creditBalance >= smsFee) {
            creditBalance = creditBalance - smsFee;
            smsAmount++;
            return true;
        }
        return false;
    }

    @Override
    protected int availableCallSeconds(int seconds) {
        double feeForSecond = minuteCallFee / 60;
        if (creditBalance >= (feeForSecond * seconds)) {
            creditBalance = creditBalance - (feeForSecond * seconds);
            callInSeconds = callInSeconds + seconds;
            return seconds;
        }
        if (creditBalance < (feeForSecond * seconds) && creditBalance > 0) {
            int paidSeconds = (int) (creditBalance / feeForSecond);
            creditBalance = creditBalance - (paidSeconds * feeForSecond);
            callInSeconds = (callInSeconds + paidSeconds);
            return paidSeconds;
        }
        return 0;
    }

    @Override
    protected boolean sendMms() {
        if (creditBalance >= mmsFee) {
            creditBalance = creditBalance - mmsFee;
            mmsAmount++;
            return true;
        }
        return false;
    }

    @Override
    public void printAccountState() {
        super.printAccountState();
    }
}
