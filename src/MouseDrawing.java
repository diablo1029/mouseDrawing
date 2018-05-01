import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class MouseDrawing extends JFrame 
{
    
	/*
	 *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * DECLARATION AND INITIALIZATION OF JPANEL CHILDREN AND COLORS
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	
    JMenuBar mainMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu canvasMenu = new JMenu("Canvas");
    JMenu penMenu = new JMenu("Pen");
    JMenuItem BlackMenuItem = new JMenuItem("Black");
    JMenuItem YellowMenuItem = new JMenuItem("Yellow");
    JMenuItem BlueMenuItem = new JMenuItem("Blue");
    JMenuItem PinkMenuItem = new JMenuItem("Pink");
    JMenuItem WhiteMenuItem = new JMenuItem("White");
    JMenuItem smallMenuItem = new JMenuItem("Small");
    JMenuItem mediumMenuItem = new JMenuItem("Medium");
    JMenuItem largeMenuItem = new JMenuItem("Large");

    JMenuItem newMenuItem = new JMenuItem("New");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    JPanel drawPanel = new JPanel();
    JLabel leftColorLabel = new JLabel();
    JLabel rightColorLabel = new JLabel();
    JPanel colorPanel = new JPanel();
    JLabel[] colorLabel = new JLabel[20];
    Graphics2D g2D;
    double xPrevious, yPrevious;
    Color drawColor, leftColor, rightColor;
    
    /*
	 *\\\\
	 * END
	 * \\\
	 */

    /*
     * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
     * MAIN FUNCTION THAT INSTANTIATES NEW Main() CONSTRUCTOR TO CREATE JFRAME
     * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
     */
    
    public static void main(String args[]) 
    {
        // construct frame
        new MouseDrawing().setVisible(true);
    }
    
    /*
	 *\\\\
	 * END
	 * \\\
	 */
    
    /*
     * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
     * PRIMARY CONSTRUCTOR TO RUN WHEN INSTANTIATED BY MAIN FUNCTION
     * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
     */
    
    public MouseDrawing()
    {
        
        setTitle("Blackboard");
        setResizable(false);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                exitForm(e);
            }
        });
        getContentPane().setLayout(new GridBagLayout());
        Color backgroundColor = new Color(150, 150, 250);
        getContentPane().setBackground(backgroundColor);
        
        setJMenuBar(mainMenuBar);
        fileMenu.setMnemonic('F');
        Color menubarColor = new Color(100, 100, 200);
        mainMenuBar.setBackground(menubarColor);
        colorPanel.setBackground(menubarColor);
        mainMenuBar.add(fileMenu);
        mainMenuBar.add(canvasMenu);
        mainMenuBar.add(penMenu);
        fileMenu.add(newMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        canvasMenu.add(BlackMenuItem);
        canvasMenu.add(BlueMenuItem);
        canvasMenu.add(YellowMenuItem);
        canvasMenu.add(PinkMenuItem);
        canvasMenu.add(WhiteMenuItem);
        penMenu.add(smallMenuItem);
        penMenu.add(mediumMenuItem);
        penMenu.add(largeMenuItem);
        
        drawPanel.setPreferredSize(new Dimension(1000, 1000));
        drawPanel.setBackground(Color.BLACK);
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.gridheight = 2;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(drawPanel, gridConstraints);
        
        leftColorLabel.setPreferredSize(new Dimension(40,40));
        leftColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,5,10,10);
        getContentPane().add(leftColorLabel, gridConstraints);
        rightColorLabel.setPreferredSize(new Dimension(40,40));
        rightColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,5,10,10);
        getContentPane().add(rightColorLabel, gridConstraints);
        
        colorPanel.setPreferredSize(new Dimension(80,300));
        colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
        colorPanel.setForeground(Color.WHITE);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 2;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(colorPanel, gridConstraints);
        
        colorPanel.setLayout(new GridBagLayout());
        int j = 0;
        for (int i = 0; i < 16; i++)
        {
            colorLabel[i] = new JLabel();
            colorLabel[i].setPreferredSize(new Dimension(30,30));
            colorLabel[i].setOpaque(true);
            gridConstraints = new GridBagConstraints();
            gridConstraints.gridx = j;
            gridConstraints.gridy = i - j * 8;
            colorPanel.add(colorLabel[i], gridConstraints);
            if(i==7)
            {
                j++;
            }
            colorLabel[i].addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent e)
                {
                    colorMousePressed(e);
                }
            });
        }
        Color Color00 = new Color(16,16,255);
        Color Color01 = new Color(16,255,255);
        Color Color02 = new Color(16,255,16);
        Color Color03 = new Color(255,255,16);
        Color Color04 = new Color(255,16,16);
        Color Color05 = new Color(16,160,255);
        Color Color06 = new Color(16,16,16);
        Color Color07 = new Color(255,16,255);
        
        Color Color08 = new Color(160,160,255);
        Color Color09 = new Color(160,255,255);
        Color Color10 = new Color(160,255,160);
        Color Color11 = new Color(255,255,160);
        Color Color12 = new Color(255,160,160);
        Color Color13 = new Color(160,16,255);
        Color Color14 = new Color(160,160,160);
        Color Color15 = new Color(255,160,255);
        
        colorLabel[0].setBackground(Color00);
        colorLabel[1].setBackground(Color01);
        colorLabel[2].setBackground(Color02);
        colorLabel[3].setBackground(Color03);
        colorLabel[4].setBackground(Color04);
        colorLabel[5].setBackground(Color05);
        colorLabel[6].setBackground(Color06);
        colorLabel[7].setBackground(Color07);
        
        colorLabel[8].setBackground(Color08);
        colorLabel[9].setBackground(Color09);
        colorLabel[10].setBackground(Color10);
        colorLabel[11].setBackground(Color11);
        colorLabel[12].setBackground(Color12);
        colorLabel[13].setBackground(Color13);
        colorLabel[14].setBackground(Color14);
        colorLabel[15].setBackground(Color15);
        
        leftColor = colorLabel[0].getBackground();
        leftColorLabel.setBackground(leftColor);
        rightColor = colorLabel[15].getBackground();
        rightColorLabel.setBackground(rightColor);
        
        
        newMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		newMenuItemActionPerformed(e);
        	}
        });
        
        exitMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		exitMenuItemActionPerformed(e);
        	}
        });
        
        BlackMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		BlackMenuItemActionPerformed(e);
        	}
        });
        
        BlueMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		BlueMenuItemActionPerformed(e);
        	}
        });
        
        YellowMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		YellowMenuItemActionPerformed(e);
        	}
        });
        
        PinkMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		PinkMenuItemActionPerformed(e);
        	}
        });
        
        WhiteMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		WhiteMenuItemActionPerformed(e);
        	}
        });
        
        smallMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		smallMenuItemActionPerformed(e);
        	}
        });
        
        mediumMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		mediumMenuItemActionPerformed(e);
        	}
        });
        
        largeMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		largeMenuItemActionPerformed(e);
        	}
        });
        
        drawPanel.addMouseListener(new MouseAdapter() {
        	public void mousePressed (MouseEvent e) {
        		drawPanelMousePressed(e);
        	}
        });
        drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
        	public void mouseDragged (MouseEvent e) {
        		drawPanelMouseDragged(e);
        	}

        });
        drawPanel.addMouseListener(new MouseAdapter() {
        	public void mouseReleased (MouseEvent e) {
        		drawPanelMouseReleased(e);
        	}
        });
        
        
        /*
    	 *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    	 * GETTER OF SETTINGS IN CONSTRUCTOR
    	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    	 */
        
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())),getWidth(),getHeight());
        g2D = (Graphics2D) drawPanel.getGraphics();
        
        /*
    	 *\\\\
    	 * END
    	 * \\\
    	 */
    }
    
    /*
	 *\\\\\\\\\\\\\\\\\\\
	 * END OF CONSTRUCTOR
	 * \\\\\\\\\\\\\\\\\\
	 */
    
    private void drawPanelMousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 || 
				e.getButton() == MouseEvent.BUTTON3) {
			xPrevious = e.getX();
			yPrevious = e.getY();
			if (e.getButton() == MouseEvent.BUTTON1) {
				drawColor = leftColor;
			}
			else {
				drawColor = rightColor;
			}
		}
	}
    
	private void drawPanelMouseDragged(MouseEvent e) {
		Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, 
				e.getX(), e.getY());
		g2D.setPaint(drawColor);
		g2D.draw(myLine);
		xPrevious = e.getX();
		yPrevious = e.getY();
		
	}
	
	private void drawPanelMouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 || 
				e.getButton() == MouseEvent.BUTTON3) {
			Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, 
					e.getX(), e.getY());
			g2D.setPaint(drawColor);
			g2D.draw(myLine);
		}
	}

	private void colorMousePressed(MouseEvent e)
    {
        Component clickedColor = e.getComponent();
        Toolkit.getDefaultToolkit().beep();
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            leftColor = clickedColor.getBackground();
            leftColorLabel.setBackground(leftColor);
        }else if (e.getButton() == MouseEvent.BUTTON3)
        {
            rightColor = clickedColor.getBackground();
            rightColorLabel.setBackground(rightColor);
        }
    }
    
    private void newMenuItemActionPerformed (ActionEvent e) {
    	int response;
    	response = JOptionPane.showConfirmDialog(null, "Are you sure you want to "
    			+ "start a new drawing?", "New Drawing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	if (response == JOptionPane.YES_OPTION) {
    		g2D.setPaint(drawPanel.getBackground());
    		g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));
    	}
    }
    
    private void exitMenuItemActionPerformed (ActionEvent e) {
    	int response;
    	response = JOptionPane.showConfirmDialog(null, "Are you sure you want to "
    			+ "exit the BlackBoard program?", "Exit Program", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	if (response == JOptionPane.NO_OPTION) {
    		return;
    	}
    	else {
    		exitForm(null);
    	}
    	
    }
    
    private void BlackMenuItemActionPerformed (ActionEvent e) {
    	boolean response;
    	response = true;
    	if (response == true) {
    		drawPanel.setBackground(Color.BLACK);
    	}
    }
    
    private void BlueMenuItemActionPerformed (ActionEvent e) {
    	boolean response;
    	response = true;
    	if (response == true) {
    		drawPanel.setBackground(Color.BLUE);
    	}
    }
    
    private void YellowMenuItemActionPerformed (ActionEvent e) {
    	boolean response;
    	response = true;
    	if (response == true) {
    		drawPanel.setBackground(Color.YELLOW);
    	}
    }
    
    private void PinkMenuItemActionPerformed (ActionEvent e) {
    	boolean response;
    	response = true;
    	if (response == true) {
    		drawPanel.setBackground(Color.PINK);
    	}
    }
    
    private void WhiteMenuItemActionPerformed (ActionEvent e) {
    	boolean response;
    	response = true;
    	if (response == true) {
    		drawPanel.setBackground(Color.WHITE);
    	}
    }
    
    private void smallMenuItemActionPerformed (ActionEvent e) {
    	boolean response;
    	response = true;
    	if (response == true) {
    		g2D.setStroke(new BasicStroke(1));
    	}
    }
    
    private void mediumMenuItemActionPerformed (ActionEvent e) {
    	boolean response;
    	response = true;
    	if (response == true) {
    		g2D.setStroke(new BasicStroke(2));
    	}
    }
    
    private void largeMenuItemActionPerformed (ActionEvent e) {
    	boolean response;
    	response = true;
    	if (response == true) {
    		g2D.setStroke(new BasicStroke(5));
    	}
    }
    
    private void exitForm(WindowEvent e)
    {
        g2D.dispose();
        System.exit(0);
    }
}