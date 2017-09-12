package com.expertsoft.tasks;

import com.expertsoft.TestUtils;
import com.expertsoft.model.Customer;
import com.expertsoft.model.Order;
import com.expertsoft.model.PaymentInfo;
import com.expertsoft.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class OrderStatsTest {

    private static final List<Customer> customers = TestUtils.generateCustomers();
    private static final List<Order> orders = TestUtils.generateOrders(10);
    private Stream<Customer> customerStream;
    private Stream<Order> orderStream;

    @Before
    public void setUp() throws Exception {
        customerStream = customers.stream();
        orderStream = orders.stream();
    }

    @Test
    public void task1Test1() {
        final List<Order> visaOrders = OrderStats.ordersForCardType(customerStream, PaymentInfo.CardType.VISA);
        assertEquals("There are 17 orders payed with VISA card in this stream",
                17, visaOrders.size());
        assertEquals("Order #24529 was payed using VISA card",
                24529, (long)visaOrders.get(4).getOrderId());
    }

    @Test
    public void task1Test2() {
        final List<Order> visaOrders = OrderStats.ordersForCardType(customerStream.limit(3), PaymentInfo.CardType.VISA);
        assertEquals("There are 11 orders payed with VISA card in this stream",
                11, visaOrders.size());
        assertEquals("Order #47021 was payed using VISA card",
                47021, (long)visaOrders.get(9).getOrderId());
    }

    @Test
    public void task1Test3() {
        final List<Order> visaOrders = OrderStats.ordersForCardType(Stream.empty(), PaymentInfo.CardType.MASTERCARD);
        assertEquals("There are not orders payed with VISA card in this stream",
                0, visaOrders.size());
    }

    @Test
    public void task2Test1() {
        final Stream<Order> orders = orderStream;
        final Map<Integer, List<Order>> orderSizes = OrderStats.orderSizes(orders);
        assertEquals("There are 3 orders with size = 15 in this stream",3, orderSizes.get(15).size());
        assertEquals("Order #108233 has size = 21", 108233, (long)orderSizes.get(21).get(0).getOrderId());
        assertEquals("There is no orders with size = 3 in this stream", null, orderSizes.get(0));
    }

    @Test
    public void task2Test2() {
        final Map<Integer, List<Order>> orderSizes = OrderStats.orderSizes(Stream.empty());
        assertEquals("Empty stream of order should produce empty map", 0, orderSizes.size());
    }

    @Test
    public void task3Test1() {
        final Stream<Order> orders = orderStream.limit(2);
        final boolean hasColorProduct = OrderStats.hasColorProduct(orders, Product.Color.RED);
        assertEquals("Each of the orders in this stream contains red product", true, hasColorProduct);
    }

    @Test
    public void task3Test2() {
        final Stream<Order> orders = orderStream.limit(4).skip(1);
        final boolean hasColorProduct = OrderStats.hasColorProduct(orders, Product.Color.BLUE);
        assertEquals("One of the orders in this stream does not contains any blue products", false, hasColorProduct);
    }

    @Test
    public void task4Test1() {
        final Map<String, Long> cardsForCustomer = OrderStats.cardsCountForCustomer(customerStream);

        final long actual1 = cardsForCustomer.get("DonnaDonna@gmail.com");
        final long actual2 = cardsForCustomer.get("super-rory@tut.by");
        final long actual3 = cardsForCustomer.get("martha@mail.ru");
        final long actual4 = cardsForCustomer.get("john.smith@rambler.uk");
        final long actual5 = cardsForCustomer.get("r0se-tyler@gmail.com");

        assertEquals("Donna was using 2 credit cards, not " + actual1, 2, actual1);
        assertEquals("Rory was using 2 credit cards, not " + actual2, 2, actual2);
        assertEquals("Martha was using 3 credit cards, not " + actual3, 3, actual3);
        assertEquals("John was using 1 credit card, not " + actual4, 1, actual4);
        assertEquals("Rory was using 3 credit cards, not" + actual5, 3, actual5);

        final Map<String, Long> emptyMap = OrderStats.cardsCountForCustomer(Stream.empty());
        assertTrue(emptyMap.isEmpty());
    }

    @Test
    public void task5Test1() {
        final Optional<String> mostPopularCountry = OrderStats.mostPopularCountry(customerStream);
        assertEquals(Optional.of("Great Britain"), mostPopularCountry);
    }

    @Test
    public void task5Test2() {
        final Optional<String> mostPopularCountry = OrderStats.mostPopularCountry(customerStream.skip(2));
        assertEquals(Optional.of("USA"), mostPopularCountry);
    }

    @Test
    public void task5Test3() {
        final Optional<String> mostPopularCountry = OrderStats.mostPopularCountry(Stream.empty());
        assertEquals(Optional.empty(), mostPopularCountry);
    }

    @Test
    public void task6Test1() {
        final String testCardNumber = "9785 5409 1111 5555";
        final BigDecimal avgPrice = OrderStats.averageProductPriceForCreditCard(customerStream, testCardNumber);
        assertEquals("Invalid average product price for card " + testCardNumber,
                495.83, avgPrice.setScale(2, RoundingMode.CEILING).doubleValue(), 0.01);
    }

    @Test
    public void task6Test2() {
        final String testCardNumber = "4111 3456 5454 9900";
        final BigDecimal avgPrice = OrderStats.averageProductPriceForCreditCard(customerStream, testCardNumber);
        assertEquals("Invalid average product price for card " + testCardNumber,
                524.99, avgPrice.setScale(2, RoundingMode.CEILING).doubleValue(), 0.01);
    }

    @Test
    public void task6Test3() {
        final String testCardNumber = "6677 5432 9587 1670";
        final BigDecimal avgPrice = OrderStats.averageProductPriceForCreditCard(customerStream, testCardNumber);
        assertEquals("Invalid average product price for card " + testCardNumber,
                505.64, avgPrice.setScale(2, RoundingMode.CEILING).doubleValue(), 0.01);
    }

    @Test
    public void task6Test4() {
        final String testCardNumber = "9785 5409 1111 5555";
        final BigDecimal zeroPrice = OrderStats.averageProductPriceForCreditCard(Stream.empty(), testCardNumber);
        assertEquals("Average product price for empty stream of customers should be 0",
                BigDecimal.ZERO, zeroPrice);
    }

    @Test
    public void task6Test5() {
        final BigDecimal nonExistingCard = OrderStats.averageProductPriceForCreditCard(customerStream, "INVALID");
        assertEquals("Average product price for non-existing card should be 0",
                BigDecimal.ZERO, nonExistingCard);
    }
}