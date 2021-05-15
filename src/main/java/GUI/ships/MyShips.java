package GUI.ships;

import DummyAPI.DummyFacade;
import GUI.UserPage;
import GUI.Window;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class MyShips extends JFrame {
    static MyShips frame;
    private JPanel contentPane;
    private JButton check;
    private JButton buy;
    private static Console console;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new MyShips(args[0], args[1], args[2]);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public MyShips(String type, String name, String token) {

        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel statusLabel = new JLabel("Your Ships");

        statusLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        if (type.equals("online")){
            console = new OnlineConsole();
        } else if (type.equals("offline")){
            console = new DummyFacade();
        } else {
            console = null;
        }
        if (console == null){
            JOptionPane.showMessageDialog(MyShips.this,"INVALID WORKING MODE");
            return;
        }
        JSONObject json = console.getShips(name, token);
        JSONArray shipArray = null;
        try{
            shipArray = (JSONArray) json.get("ships");
        }catch (Exception e){
            e.printStackTrace();
        }

        if (shipArray == null){
            String[] passArgs = new String[3];
            passArgs[0] = type;
            passArgs[1] = name;
            passArgs[2] = token;
            MyShips.main(passArgs);
            frame.dispose();
        }

        int length = shipArray.size();


        String[][] shipData = new String[length][9];

        Iterator<JSONObject> iterator = (Iterator<JSONObject>) shipArray.iterator();
        int counter = 0;

        // Get every ship
        while (iterator.hasNext()) {
            JSONObject eachShip = (JSONObject) iterator.next();

            shipData[counter][0] = (String) eachShip.get("id");
            shipData[counter][1] = (String) eachShip.get("class");
            shipData[counter][2] = (String) eachShip.get("location");
            shipData[counter][3] = String.format("(%s,%s)",(Long)eachShip.get("x")+"", (Long)eachShip.get("y")+"");
            shipData[counter][4] = (Long) eachShip.get("speed") + "";
            shipData[counter][5] = String.format("%s/%s",(Long)eachShip.get("spaceAvailable")+"", (Long)eachShip.get("maxCargo")+"");
            shipData[counter][6] = (String) eachShip.get("manufacturer");
            shipData[counter][7] = (Long) eachShip.get("plating") + "";
            shipData[counter][8] = (Long) eachShip.get("weapons") + "";
            counter ++;
        }

        String[] columnName = {"ShipId", "class", "loaction", "coordinate", "speed", "cargo remain", "manufacturer", "plating", "weapons"};

        JTable itemsTable = new JTable(shipData, columnName);
        JScrollPane items = new JScrollPane(itemsTable);

        JLabel nameLabel = new JLabel("Ship ID:");

        JTextField id = new JTextField();
        id.setColumns(30);


        this.check = new JButton("Check Goods");
        this.buy = new JButton("View Available Ships");

        JButton back = new JButton("back");

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] passArgs = new String[3];
                passArgs[0] = type;
                passArgs[1] = name;
                passArgs[2] = token;
                UserPage.main(passArgs);
                frame.dispose();
            }
        });

        this.buy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] passArgs = new String[4];
                passArgs[0] = type;
                passArgs[1] = name;
                passArgs[2] = token;
                passArgs[3] = "";
                AvailableShips.main(passArgs);
                frame.dispose();
            }
        });

        this.check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ship_id = id.getText();
                if (ship_id.length() == 0){
                    JOptionPane.showMessageDialog(MyShips.this, "Missing Ship ID");
                    return;
                }

                JSONObject ship = (JSONObject) console.getGood(name, token, ship_id).get("ship");
                JSONArray cargo = (JSONArray) ship.get("cargo");
                int length = cargo.size();
                if (length == 0){
                    JOptionPane.showMessageDialog(MyShips.this, "No Good In Cargo");
                    return;
                }
                String[][] goodData = new String[length][3];
                Iterator<JSONObject> iterator = (Iterator<JSONObject>) cargo.iterator();
                int counter = 0;
                String message = "good : quantity : total volume\n";
                // Get every ship
                while (iterator.hasNext()) {
                    JSONObject eachGood = (JSONObject) iterator.next();
                    message += (String) eachGood.get("good") + " : " + (Long) eachGood.get("quantity") + " : " + (Long) eachGood.get("totalVolume") + "\n";
                    counter ++;
                }
                JOptionPane.showMessageDialog(MyShips.this, message);
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
                                        .addComponent(check)
                                        .addGap(60)
                                        .addComponent(buy))
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
                                        .addGroup(layout.createParallelGroup())
                                        .addComponent(check)
                                        .addComponent(buy)
                                )
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
