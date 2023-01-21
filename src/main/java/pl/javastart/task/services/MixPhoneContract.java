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
    int calculatingAvailableSeconds(int expectedCallLengthInSeconds) {
        double remainingSeconds = (int) (remainingMinutes * 60);
        double availableSecondsToCall = 0;
        if (remainingSeconds >= expectedCallLengthInSeconds) { // pobiera wszystko z darmowych minut
            callInSeconds = callInSeconds + expectedCallLengthInSeconds;
            availableSecondsToCall = expectedCallLengthInSeconds;
            remainingMinutes -= availableSecondsToCall / 60;
            return (int) availableSecondsToCall;
        } else if (remainingSeconds > 0 && remainingSeconds < expectedCallLengthInSeconds) { // wybiera reszte z darmowych minut
            remainingMinutes = 0;
            callInSeconds = (int) (callInSeconds + remainingSeconds);
            availableSecondsToCall += remainingSeconds;
        }
        int secondsToPay = (int) (expectedCallLengthInSeconds - remainingSeconds);
        availableSecondsToCall += super.calculatingAvailableSeconds(secondsToPay);
        return (int) availableSecondsToCall;
    }

    @Override
    boolean sendSms() {
        if (remainingSms >= 1) {
            remainingSms--;
            smsAmount++;
            return true;
        }
        return super.sendSms();
    }

    @Override
    boolean sendMms() {
        if (remainingMms >= 1) {
            remainingMms--;
            mmsAmount++;
            return true;
        }
        return super.sendMms();
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