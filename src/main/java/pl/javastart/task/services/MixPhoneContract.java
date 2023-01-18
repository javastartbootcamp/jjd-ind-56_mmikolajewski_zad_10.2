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
    protected int availableCallSeconds(int seconds) {
        double remainingMinutesAsSeconds = (int) (remainingMinutes * 60);
        double allowanceSeconds = 0;
        if (remainingMinutesAsSeconds >= seconds) { // pobiera wszystko z pakietu minut
            callInSeconds = callInSeconds + seconds;
            allowanceSeconds = seconds;
            remainingMinutes -= allowanceSeconds / 60;
            return (int) allowanceSeconds;
        } else if (remainingMinutesAsSeconds > 0 && remainingMinutesAsSeconds < seconds) { // pobiera reszte z pakietu  minut
            remainingMinutes = 0;
            callInSeconds = (int) (callInSeconds + remainingMinutesAsSeconds);
            allowanceSeconds += remainingMinutesAsSeconds;
        }
        double feeForSecond = minuteCallFee / 60;
        int secondsHasToBePayedFromCredit = (int) (seconds - remainingMinutesAsSeconds);
        double feeForSecondsFromCredit = secondsHasToBePayedFromCredit * feeForSecond;

        if (creditBalance >= feeForSecondsFromCredit) { // wystarczająca ilość creditu
            creditBalance = creditBalance - feeForSecondsFromCredit;
            callInSeconds = callInSeconds + secondsHasToBePayedFromCredit;
            allowanceSeconds += secondsHasToBePayedFromCredit;
            return (int) allowanceSeconds;
        }
        int creditRating = (int) (creditBalance / feeForSecond);
        if (creditBalance > 0) { // dostępna tylko część creditu
            creditBalance -= creditRating * feeForSecond;
            callInSeconds = (callInSeconds + creditRating);
            allowanceSeconds += creditRating;
        }
        return (int) allowanceSeconds;
    }

    @Override
    protected boolean sendSms() {
        if (remainingSms >= 1) {
            remainingSms--;
            smsAmount++;
            return true;
        }
        return super.sendSms();
    }

    @Override
    protected boolean sendMms() {
        if (remainingMms >= 1) {
            remainingMms--;
            mmsAmount++;
            return true;
        } else if (getCreditBalance() >= getMmsFee()) {
            this.sendMms();
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