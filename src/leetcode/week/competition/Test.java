package leetcode.week.competition;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        double principal = 1_500_000;
        int months = 360;
//        double aheadPrincipal = 200_000;
        int payTimes = 13;
        double rate = 5.73D;
        for (int i = 1; i <= 145; i++) {
            double aheadPrincipal = i * 10_000;
            calculateEqualPrincipalAndInterestApart2(principal, months, aheadPrincipal, payTimes, rate);
        }
    }

    final static DecimalFormat FORMAT = new DecimalFormat("#.00");

    /**
     * 部分提前还款计算（等额本息、期限不变）
     *
     * @param principal      贷款总额
     * @param months         贷款期限
     * @param aheadPrincipal 提前还款金额
     * @param payTimes       已还次数
     * @param rate           贷款利率
     * @return
     */
    public static String[] calculateEqualPrincipalAndInterestApart2(double principal, int months, double aheadPrincipal, int payTimes, double rate) {
        ArrayList<String> data = new ArrayList<String>();
        double monthRate = rate / (100 * 12);//月利率
        double preLoan = (principal * monthRate * Math.pow((1 + monthRate), months)) / (Math.pow((1 + monthRate), months) - 1);//每月还款金额
        double totalMoney = preLoan * months;//还款总额
        double interest = totalMoney - principal;//还款总利息
        double leftLoanBefore = principal * Math.pow(1 + monthRate, payTimes) - preLoan * (Math.pow(1 + monthRate, payTimes) - 1) / monthRate;//提前还款前欠银行的本金
        double leftLoan = principal * Math.pow(1 + monthRate, payTimes + 1) - preLoan * (Math.pow(1 + monthRate, payTimes + 1) - 1) / monthRate - aheadPrincipal;//提前还款后欠银行的本金
//        System.out.println("提前还款前欠银行的钱：" + leftLoanBefore);
//        System.out.println("提前还款后欠银行的钱：" + leftLoan);
        double payLoan = principal - leftLoanBefore;//已还本金
        double payTotal = preLoan * payTimes;//已还总金额
        double payInterest = payTotal - payLoan;//已还利息
        double aheadTotalMoney = aheadPrincipal + preLoan;//提前还款总额
        //计算剩余还款期限
        int leftMonth = (int) Math.floor(Math.log(preLoan / (preLoan - leftLoan * monthRate)) / Math.log(1 + monthRate));
        double newPreLoan = (leftLoan * monthRate * Math.pow((1 + monthRate), leftMonth)) / (Math.pow((1 + monthRate), leftMonth) - 1);//剩余贷款每月还款金额
        double leftTotalMoney = newPreLoan * leftMonth;//剩余还款总额
        double leftInterest = leftTotalMoney - leftLoan;
        double saveInterest = totalMoney - aheadTotalMoney - leftTotalMoney - payTotal;
//        data.add(FORMAT.format(totalMoney));//原还款总额
//        System.out.println("还款总额：" + totalMoney);
//        data.add(FORMAT.format(principal));//贷款总额
//        System.out.println("贷款总额：" + FORMAT.format(principal));
//        data.add(FORMAT.format(interest));//原还款总利息
//        System.out.println("原还款总利息：" + FORMAT.format(interest));
//        data.add(FORMAT.format(preLoan));//原还每月还款金额
//        System.out.println("原还每月还款金额：" + FORMAT.format(preLoan));
//        data.add(FORMAT.format(payTotal));//已还总金额
//        System.out.println("已还总金额：" + FORMAT.format(payTotal));
//        data.add(FORMAT.format(payLoan));//已还本金
//        System.out.println("已还本金：" + FORMAT.format(payLoan));
//        data.add(FORMAT.format(payInterest));//已还利息
//        System.out.println("已还利息：" + FORMAT.format(payInterest));
//        data.add(FORMAT.format(aheadTotalMoney));//提前还款总额
//        System.out.println("提前还款总额：" + FORMAT.format(aheadTotalMoney));
//        data.add(FORMAT.format(leftTotalMoney));//剩余还款总额
//        System.out.println("剩余还款总额：" + FORMAT.format(leftTotalMoney));
//        data.add(FORMAT.format(leftInterest));//剩余还款总利息
//        System.out.println("剩余还款总利息：" + FORMAT.format(leftInterest));
//        data.add(FORMAT.format(newPreLoan));//剩余每月还款金额
//        System.out.println("剩余每月还款金额：" + FORMAT.format(newPreLoan));
//        data.add(FORMAT.format(saveInterest));//节省利息
//        System.out.println("节省利息：" + FORMAT.format(saveInterest));
//        data.add(String.valueOf(leftMonth));//剩余还款期限
//        System.out.println("剩余还款期限：" + FORMAT.format(leftMonth));
        System.out.println("提前还款：" + aheadPrincipal + " 节省利息：" + FORMAT.format(saveInterest) + " 节省比例：" + FORMAT.format(saveInterest / aheadPrincipal));
        return data.toArray(new String[0]);
    }
}

