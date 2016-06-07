import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GUITextBoard extends JPanel implements Observer {
	private static final long serialVersionUID = 1;
	private JList<String> list;
	private DefaultListModel<String> lines;
	int m_line_number;

	GUITextBoard() {
		m_line_number = 0;
		setLayout(new BorderLayout());
		list = new JList<String>();
		
		lines = new DefaultListModel<String>();
		list.setModel(lines);
		list.setFixedCellHeight(30);
		list.setOpaque(false);
        list.setBackground(new Color(0, 0, 0, 0));
        list.setForeground(Color.BLACK);
        list.setBorder(BorderFactory.createEmptyBorder());
        list.setSelectionBackground(new Color(0,0,0,0));
        list.setSelectionForeground(Color.BLACK);
		JScrollPane pane = new JScrollPane(list);
		pane.setOpaque(false);
		pane.getViewport().setOpaque(false);
		add(pane, BorderLayout.CENTER);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		String str = (String)arg;
		if(str.substring(0, 5).equals("BLACK") ||
				str.substring(0, 5).equals("WHITE")) {
			this.m_line_number++;
			lines.addElement(this.m_line_number+" "+(String)arg);
		}
		else 
			lines.addElement("     "+(String)arg);
		list.ensureIndexIsVisible(lines.getSize() - 1);
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.drawImage(new ImageIcon("background/right.jpg").getImage(),
				0, 0, this);
		graphics.drawImage(new ImageIcon("background/history.png").getImage(),
				350, 400, 150, 100, this);
	}
}
