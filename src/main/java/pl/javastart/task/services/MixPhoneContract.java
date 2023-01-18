package pl.javastart.task.services;

public class MixPhoneContract extends CardPhoneContract {
    int remainingSms;
    int remainingMms;
    double remainingMinutes;

    public MixPhoneContract(double balance, double smsFee, double mmsFee, double minuteCallFee,
                            int smsAvailable, int mmsAvailable, double minutesAvailable) {
        super(balance, smsFee, mmsFee, minuteCallFee);
        this.remainingSms = smsAvailable;
        this.remainingMms = mmsAvailable;
        this.remainingMinutes = minutesAvailable;
    }

    @Override
    int availableCallSeconds(int seconds) {
        double remainingMinutesAsSeconds = (int) (remainingMinutes * 60);
        double allowanceSeconds = 0;
        if (remainingMinutesAsSeconds >= seconds) { // pobiera wszystko z darmowych minut
            callInSeconds = callInSeconds + seconds;
            allowanceSeconds = seconds;
            remainingMinutes -= allowanceSeconds / 60;
            return (int) allowanceSeconds;
        } else if (remainingMinutesAsSeconds > 0 && remainingMinutesAsSeconds < seconds) { // wybiera reszte z darmowych minut
            remainingMinutes = 0;
            callInSeconds = (int) (callInSeconds + remainingMinutesAsSeconds);
            allowanceSeconds += remainingMinutesAsSeconds;
        }
        int secondsHasToBePayedFromCredit = (int) (seconds - remainingMinutesAsSeconds);
        allowanceSeconds += super.availableCallSeconds(secondsHasToBePayedFromCredit);
        return (int) allowanceSeconds;
    }

    @Override
    boolean sendSms() {
        if (remainingSms >= 1) {
            remainingSms--;
            smsAmount++;
            return true;
        }
        if (creditBalance >= mmsFee) {
            creditBalance = creditBalance - mmsFee;
            mmsAmount++;
            return true;
        }
        return false;
    }

    @Override
    boolean sendMms() {
        if (remainingMms >= 1) {
            remainingMms--;
            mmsAmount++;
            return true;
        } else if (creditBalance >= mmsFee) {
            creditBalance = creditBalance - mmsFee;
            mmsAmount++;
            return true;
        }
        return false;
    }

    @Override
    void printAccountState() {
        super.printAccountState();
        System.out.println("Pozostałe SMSy: " + remainingSms + "\n"
                + "Pozostałe MMSy: " + remainingMms);
        System.out.printf("Pozostałe minuty: %.1f \n", remainingMinutes);
    }

    @Override
    void printTableOfFees() {
        super.printTableOfFees();
    }
}