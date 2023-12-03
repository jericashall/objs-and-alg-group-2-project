package OnlineOrderingQueue;

import java.util.ArrayList;
public class Heap<T extends Comparable<T>> {

    private ArrayList<T> values;
    private void percolateUp(T value) {
        int i = size();
        this.values.add(value);

        while (i != 0 && (this.values.get(i).compareTo(this.values.get(parent(i))) < 0 )) {
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
            T indexValue = this.values.get(index);
            T leftValue = this.values.get(left(index));

            if (indexValue.compareTo(leftValue) > 0) {
                swap(index, left(index));
                index = left(index);
            }
            return;
        }

        T indexValue = this.values.get(index);
        T leftValue = this.values.get(left(index));
        T rightValue = this.values.get(right(index));

        while (((indexValue.compareTo(rightValue) > 0) || (indexValue.compareTo(leftValue) > 0)) && (left(index) < size())) {
            if (leftValue.compareTo(rightValue) <= 0) {
                swap(index, left(index));
                index = left(index);
            } else {
                swap(index, right(index));
                index = right(index);
            }

            try {
                indexValue = this.values.get(index);
                leftValue = this.values.get(left(index));
                rightValue = this.values.get(right(index));
            } catch(Exception e) {
                System.out.println(e);
                // rightValue = rightValue * 1000;
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
        String output = "";
        for (T value : this.values) {
            output = output + value.toString() + "\n";
        }
        return output;
    }
}