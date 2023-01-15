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

    protected void consumeData(int seconds) {
        double secondsAsMinute = (double) seconds / 60;
        if (remainingMinutes >= secondsAsMinute) {
            remainingMinutes -= secondsAsMinute;
            voiceEventsTime = voiceEventsTime + seconds;
        } else {
            double feeForSecond = minuteCallFee / 60;
            creditBalance = creditBalance - (feeForSecond * seconds);
            voiceEventsTime = voiceEventsTime + seconds;
        }

    }

    @Override
    protected int availableCallSeconds(int seconds) {
        double secondsAsMinute = (double) seconds / 60;
        double feeForSecond = minuteCallFee / 60;
        int remainingMinutesInSeconds = (int) (remainingMinutes * 60);
        if (remainingMinutesInSeconds >= seconds) {
            return seconds;
        }
        if (remainingMinutesInSeconds > 0) {
            consumeData(remainingMinutesInSeconds);
        }
        double nonConsumedSecondsFromData = seconds - remainingMinutesInSeconds;
        if (creditBalance >= (feeForSecond * nonConsumedSecondsFromData)) {
            return (int) (nonConsumedSecondsFromData + remainingMinutesInSeconds);
        }
        return 0;
    }

    @Override
    protected boolean checkSmsAvailability() {
        if (remainingSms >= 1) {
            remainingSms--;
            smsAmount++;
            return true;
        }
        return super.checkSmsAvailability();
    }

    @Override
    protected boolean checkMmsAvailability() {
        if (remainingMms >= 1) {
            remainingMms--;
            mmsAmount++;
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