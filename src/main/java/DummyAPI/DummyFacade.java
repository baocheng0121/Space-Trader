package DummyAPI;

import OnlineAPI.Console;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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
        if (!token.equals("84646acb-abb4-4472-9d09-86afb6b123d8")){
            return null;
        }
        String response = String.format("{\n" +
                "    \"user\": {\n" +
                "        \"username\": \"%s\",\n" +
                "        \"credits\": 0,\n" +
                "        \"ships\": [],\n" +
                "        \"loans\": []\n" +
                "    }\n" +
                "}",username);
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject claimUserName(String username) {
        String response = String.format("{\n" +
                "    \"token\": \"84646acb-abb4-4472-9d09-86afb6b123d8\",\n" +
                "    \"user\": {\n" +
                "        \"username\": \"%s\",\n" +
                "        \"credits\": 0,\n" +
                "        \"ships\": [],\n" +
                "        \"loans\": []\n" +
                "    }\n" +
                "}", username);
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * LOANS
     * */
    @Override
    public JSONObject requestLoan(String token, String username, String type){
        if (!type.equalsIgnoreCase("STARTUP")){
            return null;
        }

        String response = "\"credits\": 200000,\n" +
                "    \"loan\": {\n" +
                "        \"id\": \"ckoqjb4nr13872811bs68hjosa1w\",\n" +
                "        \"due\": \"2021-05-18T02:03:30.515Z\",\n" +
                "        \"repaymentAmount\": 280000,\n" +
                "        \"status\": \"CURRENT\",\n" +
                "        \"type\": \"STARTUP\"\n" +
                "    }";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject viewAvailableLoans(String token){
        String response = "{\n" +
                "    \"loans\": [\n" +
                "        {\n" +
                "            \"amount\": 200000,\n" +
                "            \"collateralRequired\": false,\n" +
                "            \"rate\": 40,\n" +
                "            \"termInDays\": 2,\n" +
                "            \"type\": \"STARTUP\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getActiveLoans(String username, String token) {
        String response =
                "{\n" +
                "            \"loans\": [\n" +
                "            {\n" +
                "                \"id\": \"ckoqjb4nr13872811bs68hjosa1w\",\n" +
                "                    \"due\": \"2021-05-18T02:03:30.515Z\",\n" +
                "                    \"repaymentAmount\": 280000,\n" +
                "                    \"status\": \"CURRENT\",\n" +
                "                    \"type\": \"STARTUP\"\n" +
                "            }\n" +
                "    ]\n" +
                "}";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject payOffLoan(String username, String token, String loanID){
        if (!loanID.equalsIgnoreCase("ckoqjb4nr13872811bs68hjosa1w")){
            return null;
        }
        String response =
                    "\"user\": {\n" +
                    "    \"credits\": 32000,\n" +
                    "    \"loans\": [\n" +
                    "      {\n" +
                    "        \"due\": \"2021-03-10T00:39:03.890Z\",\n" +
                    "        \"id\": \"ckoqjb4nr13872811bs68hjosa1w\",\n" +
                    "        \"repaymentAmount\": 168000,\n" +
                    "        \"status\": \"PAID\",\n" +
                    "        \"type\": \"STARTUP\"\n" +
                    "      }\n" +
                    "    ],\n" +
                    "    \"ships\": [\n" +
                    "      {\n" +
                    "        \"cargo\": [\n" +
                    "          {\n" +
                    "            \"good\": \"FUEL\",\n" +
                    "            \"quantity\": 4,\n" +
                    "            \"totalVolume\": 4\n" +
                    "          }\n" +
                    "        ],\n" +
                    "        \"class\": \"MK-I\",\n" +
                    "        \"id\": \"cklzv1dih0238jpvqvr19pk62\",\n" +
                    "        \"location\": \"OE-PM\",\n" +
                    "        \"manufacturer\": \"Jackshaw\",\n" +
                    "        \"maxCargo\": 100,\n" +
                    "        \"plating\": 5,\n" +
                    "        \"spaceAvailable\": 96,\n" +
                    "        \"speed\": 2,\n" +
                    "        \"type\": \"JW-MK-I\",\n" +
                    "        \"weapons\": 5\n" +
                    "      }\n" +
                    "    ],\n" +
                    "    \"username\": \"space-trader\"\n" +
                    "  }";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * SHIPS
     * */
    @Override
    public JSONObject viewAvailableShips(String token, String shipClass){
        String response =
                "{\n" +
                        "  \"ships\": [\n" +
                        "    {\n" +
                        "      \"class\": \"MK-I\",\n" +
                        "      \"manufacturer\": \"Gravager\",\n" +
                        "      \"maxCargo\": 300,\n" +
                        "      \"plating\": 10,\n" +
                        "      \"purchaseLocations\": [\n" +
                        "        {\n" +
                        "          \"location\": \"OE-PM-TR\",\n" +
                        "          \"price\": 184000\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"location\": \"OE-NY\",\n" +
                        "          \"price\": 184000\n" +
                        "        }\n" +
                        "      ],\n" +
                        "      \"speed\": 1,\n" +
                        "      \"type\": \"GR-MK-I\",\n" +
                        "      \"weapons\": 5\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"class\": \"MK-I\",\n" +
                        "      \"manufacturer\": \"Jackshaw\",\n" +
                        "      \"maxCargo\": 100,\n" +
                        "      \"plating\": 5,\n" +
                        "      \"purchaseLocations\": [\n" +
                        "        {\n" +
                        "          \"location\": \"OE-PM-TR\",\n" +
                        "          \"price\": 94000\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"location\": \"OE-NY\",\n" +
                        "          \"price\": 94000\n" +
                        "        }\n" +
                        "      ],\n" +
                        "      \"speed\": 2,\n" +
                        "      \"type\": \"JW-MK-I\",\n" +
                        "      \"weapons\": 5\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"class\": \"MK-I\",\n" +
                        "      \"manufacturer\": \"Electrum\",\n" +
                        "      \"maxCargo\": 100,\n" +
                        "      \"plating\": 5,\n" +
                        "      \"purchaseLocations\": [\n" +
                        "        {\n" +
                        "          \"location\": \"OE-NY\",\n" +
                        "          \"price\": 302400\n" +
                        "        }\n" +
                        "      ],\n" +
                        "      \"speed\": 2,\n" +
                        "      \"type\": \"EM-MK-I\",\n" +
                        "      \"weapons\": 10\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject buyShip(String username, String token, String location, String type){
        String response =
                "{\n" +
                        "  \"credits\": 178875,\n" +
                        "  \"ship\": {\n" +
                        "    \"cargo\": [],\n" +
                        "    \"class\": \"MK-I\",\n" +
                        "    \"id\": \"ckno9324k0079iiop71j5nrob\",\n" +
                        "    \"location\": \"OE-PM-TR\",\n" +
                        "    \"manufacturer\": \"Jackshaw\",\n" +
                        "    \"maxCargo\": 50,\n" +
                        "    \"plating\": 5,\n" +
                        "    \"spaceAvailable\": 50,\n" +
                        "    \"speed\": 1,\n" +
                        "    \"type\": \"JW-MK-I\",\n" +
                        "    \"weapons\": 5,\n" +
                        "    \"x\": 23,\n" +
                        "    \"y\": -28\n" +
                        "  }\n" +
                        "}";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getShips(String username, String token){
        String response =
                "{\n" +
                        "    \"ships\": [\n" +
                        "        {\n" +
                        "            \"id\": \"ckoqjtqoh579601bs6puo9q1t7\",\n" +
                        "            \"location\": \"OE-PM-TR\",\n" +
                        "            \"x\": 21,\n" +
                        "            \"y\": -24,\n" +
                        "            \"cargo\": [],\n" +
                        "            \"spaceAvailable\": 50,\n" +
                        "            \"type\": \"JW-MK-I\",\n" +
                        "            \"class\": \"MK-I\",\n" +
                        "            \"maxCargo\": 50,\n" +
                        "            \"speed\": 1,\n" +
                        "            \"manufacturer\": \"Jackshaw\",\n" +
                        "            \"plating\": 5,\n" +
                        "            \"weapons\": 5\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getShip(String username, String token, String shipID) {
        String response =
                "{\n" +
                        "    \"ship\": {\n" +
                        "            \"id\": \"ckoqjtqoh579601bs6puo9q1t7\",\n" +
                        "            \"location\": \"OE-PM-TR\",\n" +
                        "            \"x\": 21,\n" +
                        "            \"y\": -24,\n" +
                        "            \"cargo\": [],\n" +
                        "            \"spaceAvailable\": 50,\n" +
                        "            \"type\": \"JW-MK-I\",\n" +
                        "            \"class\": \"MK-I\",\n" +
                        "            \"maxCargo\": 50,\n" +
                        "            \"speed\": 1,\n" +
                        "            \"manufacturer\": \"Jackshaw\",\n" +
                        "            \"plating\": 5,\n" +
                        "            \"weapons\": 5\n" +
                        "        }\n" +
                        "}";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null; }

    @Override
    public JSONObject getGood(String username, String token, String shipId) {
        String response =
                "{\n" +
                        "    \"ship\": {\n" +
                        "            \"id\": \"ckoqjtqoh579601bs6puo9q1t7\",\n" +
                        "            \"location\": \"OE-PM-TR\",\n" +
                        "            \"x\": 21,\n" +
                        "            \"y\": -24,\n" +
                        "            \"cargo\": [],\n" +
                        "            \"spaceAvailable\": 50,\n" +
                        "            \"type\": \"JW-MK-I\",\n" +
                        "            \"class\": \"MK-I\",\n" +
                        "            \"maxCargo\": 50,\n" +
                        "            \"speed\": 1,\n" +
                        "            \"manufacturer\": \"Jackshaw\",\n" +
                        "            \"plating\": 5,\n" +
                        "            \"weapons\": 5\n" +
                        "        }\n" +
                        "}";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * PURCHASE
     * */
    @Override
    public JSONObject buyFuel(String username, String token, String shipId, String good, String quantity){
        String response =
                "{\n" +
                        "  \"credits\": 105960,\n" +
                        "  \"order\": {\n" +
                        "    \"good\": \"FUEL\",\n" +
                        "    \"pricePerUnit\": 2,\n" +
                        "    \"quantity\": 20,\n" +
                        "    \"total\": 40\n" +
                        "  },\n" +
                        "  \"ship\": {\n" +
                        "    \"cargo\": [\n" +
                        "      {\n" +
                        "        \"good\": \"FUEL\",\n" +
                        "        \"quantity\": 20,\n" +
                        "        \"totalVolume\": 20\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"class\": \"MK-I\",\n" +
                        "    \"id\": \"ckno9324k0079iiop71j5nrob\",\n" +
                        "    \"location\": \"OE-PM-TR\",\n" +
                        "    \"manufacturer\": \"Jackshaw\",\n" +
                        "    \"maxCargo\": 100,\n" +
                        "    \"plating\": 5,\n" +
                        "    \"spaceAvailable\": 80,\n" +
                        "    \"speed\": 2,\n" +
                        "    \"type\": \"JW-MK-I\",\n" +
                        "    \"weapons\": 5,\n" +
                        "    \"x\": 21,\n" +
                        "    \"y\": -26\n" +
                        "  }\n" +
                        "}";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * MARKETPLACE
     * */
    @Override
    public JSONObject viewMarketPlace(String token, String symbol){
        String response =
                "{\n" +
                        "  \"location\": {\n" +
                        "    \"marketplace\": [\n" +
                        "      {\n" +
                        "        \"pricePerUnit\": 4,\n" +
                        "        \"quantityAvailable\": 406586,\n" +
                        "        \"symbol\": \"METALS\",\n" +
                        "        \"volumePerUnit\": 1\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"pricePerUnit\": 231,\n" +
                        "        \"quantityAvailable\": 5407,\n" +
                        "        \"symbol\": \"MACHINERY\",\n" +
                        "        \"volumePerUnit\": 4\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"pricePerUnit\": 8,\n" +
                        "        \"quantityAvailable\": 406586,\n" +
                        "        \"symbol\": \"CHEMICALS\",\n" +
                        "        \"volumePerUnit\": 1\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"pricePerUnit\": 1,\n" +
                        "        \"quantityAvailable\": 462806,\n" +
                        "        \"symbol\": \"FUEL\",\n" +
                        "        \"volumePerUnit\": 1\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"pricePerUnit\": 127,\n" +
                        "        \"quantityAvailable\": 19961,\n" +
                        "        \"symbol\": \"SHIP_PLATING\",\n" +
                        "        \"volumePerUnit\": 2\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"pricePerUnit\": 30,\n" +
                        "        \"quantityAvailable\": 403,\n" +
                        "        \"symbol\": \"DRONES\",\n" +
                        "        \"volumePerUnit\": 2\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"pricePerUnit\": 1283,\n" +
                        "        \"quantityAvailable\": 8738,\n" +
                        "        \"symbol\": \"SHIP_PARTS\",\n" +
                        "        \"volumePerUnit\": 5\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"name\": \"Tritus\",\n" +
                        "    \"symbol\": \"OE-PM-TR\",\n" +
                        "    \"type\": \"MOON\",\n" +
                        "    \"x\": 21,\n" +
                        "    \"y\": -26\n" +
                        "  }\n" +
                        "}";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject listNearBy(String token, String symbol, String type){
        String response =
                "{\n" +
                        "  \"locations\": [\n" +
                        "    {\n" +
                        "      \"name\": \"Carth\",\n" +
                        "      \"symbol\": \"OE-CR\",\n" +
                        "      \"type\": \"PLANET\",\n" +
                        "      \"x\": 16,\n" +
                        "      \"y\": 17\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"Koria\",\n" +
                        "      \"symbol\": \"OE-KO\",\n" +
                        "      \"type\": \"PLANET\",\n" +
                        "      \"x\": -48,\n" +
                        "      \"y\": -7\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"Ucarro\",\n" +
                        "      \"symbol\": \"OE-UC\",\n" +
                        "      \"type\": \"PLANET\",\n" +
                        "      \"x\": -75,\n" +
                        "      \"y\": 82\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"Prime\",\n" +
                        "      \"symbol\": \"OE-PM\",\n" +
                        "      \"type\": \"PLANET\",\n" +
                        "      \"x\": 20,\n" +
                        "      \"y\": -25\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}\n";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * FLIGHT PLAN
     * */
    @Override
    public JSONObject viewFlightPlan(String username, String token, String flightPlanId){
        if (!flightPlanId.equalsIgnoreCase("ckm07t6ki0038060jv7b2x5gk")){
            return null;
        }
        String response =
                "\"flightPlan\": {\n" +
                        "    \"arrivesAt\": \"2021-03-08T06:41:19.658Z\",\n" +
                        "    \"departure\": \"OE-PM-TR\",\n" +
                        "    \"destination\": \"OE-PM\",\n" +
                        "    \"distance\": 1,\n" +
                        "    \"fuelConsumed\": 1,\n" +
                        "    \"fuelRemaining\": 19,\n" +
                        "    \"id\": \"ckm07t6ki0038060jv7b2x5gk\",\n" +
                        "    \"shipId\": \"ckm07ezq50354ti0j1drcey9v\",\n" +
                        "    \"terminatedAt\": \"2021-03-08T06:41:18.752Z\",\n" +
                        "    \"timeRemainingInSeconds\": 0\n" +
                        "  }\n" +
                        "}\n";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject createFlightPlan(String username, String token, String shipId, String destination){
        String response =
                "{\n" +
                        "  \"flightPlan\": {\n" +
                        "    \"arrivesAt\": \"2021-03-08T06:41:19.658Z\",\n" +
                        "    \"departure\": \"OE-PM-TR\",\n" +
                        "    \"destination\": \"OE-PM\",\n" +
                        "    \"distance\": 1,\n" +
                        "    \"fuelConsumed\": 1,\n" +
                        "    \"fuelRemaining\": 19,\n" +
                        "    \"id\": \"ckm07t6ki0038060jv7b2x5gk\",\n" +
                        "    \"shipId\": \"ckm07ezq50354ti0j1drcey9v\",\n" +
                        "    \"terminatedAt\": null,\n" +
                        "    \"timeRemainingInSeconds\": 67\n" +
                        "  }\n" +
                        "}\n";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * SELL
     * */
    @Override
    public JSONObject sell(String username, String token, String shipId, String good, String quantity){
        if (!shipId.equalsIgnoreCase("ckno9324k0079iiop71j5nrob")){
            return null;
        }
        String response =
                "{\n" +
                        "  \"credits\": 178935,\n" +
                        "  \"order\": {\n" +
                        "    \"good\": \"METALS\",\n" +
                        "    \"pricePerUnit\": 7,\n" +
                        "    \"quantity\": 30,\n" +
                        "    \"total\": 210\n" +
                        "  },\n" +
                        "  \"ship\": {\n" +
                        "    \"cargo\": [\n" +
                        "      {\n" +
                        "        \"good\": \"FUEL\",\n" +
                        "        \"quantity\": 18,\n" +
                        "        \"totalVolume\": 18\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"class\": \"MK-I\",\n" +
                        "    \"id\": \"ckno9324k0079iiop71j5nrob\",\n" +
                        "    \"location\": \"OE-PM\",\n" +
                        "    \"manufacturer\": \"Jackshaw\",\n" +
                        "    \"maxCargo\": 50,\n" +
                        "    \"plating\": 5,\n" +
                        "    \"spaceAvailable\": 32,\n" +
                        "    \"speed\": 1,\n" +
                        "    \"type\": \"JW-MK-I\",\n" +
                        "    \"weapons\": 5,\n" +
                        "    \"x\": 20,\n" +
                        "    \"y\": -25\n" +
                        "  }\n" +
                        "}";
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(response);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

}
