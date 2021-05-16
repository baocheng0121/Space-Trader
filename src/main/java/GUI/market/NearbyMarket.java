package GUI.market;

import DummyAPI.DummyFacade;
import GUI.UserPage;
import GUI.Window;
import GUI.ships.MyShips;
import OnlineAPI.Console;
import OnlineAPI.OnlineConsole;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class NearbyMarket extends JFrame {
    static NearbyMarket frame;
    private JPanel contentPane;
    private JButton search;
    private JButton check;
    private JButton submit;
    private static Console console;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new NearbyMarket(args[0], args[1], args[2], args[3], args[4]);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public NearbyMarket(String type, String name, String token, String shipID, String searchType) {

        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel statusLabel = new JLabel("Nearby Market Places");

        statusLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        if (type.equals("online")){
            console = new OnlineConsole();
        } else if (type.equals("offline")){
            console = new DummyFacade();
        } else {
            console = null;
        }
        if (console == null){
            JOptionPane.showMessageDialog(NearbyMarket.this,"INVALID WORKING MODE");
            return;
        }
        JSONObject json = console.getShip(name, token, shipID);
        JSONObject ship = (JSONObject)json.get("ship");

        if (ship == null){
            JOptionPane.showMessageDialog(NearbyMarket.this, "INVALID SHIP ID");

            String[] passArgs = new String[4];
            passArgs[0] = type;
            passArgs[1] = name;
            passArgs[2] = token;
            passArgs[3] = shipID;
            frame.dispose();
            NearbyMarket.main(passArgs);
        }

        String location = (String) ship.get("location");

        String symbol = location.split("-")[0];
        JSONObject marketPlaces = console.listNearBy(token, symbol, searchType);
        if (marketPlaces == null){
            marketPlaces = console.listNearBy(token, symbol, "");
            JOptionPane.showMessageDialog(NearbyMarket.this,"No Element Found");
        }
        JSONArray nearby = (JSONArray) marketPlaces.get("locations");

        int length = nearby.size();

        String[][] shipData = new String[length][5];

        Iterator<JSONObject> iterator = (Iterator<JSONObject>) nearby.iterator();
        int counter = 0;

        // Get every place
        while (iterator.hasNext()) {
            JSONObject eachMarket = (JSONObject) iterator.next();

            shipData[counter][0] = (String) eachMarket.get("symbol");
            shipData[counter][1] = (String) eachMarket.get("type");
            shipData[counter][2] = (String) eachMarket.get("name");
            shipData[counter][3] = String.format("(%s,%s)",(Long) eachMarket.get("x") + "",(Long) eachMarket.get("y")+"");
            shipData[counter][4] = (Boolean) eachMarket.get("allowsConstruction") +"";
            counter ++;
        }

        String[] columnName = {"symbol", "type", "name", "coordinate", "construction allow"};

        JTable itemsTable = new JTable(shipData, columnName);
        JScrollPane items = new JScrollPane(itemsTable);

        JLabel nameLabel = new JLabel("Destination Symbol:");
        JLabel searchTypeLabel = new JLabel("Serarch Type:");
        JLabel idToCheck = new JLabel("Flight Plan ID:");

        JTextField id = new JTextField();
        JTextField searchField = new JTextField();
        JTextField IDField = new JTextField();
        id.setColumns(30);
        searchField.setColumns(30);
        IDField.setColumns(40);

        this.search = new JButton("Search Nearby");
        this.submit = new JButton("Submit Flight Plan");
        this.check = new JButton("Check Flight Plan");

        JButton back = new JButton("back");

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] passArgs = new String[4];
                passArgs[0] = type;
                passArgs[1] = name;
                passArgs[2] = token;
                passArgs[3] = shipID;
                TradePage.main(passArgs);
                frame.dispose();
            }
        });

        this.check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String flightPlanID = IDField.getText();
                if (flightPlanID.length() == 0){
                    JOptionPane.showMessageDialog(NearbyMarket.this, "Please Enter Flight Plan ID Before Check");
                    return;
                }
                JSONObject flightPlanChecked = console.viewFlightPlan(name, token, flightPlanID);
                if (flightPlanChecked == null){
                    JOptionPane.showMessageDialog(NearbyMarket.this, "No such flight plan");
                    return;
                }
                JSONObject essence = (JSONObject) flightPlanChecked.get("flightPlan");
                String arriveTime = (String) essence.get("arrivesAt");
                String createTime = (String) essence.get("createdAt");
                String departure = (String) essence.get("departure");
                String destination = (String) essence.get("destination");
                String distance = (Long) essence.get("distance") + "";
                String fuelNeed = (Long) essence.get("fuelConsumed") + "";
                String fuelRemain = (Long) essence.get("fuelRemaining") + "";
                String flightId = (String) essence.get("id");
                String shipId = (String) essence.get("shipId");
                String time = (Long) essence.get("timeRemainingInSeconds") + "";
                String message = String.format("flight plan id: %s\n" +
                        "ship id: %s\n" +
                        "created at: %s\n" +
                        "arrives at: %s\n" +
                        "from %s to %s\n" +
                        "distance: %s\n" +
                        "fuel consumed: %s\n" +
                        "fuel remained: %s\n" +
                        "time remaining %s s\n",
                        flightId, shipId, createTime, arriveTime, departure,
                        destination, distance, fuelNeed, fuelRemain, time
                );
                JOptionPane.showMessageDialog(NearbyMarket.this, message);

                frame.dispose();
            }
        });

        this.submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dest = id.getText();
                if (dest.length() == 0){
                    JOptionPane.showMessageDialog(NearbyMarket.this, "Please Enter your Destination");
                    return;
                }
                JSONObject flight = console.createFlightPlan(name, token, shipID, dest);
                if (flight == null){
                    JOptionPane.showMessageDialog(NearbyMarket.this, "Failed to submit the flight plan");
                    return;
                }
                JSONObject plan = (JSONObject) flight.get("flightPlan");
                if (plan == null){
                    JOptionPane.showMessageDialog(NearbyMarket.this, "Failed to submit the flight plan");
                    return;
                }
                String flightId = (String) plan.get("id");
                String departure = (String) plan.get("departure");
                String destination = (String) plan.get("destination");
                String time = (Long) plan.get("timeRemainingInSeconds") +"";
                String message = String.format("Flight Plan has been submitted:\nid: %s\ndeparture from: %s\n" +
                        "arrives at: %s\ntime remaining: %s s",flightId,departure,destination, time);
                JOptionPane.showMessageDialog(NearbyMarket.this, message);
            }
        });

        this.search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String symbolToSearch = searchField.getText();

                String[] passArgs = new String[5];
                passArgs[0] = type;
                passArgs[1] = name;
                passArgs[2] = token;
                passArgs[3] = shipID;
                passArgs[4] = symbolToSearch;
                frame.dispose();
                NearbyMarket.main(passArgs);
            }
        });

        GroupLayout layout = new GroupLayout(contentPane);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(statusLabel))
                                        .addGap(50)
                                        .addComponent(items, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
                                        .addGap(100)
                                        .addComponent(nameLabel)
                                        .addComponent(id, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchTypeLabel)
                                        .addComponent(searchField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idToCheck)
                                        .addComponent(IDField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(search)
                                        .addComponent(submit)
                                        .addComponent(check))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(statusLabel)
                                .addGap(50)
                                .addComponent(items, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addGap(50)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(nameLabel)
                                        .addComponent(id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(searchTypeLabel)
                                        .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(idToCheck)
                                        .addComponent(IDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup())
                                        .addComponent(search)
                                        .addComponent(submit)
                                        .addComponent(check)
                                )
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
