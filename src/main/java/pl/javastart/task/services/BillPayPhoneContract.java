package pl.javastart.task.services;

public class BillPayPhoneContract extends Contract {
    private int billPayFee;

    public BillPayPhoneContract(int billPayFee) {
        this.billPayFee = billPayFee;
    }

    public int getBillPayFee() {
        return billPayFee;
    }

    protected void setBillPayFee(int billPayFee) {
        this.billPayFee = billPayFee;
    }

    @Override
    protected int availableCallSeconds(int seconds) {
        callInSeconds = callInSeconds + seconds;
        return seconds;
    }

    @Override
    protected boolean sendSms() {
        smsAmount++;
        return true;
    }

    @Override
    protected boolean sendMms() {
        mmsAmount++;
        return true;
    }
}
