package com.ray.framework.util;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Title:Financials     
 * @Description: PMT
 * @Auth:LiangRui   
 * @CreateTime:2016年8月17日 下午3:01:22       
 * @version V1.0
 */
public class PmtTools {
    private static final NumberFormat nfPercent;
    private static final NumberFormat nfCurrency;
    static {
        // establish percentage formatter.
        nfPercent = NumberFormat.getPercentInstance();
        nfPercent.setMinimumFractionDigits(2);
        nfPercent.setMaximumFractionDigits(4);
        // establish currency formatter.
        nfCurrency = NumberFormat.getCurrencyInstance();
        nfCurrency.setMinimumFractionDigits(2);
        nfCurrency.setMaximumFractionDigits(2);
    }
    
  
    public static String formatCurrency(double number) {
        return nfCurrency.format(number);
    }
    
   
    public static String formatPercent(double number) {
        return nfPercent.format(number);
    }
    
     
    public static double stringToPercent(String s) throws ParseException {
        return nfPercent.parse(s).doubleValue();
    }
    
    /**
     * @param r
     *            - 利率
     * @param nper
     *            - 租赁期数
     * @param pv
     *            - 租金
     * @param fv
     *            - 最后一期希望付款的现金余额（可省略此值不填，省略时默认值为0）
     * @param type
     *            - 期初值1，期末值0 
     */
    public static double pmt(double yearRate, int totalMonth, double invest, double fv, int type) {
        /* double pmt = yearRate / (Math.pow(1 + yearRate, totalMonth) - 1) * -(invest * Math.pow(1 + yearRate, totalMonth) + fv);
        if (type == 1){
            pmt = pmt/(1 + yearRate);
        }*/
    	//return pmt;
        BigDecimal monthIncome = new BigDecimal(invest).multiply(new BigDecimal(yearRate * Math.pow(1 + yearRate, totalMonth)))
                .divide(new BigDecimal(Math.pow(1 + yearRate, totalMonth) - 1), 2, BigDecimal.ROUND_HALF_UP);
        if(type == 1){
        	monthIncome = monthIncome.divide(new BigDecimal(1+yearRate),2,BigDecimal.ROUND_HALF_UP);
        }
        return monthIncome.doubleValue();
    }
    
    public static double pmt(double r, int nper, double pv, double fv) {
        return pmt(r, nper, pv, fv, 0);
    }
    
    public static double pmt(double r, int nper, double pv) {
        return pmt(r, nper, pv, 0);
    }
    
    /**
     * @param r
     *            - periodic interest rate represented as a decimal.
     * @param nper
     *            - number of total payments / periods.
     * @param c
     *            - periodic payment amount.
     * @param pv 
     *            - present value -- borrowed or invested principal.
     * @param type
     *            - when payment is made: beginning of period is 1; end, 0.
     */
    public static double fv(double yearRate, int totalMonth, double c, double invest, int type) {
        if (type == 1){
            c *= (1 + yearRate);
        }
        double fv = -((Math.pow(1 + yearRate, totalMonth) - 1) / yearRate * c + invest * Math.pow(1 + yearRate, totalMonth));
        return fv;
    }
    
    public static double fv(double r, int nper, double c, double pv) {
        return fv(r, nper, c, pv);
    }
    
    /**
     * @param r
     *            - 利率
     * @param per
     *            - 付款期号.
     * @param nper
     *            - 租赁期数
     * @param pv
     *            - 租金
     * @param fv
     *            - 最后一期希望付款的现金余额（可省略此值不填，省略时默认值为0）
     * @param type
     *            - 期初值1，期末值0 
     */
    public static Map<Integer, BigDecimal> ipmt(double monthRate, int totalMonth, double invest, double fv, int type) {
    	/*Map<Integer, Double> map = new HashMap<Integer, Double>();
    	for (int i = 1; i < totalMonth+1; i++) {
    		double ipmt = fv(yearRate, i - 1, pmt(yearRate, totalMonth, invest, fv, type), invest, type) * yearRate;
            if (type == 1){
                ipmt = ipmt/(1 + yearRate);
            }
            DecimalFormat df = new DecimalFormat("#.00");
            String val = df.format(Math.abs(ipmt));
    		map.put(i,Double.parseDouble(val));
		}*/
	    Map<Integer, BigDecimal> map = new LinkedHashMap<Integer, BigDecimal>();
        BigDecimal monthInterest;
        for (int i = 1; i < totalMonth + 1; i++) {
           BigDecimal multiply = new BigDecimal(invest).multiply(new BigDecimal(monthRate));
           BigDecimal sub  = new BigDecimal(Math.pow(1 + monthRate, totalMonth)).subtract(new BigDecimal(Math.pow(1 + monthRate, i-1)));
           monthInterest = multiply.multiply(sub).divide(new BigDecimal(Math.pow(1 + monthRate, totalMonth) - 1), 6, BigDecimal.ROUND_DOWN);
           monthInterest = monthInterest.setScale(2, BigDecimal.ROUND_HALF_UP);
           if (type == 1){
        	   monthInterest = monthInterest.divide(new BigDecimal(1+monthRate),3,BigDecimal.ROUND_HALF_UP);
           }
           map.put(i, monthInterest);
        }
        return map;
    }
    
    /**
     * @param r
     *            - 利率
     * @param per
     *            - 付款期号.
     * @param nper
     *            - 租赁期数
     * @param pv
     *            - 租金
     * @param fv
     *            - 最后一期希望付款的现金余额（可省略此值不填，省略时默认值为0）
     * @param type
     *            - 期初值1，期末值0 
     */
    public static Map<Integer, BigDecimal> ppmt(double r, int nper, double pv, double fv, int type) {
    	 DecimalFormat df = new DecimalFormat("#.00");  
    	 Map<Integer, BigDecimal> mapVal = new LinkedHashMap<Integer, BigDecimal>();
    	 Double dbVal = pmt(r, nper, pv, fv, type);
    	 String val = df.format(Math.abs(dbVal));
         Map<Integer, BigDecimal> maps = ipmt(r, nper, pv, fv, type);
         for (Map.Entry<Integer, BigDecimal> entry : maps.entrySet()) {
        	 BigDecimal results = new BigDecimal(val).subtract(entry.getValue());
        	 mapVal.put(entry.getKey(),results);
         }
        return mapVal;
    }
    
    
    public static double getInterestCount(double r, int nper, double pv, double fv, int type) {
    	DecimalFormat df = new DecimalFormat("#.00");  
    	BigDecimal count = new BigDecimal(0);
        Map<Integer, BigDecimal> mapInterest = ipmt(r,nper,pv,0,type);
        for (Map.Entry<Integer, BigDecimal> entry : mapInterest.entrySet()) {
            count = count.add(entry.getValue());
        }
        String val = df.format(count.doubleValue());
        return Double.parseDouble(val);
    }
    
    
    public static double getPrincipalInterestCount(double r, int nper, double pv, double fv, int type) {
        BigDecimal perMonthInterest = new BigDecimal(pv)
        .multiply(new BigDecimal(r * Math.pow(1 + r, nper)))
        .divide(new BigDecimal(Math.pow(1 + r, nper) - 1), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal count = perMonthInterest.multiply(new BigDecimal(nper));
        count = count.setScale(2, BigDecimal.ROUND_HALF_UP);
        return count.doubleValue();
    }
    
    
    /****/
    public static void main(String[] args) {
    	double invest = 750000.0;     // 本金
        double yearRate = 0.15/12; // 年利率
        int totalMonth = 11;       // 租赁周期
        //int firstPay = 1;
        int endPay = 0;  //期初
        DecimalFormat df = new DecimalFormat("#.000");   
        double money1 = Math.abs(pmt(yearRate,totalMonth,invest,0,endPay));
        System.out.println("等额本息---每月还款本息：" + df.format(Double.parseDouble(money1+"")));
        Map<Integer, BigDecimal> mapInterest = ipmt(yearRate,totalMonth,invest,0,endPay);
        System.out.println("等额本息---每月还款利息：" + mapInterest);
        Map<Integer, BigDecimal> mapPrincipal = ppmt(yearRate,totalMonth,invest,0,endPay);
        System.out.println("等额本息---每月还款本金：" + mapPrincipal);
        double count = getInterestCount(yearRate,totalMonth,invest,0,endPay);
        System.out.println("等额本息---总利息：" + count);
        double principalInterestCount = getPrincipalInterestCount(yearRate,totalMonth,invest,0,endPay);
        System.out.println("等额本息---应还本息总和：" + principalInterestCount);
    } 
}


