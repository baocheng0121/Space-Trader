package GUI.ships;

import DummyAPI.DummyFacade;
import OnlineAPI.Console;
import OnlineAPI.OnlineConsole;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;

public class AvailableShips extends JFrame {
    static AvailableShips frame;
    private JPanel contentPane;
    private JButton buy;
    private JButton loans;
    private static Console console;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new AvailableShips(args[0], args[1], args[2], args[3]);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public AvailableShips(String type, String name, String token, String shipClass) {

        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel statusLabel = new JLabel("Available Ships to buy");

        statusLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        if (type.equals("online")){
            console = new OnlineConsole();
        } else if (type.equals("offline")){
            console = new DummyFacade();
        } else {
            console = null;
        }
        if (console == null){
            JOptionPane.showMessageDialog(AvailableShips.this,"INVALID WORKING MODE");
            return;
        }
        JSONObject json = console.viewAvailableShips(token, shipClass);
        if (json == null || json.get("ships") == null){
            String[] passArgs = new String[3];
            passArgs[0] = type;
            passArgs[1] = name;
            passArgs[2] = token;
            MyShips.main(passArgs);
            frame.dispose();
        }

        JSONArray ships = (JSONArray) json.get("ships");
        System.out.println(ships.size());
        Iterator<JSONObject> iterator = (Iterator<JSONObject>) ships.iterator();
        int counter = 0;

        int length = ships.size();

        String[][] shipData = new String[length][7];

        // Get every ship
        while (iterator.hasNext()) {
            JSONObject eachShip = (JSONObject) iterator.next();

            shipData[counter][0] = (String) eachShip.get("type");
            shipData[counter][1] = (String) eachShip.get("class");
            shipData[counter][2] = (Long) eachShip.get("maxCargo") + "";
            shipData[counter][3] = (Long) eachShip.get("speed") + "";
            shipData[counter][4] = (String) eachShip.get("manufacturer");
            shipData[counter][5] = (Long) eachShip.get("plating") + "";
            shipData[counter][6] = (Long) eachShip.get("weapons") + "";

            counter ++;
        }

        String[] columnName = {"type", "class", "maxCargo", "speed", "manufacturer", "plating", "weapons"};
        JTable itemsTable = new JTable(shipData, columnName);
        JScrollPane items = new JScrollPane(itemsTable);

        JLabel nameLabel = new JLabel("Location: ");
        JLabel shipTypeLabel = new JLabel("Type: ");

        JTextField location = new JTextField();
        location.setColumns(10);

        JTextField shipType = new JTextField();
        shipType.setColumns(10);


        this.buy = new JButton("Buy");
        this.loans = new JButton("View");

        JButton back = new JButton("back");

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] passArgs = new String[3];
                passArgs[0] = type;
                passArgs[1] = name;
                passArgs[2] = token;
                MyShips.main(passArgs);
                frame.dispose();
            }
        });

        this.loans.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newClass = shipType.getText();
                if (newClass.length() == 0){
                    return;
                }
                String[] passArgs = new String[5];
                passArgs[0] = type;
                passArgs[1] = name;
                passArgs[2] = token;
                passArgs[3] = newClass;
                frame.dispose();
                AvailableShips.main(passArgs);
            }
        });

        this.buy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String loc = location.getText();
                String buyType = shipType.getText();
                if (loc.length() == 0 || buyType.length() == 0){
                    JOptionPane.showMessageDialog(AvailableShips.this, "NOT ENOUGH ARGUMENTS");
                    return;
                }
                JSONObject json = console.buyShip(name, token, loc, buyType);
                if (json == null){
                    JOptionPane.showMessageDialog(AvailableShips.this, "Failed to complete the purchase.");
                    return;
                }
                String credits = (Long) json.get("credits") + "";
                JSONObject boughtShip = (JSONObject) json.get("ship");
                String boughtId = (String) boughtShip.get("id");
                String message = String.format("Credits left: %s\nShip ID: %s\n",credits, boughtId);
                JOptionPane.showMessageDialog(AvailableShips.this, message);
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
                                        .addComponent(items, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                                        .addGap(100)
                                        .addComponent(nameLabel)
                                        .addComponent(shipTypeLabel)
                                        .addComponent(shipType, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(location, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buy)
                                        .addGap(60)
                                        .addComponent(loans))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(statusLabel)
                                .addGap(50)
                                .addComponent(items, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(nameLabel)
                                        .addComponent(location, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(shipTypeLabel)
                                        .addComponent(shipType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup())
                                        .addComponent(buy)
                                        .addComponent(loans)
                                )
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
