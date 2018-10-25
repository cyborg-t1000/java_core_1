package Homework_2;

import static Homework_2.DayOfWeek.TUESDAY;

public class Main {
    public static void main(String[] args) {
        String[][] test = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}
        };

        try {
            System.out.println("Сумма элементов массива: " + array4x4(test));
        } catch (MyArrayDataException | MyArraySizeException e) {
            e.printStackTrace();
        }

        System.out.println("До конца недели осталось часов: " + getWorkingHours(TUESDAY));
    }

    private static int array4x4(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;

        if(array.length!=4) throw new MyArraySizeException("Размер массива должен быть 4x4");
        for (String[] a: array) {
            if(a.length!=4) throw new MyArraySizeException("Размер массива должен быть 4x4");
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Ошибка преобразования строки в число в ячейке [" + i + "," + j + "]");
                }
            }
        }
        return sum;
    }

    private static String getWorkingHours(DayOfWeek day) {
        if(day.getHours()>0) return Integer.toString(day.getHours());
        return "Выходной";
    }
}

