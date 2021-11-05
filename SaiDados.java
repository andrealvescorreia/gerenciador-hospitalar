package principal;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public abstract class SaiDados {
	
	
	public static void JOptionPaneComScroller(String texto, String titulo, int xPixels, int yPixels) {
		
		JScrollPane scrollPane = new JScrollPane(new JLabel(texto));
        scrollPane.setPreferredSize(new Dimension(xPixels,yPixels));
        Object messagem = scrollPane;

        
        JTextArea textArea = new JTextArea(texto);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(5,5,5,5));
        scrollPane.getViewport().setView(textArea);
        JOptionPane.showMessageDialog(null,
                                           messagem,
                                           titulo,
                                           JOptionPane.OK_OPTION);
	}
}
