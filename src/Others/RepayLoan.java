package Others;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/**
 * https://blog.csdn.net/flueky/article/details/77099454
 */
public class RepayLoan {
    static DecimalFormat FORMAT = new DecimalFormat("#,###0.##");

    /**
     * 部分提前还款计算（等额本息、月供不变）
     *
     * @param principal      贷款总额
     * @param months         贷款期限
     * @param aheadPrincipal 提前还款金额
     * @param payTimes       已还次数
     * @param rate           贷款利率，百分号前面的部分，如5代表利率为5%
     * @return
     */
    public static List<String> calculateEqualPrincipalAndInterestApart(double principal, int months, double aheadPrincipal, int payTimes, double rate) {
        ArrayList<String> data = new ArrayList<>();
        double monthRate = rate / (100 * 12);//月利率
        double preLoan = (principal * monthRate * Math.pow((1 + monthRate), months)) / (Math.pow((1 + monthRate), months) - 1);//每月还款金额
        double totalMoney = preLoan * months;//还款总额
        double interest = totalMoney - principal;//还款总利息
        double leftLoanBefore = principal * Math.pow(1 + monthRate, payTimes) - preLoan * (Math.pow(1 + monthRate, payTimes) - 1) / monthRate;//提前还款前欠银行的钱
        double leftLoan = principal * Math.pow(1 + monthRate, payTimes + 1) - preLoan * (Math.pow(1 + monthRate, payTimes + 1) - 1) / monthRate - aheadPrincipal;//提前还款后欠银行的钱
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
        LocalDate lastRepayMonth = LocalDate.now().plusMonths(leftMonth);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
//        data.add("\n原还款总额: " +  FORMAT.format(totalMoney));//原还款总额
//        data.add("\n贷款总额: " +  FORMAT.format(principal));//贷款总额
        data.add("\n原还款总利息: " +  FORMAT.format(interest));//原还款总利息
//        data.add("\n原还每月还款金额: " +  FORMAT.format(preLoan));//原还每月还款金额
//        data.add("\n已还总金额: " +  FORMAT.format(payTotal));//已还总金额
//        data.add("\n已还本金: " +  FORMAT.format(payLoan));//已还本金
//        data.add("\n已还利息: " +  FORMAT.format(payInterest));//已还利息
        data.add("\n提前还款总额: " +  FORMAT.format(aheadTotalMoney));//提前还款总额
        data.add("\n剩余还款总额: " +  FORMAT.format(leftTotalMoney));//剩余还款总额
        data.add("\n剩余还款总利息: " +  FORMAT.format(leftInterest));//剩余还款总利息
//        data.add("\n剩余每月还款金额: " +  FORMAT.format(newPreLoan));//剩余每月还款金额
        data.add("\n节省利息: " +  FORMAT.format(saveInterest));//节省利息
        data.add("\n节省利息/还款总额: " +  FORMAT.format(saveInterest /aheadTotalMoney));//节省利息

//        data.add("\n剩余还款期限: " + leftMonth);//剩余还款期限
        data.add("\n最后还款日期: " + dtf.format(lastRepayMonth));//最后还款日期
        return data;
    }

    public static void main(String[] args) {
        double principle = 1500000;
        int months = 360;
        int payTimes = 12;
        double rate = 5.73;
        for (int i = 40; i <= 60; i+=5) {
            System.out.println(calculateEqualPrincipalAndInterestApart(1500000, 360, i * 10000, 12, 5.73));
        }
    }
}
