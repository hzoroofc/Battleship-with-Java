// do not remove imports
import java.util.*;
import java.util.function.Function;
import java.util.Arrays.*;

class ArrayUtils {
    // define invert method here
    public static <T> T[] invert(T[] t) {
        for(int i = 0; i < t.length / 2; i++)
        {
            T temp = t[i];
            t[i] = t[t.length - i - 1];
            t[t.length - i - 1] = temp;
        }
        return t;
    }
}