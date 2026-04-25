package Game;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Piece extends JButton{
	private String color = null;
	private String type = "";
	private boolean selected = false;
	private String sprite =null;
	private boolean northborder = false;
	private boolean southborder = false;
	private boolean westborder = false;
	private boolean eastborder = false;
	private boolean upgradeable = false;
	private String tri = null;
	public Piece() {
		this.color = null;
		this.type = null;
	}
	public Piece(String c, String t) {
		this.color = c;
		this.type = t;
	}
	
	
	public void SetNBorder(boolean b) {
		this.northborder=b;
	}
	public void SetUpgrade(boolean b) {
		this.upgradeable=b;
	}
	public boolean GetUpgrade() {
		return upgradeable;
	}
	public void SetSBorder(boolean b) {
		this.southborder=b;
	}
	public void SetEBorder(boolean b) {
		this.eastborder=b;
	}
	public void SetWBorder(boolean b) {
		this.westborder=b;
	}
	public boolean GetNBorder() {
		return northborder;
	}
	public boolean GetSBorder() {
		return southborder;
	}
	public boolean GetEBorder() {
		return eastborder;
	}
	public boolean GetWBorder() {
		return westborder;
	}
	public void SetColor(String color) {
		this.color=color;
	}
	public String GetColor() {
		if (this.color==null) {
			return "";}
		else
		return color;
	}
	public void SetType(String type) {
		this.type=type;
	}
	public String GetType() {
		if (this.type==null) {
		return "";}
		else
		return type;
	}
	public void SetTri(String type) {
		this.tri=type;
	}
	public String GetTri() {
	if(this.tri==null)
		return "";
		else
		return tri;
	}
	public void SetImage() {
		if (this.color==null||this.type==null) {
			this.setIcon(null);
		}
		sprite=color+type+".png";
		System.out.println(sprite);
		ImageIcon imgicon = new ImageIcon("src/Pieces/"+sprite);
		this.setIcon(imgicon);
	}
	
}
