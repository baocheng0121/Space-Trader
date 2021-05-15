package GUI.loans;

import DummyAPI.DummyFacade;
import GUI.UserPage;
import GUI.Window;
import GUI.ships.AvailableShips;
import OnlineAPI.Console;
import OnlineAPI.OnlineConsole;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;

public class MyLoans extends JFrame {
    static MyLoans frame;
    private JPanel contentPane;
    private JButton buy;
    private JButton trade;
    private static Console console;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new MyLoans(args[0], args[1], args[2]);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public MyLoans(String type, String name, String token) {

        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel statusLabel = new JLabel("Your Loans");

        statusLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        if (type.equals("online")){
            console = new OnlineConsole();
        } else if (type.equals("offline")){
            console = new DummyFacade();
        } else {
            console = null;
        }
        if (console == null){
            JOptionPane.showMessageDialog(MyLoans.this,"INVALID WORKING MODE");
            return;
        }
        JSONObject json = console.getActiveLoans(name, token);
        JSONArray loanArray = (JSONArray) json.get("loans");

        int length = loanArray.size();


        String[][] shipData = new String[length][5];

        Iterator<JSONObject> iterator = (Iterator<JSONObject>) loanArray.iterator();
        int counter = 0;

        // Get every loan
        while (iterator.hasNext()) {
            JSONObject eachLoan = (JSONObject) iterator.next();

            shipData[counter][0] = (String) eachLoan.get("id");
            shipData[counter][1] = (String) eachLoan.get("due");
            shipData[counter][2] = (Long) eachLoan.get("repaymentAmount") + "";
            shipData[counter][3] = (String) eachLoan.get("status");
            shipData[counter][4] = (String) eachLoan.get("type");

            counter ++;
        }

        String[] columnName = {"loanID", "due", "repayment", "status", "type"};

        JTable itemsTable = new JTable(shipData, columnName);
        JScrollPane items = new JScrollPane(itemsTable);

        JLabel nameLabel = new JLabel("Loan ID:");

        JTextField id = new JTextField();
        id.setColumns(30);

        this.buy = new JButton("Available Loans");
        this.trade = new JButton("Pay Off A Loan");

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
                String[] passArgs = new String[3];
                passArgs[0] = type;
                passArgs[1] = name;
                passArgs[2] = token;
                AvailableLoans.main(passArgs);
                frame.dispose();
            }
        });

        this.trade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String loanId = id.getText();
                if (loanId.length() == 0){
                    JOptionPane.showMessageDialog(MyLoans.this, "Enter the loanId first.");
                    return;
                }
                // JSONObject console.payOffLoan(name, token, loanId);

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
                                        .addComponent(trade)
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
                                        .addComponent(trade)
                                        .addComponent(buy)
                                )
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
