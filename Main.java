/* Необходимо разработать консольное приложение, которое при запуске обращается к публичному
 *api методу GET http://numbersapi.com/<number>/trivia, где <number> определяется случайным образом.
 *В ответе на данный метод будет возвращена некоторая строка (факт из жизни, вывести в консоль).
 *В полученной строке подсчитать, сколько раз встречается каждый символ (вывести в консоль).
 *Задача со звездочкой
 *Подсчитать среднее количество частоты встречающихся символов (вывести в консоль).
 *Определить символ, у которого частота будет наиболее приближена к полученному среднему значению
 *(вывести в консоль сам символ и значение кода в UTF-8)
 */

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws java.net.URISyntaxException, IOException, InterruptedException {
        int number = getNumber();
        System.out.println("Сгенерировано число " + number + " Будет запрошен факт № " + number);
        String uri = "http://numbersapi.com/" + number + "/trivia";
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri)).GET().build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String fact = response.body();
        System.out.println(fact);
        fact = nonSpace(fact).toLowerCase();
        String[] charList = entryChars(fact);
        printAnswer(charList);



    }
    /*
    public static int getMax(ArrayList<Integer> list){
        int[] digitals = new int[list.size()];
        int max = 0;
        for(int i = 0; i< digitals.length; i++){
            digitals[i] = list.get(i);
            if(digitals[i]>max){
                max = digitals[i];
            }
        }
        return max;
    }
    public static void finalAnswer(String text){
        char[]sym = chars(text);
        ArrayList<Integer> freq = frequencyChars(text);
        int max = getMax(freq);
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for(int i = 0, j = 0; i< freq.size();i++){
            if(freq.get(i) == max){
                indexes.set(j,freq.get(i));
                j++;
            }
        }
        String[] finalAnswer = new String[indexes.size()];
        for(int i = 0; i< indexes.size();i++){
            finalAnswer[i] = (String)(sym[indexes.get(i)]+"("+(int)sym[indexes.get(i)]+")");
        }
        for(int i = 0; i<finalAnswer.length; i++){
            System.out.print(finalAnswer[i]);
        }
    }

    public static int frequency(String string, ArrayList<String> list) {
        int freq = string.length() / list.size();
        return freq;

    }
    */
    public static void printAnswer(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    public static ArrayList<Integer> frequencyChars(String text) {
        char[] array = text.toCharArray();
        Arrays.sort(array);
        ArrayList<Integer> entryList = new ArrayList<Integer>();
        int count = 1;
        for (int i = 0, j = 0; i < array.length - 1; i++) {
            if (array[i + 1] == array[i]) {
                count++;
            } else {
                entryList.set(j,count);
                count = 1;
                j++;
            }
        }
        return entryList;
    }

    public static char[] chars(String text) {
        char[] array = text.toCharArray();
        Arrays.sort(array);
        char[] symbols = new char[array.length];
        for (int i = 0, j = 0; i < array.length - 1; i++) {
            if (array[i + 1] != array[i]) {
                symbols[j] = array[i];
                j++;
            }
        }
        return symbols;
    }

    public static String[] entryChars(String text) {
        char[] array = text.toCharArray();
        Arrays.sort(array);
        ArrayList<String> entryList = new ArrayList<String>();
        int count = 1;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] == array[i]) {
                count++;
            } else {
                entryList.add("Символ " + array[i] + " встречается " + count + " раз(а)");
                count = 1;
            }
        }
        String[] entryArray = new String[entryList.size()];
        for (int i = 0; i < entryArray.length; i++) {
            entryArray[i] = entryList.get(i);
        }

        return entryArray;
    }

    public static int getNumber() {
        double num = Math.random() * 10000; //9999 - максимально допустимое число на сайте
        return (int) num;
    }

    public static String nonSpace(String str) {
        str = str.replaceAll("\\s+", "");
        return str;
    }

}



