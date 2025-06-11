// AdvancedPQ.java

import java.util.NoSuchElementException;
import java.util.Arrays;

public class AdvancedPQ<K extends Comparable<K>, V> {
    private Entry<K, V>[] heap;
    private int size;
    private boolean isMinHeap;

    public AdvancedPQ(boolean minHeap) {
        this.heap = new Entry[10];
        this.size = 0;
        this.isMinHeap = minHeap;
    }

    public static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public K getKey() {
        	return key;
        }
        
        public V getValue() {
        	return value;
        }
        
        public void setKey(K key) {
        	this.key = key;
        }
        
        public void setValue(V value) {
        	this.value = value;
        }

        
        public String toString() {
            return "(" + key + " , " + value + ")";
        }
    }

    // 1
    public void toggle() {
        isMinHeap = !isMinHeap;
        heapify();
    }
    
    // 2
    public Entry<K, V> removeTop() {
        if (isEmpty()) throw new NoSuchElementException();
        Entry<K, V> result = heap[0];
        heap[0] = heap[--size];
        downHeap(0);
        return result;
    }

    // 3
    public Entry<K, V> insert(K key, V value) {
        ensureCapacity();
        Entry<K, V> newEntry = new Entry<>(key, value);
        heap[size] = newEntry;
        upHeap(size);
        size++;
        return newEntry;
    }

    // 4
    public Entry<K, V> top() {
        if (isEmpty()) throw new NoSuchElementException();
        return heap[0];
    }


    // 5
    public Entry<K, V> remove(Entry<K, V> entry) {
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(entry)) {
                Entry<K, V> removed = heap[i];
                heap[i] = heap[--size];
                downHeap(i);
                upHeap(i);
                return removed;
            }
        }
        throw new NoSuchElementException();
    }

    // 6
    public K replaceKey(Entry<K, V> entry, K newKey) {
        K oldKey = entry.getKey();
        entry.setKey(newKey);
        
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (heap[i] == entry) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new NoSuchElementException("Entry not in heap");
        }

        int parent = (index - 1) / 2;
        if (index > 0 && compare(heap[index], heap[parent]) < 0) {
            upHeap(index);
        } else {
            downHeap(index);
        }

        return oldKey;
    }

    // 7
    public V replaceValue(Entry<K, V> entry, V newValue) {
        V oldValue = entry.getValue();
        entry.setValue(newValue);
        return oldValue;
    }

    // 8 
    public String state() {
        return isMinHeap ? "Min" : "Max";
    }

    // 9
    public boolean isEmpty() {
        return size == 0;
    }

    // 10
    public int size() {
        return size;
    }

    // 11
    public Entry<K, V> peekAt(int n) {
        if (n < 1 || n > size) throw new IndexOutOfBoundsException("Index out of bounds: " + n);
        Entry<K, V>[] copy = Arrays.copyOf(heap, size);
        Arrays.sort(copy, (a, b) -> isMinHeap ? a.key.compareTo(b.key) : b.key.compareTo(a.key));
        return copy[n - 1];
    }

    // 12
    public void merge(AdvancedPQ<K, V> other) {
        for (int i = 0; i < other.size; i++) {
            insert(other.heap[i].getKey(), other.heap[i].getValue());
        }
    }

    //double heap size
    private void ensureCapacity() {
        if (size >= heap.length) {
            Entry<K, V>[] newHeap = new Entry[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap;
        }
    }

    //moves entry up to the hear to restore order
    private void upHeap(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (compare(heap[i], heap[parent]) >= 0) break;
            swap(i, parent);
            i = parent;
        }
    }

    //pushes  entry down the heap to maintain heap structure
    private void downHeap(int i) {
        while (2 * i + 1 < size) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int best = left;
            if (right < size && compare(heap[right], heap[left]) < 0) best = right;
            if (compare(heap[best], heap[i]) >= 0) break;
            swap(i, best);
            i = best;
        }
    }

    //compare two entries 
    private int compare(Entry<K, V> a, Entry<K, V> b) {
        return isMinHeap ? a.key.compareTo(b.key) : b.key.compareTo(a.key);
    }

    //swap two entries
    private void swap(int i, int j) {
        Entry<K, V> temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    //rebuilds the heap from scartch to restore heap property
    private void heapify() {
        for (int i = (size / 2) - 1; i >= 0; i--) {
            downHeap(i);
        }
    }
}

