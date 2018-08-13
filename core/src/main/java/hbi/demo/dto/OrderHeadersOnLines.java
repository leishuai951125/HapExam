package hbi.demo.dto;

import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class OrderHeadersOnLines  extends BaseDTO {
    public static final String FIELD_ORDER_NUMBER = "orderNumber";
    public static final String FIELD_COMPANY_NAME = "companyName";
    public static final String FIELD_CUSTOMER_NAME = "customerName";
    public static final String FIELD_ORDER_DATE = "orderDate";
    public static final String FIELD_SUM_PRICE = "sumPrice";
    public static final String FIELD_ORDER_STATUS = "orderStatus";

    @NotEmpty
    @Length(max = 60)
    private String orderNumber; //订单编号

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Integer sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @NotEmpty

    @Length(max = 240)
    private String companyName; //公司名称

    @NotEmpty
    @Length(max = 240)
    private String customerName; //客户名称

    private Date orderDate; //订单日期

    private Integer sumPrice;//订单总额

    @NotEmpty
    @Length(max = 30)
    private String orderStatus; //订单状态

    @Override
    public String toString() {
        return "OrderHeadersOnLines{" +
                "orderNumber='" + orderNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderDate=" + orderDate +
                ", sumPrice=" + sumPrice +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
