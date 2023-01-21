package pl.javastart.task.services;

abstract class Contract {
    int smsAmount;
    int mmsAmount;
    int callInSeconds;
    double creditBalance;

    abstract boolean sendSms();

    abstract boolean sendMms();

    abstract int calculatingAvailableSeconds(int seconds);

    void printAccountState() {
        System.out.println("=== STAN KONTA ===");
        System.out.println("Wysłanych SMSów:" + smsAmount + "\n"
                + "Wysłanych MMSów:" + mmsAmount + "\n"
                + "Liczba sekund rozmowy: " + callInSeconds + "\n"
                + "Na koncie zostało: " + creditBalance + "zł");
    }

    void printTableOfFees() {
    }
}