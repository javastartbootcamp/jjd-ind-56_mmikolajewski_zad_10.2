package pl.javastart.task.services;

public class Phone {
    private Contract contract;

    public Phone(Contract contract) {
        this.contract = contract;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public void sendSms() {
        if (contract.checkSmsAvailability()) {
            System.out.println("SMS wysłany");
        } else {
            System.out.println("nie udało się wsyłać SMS'a");
        }
    }

    public void call(int seconds) {
        if (contract.checkCallAvailability(seconds)) {
            System.out.println("Połączenie trwało " + seconds + " sekund");
        } else {
            System.out.println("Nie mona wykonać połączenia, brak środków na koncie");
        }
    }

    public void sendMms() {
        if (contract.checkMmsAvailability()) {
            System.out.println("MMS wysłany");
        } else {
            System.out.println("nie udało się wsyłać MMS'a");
        }

    }

    public void printAccountState() {
        contract.printAccountState();
    }
}
