package com.thoughtworks;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        // 以下是执行交易的交易员和发生的一系列交易,请为以下八个查询方法提供实现。
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1.找出2011年的所有交易并按交易额排序(从低到高)
        System.out.println(get2011Transactions(transactions));

        // 2.交易员都在哪些不同的􏱜城市工作过
        System.out.println(getTradersCity(transactions));

        // 3.查找所有来自于剑桥的交易员，并按姓名排序
        System.out.println(getCambridgeTraders(transactions));

        // 4.返回所有交易员的姓名字符串，按字母顺序排序
        System.out.println(getTradersName(transactions));

        // 5.有没有交易员是在米兰工作的
        System.out.println(hasMilanTrader(transactions));

        // 6.返回交易员是剑桥的所有交易的交易额
        System.out.println(getCambridgeTransactionsValue(transactions));

        // 7.所有交易中，最高的交易额是多少
        System.out.println(getMaxTransactionValue(transactions));

        // 8.返回交易额最小的交易
        System.out.println(getMinTransaction(transactions));
    }

    public static List<Transaction> get2011Transactions(List<Transaction> transactions) {
        return transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue)).collect(Collectors.toList());
    }

    public static List<String> getTradersCity(List<Transaction> transactions) {
        return transactions.stream().map(transaction -> transaction.getTrader().getCity())
                .distinct().collect(Collectors.toList());
    }

    public static List<Trader> getCambridgeTraders(List<Transaction> transactions) {
      return transactions.stream().map(Transaction::getTrader).filter(trader -> trader.getCity().equals("Cambridge"))
              .distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
    }

    public static List<String> getTradersName(List<Transaction> transactions) {
        return transactions.stream().map(transaction -> transaction.getTrader().getName())
                .distinct().sorted().collect(Collectors.toList());
    }

    public static boolean hasMilanTrader(List<Transaction> transactions) {
        return transactions.stream().map(Transaction::getTrader).anyMatch(trader -> trader.getCity().equals("Milan"));
    }

    public static List<Integer> getCambridgeTransactionsValue(List<Transaction> transactions) {
        return transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue).collect(Collectors.toList());
    }

    public static int getMaxTransactionValue(List<Transaction> transactions) {
        return transactions.stream().mapToInt(Transaction::getValue).max().orElse(0);
    }

    public static Transaction getMinTransaction(List<Transaction> transactions) {
        return transactions.stream().min(Comparator.comparingInt(Transaction::getValue)).orElse(null);
    }
}
