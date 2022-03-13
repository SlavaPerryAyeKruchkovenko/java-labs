import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class IntegerTest {
    @Test
    @DisplayName("Test empty param")
    void decodeEmpty(){
        assertThrows(NumberFormatException.class,()->Integer.decode(""));
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 0, 0b10101, 0x0ff,Integer.MAX_VALUE})
    void signMinusTest(int num){
        assertTrue( Integer.decode("-"+String.valueOf(num)) == -num);
    }
    @ParameterizedTest
    @ValueSource(ints = {+1, 0, 0b101, 0x0AAF,Integer.MAX_VALUE})
    void signPlusTest(int num){
        assertTrue( Integer.decode("+"+String.valueOf(num)) == num);
    }
}