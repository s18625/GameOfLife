import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame implements ActionListener {

    ArrayList<JButton> buttonList = new ArrayList();
    JButton next, clear;

    int dim ;

    public GUI(int dim) {
        this.dim = dim;
        setTitle("Game Of Life");
        setIconImage(Toolkit.getDefaultToolkit().getImage("Gol.png"));


        setLayout(null);
        setResizable(true);
        setBounds(200, 100, 1200, 800);
        int width = this.getWidth();
        int heigh = this.getHeight();




        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(dim, dim));
        panel.setBounds(0, 0, width * 2 / 3, heigh);

        for (int i = 0; i < dim * dim; i++) {

            JButton button = new JButton();
            button.setBackground(Color.GRAY);
            button.setSize(new Dimension(90, 90));
            button.addActionListener(this);
            panel.add(button);
            buttonList.add(button);

        }

        JPanel nav = new JPanel();

        nav.setLayout(null);
        nav.setBackground(Color.BLACK);
        nav.setBounds(panel.getWidth(), 0, width * 1 / 3, heigh);
        //nav.setLayout(new GridLayout(2, 1));
        next = new JButton("next step");
        next.setBounds(100,100,150,100);
        clear = new JButton("Clear");
        clear.setBounds(next.getX(),next.getY()+next.getHeight()+40,next.getWidth(),next.getHeight());
        next.addActionListener(this);
        clear.addActionListener(this);
        //next.setSize(new Dimension(100,100));
        //next.setBounds(0,010,10,10);

        nav.add(next);
        nav.add(clear);

        //setLayout(new GridLayout(1, 2));

        add(panel);
        add(nav);


        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (buttonList.contains(src)) {

            int index = buttonList.indexOf(src);
            JButton b = buttonList.get(index);
            int i = countNei(buttonList, b);
            System.out.println(i);

            if (b.getBackground() == Color.BLUE) {
                b.setBackground(Color.GRAY);
            } else {
                b.setBackground(Color.BLUE);
            }

        } else if (src.equals(next)) {



            List<Integer> integers = new ArrayList<>();
            for (JButton but : buttonList
            ) {


                int ne = countNei(buttonList, but);
                integers.add(ne);

            }
            for (int i = 0; i < integers.size(); i++)

             {
                 int nei = integers.get(i);
                JButton jb = buttonList.get(i);
                if (nei == 3) {
                    jb.setBackground(Color.BLUE);
                } else if (nei == 2) {

                } else {
                    jb.setBackground(Color.GRAY);
                }

            }
            //buttonList = copy;
        } else if (src.equals(clear)) {
            for (JButton butt : buttonList
            ) {
                butt.setBackground(Color.GRAY);

            }
        }


    }

    public int countNei(ArrayList<JButton> lis, JButton button) {

        int nei = 0;
        int index = lis.indexOf(button);
//

        //saasiad ppo prawej
        if (index % dim != dim - 1) {

            if (lis.get(index + 1).getBackground() == Color.BLUE) {
                nei++;
            }
        }
        //sonsiad po lewej
        if (index % dim != 0) {

            if (lis.get(index - 1).getBackground() == Color.BLUE) {
                nei++;
            }
        }
        //sonsiad z gory
        if (index >= dim) {

            if (lis.get(index - dim).getBackground() == Color.BLUE) {
                nei++;
            }
        }
        //sonwiad z dolu
        if (index < dim * dim - dim) {

            if (lis.get(index + dim).getBackground() == Color.BLUE) {
                nei++;
            }
        }

        //prawy dolny skos
        if (index % dim != dim - 1 && index < dim * dim - dim) {

            if (lis.get(index + dim + 1).getBackground() == Color.BLUE) {
                nei++;
            }
        }
        //lewy dolny skos
        if (index % dim != 0 && index < dim * dim - dim) {

            if (lis.get(index + dim - 1).getBackground() == Color.BLUE) {
                nei++;
            }
        }
        //lewy gorny skos
        if (index % dim != 0 && index >= dim) {

            if (lis.get(index - dim - 1).getBackground() == Color.BLUE) {
                nei++;
            }
        }
        //prawy gorny skos
        if (index % dim != dim - 1 && index >= dim) {

            if (lis.get(index - dim + 1).getBackground() == Color.BLUE) {
                nei++;
            }
        }


        return nei;
    }
}
