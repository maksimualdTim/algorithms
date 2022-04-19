package app;

public class LowArray {
    private int nElems; // Количество элементов в массиве
    private long[] a; // Ссылка на массив a

    public LowArray(int size) // Конструктор
    {
        a = new long[size];
        nElems = size;
    } // Создание массива

    public void setElem(int index, long value) // Запись элемента
    {
        a[index] = value;
    }

    public long getElem(int index) // Чтение элемента
    {
        return a[index];
    }

    /**
     * linear search
     *
     * @param long searchKey
     * @return void
     */
    public boolean find(long searchKey) { // Поиск заданного значения
        int j;
        boolean found = false;

        for (j = 0; j < nElems; j++){
            if (a[j] == searchKey){
                found = true;
                break;
            }
        }
        return found;
    }

    public void push(long value) // Вставка элемента в массив
    {
        long[] newA = new long [nElems+1];
        int i = 0;
        for (i = 0; i < nElems; i++)
            newA[i] = a[i];
        nElems++;
        newA[i] = value;
        a = newA;
    }

    public boolean delete(long value) {
        int j;
        for (j = 0; j < nElems; j++) // Поиск заданного значения
            if (value == a[j])
                break;
        if (j == nElems) // Найти не удалось
            return false;
        else { // Значение найдено
            nElems--;
            for (int k = j; k < nElems; k++) // Сдвиг последующих элементов
                a[k] = a[k + 1];
            return true;
        }
    }

    public int findBinary(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn;
        while (true) {
            curIn = (lowerBound + upperBound) / 2;
            if (a[curIn] == searchKey)
                return curIn; // Элемент найден
            else if (lowerBound > upperBound)
                return nElems; // Элемент не найден
            else { // Деление диапазона
                if (a[curIn] < searchKey)
                    lowerBound = curIn + 1; // В верхней половине
                else
                    upperBound = curIn - 1; // В нижней половине
            }
        }
    }

    public void insert(long value) // Вставка элемента в массив
    {
        int j;
        boolean found = false;

        for (j = 0; j < nElems; j++) // Определение позиции вставки
            if (a[j] > value){
                found = true;
                break;
            }
        if(found){
            long[] newA = new long [nElems+1];
            int i;
            for (i = 0; i < nElems;i++){
                if(i != j){
                    newA[i] = a[i];
                }else {
                    newA[i] = value;
                    break;
                }
            }
            for (int k = i; k < nElems; k++){
                newA[k+1] = a[k];
            }
            a = newA;
            nElems++;
        }else {
            this.push(value);
        }
    }

    public boolean deleteBinary(long value) {
        int j = findBinary(value);
        if (j == nElems) // Найти не удалось
            return false;
        else // Элемент найден
        {
            long[] newA = new long [nElems-1];
            int i;
            for (i = 0; i < nElems; i++){
                if(i == j){
                    break;
                }
                newA[i] = a[i];
            }

            for (int k = i+1; k < nElems; k++){
                newA[k-1] = a[k];
            }
            nElems--;
            a = newA;
            return true;
        }
    }

    public void fill(int size) {
        for (int i = 0; i < size; i++)
            a[i] = i;
    }
    public void print()
    {
        for (int i = 0; i < nElems; i++)
            System.out.println("a["+i+"] = "+a[i]+"");
    }
}
