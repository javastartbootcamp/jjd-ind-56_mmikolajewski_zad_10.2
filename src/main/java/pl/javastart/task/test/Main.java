package pl.javastart.task.test;

import pl.javastart.task.services.BillPayPhoneContract;
import pl.javastart.task.services.CardPhoneContract;
import pl.javastart.task.services.MixPhoneContract;
import pl.javastart.task.services.Phone;

public class Main {

    public static void main(String[] args) {
        Phone phone1 = new Phone(new CardPhoneContract(0.20, 0.1, 0.2, 0.1));
        Phone phone2 = new Phone(new BillPayPhoneContract(20));
        Phone phone3 = new Phone(new MixPhoneContract(0.40, 0.1, 0.2, 0.40, 3, 3, 1));
        phone1.printAccountState();
        phone1.call(130);
        phone1.sendSms();
        phone1.sendMms();
        phone1.printAccountState();
        System.out.println("<<<<<<<<<<<<<<<<<");
        phone3.printAccountState();
        phone3.sendSms();
        phone3.sendSms();
        phone3.sendSms();
        phone3.sendSms();

        phone3.printAccountState();
        System.out.println("<<<<<<<<<<<<<<<<<");
        phone3.call(119); // przy 61 sekundzie przechodzi z darmowych minut na pobieranie opłaty.
        phone3.printAccountState();
        phone3.printTableOfFees();
        phone2.printTableOfFees();
        phone1.printTableOfFees();

    }
}
