package com.thoughtworks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {

    private List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    void get2011Transactions_should_success() {
        List<Transaction> result = App.get2011Transactions(transactions);

        String excepted = "[{Trader:Brian in Cambridge, year: 2011, value:300}, {Trader:Raoul in Cambridge, year: 2011, value:400}]";
        assertEquals(excepted, result.toString());
    }

    @Test
    void getTradersCity_should_success() {
        List<String> result = App.getTradersCity(transactions);

        String excepted = "[Cambridge, Milan]";
        assertEquals(excepted, result.toString());
    }

    @Test
    void getCambridgeTraders_should_success() {
        List<Trader> result = App.getCambridgeTraders(transactions);

        String excepted = "[Trader:Alan in Cambridge, Trader:Brian in Cambridge, Trader:Raoul in Cambridge]";
        assertEquals(excepted, result.toString());
    }

    @Test
    void getTradersName_should_success() {
        List<String> result = App.getTradersName(transactions);

        String excepted = "[Alan, Brian, Mario, Raoul]";
        assertEquals(excepted, result.toString());
    }

    @Test
    void hasMilanTrader_should_success() {
        boolean result = App.hasMilanTrader(transactions);

        assertTrue(result);
    }

    @Test
    void getCambridgeTransactionsValue_should_success() {
        List<Integer> result = App.getCambridgeTransactionsValue(transactions);
        System.out.println(result);
        String excepted = "[300, 1000, 400, 950]";
        assertEquals(excepted, result.toString());
    }

    @Test
    void getMaxTransactionValue_should_success() {
        int result = App.getMaxTransactionValue(transactions);

        assertEquals(1000, result);
    }

    @Test
    void getMinTransaction_should_success() {
        Transaction result = App.getMinTransaction(transactions);

        String excepted = "{Trader:Brian in Cambridge, year: 2011, value:300}";
        assertEquals(excepted, result.toString());
    }
}
