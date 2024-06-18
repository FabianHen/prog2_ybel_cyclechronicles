package cyclechronicles;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class ShopTest {

    @InjectMocks
    private Shop shop;

    @BeforeEach
    void setUp() {
        shop = new Shop();
    }
    //1, 4
    @Test
    void testAcceptValidFahrrad() {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("customer1");

        boolean result = shop.accept(order);

        assertTrue(result);
    }
    // 2
    @Test
    void testAcceptEbicycle() {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.EBIKE);
        when(order.getCustomer()).thenReturn("customer2");

        boolean result = shop.accept(order);

        assertFalse(result);
    }
    // 3
    @Test
    void testAcceptGravelbike() {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.GRAVEL);
        when(order.getCustomer()).thenReturn("customer3");

        boolean result = shop.accept(order);

        assertFalse(result);
    }
    // 5 8
    @Test
    void testAcceptMoreThanFiveOrdersForCustomer() {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("customer5");

        for (int i = 0; i < 5; i++) {
            Order existingOrder = mock(Order.class);
            when(existingOrder.getCustomer()).thenReturn("customer5");
            shop.accept(existingOrder);
        }

        boolean result = shop.accept(order);

        assertFalse(result);
    }
    // 6
    @Test
    void testAcceptValidOrderWithLessThanFivePendingOrders() {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("customer6");

        for (int i = 0; i < 3; i++) {
            Order existingOrder = mock(Order.class);
            when(existingOrder.getCustomer()).thenReturn("customer" + i);
            shop.accept(existingOrder);
        }

        boolean result = shop.accept(order);

        assertTrue(result);
    }
    // 7
    @Test
    void testAcceptOrderWhenPendingOrdersExceedLimit() {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("customer7");

        for (int i = 0; i < 5; i++) {
            Order existingOrder = mock(Order.class);
            when(existingOrder.getCustomer()).thenReturn("customer" + i);
            shop.accept(existingOrder);
        }

        boolean result = shop.accept(order);

        assertFalse(result);
    }
    // 9
    @Test
    void testAcceptValidOrderAtLimitOfPendingOrders() {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("customer9");

        for (int i = 0; i < 4; i++) {
            Order existingOrder = mock(Order.class);
            when(existingOrder.getCustomer()).thenReturn("customer" + i);
            shop.accept(existingOrder);
        }

        boolean result = shop.accept(order);

        assertTrue(result);
    }
    // 10
    @Test
    void testAcceptOrderExceedingLimitOfPendingOrders() {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("customer10");

        for (int i = 0; i < 5; i++) {
            Order existingOrder = mock(Order.class);
            when(existingOrder.getCustomer()).thenReturn("customer" + i);
            shop.accept(existingOrder);
        }

        boolean result = shop.accept(order);

        assertFalse(result);
    }
}
