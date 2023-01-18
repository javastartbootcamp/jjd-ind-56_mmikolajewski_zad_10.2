package pl.javastart.task.services;

public class CardPhoneContract extends Contract {
    double smsFee;
    double mmsFee;
    double minuteCallFee;

    public CardPhoneContract(double balance, double smsFee, double mmsFee, double minuteCallFee) {
        super.creditBalance = balance;
        this.smsFee = smsFee;
        this.mmsFee = mmsFee;
        this.minuteCallFee = minuteCallFee;
    }

    @Override
    boolean sendSms() {
        if (creditBalance >= smsFee) {
            creditBalance = creditBalance - smsFee;
            smsAmount++;
            return true;
        }
        return false;
    }

    @Override
    int availableCallSeconds(int seconds) {
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
    boolean sendMms() {
        if (creditBalance >= mmsFee) {
            creditBalance = creditBalance - mmsFee;
            mmsAmount++;
            return true;
        }
        return false;
    }

    @Override
    void printAccountState() {
        super.printAccountState();
    }

    @Override
    void printTableOfFees() {
        System.out.println("Tabela opłat."
                + " koszt wysłania: \n"
                + " 1 SMS " + smsFee + "zł" +  "\n"
                + " 1 MMS " + mmsFee + "zł" + "\n"
                + " 1 minuta połączenia " + minuteCallFee + "zł" + "\n");
    }
}
