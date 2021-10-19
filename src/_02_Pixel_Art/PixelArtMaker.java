package _02_Pixel_Art;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PixelArtMaker implements MouseListener, ActionListener{
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	ColorSelectionPanel csp;
	JButton saveButton = new JButton("Save");
	
	//Create a load button and code to load in previously saved art. You already finished saving the art.
	
	
	public void start() {
		gip = new GridInputPanel(this);	
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		
		saveButton.addActionListener(this);
		
		window.add(gip);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void submitGridData(int w, int h, int r, int c) {
		gp = new GridPanel(w, h, r, c);
		csp = new ColorSelectionPanel();
		window.remove(gip);
		window.add(gp);
		window.add(csp);
		window.add(saveButton);
		gp.repaint();
		gp.addMouseListener(this);
		window.pack();
	}
	
	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		System.out.println(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		System.out.println("x: " + e.getX() + "   y: "+e.getY());
		gp.repaint();
		
//		if(e.getSource() == saveButton){
//			System.out.println("asdfjadsk;l");
//		}
//		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	
	
	public static void save(GridPanel gp){
		try(FileOutputStream fos = new FileOutputStream(new File("pixelCoordinates.txt")); ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(gp);
			oos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static GridPanel load(){
		try(FileInputStream fis = new FileInputStream(new File("pixelCoordinates.txt")); ObjectInputStream ois = new ObjectInputStream(fis)){
			return (GridPanel) ois.readObject();
		} catch(IOException e){
			e.printStackTrace();
			return null;
		} catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == saveButton){
			System.out.println("asdflakjp;");
			save(gp);
		}
	}
	
	
	
	
}
