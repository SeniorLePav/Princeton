import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStackWithReflection<Item> implements Iterable<Item> {
    
    private static final int INIT_CAPACITY = 8;
    
    private Class<Item[]> itemArrayClass;
    private Item[] array;
    private int n = 0;

    public ResizingArrayStackWithReflection(Class<Item[]> itemArrayClass) {
        this.itemArrayClass = itemArrayClass;
        array = itemArrayClass.cast(Array.newInstance(itemArrayClass.getComponentType(), INIT_CAPACITY));
    }

    throws IllegalArgumentException if {@code item} is {@code null}.
     */
    public void push(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null item.");
        if (n == array.length)
            resize(array.length * 2);
        array[n++] = item;
    }

    // Removes and returns the top element of the Stack.
     
    public Item pop() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = array[--n];
        array[n] = null;
        if ((n > 0) && (n <= array.length / 4))
            resize(array.length / 2);
        return item;
    }

    // Returns, but doesn't remove, the top element of the Stack.
     
    public Item peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return array[n-1];
    }

    // Returns {@code true} if the Stack is empty.
     
    public boolean isEmpty() {
        return n == 0;
    }

    //Returns the size of the Stack.
    
    public int size() {
        return n;
    }
    
    // Returns an iterator which iterates over the Stack from the top element to the bottom element.
  
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private void resize(int size) {
        Item[] newarray = itemArrayClass.cast(Array.newInstance(itemArrayClass.getComponentType(), size));
        for (int i = 0; i < n; i++)
            newarray[i] = array[i];
        array = newarray;
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        
        private int i = n-1;

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return array[i--];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported.");
        }
        
    }
	
    public static void main(String[] args) {
        ResizingArrayStackWithReflection<String> s = new ResizingArrayStackWithReflection<String>(String[].class);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
