package com.expertsoft.tasks;

import com.expertsoft.model.*;
import com.expertsoft.util.AveragingBigDecimalCollector;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * This class provides several methods to collect statistical information from customers and orders of an e-shop.
 * Your task is to implement methods of this class using Java 8 Stream API.
 * Each method is covered with a number of unit tests. You can use <code>mvn test</code> call to check your implementation.
 *
 * Refer to the <code>com.expertsoft.model</code> package to observe the domain model of the shop.
 */
class OrderStats {

    /**
     * Task 1 (⚫⚫⚪⚪⚪)
     *
     * Given a stream of customers, return the list of orders, paid using provided credit card type (Visa or MasterCard).
     *
     * @param customers stream of customers
     * @param cardType credit card type
     * @return list, containing orders paid with provided card type
     */
    static List<Order> ordersForCardType(final Stream<Customer> customers, PaymentInfo.CardType cardType) {
        return null;
    }

    /**
     * Task 2 (⚫⚫⚪⚪⚪)
     *
     * Given a stream of orders, return a map, where keys are different order sizes and values are lists of orders,
     * referring to this sizes. Order size here is just a total number of products in the order.
     *
     * @param orders stream of orders
     * @return map, where order size values mapped to lists of orders
     */
    static Map<Integer, List<Order>> orderSizes(final Stream<Order> orders) {
        return null;
    }

    /**
     * Task 3 (⚫⚫⚫⚪⚪)
     *
     * Given a stream of orders, return true only if EVERY order in the stream contains at least
     * one product of the provided color and false otherwise.
     *
     * @param orders stream of orders
     * @param color product color to test
     * @return boolean, representing if every order in the stream contains product of specified color
     */
    static Boolean hasColorProduct(final Stream<Order> orders, final Product.Color color) {
        return null;
    }

    /**
     * Task 4 (⚫⚫⚫⚫⚪)
     *
     * Given a stream of customers, return the map, where customer email is mapped to a number of different credit cards he/she used by the customer.
     *
     * @param customers stream of customers
     * @return map, where for each customer email there is a long referencing a number of different credit cards this customer uses.
     */
    static Map<String, Long> cardsCountForCustomer(final Stream<Customer> customers) {
        return null;
    }

    /**
     * Task 5 (⚫⚫⚫⚫⚫)
     *
     * Given a stream of customers, return the optional, containing the most popular country name,
     * that is, the name of the country set in addressInfo by the biggest amount of customers.
     * If there are two or more countries with the same amount of customers, return the country name that has a smallest length.
     * If customer stream is empty, Optional.empty should be returned.
     *
     * Example: For the stream, containing
     *      Customer#1 -> USA
     *      Customer#2 -> France
     *      Customer#3 -> Japan
     *      Customer#4 -> USA
     *      Customer#5 -> Japan
     *
     *      "USA" should be returned.
     *
     * @param customers stream of customers
     * @return java.util.Optional containing the name of the most popular country
     */
    static Optional<String> mostPopularCountry(final Stream<Customer> customers) {
        return null;
    }

    /**
     * Task 6 (⚫⚫⚫⚫⚫)
     *
     * Given a stream of customers, return the average product price for the provided credit card number.
     *
     * Info: If order contains the following order items:
     *  [
     *      Product1(price = 100$, quantity = 2),
     *      Product2(price = 160$, quantity = 1)
     *  ]
     * then the average product price for this order will be 120$ ((100 * 2 + 160 * 1) / 3)
     *
     * Hint: Since product prices are represented as BigDecimal objects, you are provided with the collector implementation
     *       to compute the average value of BigDecimal stream.
     *
     * @param customers stream of customers
     * @param cardNumber card number to check
     * @return average price of the product, ordered with the provided card
     */
    static BigDecimal averageProductPriceForCreditCard(final Stream<Customer> customers, final String cardNumber) {
        final AveragingBigDecimalCollector collector = new AveragingBigDecimalCollector();
        return null;
    }
}
