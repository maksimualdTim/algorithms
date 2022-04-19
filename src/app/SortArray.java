package app;

public class SortArray {
    private int nElems; // Количество элементов в массиве
    private int[] a; // Ссылка на массив a

    private int[] duplicate;

    public SortArray(int size) // Конструктор
    {
        a = new int[size];
        duplicate = new int[size];
        nElems = size;
    } // Создание массива

    public void bubbleSort() {
        int out, in;
        for (out = nElems - 1; out > 1; out--) // Внешний цикл (обратный)
            for (in = 0; in < out; in++) // Внутренний цикл (прямой)
                if (a[in] > a[in + 1]) // Порядок нарушен?
                    swap(in, in + 1); // Поменять местами
    }

    public void SelectionSort() {
        for (int i = 0; i < nElems - 1; ++i) {
            int minPos = i;
            for (int j = i + 1; j < nElems; ++j) {
                if (a[minPos] > a[j]) {
                    minPos = j;
                }
            }
            this.swap(minPos, i);
        }
    }

    public void InsertionSort() {
        for (int left = 0; left < nElems; left++) {
            // Вытаскиваем значение элемента
            int value = a[left];
            // Перемещаемся по элементам, которые перед вытащенным элементом
            int i = left - 1;
            for (; i >= 0; i--) {
                // Если вытащили значение меньшее — передвигаем больший элемент дальше
                if (value < a[i]) {
                    a[i + 1] = a[i];
                } else {
                    // Если вытащенный элемент больше — останавливаемся
                    break;
                }
            }
            // В освободившееся место вставляем вытащенное значение
            a[i + 1] = value;
        }
    }

    public void shellSort() {
        int inner, outer;
        int temp;
        int h = 1; // Вычисление исходного значения h
        while (h <= nElems / 3)
            h = h * 3 + 1; // (1, 4, 13, 40, 121, ...)
        while (h > 0) // Последовательное уменьшение h до 1
        {
// h-сортировка
            for (outer = h; outer < nElems; outer++) {
                temp = a[outer];
                inner = outer;
// Первый подмассив (0, 4, 8)
                while (inner > h - 1 && a[inner - h] >= temp) {
                    a[inner] = a[inner - h];
                    inner -= h;
                }
                a[inner] = temp;
            }
            h = (h - 1) / 3; // Уменьшение h
        }
    }

    public void quickSort(int low, int high) {
        if (nElems == 0)
            return;//массив отсортирован

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = a[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (a[i] < opora) {
                i++;
            }

            while (a[j] > opora) {
                j--;
            }

            if (i <= j) {//меняем местами
                this.swap(i, j);
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(low, j);

        if (high > i)
            quickSort(i, high);
    }

    private void swap(int one, int two) {
        int temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }

    public void fill() {
        for (int i = 0; i < nElems; i++) {
            a[i] = this.getRandomNumber(-1000000, 1000000);
            duplicate[i] = a[i];
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void restore() {
        a = duplicate;
    }
    public int getnElems (){
        return nElems;
    }
    public void print()
    {
        for (int i = 0; i < nElems; i++)
            System.out.println("a["+i+"] = "+a[i]+"");
    }
}
