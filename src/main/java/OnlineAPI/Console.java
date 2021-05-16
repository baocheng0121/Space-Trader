package OnlineAPI;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.util.Iterator;
import java.io.IOException;

public interface Console {

    /**
     * SYSTEM
     * */
    JSONObject isGameActive();

    /**
     * USERS
     * */
    JSONObject claimUserName(String username);

    JSONObject getUserInfo(String username, String token);

    /**
     * LOANS
     * */
    JSONObject viewAvailableLoans(String token);

    JSONObject requestLoan(String token, String username, String type);

    JSONObject getActiveLoans(String username, String token);

    JSONObject payOffLoan(String username, String token, String loanID);

    /**
     * SHIPS
     * */
    JSONObject viewAvailableShips(String token, String shipClass);

    JSONObject buyShip(String username, String token, String location, String type);

    JSONObject getShips(String username, String token);

    JSONObject getShip(String username, String token, String shipID);

    JSONObject getGood(String username, String token, String shipId);

    /**
     * PURCHASE
     * */
    JSONObject buyFuel(String username, String token, String shipId, String good, String quantity);

    /**
     * MARKETPLACE
     * */
    JSONObject viewMarketPlace(String token, String symbol);

    JSONObject listNearBy(String token, String symbol, String type);

    /**
     * FLIGHT PLAN
     * */
    JSONObject viewFlightPlan(String username, String token, String flightPlanId);

    JSONObject createFlightPlan(String username, String token, String shipId, String destination);

    /**
     * SELL
     * */
    JSONObject sell(String username, String token, String shipId, String good, String quantity);

}
