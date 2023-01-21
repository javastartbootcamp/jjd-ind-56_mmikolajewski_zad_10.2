package pl.javastart.task.services;

public class Phone {
    private Contract contract;

    public Phone(Contract contract) {
        this.contract = contract;
    }

    public void sendSms() {
        if (contract.sendSms()) {
            System.out.println(">>>>> SMS wysłany");
        } else {
            System.out.println(">>>>|nie udało się wsyłać SMS'a");
        }
    }

    public void call(int expectedCallLengthInSeconds) {
        int availableSeconds = contract.calculatingAvailableSeconds(expectedCallLengthInSeconds);
        if (availableSeconds == expectedCallLengthInSeconds) {
            System.out.println("@@ --- @@ Połączenie trwało " + expectedCallLengthInSeconds + " sekund");
        } else if (availableSeconds < expectedCallLengthInSeconds && availableSeconds > 0) {
            System.out.println("@@ ||| Połączenie przerwane, brak środków na koncie, Połączenie trwało " + availableSeconds + " sekund");
        } else {
            System.out.println("--- Nie mona wykonać połączenia, brak środków na koncie");
        }
    }

    public void sendMms() {
        if (contract.sendMms()) {
            System.out.println(">>>>> MMS wysłany");
        } else {
            System.out.println(">>>>>| nie udało się wsyłać MMS'a");
        }
    }

    public void printAccountState() {
        contract.printAccountState();
    }

    public void printTableOfFees() {
        contract.printTableOfFees();
    }
}
