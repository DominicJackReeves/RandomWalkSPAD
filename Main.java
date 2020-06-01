import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import processing.core.PApplet;
public class Main{

	public static void main(String[] args) {
		new Main();
	}

	private JFrame simFrame;

	private JTextField a_A, a_B, a_C, b_A, b_B, b_C, driftV, voltage_start, voltage_end, voltage_step, depletionRegion, thresh_A, thresh_B, seedNo, injection_n, injection_p, injection_pos, currentLim, builtIn, trial;

	public String readOut = "readOut";

	public Main() {
		simFrame = new JFrame();
		simFrame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		simFrame.setBackground(Color.LIGHT_GRAY);
		simFrame.setResizable(false);
		simFrame.setTitle("APD RPL Model");
		simFrame.setBounds(100, 100, 500, 500);
		simFrame.getContentPane().setLayout(null);
		simFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		simFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);	
			}
		});

		JLabel title = new JLabel("APD RPL");
		title.setBounds(100, 12, 300, 50);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		title.setBackground(Color.LIGHT_GRAY);
		simFrame.getContentPane().add(title);

		JLabel alpha_A = new JLabel("Alpha (m^-1): A,B,C");
		alpha_A.setBounds(30, 50, 247, 23);
		simFrame.getContentPane().add(alpha_A);

		JLabel beta_A = new JLabel("Beta (m^-1): A,B,C");
		beta_A.setBounds(30, 75, 247, 23);
		simFrame.getContentPane().add(beta_A);

		JLabel drift = new JLabel("Drift Velocity (m/s):");
		drift.setBounds(30, 100, 247, 23);
		simFrame.getContentPane().add(drift);

		JLabel electric = new JLabel("Applied Voltage (V): start,end,steps ");
		electric.setBounds(30, 125, 247, 23);
		simFrame.getContentPane().add(electric);

		JLabel depletion = new JLabel("Depletion Region Width (m):");
		depletion.setBounds(30, 150, 247, 23);
		simFrame.getContentPane().add(depletion);

		JLabel energy_A = new JLabel("Alpha Threshold Energy (eV): ");
		energy_A.setBounds(30, 175, 247, 23);
		simFrame.getContentPane().add(energy_A);

		JLabel energy_B = new JLabel("Beta Threshold Energy (eV): ");
		energy_B.setBounds(30, 200, 247, 23);
		simFrame.getContentPane().add(energy_B);

		JLabel seed = new JLabel("Random Generation Seed:");
		seed.setBounds(30, 225, 247, 23);
		simFrame.getContentPane().add(seed);

		JLabel inject_n = new JLabel("Electron Injection:");
		inject_n.setBounds(30, 250, 247, 23);
		simFrame.getContentPane().add(inject_n);

		JLabel inject_p = new JLabel("Hole Injection:");
		inject_p.setBounds(30, 275, 247, 23);
		simFrame.getContentPane().add(inject_p);

		JLabel injectPos = new JLabel("Injection Position (m):");
		injectPos.setBounds(30, 300, 247, 23);
		simFrame.getContentPane().add(injectPos);

		JLabel iLimit =	 new JLabel("Current Limit (A): ");
		iLimit.setBounds(30, 325, 247, 23);
		simFrame.getContentPane().add(iLimit);
		
		JLabel electricSetting = new JLabel("Electric Field Setting: ");
		electricSetting.setBounds(30, 350, 247, 23);
		simFrame.getContentPane().add(electricSetting);

		JLabel builtInV = new JLabel("Built In Voltage (V): ");
		builtInV.setBounds(30, 375, 247, 23);
		simFrame.getContentPane().add(builtInV);

		JLabel trials = new JLabel("No. of trials: ");
		trials.setBounds(30, 400, 247, 23);
		simFrame.getContentPane().add(trials);

		JLabel read = new JLabel(readOut);
		read.setBounds(30, 425, 247, 23);
		simFrame.getContentPane().add(read);

		a_A = new JTextField();
		a_A.setText("4.22E7");
		a_A.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		a_A.setColumns(10);
		a_A.setBounds(289, 50, 60, 23);
		simFrame.getContentPane().add(a_A);

		a_B = new JTextField();
		a_B.setText("7.09E7");
		a_B.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		a_B.setColumns(10);
		a_B.setBounds(349, 50, 60, 23);
		simFrame.getContentPane().add(a_B);

		a_C = new JTextField();
		a_C.setText("1.535");
		a_C.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		a_C.setColumns(10);
		a_C.setBounds(409, 50, 60, 23);
		simFrame.getContentPane().add(a_C);

		b_A = new JTextField();
		b_A.setText("5.89E7");
		b_A.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		b_A.setColumns(10);
		b_A.setBounds(289, 75, 60, 23);
		simFrame.getContentPane().add(b_A);

		b_B = new JTextField();
		b_B.setText("8.89E7");
		b_B.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		b_B.setColumns(10);
		b_B.setBounds(349, 75, 60, 23);
		simFrame.getContentPane().add(b_B);

		b_C = new JTextField();
		b_C.setText("1.402");
		b_C.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		b_C.setColumns(10);
		b_C.setBounds(409, 75, 60, 23);
		simFrame.getContentPane().add(b_C);

		driftV = new JTextField();
		driftV.setText("1");
		driftV.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		driftV.setColumns(10);
		driftV.setBounds(289, 100, 143, 23);
		simFrame.getContentPane().add(driftV);

		voltage_start = new JTextField();
		voltage_start.setText("0");
		voltage_start.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		voltage_start.setColumns(10);
		voltage_start.setBounds(289, 125, 60, 23);
		simFrame.getContentPane().add(voltage_start);

		voltage_end = new JTextField();
		voltage_end.setText("15");
		voltage_end.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		voltage_end.setColumns(10);
		voltage_end.setBounds(349, 125, 60, 23);
		simFrame.getContentPane().add(voltage_end);

		voltage_step = new JTextField();
		voltage_step.setText("10");
		voltage_step.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		voltage_step.setColumns(10);
		voltage_step.setBounds(409, 125, 60, 23);
		simFrame.getContentPane().add(voltage_step);

		depletionRegion = new JTextField();
		depletionRegion.setText("0.2E-6");
		depletionRegion.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		depletionRegion.setColumns(10);
		depletionRegion.setBounds(289, 150, 143, 23);
		simFrame.getContentPane().add(depletionRegion);

		thresh_A = new JTextField();
		thresh_A.setText("3.1");
		thresh_A.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		thresh_A.setColumns(10);
		thresh_A.setBounds(289, 175, 143, 23);
		simFrame.getContentPane().add(thresh_A);


		thresh_B = new JTextField();
		thresh_B.setText("3.3");
		thresh_B.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		thresh_B.setColumns(10);
		thresh_B.setBounds(289, 200, 143, 23);
		simFrame.getContentPane().add(thresh_B);

		seedNo = new JTextField();
		seedNo.setText("1");
		seedNo.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		seedNo.setColumns(10);
		seedNo.setBounds(289, 225, 143, 23);
		simFrame.getContentPane().add(seedNo);

		injection_n = new JTextField();
		injection_n.setText("1");
		injection_n.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		injection_n.setColumns(10);
		injection_n.setBounds(289, 250, 143, 23);
		simFrame.getContentPane().add(injection_n);

		injection_p = new JTextField();
		injection_p.setText("0");
		injection_p.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		injection_p.setColumns(10);
		injection_p.setBounds(289, 275, 143, 23);
		simFrame.getContentPane().add(injection_p);

		injection_pos = new JTextField();
		injection_pos.setText("0");
		injection_pos.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		injection_pos.setColumns(10);
		injection_pos.setBounds(289, 300, 143, 23);
		simFrame.getContentPane().add(injection_pos);

		currentLim = new JTextField();
		currentLim.setText("100");
		currentLim.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		currentLim.setColumns(10);
		currentLim.setBounds(289, 325, 143, 23);
		simFrame.getContentPane().add(currentLim);
		
		JComboBox electricSettingBox = new JComboBox();
		electricSettingBox.setModel(new DefaultComboBoxModel(new String[] {"Constant Field", "Boundary Fields"}));
		electricSettingBox.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		electricSettingBox.setBounds(289, 350, 143, 23);
		simFrame.getContentPane().add(electricSettingBox);
		electricSettingBox.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if(electricSettingBox.getSelectedItem() == "Constant Field"){
					builtIn.setEnabled(true);
					builtIn.setBackground(Color.WHITE);
					builtIn.setText("1.2");
				}else{
					builtIn.setEnabled(false);
					builtIn.setBackground(Color.LIGHT_GRAY);
					builtIn.setText("No Y0 Value Needed");
				}
			}
		});
		
		

		builtIn = new JTextField();
		builtIn.setText("1.2");
		builtIn.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		builtIn.setColumns(10);
		builtIn.setBounds(289, 375, 143, 23);
		simFrame.getContentPane().add(builtIn);
		

		trial = new JTextField();
		trial.setText("100000");
		trial.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		trial.setColumns(10);
		trial.setBounds(289, 400, 143, 23);
		simFrame.getContentPane().add(trial);

		Button button = new Button("Start Test");
		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						Param.a_A=getDouble(a_A);
						Param.a_B=getDouble(a_B);
						Param.a_C=getDouble(a_C);
						Param.b_A=getDouble(b_A);
						Param.b_B=getDouble(b_B);
						Param.b_C=getDouble(b_C);
						Param.driftV=getDouble(driftV);
						Param.voltage_start=getDouble(voltage_start);
						Param.voltage_end=getDouble(voltage_end);
						Param.voltage_step=getDouble(voltage_step);
						Param.depletionRegion=getDouble(depletionRegion);
						Param.thresh_A=getDouble(thresh_A)*Param.e;
						Param.thresh_B=getDouble(thresh_B)*Param.e;
						Param.seedNo=getDouble(seedNo);
				 		Param.injection_n=getDouble(injection_n);
						Param.injection_p=getDouble(injection_p);
						Param.injection_pos=getDouble(injection_pos);
						Param.currentLim=getDouble(currentLim);
						Param.builtInVoltage=getDouble(builtIn);
						Param.trials=(int) getDouble(trial);
						if(electricSettingBox.getSelectedItem() == "Constant Field"){
							Param.eField.add(new Field(Param.depletionRegion, Param.builtInVoltage/Param.depletionRegion));
						}else{
							//THIS IS WHERE THE FILE READER NEEDS TO BE 
						}
						Trials.trials();
						System.out.println();
					}
				}
				);
		button.setBounds(200, 450, 100, 30);
		simFrame.getContentPane().add(button);

		simFrame.setVisible(true);
	}

	private static double getDouble(JTextField t){
		return Double.parseDouble(t.getText());
	}
}