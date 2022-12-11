package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MyGame extends JFrame implements ActionListener {
    JLabel heading;
    JLabel clocklable;
    Font font=new Font("",Font.PLAIN,40);
    JPanel mainpanel;
    JButton jbutton[]=new JButton[9];
    int gameChances[]={2,2,2,2,2,2,2,2,2};
    int activeplayer=0,winner=0;
    boolean gameover=false;
    int wps[][]={{0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}

    };


    MyGame(){
        setSize(850,850);
        setTitle("My Tic Tac Toe Game.......");
        setVisible(true);
        ImageIcon icon=new ImageIcon("C:\\Users\\lenovo\\IdeaProjects\\GameDevelopment\\src\\com\\company\\img\\download (1).png");
        setIconImage(icon.getImage());
        createGUI();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void createGUI(){
      this.getContentPane().setBackground(Color.decode("#2196f3"));
      this.setLayout(new BorderLayout());
      heading=new JLabel("Tic Tak Toe");
      heading.setFont(font);
      heading.setHorizontalAlignment(SwingConstants.CENTER);
      heading.setForeground(Color.WHITE);
      heading.setIcon(new ImageIcon("src/com/company/img/download (1).png"));
      heading.setHorizontalTextPosition(SwingConstants.CENTER);
      heading.setVerticalTextPosition(SwingConstants.BOTTOM);
      this.add(heading,BorderLayout.NORTH);
      clocklable=new JLabel("Clock");
      clocklable.setFont(font);
      clocklable.setHorizontalAlignment(SwingConstants.CENTER);
      this.add(clocklable,BorderLayout.SOUTH);

      Thread t=new Thread(){
          public void run(){
              try{
                  while (true){
                      String dateTime=new Date().toString();
                      clocklable.setText(dateTime);
                      Thread.sleep(1000);
                  }
              }
              catch (Exception e){
                  e.printStackTrace();
              }

          }
      };
        t.start();
        mainpanel=new JPanel();
        mainpanel.setLayout(new GridLayout(3,3));
        for (int i = 1; i <10 ; i++) {
            JButton jb=new JButton();
         //   jb.setIcon(new ImageIcon("src/com/company/img/tic-tac-toe-411222.jpg"));
            jb.setFont(font);
            jb.setBackground(Color.decode("#90caf9"));
            jb.addActionListener(this);
            jb.setName(String.valueOf(i-1));
            mainpanel.add(jb);
            jbutton[i-1]=jb;

        }
        this.add(mainpanel,BorderLayout.CENTER);






    }

    @Override
    public void actionPerformed(ActionEvent e) {

       JButton currentbutton=(JButton)e.getSource();
       String str=currentbutton.getName();
       int name=Integer.parseInt(str.trim());
       if(gameover){
           JOptionPane.showMessageDialog(this,"Game is already over.....");
           return;
       }
        System.out.println(str);
        if(gameChances[name]==2){
            if(activeplayer==1){
                gameChances[name]=activeplayer;
                currentbutton.setIcon(new ImageIcon("src/com/company/img/download (2).png"));
                activeplayer=0;

            }
            else {
                gameChances[name]=activeplayer;
                currentbutton.setIcon(new ImageIcon("src/com/company/img/tic-tac-toe-411222.jpg"));
                activeplayer=1;
            }

            for(int temp[]:wps){
                if((gameChances[temp[0]]==gameChances[temp[1]])&&(gameChances[temp[1]]==gameChances[temp[2]]) && (gameChances[temp[2]]!=2 )){
                    winner=gameChances[2];
                    JOptionPane.showMessageDialog(null,"player "+winner+" has won the game.....");
                    int i=JOptionPane.showConfirmDialog(this,"Do you want to play more ??");
                    gameover=true;

                    if(i==0){
                         this.setVisible(true);
                         new MyGame();
                    }
                    else if(i==1){
                        System.exit(2333);

                    }
                    else{


                    }
                    break;
                }
                

            }
            int c=0;
            for(int x:gameChances){
                if(x==2){
                    c++;
                }
                
            }
            if(c==0 && gameover==false){
                JOptionPane.showMessageDialog(this,"It's Draw........");
                int i=JOptionPane.showConfirmDialog(this,"Play more ??");
                if(i==0){
                    this.setVisible(true);
                    new MyGame();

                }
                else if(i==1){
                    System.exit(23456);
                }
                else {

                }
                gameover=true;

            }
        }
        else{
            JOptionPane.showMessageDialog(this,"Position already occupied");
        }
    }
}
