package packages;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MiddleSizeTest {

    @Test
    void getDeliveryPrice() {
        MiddleSize middleSize = new MiddleSize(5);
        assertEquals(middleSize.getDeliveryPrice(), 20);
    }

    @Test
    void getDeliveryPrice_invalid_size() {
        MiddleSize middleSize = new MiddleSize(3);
        assertEquals(middleSize.getDeliveryPrice(), 0);
    }

}