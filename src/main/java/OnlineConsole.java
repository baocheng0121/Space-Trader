import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONObject;


public class OnlineConsole implements Console{

    @Override
    public void getUserInfo(String username, String token) {
        String link = String.format("https://api.spacetraders.io/users/:%s", username);
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP Error Code : " + connection.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            List<String> data = new ArrayList<>();
            while ((output = br.readLine())!= null){
                data.add(output);
            }
            Console.toJson(data, "user.json", false);

            connection.disconnect();
        } catch (Exception e){
            System.out.println("Exception in NetClientGet: - " + e);
        }
    }

    @Override
    public void claimUserName(String username){
        String link = String.format("https://api.spacetraders.io/users/:%s/token", username);
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", String.valueOf(username.length()));
            connection.setDoOutput(true);
            connection.getOutputStream().write(username.getBytes());

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;){
                sb.append((char) c);
            }
            String response = sb.toString();
            System.out.println(response);
        } catch (Exception e){
            System.out.println("Exception in NetClientPost: - "+ e);
        }
    }

    @Override
    public void getSystemInfo(String token){
        try {
            URL url = new URL("https://api.spacetraders.io/game/systems");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP Error Code : " + connection.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            List<String> data = new ArrayList<>();
            while ((output = br.readLine())!= null){
                data.add(output);
            }
            Console.toJson(data, "system.json", false);

            connection.disconnect();
        } catch (Exception e){
            System.out.println("Exception in NetClientGet: - " + e);
        }

    }


}
