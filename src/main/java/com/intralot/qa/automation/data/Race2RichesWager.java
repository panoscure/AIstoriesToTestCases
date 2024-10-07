package com.intralot.qa.automation.data;

import com.intralot.qa.automation.core.utilities.CustomProperties;

public class Race2RichesWager {

    public Race2RichesWager() {
    }

    int activeDrawId;
    int endDrawId;
    int noOfBoards;
    int horseNumber;
    int[] panel2Numbers;
    int multiRaces;
    Boolean race2Riches;
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

    public int getHorseNumber() {
        return horseNumber;
    }

    public void horseNumber(int horseNumber) {
        this.horseNumber = horseNumber;
    }

    public int[] getPanel2Numbers() {
        return panel2Numbers;
    }

    public void setPanel2Numbers(int[] panel2Numbers) {
        this.panel2Numbers = panel2Numbers;
    }

    public int getMultiRaces() {
        return multiRaces;
    }

    public void setMultiRaces(int multiRaces) {
        this.multiRaces = multiRaces;
    }

    public Boolean getR2rPlay() {
        return race2Riches;
    }

    public void setR2rPlay(Boolean race2Riches) {
        this.race2Riches = race2Riches;
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

    public Double getPowerPlierCost(){
        if (this.race2Riches){
            return Double.valueOf(CustomProperties.getPropertyValue("race2RichesMegaplierPrice")) * this.getNoOfBoards();
        }
        else
            return 0.00;
    }
}
