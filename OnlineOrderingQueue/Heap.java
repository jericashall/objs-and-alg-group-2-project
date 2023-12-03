package Lab4;

import java.util.ArrayList;
public class Heap<T> {

    private ArrayList<T> values;
    private void percolateUp(T value) {
        int i = size();
        this.values.add(value);

        while (i != 0 && ((int) this.values.get(i) < (int) this.values.get(parent(i)))) {
            swap(i, parent(i));
            i = parent(i);
        }
    };
    private void percolateDown() {
        int index = 0;

        if (size() == 1) {
            return;
        }

        if (size() == 2) {
            int indexValue = (int) this.values.get(index);
            int leftValue = (int) this.values.get(left(index));

            if (indexValue > leftValue) {
                swap(index, left(index));
                index = left(index);
            }
            return;
        }

        int indexValue = (int) this.values.get(index);
        int leftValue = (int) this.values.get(left(index));
        int rightValue = (int) this.values.get(right(index));

        while (((indexValue > rightValue) || (indexValue > leftValue)) && (left(index) < size())) {
            if (leftValue <= rightValue) {
                swap(index, left(index));
                index = left(index);
            } else {
                swap(index, right(index));
                index = right(index);
            }

            try {
                indexValue = (int) this.values.get(index);
                leftValue = (int) this.values.get(left(index));
                rightValue = (int) this.values.get(right(index));
            } catch(Exception e) {
                rightValue = rightValue * 1000;
            };
        }
    };
    private void swap(int i, int j) {
        T temp = this.values.get(i);
        this.values.set(i, this.values.get(j));
        this.values.set(j, temp);
    }
    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int left(int index) {
        return 2 * index + 1;
    }

    private int right(int index) {
        return 2 * index + 2;
    }

    public Heap() {
        this.values = new ArrayList<T>();
    }
    public void insert(T value) {
        percolateUp(value);
    };
    public T deleteMin() {
        swap(0, size() - 1);
        T removedValue = this.values.get(size() - 1);
        this.values.remove(size() - 1);
        if (size() > 0) percolateDown();
        return removedValue;
    }
    public T getElement(int index) {
        return this.values.get(index);
    }
    public T getLastElement() {
        return this.values.get(size() - 1);
    }

    public int size() {
        return this.values.size();
    }

    @Override
    public String toString() {
        String output;
        for (T value : this.values) {
            output = output + value.toString() + "\n"
        }
        return output;
    }
}