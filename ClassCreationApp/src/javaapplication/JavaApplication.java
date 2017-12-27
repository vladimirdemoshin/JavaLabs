package javaapplication;

public class JavaApplication {

    public static void main(String[] args) {
        
        Customer[] customers = new Customer[8]; //создаем массив для 8 покупателей
        
        for(int i=0;i<8;i++)
            customers[i] = new Customer(); //выделяем для каждого объекта память
        
        //инициализируем поля класса
        
        customers[0].SetName("Андрей");  customers[0].SetSurname("Петров");   customers[0].SetPatronymic("Иванович");
        customers[1].SetName("Петр");    customers[1].SetSurname("Иванов");   customers[1].SetPatronymic("Павлович");
        customers[2].SetName("Андрей");  customers[2].SetSurname("Алексеев"); customers[2].SetPatronymic("Игоревич");
        customers[3].SetName("Игорь");   customers[3].SetSurname("Андропов"); customers[3].SetPatronymic("Кириллович");
        customers[4].SetName("Виктор");  customers[4].SetSurname("Кашин");    customers[4].SetPatronymic("Антонович");
        customers[5].SetName("Марина");  customers[5].SetSurname("Сладкова"); customers[5].SetPatronymic("Геннадьевна");
        customers[6].SetName("Любовь");  customers[6].SetSurname("Луговая");  customers[6].SetPatronymic("Павловна");
        customers[7].SetName("Галина");  customers[7].SetSurname("Андреева"); customers[7].SetPatronymic("Анатольевна");
        
        customers[0].SetAddress("Арбузова-13-124");
        customers[1].SetAddress("Калинина-7-15");
        customers[2].SetAddress("Ленина-23-15");
        customers[3].SetAddress("Красная-9-143");
        customers[4].SetAddress("Центральная-131-12");
        customers[5].SetAddress("Тютчева-10-10");
        customers[6].SetAddress("Пушкина-93-85");
        customers[7].SetAddress("Театральная-54-13");
        
        for(int i=0;i<8;i++)
        {
           customers[i].SetPhoneNumber(Customer.GenerateRandomPhoneNumber());
           customers[i].SetCardNumber(Customer.GenerateRandomCardNumber());
           customers[i].SetAccountNumber(Customer.GenerateRandomAccountNumber()); 
        }
        
       //Сортируем пользователей по именам, фамилиям и отчествам так, чтобы они располагались в алфавитном порядке, и печатаем информацию о них
       
       Customer.SortAlphabet(customers);
        
       System.out.println("Информация о пользователях в алфавитном порядке:");
       for(int i=0;i<8;i++) 
       {
           customers[i].Show();
       }
        
       //задаем интервалы и печатаем тех покупателей, у которых номер кредитной карточки находится в заданном интервале
       
       String leftBound = "4444444444444444";
       String rightBound = "9999999999999999";
       
       System.out.println("Информация о пользователях, у которых номер кредитной карточки находится в заданном интервале:");
       for(int i=0;i<8;i++)
       {
           if(customers[i].GetCardNumber().compareTo(leftBound) >= 0 && customers[i].GetCardNumber().compareTo(rightBound) <= 0)
               customers[i].Show();
           
       }

       System.out.println();
    }  
}
