package pl.javastart.task.test;

import pl.javastart.task.services.BillPayPhoneContract;
import pl.javastart.task.services.CardPhoneContract;
import pl.javastart.task.services.MixPhoneContract;
import pl.javastart.task.services.Phone;

public class Main {

    public static void main(String[] args) {
        Phone phone1 = new Phone(new CardPhoneContract(20, 0.1, 0.2, 0.5));
        Phone phone2 = new Phone(new BillPayPhoneContract(20));
        Phone phone3 = new Phone(new MixPhoneContract(20, 0.1, 0.2, 0.50, 20, 20, 100));
        phone1.printAccountState();
        phone1.call(20);
        phone1.sendSms();
        phone1.sendMms();
        phone1.printAccountState();
        phone1.sendSms();
        phone1.printAccountState();
        phone1.sendSms();
        phone1.sendSms();
        phone1.call(2320);
        phone1.printAccountState();
        System.out.println("=====================================");

        phone2.printAccountState();
        phone2.sendMms();
        phone2.sendSms();
        phone2.call(120);
        phone2.printAccountState();

        System.out.println("=====================================");
        phone3.printAccountState();
        phone3.sendSms();
        phone3.sendMms();
        phone3.call(20);
        phone3.printAccountState();
        phone3.call(5980);
        phone3.printAccountState();
        phone3.call(300);
        phone3.printAccountState();
    }
}
