package packages;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SalablePackageTest {

    @Test
    void getDeliveryPrice_small() {
        SalablePackage salablePackage = new SalablePackage(3, 100);
        assertEquals(salablePackage.getDeliveryPrice(), 19);
    }

    @Test
    void getDeliveryPrice_middle() {
        SalablePackage salablePackage = new SalablePackage(5, 100);
        assertEquals(salablePackage.getDeliveryPrice(), 30);
    }

    @Test
    void getDeliveryPrice_big() {
        SalablePackage salablePackage = new SalablePackage(9, 100);
        assertEquals(salablePackage.getDeliveryPrice(), 55);
    }

    @Test
    void getDeliveryPrice_invalid_size() {
        SalablePackage salablePackage = new SalablePackage(10, 100);
        assertEquals(salablePackage.getDeliveryPrice(), 0);
    }
}