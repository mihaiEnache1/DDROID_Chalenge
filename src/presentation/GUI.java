package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JPanel contentPane;
    private final DefaultTableModel defaultTableModel;
    private final JTable table;
    private final JTextArea textArea;
    private final JButton addButton;
    private final JButton checkOutButton;
    private final JTextArea checkArea;

    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1074, 615);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(30, 32, 420, 300);
        contentPane.add(panel);

        String[] columns = {"Item name", "Item price", "Shipped from", "Weight", "Shipping Rate"};

        defaultTableModel = new DefaultTableModel(columns, 0);

        table = new JTable(defaultTableModel);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        textArea = new JTextArea();
        textArea.setBounds(507, 32, 484, 300);
        contentPane.add(textArea);

        addButton = new JButton("Add to cart");
        addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addButton.setBounds(141, 359, 155, 53);
        contentPane.add(addButton);

        checkOutButton = new JButton("Check out");
        checkOutButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        checkOutButton.setBounds(141, 436, 155, 53);
        contentPane.add(checkOutButton);

        checkArea = new JTextArea();
        checkArea.setBounds(507, 365, 484, 187);
        contentPane.add(checkArea);

        this.setVisible(true);
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getDefaultTableModel() {
        return defaultTableModel;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void addButtonListener(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void setTextArea(String text) {
        this.textArea.setText(this.textArea.getText() + text);
    }

    public void setCheckArea(String text) {
        this.checkArea.setText(this.checkArea.getText() + text);
    }

    public JButton getCheckOutButton() {
        return checkOutButton;
    }

    public void addCheckListener(ActionListener actionListener) {
        checkOutButton.addActionListener(actionListener);
    }
}
