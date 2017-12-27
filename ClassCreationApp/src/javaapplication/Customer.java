package javaapplication;
import java.util.Random;

public class Customer {
    
    //описываем поля класса Customer
    
    private String name; //Имя 
    private String surname; //Фамилия 
    private String patronymic; //Отчество
    private String address; //Адрес
    private String phoneNumber; //Телефон
    private String cardNumber; //Номер кредитной карты
    private String accountNumber; //Номер банковского счёта
    
    //описываем методы Set(...) и Get(...) для всех полей класса
    
    public void SetName(String name)
    {
        if(name!="") this.name = name;
    }
    public String GetName()
    {
        return name;
    }
    
    public void SetSurname(String surname)
    {
        if(surname!="") this.surname = surname;
    }
    public String GetSurname()
    {
        return surname;
    }
    
    public void SetPatronymic(String patronymic)
    {
        if(patronymic!="") this.patronymic = patronymic;
    }
    public String GetPatronymic()
    {
        return patronymic;
    }
    
    public void SetAddress(String address) 
    {
       if(address!="") this.address = address;  
    }
    public String getAddress()
    {
        return address;
    }
    
    public void SetPhoneNumber(String phoneNumber) 
    {
       if(phoneNumber == "") return;
       if(phoneNumber.length()!= 11) return; //проверяем,чтобы длина номера телефона была в 11 символов
       if(!IsNumber(phoneNumber)) return; //проверяем,чтобы каждый символ в телефонном номере был цифрой
       this.phoneNumber = phoneNumber;
    }
    public String GetPhoneNumber()
    {
        return phoneNumber;
    }
    
    public void SetCardNumber(String cardNumber) 
    {
       if(cardNumber == "") return;  
       if(cardNumber.length()!= 16) return; //проверяем,чтобы длина номера банковской карты была в 16 символов
       if(!IsNumber(cardNumber)) return;  //проверяем,чтобы каждый символ в номере кредитной карты был цифрой
       this.cardNumber = cardNumber;
    }
    public String GetCardNumber()
    {
        return cardNumber;
    }
    
    public void SetAccountNumber(String accountNumber) 
    {
       if(accountNumber == "") return;
       if(!IsNumber(accountNumber)) return;   //проверяем,чтобы каждый символ в номере банковского счёта был цифрой
       this.accountNumber = accountNumber;
    }
    public String GetAccountNumber()
    {
        return accountNumber;
    }
    
    //Описываем метод Show() и други методы
    
    public void Show()
    {
        String info = "##########\n";
        info += "Имя: " + name + "\n" + "Фамилия: " + surname + "\n" + "Отчество: " + patronymic + "\n";
        info += "Адрес: " + address + "\n" + "Телефон: " + phoneNumber + "\n" + "Номер кредитной карты: " + cardNumber +"\n";
        info += "Номер банковского счёта: " + accountNumber + "\n" ;
        info += "##########\n";
        System.out.print(info);
    }
    
    private boolean IsNumber(String s)
    {
        for(int i=0;i<s.length();i++)
            if(!Character.isDigit(s.charAt(i))) return false;
        return true;
    }
    
    public static String GenerateRandomPhoneNumber()
    {
        Random rand = new Random();
        String result = "8";
        for(int i=0;i<10;i++) result += rand.nextInt(10);
        return result;
    }
    
    public static String GenerateRandomCardNumber()
    {
        Random rand = new Random();
        String result = "";
        for(int i=0;i<16;i++) result += rand.nextInt(10);
        return result;
    }
    
    public static String GenerateRandomAccountNumber()
    {
        Random rand = new Random();
        int length = rand.nextInt(10)+ 1;
        String result = "";
        for(int i=0;i<length;i++) result += rand.nextInt(10);
        return result;
    }
    
    public static void SortAlphabet(Customer[] arr)  // сортируем с помощью пузырьковой сортировки массив пользователей по алфавиту
    {
        int n = arr.length;
        for(int i=0;i<n;i++)
        {
            for(int j = n-1;j>i;j--)
            {
                int resultNameCompare = arr[j].GetName().compareTo(arr[j-1].GetName());
                int resultSurnameCompare = arr[j].GetSurname().compareTo(arr[j-1].GetSurname());
                int resultPatronymicCompare = arr[j].GetPatronymic().compareTo(arr[j-1].GetPatronymic());
                
                if(resultNameCompare == 0 ) //случай, если имена одинаковые
                {
                    if(resultSurnameCompare == 0) //случай, если фамилии одинаковые
                    {
                        if(resultPatronymicCompare < 0) //случай,если отчество j клиента меньше,чем отчество j-1 -ого клиента
                        {
                            Customer temp = arr[j];
                            arr[j] = arr[j-1];
                            arr[j-1] = temp;
                        }
                    }
                    else if(resultSurnameCompare < 0) //случай,если фамилия j клиента меньше,чем фамилия j-1 -ого клиента
                    {
                        Customer temp = arr[j];
                        arr[j] = arr[j-1];
                        arr[j-1] = temp;
                    }
                }
                else if(resultNameCompare < 0) //случай,если имя j клиента меньше,чем имя j-1 -ого клиента
                {
                    Customer temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
 
            }
        }
  
    }
}
