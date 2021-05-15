package OnlineAPI;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class OnlineConsole implements Console {

    /**
     * SYSTEM
     * */
    @Override
    public JSONObject isGameActive(){
        try {
            URL url = new URL("https://api.spacetraders.io/game/status");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP Error Code : " + connection.getResponseCode());
            }

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();

            connection.disconnect();

            return (JSONObject) parser.parse(response);
        } catch (Exception e){
            System.out.println("Exception in NetClientGet: - " + e);
        }
        return null;

    }

    /**
     * USERS
     * */
    @Override
    public JSONObject getUserInfo(String username, String token) {
        String link = String.format("https://api.spacetraders.io/users/%s", username);
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP Error Code : " + connection.getResponseCode());
            }
            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();

            connection.disconnect();

            return (JSONObject) parser.parse(response);
        } catch (Exception e){
            System.out.println("Exception in NetClientGet: - " + e);
        }
        return null;
    }

    @Override
    public JSONObject claimUserName(String username){
        String link = String.format("https://api.spacetraders.io/users/%s/token", username);
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Length", String.valueOf(username.length()));
            connection.setDoOutput(true);
            String jsonInputString = String.format("{\"username\": \"%s\"}", username);

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();

            connection.disconnect();

            return (JSONObject) parser.parse(response);

        } catch (Exception e){
            System.out.println("Exception in NetClientPost: - "+ e);
        }
        return null;
    }

    /**
     * LOANS
     * */
    @Override
    public JSONObject viewAvailableLoans(String token){
        try {
            URL url = new URL("https://api.spacetraders.io/game/loans");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP Error Code : " + connection.getResponseCode());
            }

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();

            connection.disconnect();

            return (JSONObject) parser.parse(response);
        } catch (Exception e){
            System.out.println("Exception in NetClientGet: - " + e);
        }
        return null;

    }

    @Override
    public JSONObject requestLoan(String token, String username, String type){
        String link = String.format("https://api.spacetraders.io/users/%s/loans", username);
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            String jsonInputString = String.format("{\"username\":\"%s\",\"type\":\"%s\"}", username, type);
            byte[] input = jsonInputString.getBytes("utf-8");
            connection.setRequestProperty("Content-Length", String.valueOf(input.length));
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            try(OutputStream os = connection.getOutputStream()) {
                os.write(input, 0, input.length);
                os.flush();
            }

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();

            connection.disconnect();
            return (JSONObject) parser.parse(response);

        } catch (Exception e){
            System.out.println("Exception in NetClientPost: - "+ e);
        }
        return null;
    }

    @Override
    public JSONObject getActiveLoans(String username, String token){
        String link = String.format("https://api.spacetraders.io/users/%s/loans", username);
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP Error Code : " + connection.getResponseCode());
            }
            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(response);
            connection.disconnect();
            return json;
        } catch (Exception e){
            System.out.println("Exception in NetClientGet: - " + e);
        }
        return null;
    }

    @Override
    public JSONObject payOffLoan(String username, String token, String loanID){
        String link = String.format("https://api.spacetraders.io/users/%s/loans/%s", username, loanID);
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            String jsonInputString = String.format("{\"username\":\"%s\",\"loanId\":\"%s\"}", username, loanID);
            byte[] input = jsonInputString.getBytes("utf-8");
            connection.setRequestProperty("Content-Length", String.valueOf(input.length));
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            try(OutputStream os = connection.getOutputStream()) {
                os.write(input, 0, input.length);
                os.flush();
            }

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();

            connection.disconnect();
            return (JSONObject) parser.parse(response);

        } catch (Exception e){
            System.out.println("Exception in NetClientPost: - "+ e);
        }
        return null;
    }

    /**
     * SHIPS
     * */
    @Override
    public JSONObject viewAvailableShips(String token, String shipClass){
        try {
            String link = "https://api.spacetraders.io/game/ships";
            if (shipClass.length() > 0){
                link = link + "?class=" +shipClass;
            }
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP Error Code : " + connection.getResponseCode());
            }

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();

            connection.disconnect();

            return (JSONObject) parser.parse(response);
        } catch (Exception e){
            System.out.println("Exception in NetClientGet: - " + e);
        }
        return null;
    }

    @Override
    public JSONObject buyShip(String username, String token, String location, String type){
        String link = String.format("https://api.spacetraders.io/users/%s/ships", username);
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            String jsonInputString = String.format("{\"username\":\"%s\",\"location\":\"%s\",\"type\":\"%s\"}", username, location, type);
            byte[] input = jsonInputString.getBytes("utf-8");
            connection.setRequestProperty("Content-Length", String.valueOf(input.length));
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            try(OutputStream os = connection.getOutputStream()) {
                os.write(input, 0, input.length);
                os.flush();
            }

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();

            connection.disconnect();
            return (JSONObject) parser.parse(response);

        } catch (Exception e){
            System.out.println("Exception in NetClientPost: - "+ e);
        }
        return null;
    }

    @Override
    public JSONObject getShips(String username, String token){
        String link = String.format("https://api.spacetraders.io/users/%s/ships", username);
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP Error Code : " + connection.getResponseCode());
            }
            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(response);
            connection.disconnect();
            return json;
        } catch (Exception e){
            System.out.println("Exception in NetClientGet: - " + e);
        }
        return null;
    }

    /**
     * PURCHASE
     * */
    @Override
    public JSONObject buyFuel(String username, String token, String shipId, String good, String quantity){
        String link = String.format("https://api.spacetraders.io/users/%s/purchase-orders", username);
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            String jsonInputString = String.format("{\"username\":\"%s\",\"shipId\":\"%s\",\"good\":\"%s\",\"quantity\":\"%s\"}", username, shipId, good, quantity);
            byte[] input = jsonInputString.getBytes("utf-8");
            connection.setRequestProperty("Content-Length", String.valueOf(input.length));
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            try(OutputStream os = connection.getOutputStream()) {
                os.write(input, 0, input.length);
                os.flush();
            }

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();

            connection.disconnect();
            return (JSONObject) parser.parse(response);

        } catch (Exception e){
            System.out.println("Exception in NetClientPost: - "+ e);
        }
        return null;
    }

    /**
     * MARKETPLACE
     * */
    @Override
    public JSONObject viewMarketPlace(String token, String symbol){
        try {
            String link = String.format("https://api.spacetraders.io/game/locations/%s/marketplace", symbol);
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP Error Code : " + connection.getResponseCode());
            }

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();

            connection.disconnect();

            return (JSONObject) parser.parse(response);
        } catch (Exception e){
            System.out.println("Exception in NetClientGet: - " + e);
        }
        return null;
    }

    @Override
    public JSONObject listNearBy(String token, String symbol, String type){
        try {
            String link = String.format("https://api.spacetraders.io/game/systems/%s/locations?type=%s", symbol, type);
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP Error Code : " + connection.getResponseCode());
            }

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();

            connection.disconnect();

            return (JSONObject) parser.parse(response);
        } catch (Exception e){
            System.out.println("Exception in NetClientGet: - " + e);
        }
        return null;
    }

    /**
     * FLIGHT PLAN
     * */
    @Override
    public JSONObject viewFlightPlan(String username, String token, String flightPlanId){
        try {
            String link = String.format("https://api.spacetraders.io/users/%s/flight-plans/%s", username, flightPlanId);
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP Error Code : " + connection.getResponseCode());
            }

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();

            connection.disconnect();

            return (JSONObject) parser.parse(response);
        } catch (Exception e){
            System.out.println("Exception in NetClientGet: - " + e);
        }
        return null;
    }

    @Override
    public JSONObject createFlightPlan(String username, String token, String shipId, String destination){
        String link = String.format("https://api.spacetraders.io/users/%s/flight-plans", username);
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            String jsonInputString = String.format("{\"username\":\"%s\",\"shipId\":\"%s\",\"destination\":\"%s\"}", username, shipId, destination);

            byte[] input = jsonInputString.getBytes("utf-8");
            connection.setRequestProperty("Content-Length", String.valueOf(input.length));
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            try(OutputStream os = connection.getOutputStream()) {
                os.write(input, 0, input.length);
                os.flush();
            }

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();

            connection.disconnect();
            return (JSONObject) parser.parse(response);

        } catch (Exception e){
            System.out.println("Exception in NetClientPost: - "+ e);
        }
        return null;
    }

    /**
     * SELL
     * */
    @Override
    public JSONObject sell(String username, String token, String shipId, String good, String quantity){
        String link = String.format("https://api.spacetraders.io/users/%s/sell-orders", username);
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            String jsonInputString = String.format("{\"username\":\"%s\",\"shipId\":\"%s\",\"good\":\"%s\",\"quantity\":\"%s\"}", username, shipId, good, quantity);

            byte[] input = jsonInputString.getBytes("utf-8");
            connection.setRequestProperty("Content-Length", String.valueOf(input.length));
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            try(OutputStream os = connection.getOutputStream()) {
                os.write(input, 0, input.length);
                os.flush();
            }

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
            JSONParser parser = new JSONParser();

            connection.disconnect();
            return (JSONObject) parser.parse(response);

        } catch (Exception e){
            System.out.println("Exception in NetClientPost: - "+ e);
        }
        return null;
    }

}
