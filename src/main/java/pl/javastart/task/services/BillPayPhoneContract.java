package pl.javastart.task.services;

public class BillPayPhoneContract extends Contract {
    int billPayFee;

    public BillPayPhoneContract(int billPayFee) {
        this.billPayFee = billPayFee;
    }

    @Override
    int availableCallSeconds(int seconds) {
        callInSeconds = callInSeconds + seconds;
        return seconds;
    }

    @Override
    boolean sendSms() {
        smsAmount++;
        return true;
    }

    @Override
    boolean sendMms() {
        mmsAmount++;
        return true;
    }

    @Override
    void printTableOfFees() {
        System.out.println("Tabela opłat, wszystkie usługi - NO LIMIT");
    }
}
