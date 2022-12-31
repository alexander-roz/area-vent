import java.util.Random;

public interface Message {

    String a1 = "Расчет значений невозможен.";
    String a2 = "Здесь явно где-то ошибка.";
    String a3 = "У меня нет таких формул.";
    String a4 = "Вы используете незарегистрированную версию ПО.";
    String a5 = "FATAL ERROR";
    String a6 = "ERROR. Компьютер будет перезапущен через 10 сек.";
    String a7 = "Вы ввели некорректные данные.";
    String a8 = "Введенные значения не поддаются расчету.";
    String a9 = "Ничего не выйдет.";
    String a10 = "Введены не некорректные значения";
    String a11 = "Пока не ясно, спросите позже.";
    String a12 = "ОШИБКА. Обратитесь к производителю.";
    String a13 = "ОШИБКА. Дальнейший расчет невозможен.";
    String a14 = "Версия более не является бесплатной.";
    String a15 = "Проверьте введенные значения.";
    String a16 = "Я умею работать только с числами.";
    String a17 = "Странные значения...";
    String a18 = "Вы видели элементы с такими размерами?";
    String a19 = "Даже не знаю, как это потом монтировать.";
    String a20 = "Сомневаюсь что смогу это посчитать.";
    String instruction = "\nНеобходимо вводить только числа, знаки '.' или ','";

    static String getMessage(){
        String [] list = new String[]{
                a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20
        };
        Random random = new Random();
        int index = random.nextInt(list.length);
        return list[index + 1] + instruction;
    }
}
