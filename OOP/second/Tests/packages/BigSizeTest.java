package packages;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BigSizeTest {

    @Test
    void getDeliveryPrice() {
        BigSize bigSize = new BigSize(9);
        assertEquals(bigSize.getDeliveryPrice(), 45);
    }

    @Test
    void getDeliveryPrice_invalid_size() {
        BigSize bigSize = new BigSize(10);
        assertEquals(bigSize.getDeliveryPrice(), 0);
    }

}
