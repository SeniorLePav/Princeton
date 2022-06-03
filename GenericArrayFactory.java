import java.lang.reflect.Array;

public class GenericArrayFactory<Item> {  

    public Item[] arrayOf(Class<Item[]> clazz, int length) {  
        return clazz.cast(Array.newInstance(clazz.getComponentType(), length));  
    }

    public static void main(String[] args) {  
        Integer[] intArray = new GenericArrayFactory<Integer>().arrayOf(Integer[].class, 42);  
    }  
}
