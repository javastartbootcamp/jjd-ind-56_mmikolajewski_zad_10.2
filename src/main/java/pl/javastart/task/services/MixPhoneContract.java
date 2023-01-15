package pl.javastart.task.services;

public class MixPhoneContract extends CardPhoneContract {
    private int remainingSms;
    private int remainingMms;
    private double remainingMinutes;

    public MixPhoneContract(double balance, double smsFee, double mmsFee, double minuteCallFee,
                            int smsAvailable, int mmsAvailable, double minutesAvailable) {
        super(balance, smsFee, mmsFee, minuteCallFee);
        this.remainingSms = smsAvailable;
        this.remainingMms = mmsAvailable;
        this.remainingMinutes = minutesAvailable;
    }

    public int getRemainingSms() {
        return remainingSms;
    }

    protected void setRemainingSms(int remainingSms) {
        this.remainingSms = remainingSms;
    }

    public int getRemainingMms() {
        return remainingMms;
    }

    protected void setRemainingMms(int remainingMms) {
        this.remainingMms = remainingMms;
    }

    public double getRemainingMinutes() {
        return remainingMinutes;
    }

    protected void setRemainingMinutes(double remainingMinutes) {
        this.remainingMinutes = remainingMinutes;
    }

    @Override
    protected boolean checkCallAvailability(int seconds) {
        double secondsInMinute = (double) seconds / 60;
        double feeForSecond = getMinuteCallFee() / 60;
        if (remainingMinutes >= secondsInMinute) {
            remainingMinutes -= secondsInMinute;
            setVoiceEventsTime(getVoiceEventsTime() + seconds);
            return true;
        } else if (getCreditBalance() >= getMinuteCallFee()) {
            setCreditBalance(getCreditBalance() - (feeForSecond * seconds));
            setVoiceEventsTime(getVoiceEventsTime() + seconds);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean checkSmsAvailability() {
        if (remainingSms >= 1) {
            remainingSms--;
            setSmsAmount(getSmsAmount() + 1);
            return true;
        } else if (getCreditBalance() >= getSmsFee()) {
            checkSmsAvailability();
        }
        return false;

    }

    @Override
    protected boolean checkMmsAvailability() {
        if (remainingMms >= 1) {
            remainingMms--;
            setMmsAmount(getMmsAmount() + 1);
            return true;
        } else if (getCreditBalance() >= getMmsFee()) {
            this.checkMmsAvailability();
        }
        return false;
    }

    @Override
    public void printAccountState() {
        super.printAccountState();
        System.out.println("Pozostałe SMSy: " + remainingMms + "\n"
                + "Pozostałe MMSy: " + remainingMms);
        System.out.printf("Pozostałe minuty: %.1f \n", remainingMinutes);
    }
}
