package validator.admin.AdminSellsService.CreateSells;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarwar on 2/2/2017.
 */
public class createSellsForm {

    @NotNull(message = "Selling Amount are required")
    private Float sellingAmount;
    private  String sellingComment;

    @NotNull(message = "Status is required")
    private boolean isCombo;

    @NotNull(message = "Total  quantity are required")
    private Integer totalQuantity;

    @NotNull(message = "Terminal are required")
    private Integer terminalId;

    private String concessionProductIds;
    private String comboIds;
    private String seatTypeIds;

    @NotNull(message = "Sell amount are required")
    private Float unitSellingAmount;

    @NotNull(message = "Quantity are required")
    private Integer quantity;


    private List<Integer> concessionProductIdsArray = new ArrayList<>();
    private List<Integer> comboIdsArray = new ArrayList<>();
    private List<Integer> seatTypeIdsArray = new ArrayList<>();

    public Float getSellingAmount() {
        return sellingAmount;
    }

    public void setSellingAmount(Float sellingAmount) {
        this.sellingAmount = sellingAmount;
    }

    public String getSellingComment() {
        return sellingComment;
    }

    public void setSellingComment(String sellingComment) {
        this.sellingComment = sellingComment;
    }

    public boolean isCombo() {
        return isCombo;
    }

    public void setCombo(boolean combo) {
        isCombo = combo;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

    public String getConcessionProductIds() {
        return concessionProductIds;
    }

    public void setConcessionProductIds(String concessionProductIds) {
        this.concessionProductIds = concessionProductIds;
    }

    public String getComboIds() {
        return comboIds;
    }

    public void setComboIds(String comboIds) {
        this.comboIds = comboIds;
    }

    public String getSeatTypeIds() {
        return seatTypeIds;
    }

    public void setSeatTypeIds(String seatTypeIds) {
        this.seatTypeIds = seatTypeIds;
    }

    public Float getUnitSellingAmount() {
        return unitSellingAmount;
    }

    public void setUnitSellingAmount(Float unitSellingAmount) {
        this.unitSellingAmount = unitSellingAmount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Integer> getConcessionProductIdsArray() {
        return concessionProductIdsArray;
    }

    public void setConcessionProductIdsArray(List<Integer> concessionProductIdsArray) {
        this.concessionProductIdsArray = concessionProductIdsArray;
    }

    public List<Integer> getComboIdsArray() {
        return comboIdsArray;
    }

    public void setComboIdsArray(List<Integer> comboIdsArray) {
        this.comboIdsArray = comboIdsArray;
    }

    public List<Integer> getSeatTypeIdsArray() {
        return seatTypeIdsArray;
    }

    public void setSeatTypeIdsArray(List<Integer> seatTypeIdsArray) {
        this.seatTypeIdsArray = seatTypeIdsArray;
    }

    @Override
    public String toString() {
        return "createSellsForm{" +
                "sellingAmount=" + sellingAmount +
                ", sellingComment='" + sellingComment + '\'' +
                ", isCombo=" + isCombo +
                ", totalQuantity=" + totalQuantity +
                ", terminalId=" + terminalId +
                ", concessionProductIds='" + concessionProductIds + '\'' +
                ", comboIds='" + comboIds + '\'' +
                ", seatTypeIds='" + seatTypeIds + '\'' +
                ", unitSellingAmount=" + unitSellingAmount +
                ", quantity=" + quantity +
                ", concessionProductIdsArray=" + concessionProductIdsArray +
                ", comboIdsArray=" + comboIdsArray +
                ", seatTypeIdsArray=" + seatTypeIdsArray +
                '}';
    }
}
