import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreOrderGui {

    String itemName = "Iphone 14 pro";
    float itemPrice = 1350999;

    public static void main(String[] args) {
        PreOrderGui newApp = new PreOrderGui();
        newApp.gui();
    }

    public void gui () {
        // Create stack
        Queue preorderQueue = new Queue(20);

        // Frame Setup
        JFrame frame = new JFrame("Pre-Order The "+itemName+ " Today.");
        frame.setLayout(new GridLayout(10, 1));
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Goods to pre-order info
        JLabel itemText = new JLabel("Item: " + itemName);
        JLabel itemPriceText = new JLabel("Price: N" + itemPrice);

        // Fields
        JLabel fullNameLabel = new JLabel("full name");
        JTextField fullName = new JTextField();

        JLabel amountLabel = new JLabel("Amount");
        JTextField amount = new JTextField();

        // Buttons
        JButton preOrderButton = new JButton("Pre-Order");
        preOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameValue = fullName.getText();
                int amountValue = Integer.parseInt(amount.getText());
                if (amountValue > itemPrice) {
                    preorderQueue.enqueue(nameValue);
                    JOptionPane.showMessageDialog(null, nameValue + ", You just successfully PreOrdered the "+itemName);

                    fullName.setText("");
                    amount.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Sorry, you do not have sufficient money to complete this purchase.");
                }

            }
        });
        JButton removePreOrderButton = new JButton("Remove Pre-Order");
        removePreOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // when the user pre-ordered item has been sent out
                // on delivery we are allowed to remove the user from the
                // queue of preorders
                String name = preorderQueue.dequeue();
                if (name != null) {
                    JOptionPane.showMessageDialog(null, name + "'s " + itemName + " has been succesfully sent out on delivery to him and removed from the queue. " );
                } else {
                    JOptionPane.showMessageDialog(null, "The Queue is Empty");
                }
            }
        });
        JButton printOrdersButton = new JButton("Print all Pre-Orders");
        printOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preorderQueue.printOutQueue();
            }
        });

        // Add to frame
        frame.add(itemText);
        frame.add(itemPriceText);
        frame.add(fullNameLabel);
        frame.add(fullName);
        frame.add(amountLabel);
        frame.add(amount);
        frame.add(preOrderButton);
        frame.add(removePreOrderButton);
        frame.add(printOrdersButton);

        // See frame
        frame.setVisible(true);
    }
}


class Queue {
    private String array[];
    private int front;
    private int back;
    private int size;

    Queue (int size) {
        array = new String[size];
        this.front = -1;
        this.back = -1;
        this.size = size;
    }

    public void enqueue (String item) {
        if (isFull()) {
            System.out.println("Queue is completely full!");
            return;
        }

        if (front == -1) front = 0;
        back++;
        array[back] = item;
    }

    public String dequeue () {
        if (isEmpty()) {
            System.out.println("Queue is completely empty!");
            return null;
        }
        String element = array[front];
        if (front >= back) {
            front = -1; back = -1;
        } else {
            front++;
        }
        return element;
    }


    public boolean isEmpty() { return front == back; }

    public boolean isFull() { return back == size - 1; }

    public String peek() { return array[0]; }

    public void printOutQueue() {
        for (int i = front; i <= back; i++) {
            System.out.println(array[i]);
        }
    }
}
