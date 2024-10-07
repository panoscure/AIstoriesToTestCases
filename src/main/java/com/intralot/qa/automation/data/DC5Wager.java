package com.intralot.qa.automation.data;

import com.intralot.qa.automation.core.utilities.CustomProperties;

public class DC5Wager {

    public DC5Wager() {
    }

    int activeDrawId;
    int endDrawId;
    int noOfBoards;
    int[] panel1Numbers;
    int[] panel2Numbers;
    int multiDraws;
    Boolean dc5;
    Boolean jackpot;
    String drawFrom;
    String drawTo;
    String drawFromWithoutZeros;
    String drawToWithoutZeros;
    double totalCost;
    String wagerId;

    public int getActiveDrawId() {
        return activeDrawId;
    }

    public void setActiveDrawId(int activeDrawId) {
        this.activeDrawId = activeDrawId;
    }

    public int getEndDrawId() {
        return endDrawId;
    }

    public void setEndDrawId(int endDrawId) {
        this.endDrawId = endDrawId;
    }

    public int getNoOfBoards() {
        return noOfBoards;
    }

    public void setNoOfBoards(int noOfBoards) {
        this.noOfBoards = noOfBoards;
    }

    public int[] getPanel1Numbers() {
        return panel1Numbers;
    }

    public void setPanel1Numbers(int[] panel1Numbers) {
        this.panel1Numbers = panel1Numbers;
    }

    public int[] getPanel2Numbers() {
        return panel2Numbers;
    }

    public void setPanel2Numbers(int[] panel2Numbers) {
        this.panel2Numbers = panel2Numbers;
    }

    public int getMultiDraws() {
        return multiDraws;
    }

    public void setMultiDraws(int multiDraws) {
        this.multiDraws = multiDraws;
    }

    public Boolean getDc5() {
        return dc5;
    }

    public void setDc5(Boolean dc5) {
        this.dc5 = dc5;
    }

    public Boolean getJackpot() {
        return jackpot;
    }

    public void setJackpot(Boolean jackpot) {
        this.jackpot = jackpot;
    }

    public String getDrawFrom() {
        return drawFrom;
    }

    public void setDrawFrom(String drawFrom) {
        this.drawFrom = drawFrom;
    }

    public String getDrawTo() {
        return drawTo;
    }

    public void setDrawTo(String drawTo) {
        this.drawTo = drawTo;
    }

    public String getDrawFromWithoutZeros() {
        return drawFromWithoutZeros;
    }

    public void setDrawFromWithoutZeros(String drawFromWithoutZeros) {
        this.drawFromWithoutZeros = drawFromWithoutZeros;
    }

    public String getDrawToWithoutZeros() {
        return drawToWithoutZeros;
    }

    public void setDrawToWithoutZeros(String drawToWithoutZeros) {
        this.drawToWithoutZeros = drawToWithoutZeros;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getWagerId() {
        return wagerId;
    }

    public void setWagerId(String wagerId) {
        this.wagerId = wagerId;
    }

    public Double getDC5Cost(){
        if (this.dc5){
            return Double.valueOf(CustomProperties.getPropertyValue("dc5MegaplierPrice")) * this.getNoOfBoards();
        }
        else
            return 0.00;
    }
}
