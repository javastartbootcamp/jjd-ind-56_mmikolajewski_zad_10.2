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
    protected boolean checkCallAvailability(int seconds) {
        setVoiceEventsTime(getVoiceEventsTime() + seconds);
        return true;
    }

    @Override
    protected boolean checkSmsAvailability() {
        setSmsAmount(getSmsAmount() + 1);
        return true;
    }

    @Override
    protected boolean checkMmsAvailability() {
        setMmsAmount(getMmsAmount() + 1);
        return true;
    }

    @Override
    public void printAccountState() {
        super.printAccountState();
    }
}
