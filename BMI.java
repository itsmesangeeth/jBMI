import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;

public class BMI extends JFrame {

	private static final long serialVersionUID = 6192702356246333494L;
	JTextField heightField;
	JTextField weightField;
	DecimalFormat fmt = new DecimalFormat("0.00");
	JLabel resultat;

	BMI() {
		super("BMI Calculator");
		setLayout(new FlowLayout());
		JLabel heightLable = new JLabel("Height (cm) :");
		add(heightLable);
		heightField = new JTextField(4);
		add(heightField);
		JLabel weightLable = new JLabel("Weight (kg) :");
		add(weightLable);
		weightField = new JTextField(4);
		add(weightField);

		final JButton submitButton = new JButton("-  Calculate BMI  -");
		submitButton.setForeground(Color.BLUE);
		submitButton.addActionListener(new BeraknaLyss());
		submitButton.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				/* ... */
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					submitButton.doClick();
				}
			}

			public void keyReleased(KeyEvent e) { /* ... */
			}

			public void keyTyped(KeyEvent e) { /* ... */
			}
		});
		add(submitButton);

		final JButton reset = new JButton("-       Reset      -");
		reset.setForeground(Color.RED);
		reset.addActionListener(new Reset());
		reset.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				/* ... */
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					reset.doClick();
					// heightField.grabFocus();
				}
			}

			public void keyReleased(KeyEvent e) { /* ... */
			}

			public void keyTyped(KeyEvent e) { /* ... */
			}
		});
		add(reset);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(180, 250);
		setForeground(Color.DARK_GRAY);
		setVisible(true);
		setLocation(420, 200);
		// JLabel bmiVisning = new JLabel("Calculated BMI: ");
		// add(bmiVisning);
		resultat = new JLabel("");
		add(resultat);

	}

	class Reset implements ActionListener {
		public void actionPerformed(ActionEvent ave) {
			resetFields();
			resultat.setText("");
			heightField.grabFocus();
		}
	}

	public void resetFields() {
		weightField.setText("");
		heightField.setText("");
	}

	class BeraknaLyss implements ActionListener {
		public void actionPerformed(ActionEvent ave) {
			StringBuilder errorString = new StringBuilder();
			String bmi = null;
			if (weightField.getText().trim().equals("")) {
				errorString.append("Enter Weight \n");
			}
			if (heightField.getText().trim().equals("")) {
				errorString.append("Enter Height \n");
			}
			if (errorString.toString().trim().equals("")) {
				double weightFieldValue = Double.parseDouble(weightField
						.getText());
				double heightFieldValue = Double.parseDouble(heightField
						.getText());
				bmi = (String) beraknaBMI(weightFieldValue, heightFieldValue);
				resultat.setText("Calculated BMI: " + bmi);
			} else {
				resetFields();
				resultat.setText(errorString.toString());
			}

		}
	}

	public String beraknaBMI(double weightFieldValue, double heightFieldValue) {
		return new DecimalFormat("#.##").format(weightFieldValue
				/ (heightFieldValue / 100 * heightFieldValue / 100));
	}

	public static void main(String[] args) {
		new BMI();
	}

}