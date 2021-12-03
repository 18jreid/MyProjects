package com.example.cs3200_hw3.models;

public class Scorecard {
    public String user;
    public boolean isNineHoles;
    public boolean isEighteenHoles;
    public boolean isFinished;
    private int hole1Score;
    private int hole1par;
    private int hole1distance;

    private int hole2Score;
    private int hole2par;
    private int hole2distance;

    private int hole3Score;
    private int hole3par;
    private int hole3distance;

    private int hole4Score;
    private int hole4par;
    private int hole4distance;

    private int hole5Score;
    private int hole5par;
    private int hole5distance;

    private int hole6Score;
    private int hole6par;
    private int hole6distance;

    private int hole7Score;
    private int hole7par;
    private int hole7distance;

    private int hole8Score;
    private int hole8par;
    private int hole8distance;

    private int hole9Score;
    private int hole9par;
    private int hole9distance;

    private int hole10Score;
    private int hole10par;
    private int hole10distance;

    private int hole11Score;
    private int hole11par;
    private int hole11distance;

    private int hole12Score;
    private int hole12par;
    private int hole12distance;

    private int hole13Score;
    private int hole13par;
    private int hole13distance;

    private int hole14Score;
    private int hole14par;
    private int hole14distance;

    private int hole15Score;
    private int hole15par;
    private int hole15distance;

    private int hole16Score;
    private int hole16par;
    private int hole16distance;

    private int hole17Score;
    private int hole17par;
    private int hole17distance;

    private int hole18Score;
    private int hole18par;
    private int hole18distance;

    private int totalScore;

    public Scorecard() {}

    public Scorecard(String user, boolean isNineHoles, int hole1Score, int hole1Par, int hole1Distance,
                     int hole2Score, int hole2Par, int hole2Distance,
                     int hole3Score, int hole3Par, int hole3Distance,
                     int hole4Score, int hole4Par, int hole4Distance,
                     int hole5Score, int hole5Par, int hole5Distance,
                     int hole6Score, int hole6Par, int hole6Distance,
                     int hole7Score, int hole7Par, int hole7Distance,
                     int hole8Score, int hole8Par, int hole8Distance,
                     int hole9Score, int hole9Par, int hole9Distance) {

        this.user = user;
        this.hole1Score = hole1Score; this.hole1par = hole1Par; this.hole1distance = hole1Distance;
        this.hole2Score = hole2Score; this.hole2par = hole2Par; this.hole2distance = hole2Distance;
        this.hole3Score = hole3Score; this.hole3par = hole3Par; this.hole3distance = hole3Distance;
        this.hole4Score = hole4Score; this.hole4par = hole4Par; this.hole4distance = hole4Distance;
        this.hole5Score = hole5Score; this.hole5par = hole5Par; this.hole5distance = hole5Distance;
        this.hole6Score = hole6Score; this.hole6par = hole6Par; this.hole6distance = hole6Distance;
        this.hole7Score = hole7Score; this.hole7par = hole7Par; this.hole7distance = hole7Distance;
        this.hole8Score = hole8Score; this.hole8par = hole8Par; this.hole8distance = hole8Distance;
        this.hole9Score = hole9Score; this.hole9par = hole9Par; this.hole9distance = hole9Distance;
        this.totalScore = 0;
        this.isNineHoles = isNineHoles;
        this.isEighteenHoles = false;
    }

    public Scorecard(String user, boolean isEighteenHoles, int hole1Score, int hole1Par, int hole1Distance,
                     int hole2Score, int hole2Par, int hole2Distance,
                     int hole3Score, int hole3Par, int hole3Distance,
                     int hole4Score, int hole4Par, int hole4Distance,
                     int hole5Score, int hole5Par, int hole5Distance,
                     int hole6Score, int hole6Par, int hole6Distance,
                     int hole7Score, int hole7Par, int hole7Distance,
                     int hole8Score, int hole8Par, int hole8Distance,
                     int hole9Score, int hole9Par, int hole9Distance,
                     int hole10Score, int hole10Par, int hole10Distance,
                     int hole11Score, int hole11Par, int hole11Distance,
                     int hole12Score, int hole12Par, int hole12Distance,
                     int hole13Score, int hole13Par, int hole13Distance,
                     int hole14Score, int hole14Par, int hole14Distance,
                     int hole15Score, int hole15Par, int hole15Distance,
                     int hole16Score, int hole16Par, int hole16Distance,
                     int hole17Score, int hole17Par, int hole17Distance,
                     int hole18Score, int hole18Par, int hole18Distance) {

        this.user = user;
        this.hole1Score = hole1Score; this.hole1par = hole1Par; this.hole1distance = hole1Distance;
        this.hole2Score = hole2Score; this.hole2par = hole2Par; this.hole2distance = hole2Distance;
        this.hole3Score = hole3Score; this.hole3par = hole3Par; this.hole3distance = hole3Distance;
        this.hole4Score = hole4Score; this.hole4par = hole4Par; this.hole4distance = hole4Distance;
        this.hole5Score = hole5Score; this.hole5par = hole5Par; this.hole5distance = hole5Distance;
        this.hole6Score = hole6Score; this.hole6par = hole6Par; this.hole6distance = hole6Distance;
        this.hole7Score = hole7Score; this.hole7par = hole7Par; this.hole7distance = hole7Distance;
        this.hole8Score = hole8Score; this.hole8par = hole8Par; this.hole8distance = hole8Distance;
        this.hole9Score = hole9Score; this.hole9par = hole9Par; this.hole9distance = hole9Distance;
        this.hole10Score = hole10Score; this.hole10par = hole10Par; this.hole10distance = hole10Distance;
        this.hole11Score = hole11Score; this.hole11par = hole11Par; this.hole11distance = hole11Distance;
        this.hole12Score = hole12Score; this.hole12par = hole12Par; this.hole12distance = hole12Distance;
        this.hole13Score = hole13Score; this.hole13par = hole13Par; this.hole13distance = hole13Distance;
        this.hole14Score = hole14Score; this.hole14par = hole14Par; this.hole14distance = hole14Distance;
        this.hole15Score = hole15Score; this.hole15par = hole15Par; this.hole15distance = hole15Distance;
        this.hole16Score = hole16Score; this.hole16par = hole16Par; this.hole16distance = hole16Distance;
        this.hole17Score = hole17Score; this.hole17par = hole17Par; this.hole17distance = hole17Distance;
        this.hole18Score = hole18Score; this.hole18par = hole18Par; this.hole18distance = hole18Distance;
        this.totalScore = 0;
        this.isEighteenHoles = isEighteenHoles;
        this.isNineHoles = false;
    }

    public int getHole1Score() {
        return hole1Score;
    }

    public int getHole1par() {
        return hole1par;
    }

    public int getHole1distance() {
        return hole1distance;
    }

    public int getHole2Score() {
        return hole2Score;
    }

    public int getHole2par() {
        return hole2par;
    }

    public int getHole2distance() {
        return hole2distance;
    }

    public int getHole3Score() {
        return hole3Score;
    }

    public int getHole3par() {
        return hole3par;
    }

    public int getHole3distance() {
        return hole3distance;
    }

    public int getHole4Score() {
        return hole4Score;
    }

    public int getHole4par() {
        return hole4par;
    }

    public int getHole4distance() {
        return hole4distance;
    }

    public int getHole5Score() {
        return hole5Score;
    }

    public int getHole5par() {
        return hole5par;
    }

    public int getHole5distance() {
        return hole5distance;
    }

    public int getHole6Score() {
        return hole6Score;
    }

    public int getHole6par() {
        return hole6par;
    }

    public int getHole6distance() {
        return hole6distance;
    }

    public int getHole7Score() {
        return hole7Score;
    }

    public int getHole7par() {
        return hole7par;
    }

    public int getHole7distance() {
        return hole7distance;
    }

    public int getHole8Score() {
        return hole8Score;
    }

    public int getHole8par() {
        return hole8par;
    }

    public int getHole8distance() {
        return hole8distance;
    }

    public int getHole9Score() {
        return hole9Score;
    }

    public int getHole9par() {
        return hole9par;
    }

    public int getHole9distance() {
        return hole9distance;
    }

    public int getHole10Score() {
        return hole10Score;
    }

    public int getHole10par() {
        return hole10par;
    }

    public int getHole10distance() {
        return hole10distance;
    }

    public int getHole11Score() {
        return hole11Score;
    }

    public int getHole11par() {
        return hole11par;
    }

    public int getHole11distance() {
        return hole11distance;
    }

    public int getHole12Score() {
        return hole12Score;
    }

    public int getHole12par() {
        return hole12par;
    }

    public int getHole12distance() {
        return hole12distance;
    }

    public int getHole13Score() {
        return hole13Score;
    }

    public int getHole13par() {
        return hole13par;
    }

    public int getHole13distance() {
        return hole13distance;
    }

    public int getHole14Score() {
        return hole14Score;
    }

    public int getHole14par() {
        return hole14par;
    }

    public int getHole14distance() {
        return hole14distance;
    }

    public int getHole15Score() {
        return hole15Score;
    }

    public int getHole16Score() {
        return hole16Score;
    }

    public int getHole16par() {
        return hole16par;
    }

    public int getHole16distance() {
        return hole16distance;
    }

    public int getHole17Score() {
        return hole17Score;
    }

    public int getHole17par() {
        return hole17par;
    }

    public int getHole17distance() {
        return hole17distance;
    }

    public int getHole18Score() {
        return hole18Score;
    }

    public int getHole18par() {
        return hole18par;
    }

    public int getHole18distance() {
        return hole18distance;
    }

    public String getUser() {
        return user;
    }

    public int getTotalScore() {
        return getHole1Score() + getHole2Score() + getHole3Score() + getHole4Score() + getHole5Score() + getHole6Score() + getHole7Score() + getHole8Score() + getHole9Score() + getHole10Score() + getHole11Score() + getHole12Score() + getHole13Score() + getHole14Score() + getHole15Score() + getHole16Score() + getHole17Score() + getHole18Score();
    }

    public boolean isEighteenHoles() {
        return isEighteenHoles;
    }

    public boolean isFinished() {
        return false;
    }

    public boolean isNineHoles() {
        return isNineHoles;
    }
}
