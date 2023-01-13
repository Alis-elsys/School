import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import address.Address;
import order.Order;
import order.Status;
import packages.MiddleSize;

class DeliverySystemTest {

    @Test
    void is_not_logged() {
        DeliverySystem deliverySystem = new DeliverySystem();
        assertTrue(deliverySystem.is_not_logged());
    }

    @Test
    void is_not_admin() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        assertFalse(deliverySystem.is_not_admin());
    }

    @Test
    void is_not_customer() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("customer", "customer");
        deliverySystem.login("customer", "customer");
        assertFalse(deliverySystem.is_not_customer());
    }

    @Test
    void is_not_driver() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("driver", "driver");
        deliverySystem.login("driver", "driver");
        assertFalse(deliverySystem.is_not_driver());
    }

    @Test
    void login() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        assertFalse(deliverySystem.is_not_logged());
    }

    @Test
    void login_with_false_password() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin123");
        assertTrue(deliverySystem.is_not_logged());
    }

    @Test
    void login_with_false_username() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin123", "admin");
        assertTrue(deliverySystem.is_not_logged());
    }

    @Test
    void login_with_false_input() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin123", "admin123");
        assertTrue(deliverySystem.is_not_logged());
    }

    @Test
    void logout() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.logout();
        assertTrue(deliverySystem.is_not_logged());
    }

    @Test
    void registerUser() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("desi", "desi123");
        assertEquals(deliverySystem.users.get(1).getUsername(), "desi");
    }

    @Test
    void registerUser_not_login() {
        DeliverySystem deliverySystem = new DeliverySystem();
        //deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("admin", "desi123");
        assertEquals(deliverySystem.users.size(), 1);
    }

    @Test
    void registerUser_not_administrator() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.logout();
        deliverySystem.login("desi", "desi123");
        deliverySystem.registerUser("ivan", "ivan123");
        assertEquals(deliverySystem.users.size(), 2);
    }

    @Test
    void registerUser_with_existing_username() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("admin", "admin");
        assertEquals(deliverySystem.users.size(), 1);
    }

    @Test
    void registerUser_with_empty_username() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("", "admin");
        assertEquals(deliverySystem.users.size(), 1);
    }

    @Test
    void registerUser_with_empty_password() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("admin123", "");
        assertEquals(deliverySystem.users.size(), 1);
    }

    @Test
    void registerDriver() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        assertEquals(deliverySystem.users.get(1).getUsername(), "ivan");
    }

    @Test
    void registerDriver_not_login() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.registerDriver("ivan", "ivan123");
        assertEquals(deliverySystem.users.size(), 1);
    }

    @Test
    void registerDriver_not_admin() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        deliverySystem.login("ivan", "ivan123");
        deliverySystem.registerDriver("desi", "desi123");
        assertEquals(deliverySystem.users.size(), 2);
    }

    @Test
    void registerDriver_same_username() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        deliverySystem.registerDriver("ivan", "ivan123");
        assertEquals(deliverySystem.users.size(), 2);
    }

    @Test
    void registerDriver_with_empty_username() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("", "admin");
        assertEquals(deliverySystem.users.size(), 1);
    }

    @Test
    void registerDriver_with_empty_password() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("admin123", "");
        assertEquals(deliverySystem.users.size(), 1);
    }

    @Test
    void addAddress() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        assertEquals(deliverySystem.addresses.size(), 1);
    }

    @Test
    void addAddress_not_login() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", 1));
        assertEquals(deliverySystem.addresses.size(), 0);
    }

    @Test
    void addAddress_not_customer() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        assertEquals(deliverySystem.addresses.size(), 0);
    }

    @Test
    void addAddress_incorrect_id() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", 12));
        assertEquals(deliverySystem.addresses.size(), 0);
    }

    @Test
    void addOrder() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        deliverySystem.addOrder(new Order(deliverySystem.addresses.get(0).getId()));
        assertEquals(deliverySystem.orders.get(0).getAddressId(), deliverySystem.addresses.get(0).getId());
        //assertEquals(deliverySystem.orders.size(), 1);
    }

    @Test
    void addOrder_not_login() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        deliverySystem.logout();
        deliverySystem.addOrder(new Order(deliverySystem.addresses.get(0).getId()));
        assertEquals(deliverySystem.orders.size(), 0);
    }

    @Test
    void addOrder_not_customer() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        deliverySystem.login("ivan", "ivan123");
        deliverySystem.addOrder(new Order(deliverySystem.addresses.get(0).getId()));
        assertEquals(deliverySystem.orders.size(), 0);
    }

    @Test
    void addPackage() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        Order order = new Order(deliverySystem.addresses.get(0).getId());
        deliverySystem.addOrder(order);
        MiddleSize p = new MiddleSize(5);
        deliverySystem.addPackage(p, order.getId());
        //аko aдреса на поръчката не е за текущия потребител да се хвърля грешка
        assertEquals(deliverySystem.orders.get(0).getPackages().get(0), p);
    }

    @Test
    void addPackage_not_login() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        Order order = new Order(deliverySystem.addresses.get(0).getId());
        deliverySystem.addOrder(order);
        MiddleSize p = new MiddleSize(5);
        deliverySystem.logout();
        deliverySystem.addPackage(p, 1000);
        assertEquals(deliverySystem.orders.get(0).getPackages().size(), 0);
    }

    @Test
    void addPackage_not_customer() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        Order order = new Order(deliverySystem.addresses.get(0).getId());
        deliverySystem.addOrder(order);
        MiddleSize p = new MiddleSize(5);
        deliverySystem.login("ivan", "ivan123");
        deliverySystem.addPackage(p, 1000);
        assertEquals(deliverySystem.orders.get(0).getPackages().size(), 0);
    }

    @Test
    void addPackage_incorrect_parameter() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        Order order = new Order(deliverySystem.addresses.get(0).getId());
        deliverySystem.addOrder(order);
        MiddleSize p = new MiddleSize(5);
        deliverySystem.addPackage(p, 1001);
        assertEquals(deliverySystem.orders.get(0).getPackages().size(), 0);
    }

    @Test
    void addPackage_different_address() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("ivan", "ivan123");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        Order order = new Order(deliverySystem.addresses.get(0).getId());
        deliverySystem.addOrder(order);
        MiddleSize p = new MiddleSize(5);
        deliverySystem.login("ivan", "ivan123");
        deliverySystem.addPackage(p, 1000);
        assertEquals(deliverySystem.orders.get(0).getPackages().size(), 0);
    }

    @Test
    void getOrderToDeliver() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        Order order = new Order(10);
        deliverySystem.addOrder(order);
        deliverySystem.login("ivan", "ivan123");
        deliverySystem.getOrderToDeliver();
        assertSame(deliverySystem.orders.get(0).getStatus(), Status.DELIVERING);
    }

    @Test
    void getOrderToDeliver_not_login() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        Order order = new Order(10);
        deliverySystem.addOrder(order);
        deliverySystem.logout();
        deliverySystem.getOrderToDeliver();
        assertNotSame(deliverySystem.orders.get(0).getStatus(), Status.DELIVERING);
    }

    @Test
    void getOrderToDeliver_not_driver() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        Order order = new Order(10);
        deliverySystem.addOrder(order);
        deliverySystem.getOrderToDeliver();
        assertNotSame(deliverySystem.orders.get(0).getStatus(), Status.DELIVERING);
    }

    @Test
    void getOrderToDeliver_different_status() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        Address address = new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId());
        deliverySystem.addAddress(address);
        Order order = new Order(address.getId());
        deliverySystem.orders.add(order);
        deliverySystem.login("ivan", "ivan123");
        deliverySystem.getOrderToDeliver(1000);
        deliverySystem.getOrderToDeliver(1000);
        assertTrue(deliverySystem.orders.get(0).getStatus() != Status.DELIVERING);
    }

    @Test
    void GetOrderToDeliver_with_parameter() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        Address address = new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId());
        deliverySystem.addAddress(address);
        Order order = new Order(address.getId());
        deliverySystem.orders.add(order);
        deliverySystem.login("ivan", "ivan123");
        deliverySystem.getOrderToDeliver(order.getId());
        assertEquals(deliverySystem.orders.get(0).getStatus(), Status.DELIVERING);
    }

    @Test
    void testGetOrderToDeliverWithId_not_login() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        Order order = new Order(2);
        deliverySystem.orders.add(order);
        deliverySystem.logout();
        deliverySystem.getOrderToDeliver(1000);
        assertNotSame(deliverySystem.orders.get(0).getStatus(), Status.DELIVERING);
    }

    @Test
    void testGetOrderToDeliverWithId_not_driver() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        Order order = new Order(2);
        deliverySystem.orders.add(order);
        deliverySystem.getOrderToDeliver(1000);
        assertNotSame(deliverySystem.orders.get(0).getStatus(), Status.DELIVERING);
    }

    @Test
    void testGetOrderToDeliverWithId_incorrect_parameter() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        Order order = new Order(2);
        deliverySystem.orders.add(order);
        deliverySystem.login("ivan", "ivan123");
        deliverySystem.getOrderToDeliver(1);
        assertNotSame(deliverySystem.orders.get(0).getStatus(), Status.DELIVERING);
    }

    @Test
    void testGetOrderToDeliverWithId_different_status() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        Address address = new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId());
        deliverySystem.addAddress(address);
        Order order = new Order(address.getId());
        deliverySystem.orders.add(order);
        deliverySystem.login("ivan", "ivan123");
        deliverySystem.getOrderToDeliver(order.getId());
        deliverySystem.getOrderToDeliver(order.getId());
        assertEquals(deliverySystem.orders.get(0).getStatus(), Status.DELIVERING);
    }

    @Test
    void testGetOrderToDeliverWithId_no_order() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        deliverySystem.login("ivan", "ivan123");
        deliverySystem.getOrderToDeliver(1000);
        assertEquals(deliverySystem.orders.size(), 0);
    }

    @Test
    void deliverOrder() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        Address address = new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId());
        deliverySystem.addAddress(address);
        Order order = new Order(address.getId());
        deliverySystem.orders.add(order);
        deliverySystem.login("ivan", "ivan123");
        deliverySystem.getOrderToDeliver(order.getId());
        deliverySystem.deliverOrder(order.getId());
        assertEquals(deliverySystem.orders.get(0).getStatus(), Status.DELIVERED);
    }

    @Test
    void deliverOrder_not_login() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        Order order = new Order(10);
        deliverySystem.orders.add(order);
        deliverySystem.login("ivan", "ivan123");
        deliverySystem.getOrderToDeliver(1000);
        deliverySystem.logout();
        deliverySystem.deliverOrder(1000);
        assertNotSame(deliverySystem.orders.get(0).getStatus(), Status.DELIVERED);
    }

    @Test
    void deliverOrder_not_driver() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        Order order = new Order(10);
        deliverySystem.orders.add(order);
        deliverySystem.login("ivan", "ivan123");
        deliverySystem.getOrderToDeliver(1000);
        deliverySystem.login("desi", "desi123");
        deliverySystem.deliverOrder(1000);
        assertNotSame(deliverySystem.orders.get(0).getStatus(), Status.DELIVERED);
    }

    @Test
    void deliverOrder_incorrect_parameter() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        deliverySystem.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId()));
        Order order = new Order(10);
        deliverySystem.orders.add(order);
        deliverySystem.login("ivan", "ivan123");
        deliverySystem.getOrderToDeliver(1000);
        deliverySystem.deliverOrder(1001);
        assertNotSame(deliverySystem.orders.get(0).getStatus(), Status.DELIVERED);
    }

    @Test
    void deliverOrder_different_status() {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.login("admin", "admin");
        deliverySystem.registerDriver("ivan", "ivan123");
        deliverySystem.registerUser("desi", "desi123");
        deliverySystem.login("desi", "desi123");
        Address address = new Address("Bulgaria", "Sofia", "Slaveykov", deliverySystem.currentUser.getId());
        deliverySystem.addAddress(address);
        Order order = new Order(address.getId());
        deliverySystem.orders.add(order);
        deliverySystem.login("ivan", "ivan123");
        deliverySystem.getOrderToDeliver(order.getId());
        deliverySystem.deliverOrder(order.getId());
        deliverySystem.deliverOrder(order.getId());
        assertTrue(deliverySystem.orders.get(0).getStatus() != Status.DELIVERING);
    }
}