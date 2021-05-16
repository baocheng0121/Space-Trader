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

public class TradePage extends JFrame {
    static TradePage frame;
    private JPanel contentPane;
    private JButton check;
    private JButton buy;
    private JButton sell;
    private static Console console;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new TradePage(args[0], args[1], args[2], args[3]);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public TradePage(String type, String name, String token, String shipID) {

        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel statusLabel = new JLabel("Market Place");

        statusLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        if (type.equals("online")){
            console = new OnlineConsole();
        } else if (type.equals("offline")){
            console = new DummyFacade();
        } else {
            console = null;
        }
        if (console == null){
            JOptionPane.showMessageDialog(TradePage.this,"INVALID WORKING MODE");
            return;
        }
        JSONObject json = console.getShip(name, token, shipID);
        JSONObject ship = (JSONObject)json.get("ship");

        if (ship == null){
            JOptionPane.showMessageDialog(TradePage.this, "INVALID SHIP ID");

            String[] passArgs = new String[4];
            passArgs[0] = type;
            passArgs[1] = name;
            passArgs[2] = token;
            passArgs[3] = shipID;
            frame.dispose();
            TradePage.main(passArgs);
        }

        String location = (String) ship.get("location");
        JSONObject marketPlace = console.viewMarketPlace(token, location);
        JSONObject locationObject = (JSONObject) marketPlace.get("location");
        JSONArray places = (JSONArray) locationObject.get("marketplace");

        int length = places.size();

        String[][] shipData = new String[length][4];

        Iterator<JSONObject> iterator = (Iterator<JSONObject>) places.iterator();
        int counter = 0;

        // Get every place
        while (iterator.hasNext()) {
            JSONObject eachMarket = (JSONObject) iterator.next();

            shipData[counter][0] = (String) eachMarket.get("symbol");
            shipData[counter][1] = (Long) eachMarket.get("pricePerUnit") + "";
            shipData[counter][2] = (Long) eachMarket.get("volumePerUnit") + "";
            shipData[counter][3] = (Long) eachMarket.get("quantityAvailable") + "";
            counter ++;
        }

        String[] columnName = {"symbol", "price/Unit", "volume/Unit", "abailable quantity"};

        JTable itemsTable = new JTable(shipData, columnName);
        JScrollPane items = new JScrollPane(itemsTable);

        JLabel nameLabel = new JLabel("Item Name:");
        JLabel quantityLabel = new JLabel("Quantity:");

        JTextField id = new JTextField();
        JTextField quantity = new JTextField();
        id.setColumns(30);

        this.check = new JButton("Purchase");
        this.sell = new JButton("Sell");
        this.buy = new JButton("View Nearby Marketplaces");

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

        this.buy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JSONObject ship = console.getShip(name, token, shipID);

                String[] passArgs = new String[5];
                passArgs[0] = type;
                passArgs[1] = name;
                passArgs[2] = token;
                passArgs[3] = shipID;
                passArgs[4] = "";
                NearbyMarket.main(passArgs);

                frame.dispose();
            }
        });

        this.sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = id.getText();
                String num = quantity.getText();
                if (item.length() == 0 || num.length() == 0){
                    JOptionPane.showMessageDialog(TradePage.this, "Missing Arguments");
                    return;
                }

                JSONObject sold = console.sell(name, token, shipID, item, num);
                if (sold == null){
                    JOptionPane.showMessageDialog(TradePage.this, "Failed to sell");
                    return;
                }
                JOptionPane.showMessageDialog(TradePage.this, "Sold Successfully");
            }
        });

        this.check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String item = id.getText();
                String num = quantity.getText();
                if (item.length() == 0 || num.length() == 0){
                    JOptionPane.showMessageDialog(TradePage.this, "Missing Arguments");
                    return;
                }

                JSONObject ship = console.buyFuel(name, token, shipID, item, num);
                if (ship == null){
                    JOptionPane.showMessageDialog(TradePage.this, "Purchase Failed");
                    return;
                }
                JOptionPane.showMessageDialog(TradePage.this, "Purchase Completed");
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
                                        .addComponent(quantityLabel)
                                        .addComponent(quantity, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(check)
                                        .addComponent(sell)
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
                                        .addComponent(quantityLabel)
                                        .addComponent(quantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup())
                                        .addComponent(check)
                                        .addComponent(sell)
                                        .addComponent(buy)
                                )
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
