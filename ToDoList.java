import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class App extends JFrame {
    private JButton addButton;
    private JButton removeButton;
    private JList<String> taskList;
    private DefaultListModel<String> taskListModel;
    private ToDoListManager manager;

    public App() {
        setTitle("To-Do List Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the model and manager
        taskListModel = new DefaultListModel<>();
        manager = new ToDoListManager();

        // Create and customize UI components
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Task");
        taskList = new JList<>(taskListModel);

        // Add components to panels and set their styles
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        JScrollPane scrollPane = new JScrollPane(taskList);

        // Set colors, fonts, and other styles
        addButton.setBackground(Color.GREEN);
        removeButton.setBackground(Color.RED);
        taskList.setFont(new Font("Arial", Font.PLAIN, 16));

        // Add panels to the main frame
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add action listeners to the buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String task = JOptionPane.showInputDialog(App.this, "Enter task:");
                if (task != null && !task.isEmpty()) {
                    manager.addTask(task);
                    taskListModel.addElement(task);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String removedTask = taskListModel.remove(selectedIndex);
                    manager.removeTask(selectedIndex);
                    JOptionPane.showMessageDialog(App.this, "Task removed: " + removedTask);
                } else {
                    JOptionPane.showMessageDialog(App.this, "Please select a task to remove.");
                }
            }
        });

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}

class ToDoListManager {
    private ArrayList<String> tasks;

    public ToDoListManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }
}
