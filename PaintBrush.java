import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.event.MouseInputListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

public class PaintBrush extends JFrame implements MouseInputListener {

    private JPanel colorPanel;
    private JPanel bluePanel;
    private JPanel redPanel;
    private JPanel greenPanel;
    private JPanel yellowPanel;
    private JPanel orangePanel;
    private JPanel magentaPanel;
    private JPanel blackPanel;

    private JPanel buttonPanel;
    private JButton rectangleButton;
    private JButton ellipticalButton;
    private JButton pencilButton;
    private JButton moveButton;
    private ArrayList<Drawing> drawings = new ArrayList<>();

    private int windowHeight = 500;
    private int windowWidth = 500;

    private int x1 = 0;
    private int x2 = 0;
    private int y1 = 0;
    private int y2 = 0;
    private int choice = -1; // -1 start, 0 rectangle, 1 elliptical, 2 pencil, 3 move
    private int color = 6; // 0 blue, 1 red, 2 green, 3 yellow, 4 orange, 5 magenta, 6 black
    private int movedItemIndex = -1;
    private int movedW = 0;
    private int movedH = 0;
    private Drawing resultItem = new Drawing();

    public PaintBrush() {
        setTitle("Paint Brush");
        addMouseListener(this);
        addMouseMotionListener(this);
        setSize(this.windowWidth, this.windowHeight);
        setLayout(new BorderLayout());
        // Color & Button & Blue Border panels
        JPanel top = new JPanel(new BorderLayout());
        // Color Panel
        this.colorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        this.bluePanel = new JPanel();
        this.bluePanel.setBackground(Color.blue);
        this.bluePanel.setPreferredSize(new Dimension(50, 25));
        this.redPanel = new JPanel();
        this.redPanel.setBackground(Color.red);
        this.redPanel.setPreferredSize(new Dimension(50, 25));
        this.greenPanel = new JPanel();
        this.greenPanel.setBackground(Color.green);
        this.greenPanel.setPreferredSize(new Dimension(50, 25));
        this.yellowPanel = new JPanel();
        this.yellowPanel.setBackground(Color.yellow);
        this.yellowPanel.setPreferredSize(new Dimension(50, 25));
        this.orangePanel = new JPanel();
        this.orangePanel.setBackground(Color.orange);
        this.orangePanel.setPreferredSize(new Dimension(50, 25));
        this.magentaPanel = new JPanel();
        this.magentaPanel.setBackground(Color.magenta);
        this.magentaPanel.setPreferredSize(new Dimension(50, 25));
        this.blackPanel = new JPanel();
        this.blackPanel.setBackground(Color.black);
        this.blackPanel.setPreferredSize(new Dimension(50, 25));
        this.colorPanel.add(bluePanel);
        this.colorPanel.add(redPanel);
        this.colorPanel.add(greenPanel);
        this.colorPanel.add(yellowPanel);
        this.colorPanel.add(orangePanel);
        this.colorPanel.add(magentaPanel);
        this.colorPanel.add(blackPanel);
        // Button Panel
        this.buttonPanel = new JPanel(new FlowLayout());
        this.rectangleButton = new JButton("Dikdortgen Ciz");
        this.ellipticalButton = new JButton("Oval Ciz");
        this.pencilButton = new JButton("Kalemle Ciz");
        this.moveButton = new JButton("Tasi");
        this.addActionListenerToButtons();
        this.buttonPanel.add(rectangleButton);
        this.buttonPanel.add(ellipticalButton);
        this.buttonPanel.add(pencilButton);
        this.buttonPanel.add(moveButton);
        // Blue Border Panel
        JPanel blueBorder = new JPanel();
        blueBorder.setPreferredSize(new Dimension(this.windowWidth, 3));
        blueBorder.setBackground(Color.blue);
        // Top Frame
        top.add(this.colorPanel, BorderLayout.NORTH);
        top.add(this.buttonPanel, BorderLayout.CENTER);
        top.add(blueBorder, BorderLayout.SOUTH);
        // Main Frame
        add(top, BorderLayout.NORTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void addActionListenerToButtons() {
        this.rectangleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choice = 0;
            }
        });
        this.ellipticalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choice = 1;
            }
        });
        this.pencilButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choice = 2;
            }
        });
        this.moveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choice = 3;
            }
        });
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (y <= 53) {
            if (x >= (this.windowWidth / 2) - 25 && x < (this.windowWidth / 2) + 25) { // yellow
                this.color = 3;
            } else if (x >= (this.windowWidth / 2) - 80 && x < (this.windowWidth / 2) - 30) { // green
                this.color = 2;
            } else if (x >= (this.windowWidth / 2) - 130 && x < (this.windowWidth / 2) - 80) { // red
                this.color = 1;
            } else if (x >= (this.windowWidth / 2) - 180 && x < (this.windowWidth / 2) - 130) { // blue
                this.color = 0;
            } else if (x >= (this.windowWidth / 2) + 30 && x < (this.windowWidth / 2) + 80) { // orange
                this.color = 4;
            } else if (x >= (this.windowWidth / 2) + 80 && x < (this.windowWidth / 2) + 130) { // magenta
                this.color = 5;
            } else if (x >= (this.windowWidth / 2) + 103 && x < (this.windowWidth / 2) + 180) { // black
                this.color = 6;
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        this.x1 = e.getX();
        this.y1 = e.getY();
        Graphics g = getGraphics();
        if (this.color == 0) {
            g.setColor(Color.blue);
        } else if (this.color == 1) {
            g.setColor(Color.red);
        } else if (this.color == 2) {
            g.setColor(Color.green);
        } else if (this.color == 3) {
            g.setColor(Color.yellow);
        } else if (this.color == 4) {
            g.setColor(Color.orange);
        } else if (this.color == 5) {
            g.setColor(Color.magenta);
        } else if (this.color == 6) {
            g.setColor(Color.black);
        }
        if (this.choice == 2 && this.y1 >= 95) {
            Drawing draw = new Drawing(this.x1, this.y1, 5, 5, this.color, this.choice);
            this.drawings.add(draw);
            g.fillOval(e.getX(), e.getY(), 5, 5);
        } else if (this.choice == 3) {
            if (this.drawings.size() > 0) {
                for (int i = 0; i < this.drawings.size(); i++) {
                    Drawing draw = this.drawings.get(i);
                    int dx = draw.x;
                    int dy = draw.y;
                    int dwidth = draw.width;
                    int dheight = draw.height;
                    if (draw.shape == 0) {
                        if (this.x1 >= dx && this.x1 <= (dx + dwidth) && this.y1 >= dy && this.y1 <= (dy + dheight)) {
                            this.movedItemIndex = i;
                            this.resultItem.width = draw.width;
                            this.resultItem.height = draw.height;
                            this.resultItem.color = draw.color;
                            this.resultItem.shape = draw.shape;
                            this.movedW = this.x1 - draw.x;
                            this.movedH = this.y1 - draw.y;
                            draw.width = 0;
                            draw.height = 0;
                            break;
                        }
                    }
                    if (draw.shape == 1) {
                        double h = (double) (draw.x + (draw.width / 2));
                        double k = (double) (draw.y + (draw.height / 2));
                        double rx = (double) draw.width / 2;
                        double ry = (double) draw.height / 2;
                        double res1 = (double) ((double) Math.pow(this.x1 - h, 2) / (double) Math.pow(rx, 2));
                        double res2 = (double) ((double) Math.pow(this.y1 - k, 2) / (double) Math.pow(ry, 2));
                        double result = res1 + res2;
                        if (result <= 1) {
                            this.movedItemIndex = i;
                            this.resultItem.width = draw.width;
                            this.resultItem.height = draw.height;
                            this.resultItem.color = draw.color;
                            this.resultItem.shape = draw.shape;
                            this.movedW = this.x1 - draw.x;
                            this.movedH = this.y1 - draw.y;
                            draw.width = 0;
                            draw.height = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        this.x2 = e.getX();
        this.y2 = e.getY();
        if (this.x2 > this.windowWidth) {
            this.x2 = this.windowWidth;
        }
        if (this.y2 > this.windowHeight) {
            this.y2 = this.windowHeight;
        }
        if (this.x2 < 0) {
            this.x2 = 0;
        }
        Graphics g = getGraphics();
        if (this.color == 0) {
            g.setColor(Color.blue);
        } else if (this.color == 1) {
            g.setColor(Color.red);
        } else if (this.color == 2) {
            g.setColor(Color.green);
        } else if (this.color == 3) {
            g.setColor(Color.yellow);
        } else if (this.color == 4) {
            g.setColor(Color.orange);
        } else if (this.color == 5) {
            g.setColor(Color.magenta);
        } else if (this.color == 6) {
            g.setColor(Color.black);
        }
        if (this.y1 >= 95 && e.getY() >= 95) {
            if (this.choice == 0) {
                g.fillRect(Math.min(this.x1, this.x2), Math.min(this.y1, this.y2), Math.abs(this.x2 - this.x1),
                        Math.abs(this.y2 - this.y1));
            } else if (this.choice == 1) {
                g.fillOval(Math.min(this.x1, this.x2), Math.min(this.y1, this.y2), Math.abs(this.x1 - this.x2),
                        Math.abs(this.y1 - this.y2));
            } else if (this.choice == 2) {
                g.fillOval(this.x2, this.y2, 5, 5);
                Drawing draw = new Drawing(this.x2, this.y2, 5, 5, this.color, this.choice);
                this.drawings.add(draw);
            } else if (this.choice == 3) {
                if (this.drawings.size() > 0 && this.movedItemIndex != -1) {
                    if (this.drawings.get(this.movedItemIndex).color == 0) {
                        g.setColor(Color.blue);
                    } else if (this.drawings.get(this.movedItemIndex).color == 1) {
                        g.setColor(Color.red);
                    } else if (this.drawings.get(this.movedItemIndex).color == 2) {
                        g.setColor(Color.green);
                    } else if (this.drawings.get(this.movedItemIndex).color == 3) {
                        g.setColor(Color.yellow);
                    } else if (this.drawings.get(this.movedItemIndex).color == 4) {
                        g.setColor(Color.orange);
                    } else if (this.drawings.get(this.movedItemIndex).color == 5) {
                        g.setColor(Color.magenta);
                    } else if (this.drawings.get(this.movedItemIndex).color == 6) {
                        g.setColor(Color.black);
                    }
                    if (this.x2 < this.movedW) {
                        this.x2 = this.movedW;
                    }
                    if (this.y2 - this.movedH < 95) {
                        this.y2 = this.movedH + 95;
                    }
                    if (this.x2 > this.windowWidth - (this.resultItem.width - this.movedW)) { // saga sigmaz
                        this.x2 = this.windowWidth - (this.resultItem.width - this.movedW);
                    }
                    if (this.y2 > this.windowHeight - (this.resultItem.height - this.movedH)) { // asagi sigmaz
                        this.y2 = this.windowHeight - (this.resultItem.height - this.movedH);
                    }
                    if (this.drawings.get(this.movedItemIndex).shape == 0) {
                        g.fillRect((this.x2 - this.movedW), (this.y2 - this.movedH), this.resultItem.width,
                                this.resultItem.height);
                    } else {
                        g.fillOval((this.x2 - this.movedW), (this.y2 - this.movedH), this.resultItem.width,
                                this.resultItem.height);
                    }
                    repaint();
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        this.x2 = e.getX();
        this.y2 = e.getY();
        if (this.y1 >= 95) {
            if (this.x2 > this.windowWidth) {
                this.x2 = this.windowWidth;
            }
            if (this.y2 > this.windowHeight) {
                this.y2 = this.windowHeight;
            }
            if (this.x2 < 0) {
                this.x2 = 0;
            }
            if (this.choice == 0) {
                Drawing draw;
                if (this.x1 <= this.x2) {
                    if (this.y1 <= this.y2) {
                        draw = new Drawing(this.x1, this.y1, Math.abs(this.x1 - this.x2), Math.abs(this.y1 - this.y2),
                                this.color, this.choice);
                    } else {
                        if (this.y2 < 95) {
                            this.y2 = 95;
                        }
                        draw = new Drawing(this.x1, this.y2, Math.abs(this.x1 - this.x2), Math.abs(this.y1 - this.y2),
                                this.color, this.choice);
                    }
                } else {
                    if (this.y1 <= this.y2) {
                        draw = new Drawing(this.x2, this.y1, Math.abs(this.x1 - this.x2), Math.abs(this.y1 - this.y2),
                                this.color, this.choice);
                    } else {
                        if (this.y2 < 95) {
                            this.y2 = 95;
                        }
                        draw = new Drawing(this.x2, this.y2, Math.abs(this.x1 - this.x2), Math.abs(this.y1 - this.y2),
                                this.color, this.choice);
                    }
                }
                this.drawings.add(draw);
            } else if (this.choice == 1) {
                Drawing draw;
                if (this.x1 <= this.x2) {
                    if (this.y1 <= this.y2) {
                        draw = new Drawing(this.x1, this.y1, Math.abs(this.x1 - this.x2), Math.abs(this.y1 - this.y2),
                                this.color, this.choice);
                    } else {
                        if (this.y2 < 95) {
                            this.y2 = 95;
                        }
                        draw = new Drawing(this.x1, this.y2, Math.abs(this.x1 - this.x2), Math.abs(this.y1 - this.y2),
                                this.color, this.choice);
                    }
                } else {
                    if (this.y1 <= this.y2) {
                        draw = new Drawing(this.x2, this.y1, Math.abs(this.x1 - this.x2), Math.abs(this.y1 - this.y2),
                                this.color, this.choice);
                    } else {
                        if (this.y2 < 95) {
                            this.y2 = 95;
                        }
                        draw = new Drawing(this.x2, this.y2, Math.abs(this.x1 - this.x2), Math.abs(this.y1 - this.y2),
                                this.color, this.choice);
                    }
                }
                this.drawings.add(draw);
            } else if (this.choice == 2) {
                Drawing draw = new Drawing(this.x2, 5, this.y2, 5, this.color, 2);
                this.drawings.add(draw);
            } else if (this.choice == 3) {
                if (this.y2 - this.movedH < 95) {
                    this.y2 = this.movedH + 95;
                }
                if (this.drawings.size() > 0 && this.movedItemIndex != -1) {
                    if (this.x2 < this.movedW) {
                        this.x2 = this.movedW;
                    }
                    if (this.x2 > this.windowWidth - (this.resultItem.width - this.movedW)) { // saga sigmaz
                        this.x2 = this.windowWidth - (this.resultItem.width - this.movedW);
                    }
                    if (this.y2 > this.windowHeight - (this.resultItem.height - this.movedH)) { // asagi sigmaz
                        this.y2 = this.windowHeight - (this.resultItem.height - this.movedH);
                    }
                    this.resultItem.x = this.x2 - this.movedW;
                    this.resultItem.y = this.y2 - this.movedH;
                    this.drawings.remove(this.movedItemIndex);
                    this.drawings.add(this.resultItem);
                    this.movedItemIndex = -1;
                    this.resultItem = new Drawing();
                }
            }
        }
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        this.windowWidth = this.getBounds().width;
        this.windowHeight = this.getBounds().height;
        for (Drawing draw : drawings) {
            if (draw.color == 0) {
                g.setColor(Color.blue);
            } else if (draw.color == 1) {
                g.setColor(Color.red);
            } else if (draw.color == 2) {
                g.setColor(Color.green);
            } else if (draw.color == 3) {
                g.setColor(Color.yellow);
            } else if (draw.color == 4) {
                g.setColor(Color.orange);
            } else if (draw.color == 5) {
                g.setColor(Color.magenta);
            } else if (draw.color == 6) {
                g.setColor(Color.black);
            }
            if (draw.shape == 0) {
                g.fillRect(draw.x, draw.y, draw.width, draw.height);
            } else
                g.fillOval(draw.x, draw.y, draw.width, draw.height);
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public static void main(String[] args) {
        PaintBrush paintBrush = new PaintBrush();
    }
}