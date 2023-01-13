package packages;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SmallSizeTest {
    @Test
    void getDeliveryPrice() {
        SmallSize smallSize = new SmallSize(3);
        assertEquals(smallSize.getDeliveryPrice(), 9);
    }

    @Test
    void getDeliveryPrice_invalid_size() {
        SmallSize smallSize = new SmallSize(5);
        assertEquals(smallSize.getDeliveryPrice(), 0);
    }
}