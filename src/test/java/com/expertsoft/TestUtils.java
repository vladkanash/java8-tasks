package com.expertsoft;

import com.expertsoft.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtils {

    private static final long SEED = 1731349857;

    private static final int ORDER_ID_INCREMENT = 10000;
    private static final int PRODUCT_ID_INCREMENT = 100;

    private static final int MIN_ORDER_COUNT = 1;
    private static final int MAX_ORDER_COUNT = 8;

    private static final int MIN_ORDER_ITEMS = 1;
    private static final int MAX_ORDER_ITEMS = 10;

    private static final int MIN_PRODUCT_QUANTITY = 1;
    private static final int MAX_PRODUCT_QUANTITY = 5;

    private static final int MIN_PRODUCT_PRICE = 100_00; //value in cents
    private static final int MAX_PRODUCT_PRICE = 1000_00;

    private static final int MAX_HOUSE_NUMBER = 160;

    private static final Random rand = new Random(SEED);

    private static final List<String> productNames = new ArrayList<>();
    private static final List<String> customerNames = new ArrayList<>();
    private static final List<String> customerEmails = new ArrayList<>();
    private static final List<String> cities = new ArrayList<>();
    private static final List<String> countries = new ArrayList<>();
    private static final List<String> streets = new ArrayList<>();
    private static final List<String> cardNumbers = new ArrayList<>();

    private static long orderId = 0;
    private static long productId = 0;

    static {
        productNames.add("Apple iPhone 8");
        productNames.add("Motorola RAZR V3");
        productNames.add("Nokia 1600");
        productNames.add("Nokia 3310");
        productNames.add("Nokia 1208");
        productNames.add("Samsung Galaxy S4");
        productNames.add("Nokia 6010");
        productNames.add("Apple iPhone 5");
        productNames.add("Nokia 5130");
        productNames.add("Apple iPhone 4S");
        productNames.add("Motorola StarTAC996");
        productNames.add("Motorola C200");
        productNames.add("Samsung Galaxy S III");
        productNames.add("Apple iPhone 5S");
        productNames.add("Nokia 3100");
        productNames.add("Nokia 6230");
        productNames.add("Apple iPhone 4");
        productNames.add("amsung Galaxy S II");
        productNames.add("Samsung Galaxy Note II");
        productNames.add("Apple iPhone 3GS 5");
        productNames.add("Nokia 6270 Slider");
        productNames.add("Nokia 5200 Slider");
        productNames.add("amsung E250");
        productNames.add("Samsung Star");
        productNames.add("Apple iPhone 5C");
        productNames.add("HTC Thunderbolt");
        productNames.add("Nokia 6120");
        productNames.add("Siemens M30");

        customerNames.add("John Smith");
        customerNames.add("Rose Tyler");
        customerNames.add("Donna Noble");
        customerNames.add("Martha Jones");
        customerNames.add("Rory Williams");

        //size should be equal to customerNames
        customerEmails.add("john.smith@rambler.uk");
        customerEmails.add("r0se-tyler@gmail.com");
        customerEmails.add("DonnaDonna@gmail.com");
        customerEmails.add("martha@mail.ru");
        customerEmails.add("super-rory@tut.by");

        cities.add("New York");
        cities.add("London");
        cities.add("Moscow");

        //size should be equal to cities
        countries.add("USA");
        countries.add("Great Britain");
        countries.add("Russian Federation");

        streets.add("Marble st.");
        streets.add("Bubble avn.");
        streets.add("Evergreen terrace");
        streets.add("Lomonosova st.");
        streets.add("Basic st.");
        streets.add("Test st.");

        cardNumbers.add("4111 3456 5454 9900");
        cardNumbers.add("9785 5409 1111 5555");
        cardNumbers.add("6677 5432 9587 1670");
    }

    private static final List<Product> generatedProducts = generateProducts();
    private static final List<PaymentInfo> generatedPaymentInfos = generatePaymentInfos();

    public static List<Product> generateProducts() {
        return generate(productNames.size(), TestUtils::createProduct);
    }

    public static List<Customer> generateCustomers() {
        return generate(customerNames.size(), TestUtils::createCustomer);
    }

    public static List<Order> generateOrders(final int count) {
        return generate(count, TestUtils::createOrder);
    }

    public static List<PaymentInfo> generatePaymentInfos() {
        return generate(cardNumbers.size(), TestUtils::createPaymentInfo);
    }

    public static List<OrderItem> generateOrderItems(final int count) {
        return generate(count, TestUtils::createOrderItem);
    }

    private static Customer createCustomer(final int i) {
        final int idx = i % customerEmails.size();
        final Customer customer = new Customer();
        customer.setEmail(customerEmails.get(idx));
        customer.setName(customerNames.get(idx));
        customer.setAddress(createAddressInfo());
        customer.setOrders(generateOrders(randomInt(MIN_ORDER_COUNT, MAX_ORDER_COUNT)));
        return customer;
    }

    private static Order createOrder() {
        final Order order = new Order();
        orderId += rand.nextInt(ORDER_ID_INCREMENT);
        order.setOrderId(orderId);
        order.setOrderItems(getDistinctOrderItems());
        order.setPaymentInfo(generatedPaymentInfos.get(rand.nextInt(generatedPaymentInfos.size())));
        return order;
    }

    private static List<OrderItem> getDistinctOrderItems() {
        return generateOrderItems(randomInt(MIN_ORDER_ITEMS, MAX_ORDER_ITEMS))
                .stream()
                .collect(Collectors.toMap(OrderItem::getProduct, OrderItem::getQuantity, Integer::sum))
                .entrySet().stream()
                .map(e -> new OrderItem(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    private static OrderItem createOrderItem() {
        final OrderItem orderItem = new OrderItem();
        orderItem.setProduct(generatedProducts.get(rand.nextInt(generatedProducts.size())));
        orderItem.setQuantity(randomInt(MIN_PRODUCT_QUANTITY, MAX_PRODUCT_QUANTITY));
        return orderItem;
    }

    private static Product createProduct(final int i) {
        final int idx = i % productNames.size();
        final Product product = new Product();
        productId += idx * PRODUCT_ID_INCREMENT;
        product.setId(productId);
        product.setName(productNames.get(idx));
        product.setColor(Product.Color.values()[idx % Product.Color.values().length]);
        product.setPrice(new BigDecimal((double)randomInt(MIN_PRODUCT_PRICE, MAX_PRODUCT_PRICE) / 100));
        return  product;
    }

    private static AddressInfo createAddressInfo() {
        final int cityIdx = rand.nextInt(cities.size());
        final int stIdx = rand.nextInt(streets.size());
        final AddressInfo addressInfo = new AddressInfo();
        addressInfo.setCity(cities.get(cityIdx));
        addressInfo.setCountry(countries.get(cityIdx));
        addressInfo.setStreet(streets.get(stIdx));
        addressInfo.setHouseNumber(rand.nextInt(MAX_HOUSE_NUMBER));
        return addressInfo;
    }

    private static PaymentInfo createPaymentInfo(final int i) {
        final int idx = i % cardNumbers.size();
        final PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setCardNumber(cardNumbers.get(idx));
        paymentInfo.setCardType(PaymentInfo.CardType.values()[idx % PaymentInfo.CardType.values().length]);
        paymentInfo.setSecurityCode(cardNumbers.get(idx).substring(6, 9));
        return paymentInfo;
    }

    private static <T> List<T> generate(final int count, final Supplier<T> supplier) {
        return IntStream.range(0, count)
                .mapToObj(e -> supplier.get())
                .collect(Collectors.toList());
    }

    private static <T> List<T> generate(final int count, final IntFunction<T> supplier) {
        return IntStream.range(0, count)
                .mapToObj(supplier)
                .collect(Collectors.toList());
    }

    private static int randomInt(final int min, final int max) {
        if (min >= max) {
            throw new IllegalArgumentException("min >= max");
        }
        return rand.nextInt(max - min) + min;
    }
}
