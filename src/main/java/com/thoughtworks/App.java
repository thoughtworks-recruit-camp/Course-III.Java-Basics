package com.thoughtworks;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;


public class App {
  public static HashMap[] maps = getIdMaps();
  public static HashMap<String, String> idNameMap = maps[0];
  public static HashMap<String, Double> idPriceMap = maps[1];

  public void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("请点餐（菜品Id x 数量，用逗号隔开）：");
    String selectedItems = scan.nextLine();
    String summary = bestCharge(selectedItems);
    System.out.println(summary);
  }

  /**
   * 接收用户选择的菜品和数量，返回计算后的汇总信息
   *
   * @param selectedItems 选择的菜品信息
   */
  public static String bestCharge(String selectedItems) {
    String[] itemsArray = selectedItems.split(",");
    CostDetails[] costDetailsList = new CostDetails[itemsArray.length];
    double fullCost = 0;
    for (int i = 0, len = itemsArray.length; i < len; i++) {
      String[] idAndCount = itemsArray[i].split(" x ");
      CostDetails currentDetails = new CostDetails(idAndCount[0], idAndCount[1]);
      costDetailsList[i] = currentDetails;
      fullCost += currentDetails.price * currentDetails.count;
    }
    String promotionInfo = "";
    double finalCost = fullCost;
    for (Promotion promotion : getPromotions()) {
      PromotionDetails promotionDetails = promotion.getPromotionDetails(fullCost, costDetailsList);
      if (promotionDetails.cost < finalCost) {
        finalCost = promotionDetails.cost;
        promotionInfo = promotionDetails.info;
      }
    }
    StringBuilder summary = new StringBuilder();
    summary.append(renderDetails(costDetailsList));
    summary.append(renderPromo(promotionInfo));
    summary.append(renderFooter(finalCost));
    return summary.toString();
  }

  private static String renderDetails(CostDetails[] costDetailsList) {
    StringBuilder detailsString = new StringBuilder("============= 订餐明细 =============\n");
    for (CostDetails item : costDetailsList) {
      detailsString.append(MessageFormat.format("{0} x {1} = {2}元\n", item.name, item.count, item.price * item.count));
    }
    detailsString.append("-----------------------------------\n");
    return detailsString.toString();
  }

  private static String renderPromo(String promotionInfo) {
    if (!promotionInfo.equals("")) {
      return MessageFormat.format("使用优惠:\n{0}\n-----------------------------------\n", promotionInfo);
    } else {
      return "";
    }
  }

  private static String renderFooter(double totalCost) {
    return MessageFormat.format("总计：{0}元\n===================================", totalCost);
  }

  /**
   * 获取每个菜品依次的编号
   */
  public static String[] getItemIds() {
    return new String[]{"ITEM0001", "ITEM0013", "ITEM0022", "ITEM0030"};
  }

  /**
   * 获取每个菜品依次的名称
   */
  public static String[] getItemNames() {
    return new String[]{"黄焖鸡", "肉夹馍", "凉皮", "冰粉"};
  }

  /**
   * 获取每个菜品依次的价格
   */
  public static double[] getItemPrices() {
    return new double[]{18.00, 6.00, 8.00, 2.00};
  }

  /**
   * 获取半价菜品的编号
   */
  public static String[] getHalfPriceIds() {
    return new String[]{"ITEM0001", "ITEM0022"};
  }

  private static HashMap[] getIdMaps() {
    String[] ids = getItemIds();
    String[] names = getItemNames();
    double[] prices = getItemPrices();
    HashMap<String, String> idNameMap = new HashMap<>();
    HashMap<String, Double> idPriceMap = new HashMap<>();
    for (int i = 0, len = ids.length; i < len; i++) {
      idNameMap.put(ids[i], names[i]);
      idPriceMap.put(ids[i], prices[i]);
    }
    return new HashMap[]{idNameMap, idPriceMap};
  }

  private abstract static class Promotion {
    String type;

    abstract PromotionDetails getPromotionDetails(double fullCost, CostDetails[] costDetailsList);
  }


  private static class SimplePromotion extends Promotion {
    String type = "满30减6元";

    @Override
    PromotionDetails getPromotionDetails(double fullCost, CostDetails[] costDetailsList) {
      return new PromotionDetails(this.type, fullCost >= 30 ? fullCost - 6 : fullCost, "满30减6元，省6元");
    }
  }

  private static class HalfPricePromotion extends Promotion {
    String type = "指定菜品半价";
    String[] items = getHalfPriceIds();

    @Override
    PromotionDetails getPromotionDetails(double fullCost, CostDetails[] costDetailsList) {
      HashSet<String> promoSet = new HashSet<>(Arrays.asList(items));
      ArrayList<String> promoNames = new ArrayList<>();
      double discount = 0;
      for (CostDetails costDetails : costDetailsList) {
        if (promoSet.contains(costDetails.id)) {
          promoNames.add(costDetails.name);
          discount += costDetails.price * costDetails.count / 2;
        }
      }
      return new PromotionDetails(
              this.type,
              fullCost - discount,
              MessageFormat.format("指定菜品半价({0})，省{1}元", String.join("，", promoNames), discount));
    }
  }

  private static Promotion[] getPromotions() {
    return new Promotion[]{new SimplePromotion(), new HalfPricePromotion()};
  }

  public static class PromotionDetails {
    String type;
    double cost;
    String info;

    PromotionDetails(String type, double cost, String info) {
      this.type = type;
      this.cost = cost;
      this.info = info;
    }
  }

  public static class CostDetails {
    String id;
    String name;
    double price;
    int count;

    public CostDetails(String id, String count) {
      this.id = id;
      this.name = idNameMap.get(id);
      this.price = idPriceMap.get(id);
      this.count = Integer.parseInt(count);
    }
  }
}
