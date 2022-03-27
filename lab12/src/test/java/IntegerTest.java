import javafx.util.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class IntegerTest {
    public static Pair[] getNumAndRadix(){
        int a = 0b10101;
        return new Pair[]{new Pair<>("0X21",33),new Pair<>("#1A",26),new Pair<>("011",9)};
    }
    @Test
    @DisplayName("Test empty param")
    void decodeEmpty() {
        Exception ex = assertThrows(NumberFormatException.class, () -> Integer.decode(""));
        assertEquals(ex.getMessage(),"Zero length string");
    }

    @ParameterizedTest
    @ValueSource(strings = {"+10", "-0", "-288", "+0" , "+88"})
    @DisplayName("Test on correct sign")
    void signMinusTest(String num) {
        assertEquals(Integer.decode(String.valueOf(num)), Integer.valueOf(num));
    }

    @ParameterizedTest
    @ValueSource(strings = {"++10", "--0","+-0b10101", "-+0x0ff"})
    @DisplayName("Test on to match signs")
    void decodeToMatchSign(String num) {
        Exception ex = assertThrows(NumberFormatException.class, () -> Integer.decode(num));
        assertEquals(ex.getMessage().toLowerCase(Locale.ROOT),
                "Sign character in wrong position".toLowerCase(Locale.ROOT));
    }
    @ParameterizedTest
    @MethodSource("getNumAndRadix")
    @DisplayName("Test on decode other system of calculate")
    void decodeOtherSystem(Pair<String,Integer> pair) {
        String num = pair.getKey();
        int value = pair.getValue();
        assertEquals(Integer.decode(num),value);
    }
    @ParameterizedTest
    @ValueSource(strings = {"2147483649", "-21474836472","0XFFFFFFFFFF"})
    @DisplayName("Test decode nums with value more MAX_VALUE or less MIN_VALUE")
    void decodeSoBigNums(String num){
        assertThrows(NumberFormatException.class,()->Integer.decode(num));
    }
    @ParameterizedTest
    @ValueSource(strings = {"2147483647", "-2147483648","1000000"})
    @DisplayName("Test decode nums with so big value")
    void decodeCorBigNums(String num){
        assertEquals(Integer.decode(num),Integer.valueOf(num));
    }
    @ParameterizedTest
    @ValueSource(strings = {"f#", "dart","java","php"})
    @DisplayName("Test decode inccorect value")
    void decodeTrash(String num){
        Exception ex = assertThrows(NumberFormatException.class,()->Integer.decode(num));
        assertEquals(ex.getMessage(),String.format("For input string: \"%s\"", num));
    }

    @Test
    @DisplayName("Test decode arr")
    void decodeArr(){
        String[] arr = new String[]{"1","2","0xf","-10","#AF"};
        int[] nums = Arrays.stream(arr).mapToInt(Integer::decode).toArray();
        int[] res = new int[]{1,2,15,-10,175};
        assertArrayEquals(nums,res);
        Iterable<Integer> iNums = ()->Arrays.stream(nums).iterator();
        Iterable<Integer> iRes = ()->Arrays.stream(res).iterator();
        assertIterableEquals(iNums, iRes);
    }
}