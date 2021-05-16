package DummyAPI;

import OnlineAPI.Console;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Map;
import java.util.HashMap;

import java.io.*;

public class DummyFacade implements Console{

    private static JSONParser parser = new JSONParser();
    /**
     * SYSTEM
     * */
    @Override
    public JSONObject isGameActive() {
        String response = "{\n" +
                "    \"status\": \"spacetraders is currently online and available to play\"\n" +
                "}";

        JSONObject ret = null;
        try{
            ret = (JSONObject) parser.parse(response);
        } catch (Exception e){ }
        return ret;
    }

    /**
     * USERS
     * */
    @Override
    public JSONObject getUserInfo(String username, String token) {
        File fp = new File("DummyAPI/UserDB.txt");
        BufferedReader reader = null;
        boolean exists = false;
        try{
            reader = new BufferedReader(new FileReader(fp));
            String lineString = null;
            while ((lineString = reader.readLine()) != null){
                String[] ans = lineString.split(",", 2);
                if (ans[0].equals(username) && ans[1].equals(token)){
                    exists = true;
                    break;
                }
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            if (reader != null){
                try{
                    reader.close();
                } catch (IOException e){}
            }
        }
        return null;
    }

    @Override
    public JSONObject claimUserName(String username) {
        return null;
    }

    /**
     * LOANS
     * */
    @Override
    public JSONObject requestLoan(String token, String username, String type){
        return null;
    }

    @Override
    public JSONObject viewAvailableLoans(String token){
        return null;
    }

    @Override
    public JSONObject getActiveLoans(String username, String token) {return null;}

    @Override
    public JSONObject payOffLoan(String username, String token, String loanID){ return null; }

    /**
     * SHIPS
     * */
    @Override
    public JSONObject viewAvailableShips(String token, String shipClass){
        return null;
    }

    @Override
    public JSONObject buyShip(String username, String token, String location, String type){
        return null;
    }

    @Override
    public JSONObject getShips(String username, String token){
        return null;
    }

    @Override
    public JSONObject getShip(String username, String token, String shipID) { return null; }

    @Override
    public JSONObject getGood(String username, String token, String shipId) {return null;}

    /**
     * PURCHASE
     * */
    @Override
    public JSONObject buyFuel(String username, String token, String shipId, String good, String quantity){
        return null;
    }

    /**
     * MARKETPLACE
     * */
    @Override
    public JSONObject viewMarketPlace(String token, String symbol){ return null; }

    @Override
    public JSONObject listNearBy(String token, String symbol, String type){ return null; }

    /**
     * FLIGHT PLAN
     * */
    @Override
    public JSONObject viewFlightPlan(String username, String token, String flightPlanId){ return null; }

    @Override
    public JSONObject createFlightPlan(String username, String token, String shipId, String destination){ return null; }

    /**
     * SELL
     * */
    @Override
    public JSONObject sell(String username, String token, String shipId, String good, String quantity){ return null; }

}
