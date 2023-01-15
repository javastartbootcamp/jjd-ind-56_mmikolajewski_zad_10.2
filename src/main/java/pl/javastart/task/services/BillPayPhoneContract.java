package pl.javastart.task.services;

public class BillPayPhoneContract extends Contract {
    private int billPayFee;

    public BillPayPhoneContract(int billPayFee) {
        super.setCreditBalance(billPayFee);
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
        voiceEventsTime = voiceEventsTime + seconds;
        return seconds;
    }

    @Override
    protected boolean checkSmsAvailability() {
        smsAmount++;
        return true;
    }

    @Override
    protected boolean checkMmsAvailability() {
        mmsAmount++;
        return true;
    }

    @Override
    protected void consumeData(int soconds) {

    }
}
