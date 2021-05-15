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

public class AvailableLoans extends JFrame {
    static AvailableLoans frame;
    private JPanel contentPane;
    private JButton check;
    private static Console console;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new AvailableLoans(args[0], args[1], args[2]);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public AvailableLoans(String type, String name, String token) {

        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel statusLabel = new JLabel("Available Loans to request");

        statusLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        if (type.equals("online")){
            console = new OnlineConsole();
        } else if (type.equals("offline")){
            console = new DummyFacade();
        } else {
            console = null;
        }
        if (console == null){
            JOptionPane.showMessageDialog(AvailableLoans.this,"INVALID WORKING MODE");
            return;
        }
        JSONObject json = console.viewAvailableLoans(token);
        JSONArray loanArray = (JSONArray) json.get("loans");

        int length = loanArray.size();

        String[][] shipData = new String[length][5];

        Iterator<JSONObject> iterator = (Iterator<JSONObject>) loanArray.iterator();
        int counter = 0;

        // Get every loan
        while (iterator.hasNext()) {
            JSONObject eachLoan = (JSONObject) iterator.next();

            shipData[counter][0] = (String) eachLoan.get("type");
            shipData[counter][1] = (Long) eachLoan.get("amount") + "";
            shipData[counter][2] = (Long) eachLoan.get("rate") + "";
            shipData[counter][3] = (Long) eachLoan.get("termInDays") + "";
            shipData[counter][4] = (Boolean) eachLoan.get("collateralRequired")+"";

            counter ++;
        }

        String[] columnName = {"type", "amount", "rate", "term in days", "collateral required"};

        JTable itemsTable = new JTable(shipData, columnName);
        JScrollPane items = new JScrollPane(itemsTable);

        JLabel nameLabel = new JLabel("Loan Type:");

        JTextField id = new JTextField();
        id.setColumns(30);


        this.check = new JButton("Request New Loan");

        JButton back = new JButton("back");

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] passArgs = new String[3];
                passArgs[0] = type;
                passArgs[1] = name;
                passArgs[2] = token;
                MyLoans.main(passArgs);
                frame.dispose();
            }
        });

        this.check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String loan_type = id.getText();
                System.out.println(loan_type);

                JSONObject request = console.requestLoan(token, name, loan_type);
                if (request == null){
                    JOptionPane.showMessageDialog(AvailableLoans.this,"Failed to request for the loan");
                    return;
                }

                JSONObject loanJSON = (JSONObject) request.get("loan");


                Long credit = (Long) request.get("credits");
                JSONObject loan = (JSONObject) request.get("loan");
                String due = (String) loan.get("due");
                String loanId = (String) loan.get("id");
                String repayment = (Long) loan.get("repaymentAmount")+"";
                String status = (String) loan.get("status");
                String requestType = (String) loan.get("type");

                String message = String.format("Request Succeed!\ncredits: %s\ndue: %s\nid: %s\nrepayment: %s\nstatus: %s\n" +
                        "type: %s",credit + "", due, loanId, repayment, status, requestType);
                JOptionPane.showMessageDialog(AvailableLoans.this, message);
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
                                        .addComponent(items, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                        .addGap(100)
                                        .addComponent(nameLabel)
                                        .addComponent(id, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(check)
                                        .addGap(60))
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
                                        .addComponent(id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup())
                                        .addComponent(check)
                                )
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
