 package eoc;

 import com.github.sarxos.webcam.Webcam;
 import com.github.sarxos.webcam.WebcamPanel;
 import com.github.sarxos.webcam.WebcamResolution;
 import gnu.io.NoSuchPortException;
 import java.awt.BorderLayout;
 import java.awt.Color;
 import java.awt.FlowLayout;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.GridLayout;
 import java.awt.HeadlessException;
 import java.awt.Image;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.KeyAdapter;
 import java.awt.event.KeyEvent;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import java.awt.event.WindowAdapter;
 import java.awt.event.WindowEvent;
 import java.awt.image.BufferedImage;
 import java.awt.image.RenderedImage;
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
import java.net.URI;
 import java.net.URISyntaxException;
 import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
 import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
 import javax.swing.ImageIcon;
 import javax.swing.JButton;
 import javax.swing.JComboBox;
 import javax.swing.JFileChooser;
 import javax.swing.JFrame;
 import javax.swing.JLabel;
 import javax.swing.JOptionPane;
 import static javax.swing.JOptionPane.ERROR_MESSAGE;
 import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
 import static javax.swing.JOptionPane.QUESTION_MESSAGE;
 import static javax.swing.JOptionPane.YES_NO_OPTION;
 import javax.swing.JPanel;
 import javax.swing.JPasswordField;
 import javax.swing.JProgressBar;
 import javax.swing.JTextField;
 import javax.swing.SwingUtilities;
 import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
 import org.sintef.jarduino.DigitalPin;
 import org.sintef.jarduino.DigitalState;
 import org.sintef.jarduino.JArduino;
 import org.sintef.jarduino.PinMode;

/**
 * @author Aftab Khalil
 */

 public class EoC extends JFrame
{
     //EVER USE AND SPECIAL FIELDS
     static int i;
     static JArduino arduino;
     ElectroplateWindow ee;
     Picture windowBkPic ;
     DecimalFormat df = new DecimalFormat("#.###"); 
     SoundPlayer epBkSPlayer;
     Webcam myCamera = null ;
     
     //FIRST FRAME GUI VARIABLES
     Picture firstPicture;
     JButton signInButton;
     JButton signUpButton;
     JPanel firstPanel;
     JPanel signButtonPanel;     
     JPanel firstLastPanel;
     
    //SIGNUP FRAME GUI VARIABLES     
   
     Picture sUpBkPic;
     Picture takePicSignUp;
     Picture uploadedPicture;
     Picture newPicture;
     
     BufferedImage newImage;
     Image picToSave;
     boolean picAdded = false;
     
     ImagePanel bkPanel;     
     JPanel lowerBtnPanel;
     
     JButton signUpOk;
     JButton backScreenOne;
     
     JLabel name ;
     JLabel password;
     JLabel cPassword;
     JLabel dateOfBirth;
     JLabel gender;
     JLabel takePic;
     JLabel upload;
     
     JTextField  nField;
     JPasswordField  pField;
     JPasswordField cpField;
     JTextField  dField;
     JTextField  gField;
     
     String sName;
     String sPassword = "";
     String sCPassword = "";
     char[] chPassword;
     char[] chCPassword;
     String sDateOfBirth;
     String sGender;
     
     JComboBox  gMenu;
     String[] gMenuStrings = new String[]{"MALE","FEMALE"};
     
     JPanel signUpPanel;
     
     //SIGN IN FRAME GUI VARIABLES
     
     Picture SignInBkPic;
     Picture UserPic;
     
     ImagePanel sBkPanel;
    
     JButton signInOk;
     
     JLabel nameLabel;
     JTextField signInNameField;
     
     JLabel passwordLabel;
     JPasswordField passwordField;
     
     JPanel signInPanel;
     String sInName = null;
     String sInPassword;
     
     //MAIN PAGE VARIABLES
     Picture mainBkPicture;
     ImagePanel mainBkPanel;
     
     JButton startWork;
     JButton signOut;
     JButton Gallery;
     
     JLabel totalAmount;
     JLabel amountToDeposit;
     JLabel totalTime;
     JLabel totalTimeDisplay;
     JLabel gm1;
     JLabel gm2;
     JLabel uName;
     JTextField fTotalAmount;
     JTextField fAmountToDeposit;
     
     double totalamount ;
     double time;
     double depositamount;
     boolean valid = false;
     boolean tamnt;
     Picture userPic;
     
     JPanel mainPanel;
     
     
     //CONSTRUCTOR OF PROJECT
     public EoC()
    {      
         try
        {    arduino = new Arduino("COM7");
             firstPicture   = new Picture(900,605,"Start.jpg"); 
             mainBkPicture = new Picture(900,605,"mainBkGrnd.jpg");
             
             windowBkPic = new Picture(400,220,"electroplateBkGrnd.jpg");
             SignInBkPic = new Picture(900,605,"SignInBkGrnd.jpg");
             sUpBkPic = new Picture(900,605,"SignUpBkGrnd.jpg");
             takePicSignUp  = new Picture(120,100,"takePic.jpg");
             epBkSPlayer = new SoundPlayer("bkAudio.wav");
        }
         catch(IOException | URISyntaxException e)
        {
             JOptionPane.showMessageDialog(null, "ALL THE THINGS ARE NOT RIGHT");
             System.exit(0);
        } catch (NoSuchPortException ex) {
             Logger.getLogger(EoC.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         signInButton   = new JButton("Sign In");
         signUpButton   = new JButton("Sign Up");
         signInButton.setBackground(new Color(124,28,5));
         signUpButton.setBackground(new Color(124,28,5));
         signInButton.setForeground(Color.white);
         signUpButton.setForeground(Color.white);
         
         signButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
         signButtonPanel.setBackground(new Color(126, 58, 21));
         
         signButtonPanel.add(signInButton);
         signButtonPanel.add(signUpButton);
         
         firstPanel     = new JPanel(new GridLayout( 2, 1, 10, 10) );
         firstPanel.setBackground(new Color(126, 58, 21));
         firstPanel.add(firstPicture.thisImage);
         firstPanel.add(signButtonPanel);
         
         firstLastPanel = new JPanel(new FlowLayout());
         firstLastPanel.add(firstPanel);
         
         this.add(firstLastPanel); 
         
         signUpButton.addActionListener(new ActionListener()
                                            {
                                                 @Override
                                                 public void actionPerformed(ActionEvent e)
                                                {
                                                    SignUp();                           
                                                }
                                            }
                                        );
         
         signInButton.addActionListener(new ActionListener()
                                            {
                                                 @Override
                                                 public void actionPerformed(ActionEvent e)
                                                {
                                                     System.out.println("22");
                                                     SignIn();
                                                }
                                            }
                                        );
    }
   
     public static void main(String[] args)
    {
         EoC project =  new EoC();
         project.setSize(900,700);
         project.setDefaultCloseOperation(EXIT_ON_CLOSE);
         project.setResizable(false);
         project.setVisible(true);
  
         boolean assend = true;
         
         while(true) 
        {
             if(assend == true)
            {
                 i++;
                 if(i==200)
                {
                     assend = false;
                }
            }
             if(assend == false)
            {
                 i--;
                 if(i==50)
                {
                     assend = true;
                }
            }
            
             try 
            {
                 project.signButtonPanel.setBackground(new Color(i,0,0));                       
                 project.revalidate();
                 project.repaint();
                 Thread.sleep(20);
            } 
             catch (InterruptedException ie) 
            {
                 System.out.println(ie);
            }
        }
    }
     
     public void MainPage(JPanel previousPanel,String uName,String uDateOfBirth, String uGender)
    {
         remove(previousPanel);
       
         mainBkPanel = new ImagePanel(mainBkPicture.image);
         mainBkPanel.setBackground(new Color(126,58,21));
         
         try {
             userPic = new Picture(130,100,new File("E:\\DataOfP\\"+uName+".jpg"));
         } catch (IOException ex) {
             System.out.println(ex);
         }
         userPic.thisImage.setSize(130,100);
         userPic.thisImage.setLocation(750, 50);
         
         this.uName = new JLabel(uName);
         this.uName.setFont(new Font("Bold", Font.PLAIN, 15));
         this.uName.setForeground(Color.WHITE);
         this.uName.setSize(150, 20);
         this.uName.setLocation(750,155);
         
         totalAmount = new JLabel("TOTAL AMOUNT OF COPPER :");
         totalAmount.setFont(new Font("Bold", Font.PLAIN, 15));
         totalAmount.setForeground(Color.WHITE);
         totalAmount.setSize(220, 20);
         totalAmount.setLocation(80,500);
                 
         fTotalAmount= new JTextField();
         fTotalAmount.setFont(new Font("Bold", Font.PLAIN, 15));
         fTotalAmount.setForeground(Color.BLACK);
         fTotalAmount.setSize(200, 20);
         fTotalAmount.setLocation(300, 500);
         
         fTotalAmount.addKeyListener(new KeyAdapter()
                                        {
                                             @Override
                                             public void keyReleased(KeyEvent e)
                                            {
                                                String input = fTotalAmount.getText();
                                                 try
                                                {   
                                                     totalamount = Double.valueOf(input);
                                                     System.out.println(totalamount);
                                                     tamnt = true;
                                                }
                                                 catch(Exception ee)
                                                {
                                                    tamnt = false;
                                                }
                                                 if(tamnt==false)
                                                {
                                                     if(input.compareTo("")==0)
                                                    {
                                                         totalTimeDisplay.setText("00 sec");
                                                    }
                                                     else
                                                    {
                                                         totalTimeDisplay.setText("INVALID");
                                                    }
                                                }
                                                 else
                                                {
                                                     totalTimeDisplay.setText("00 sec");
                                                }
                                                     fAmountToDeposit.setText("");
                                                     
                                                     valid = false;
                                                     repaint();
                                                     revalidate();
                                            }
                                        }
                                    );
         
         amountToDeposit = new JLabel("AMOUNT TO DEPOSIT :");
         amountToDeposit.setFont(new Font("Bold", Font.PLAIN, 15));
         amountToDeposit.setForeground(Color.WHITE);
         amountToDeposit.setSize(220, 20);
         amountToDeposit.setLocation(80,525);
                 
         fAmountToDeposit= new JTextField();
         fAmountToDeposit.setFont(new Font("Bold", Font.PLAIN, 15));
         fAmountToDeposit.setForeground(Color.BLACK);
         fAmountToDeposit.setSize(200, 20);
         fAmountToDeposit.setLocation(300, 525);
         
         fAmountToDeposit.addKeyListener(new KeyAdapter()
                                            {
                                                 @Override
                                                 public void keyReleased(KeyEvent e)
                                                {
                                                     System.out.println(totalamount);
                                                     String value = fAmountToDeposit.getText();
                                                     if(value.compareTo("")==0)
                                                    {
                                                         valid = false;
                                                         totalTimeDisplay.setText(String.valueOf("00 sec."));
                                                         revalidate();
                                                         repaint();
                                                    }
                                                     else
                                                    {
                                                         try
                                                        {    depositamount = Double.valueOf(fAmountToDeposit.getText());
                                                             time = (double) (depositamount/(0.0003281 * 8 * 60));
                                                             System.out.println(time);
                                                         
                                                             if(totalamount-depositamount>=0)
                                                            {
                                                                 System.out.println(totalamount);
                                                                 System.out.println(Double.valueOf(fAmountToDeposit.getText()));
                                                                 totalTimeDisplay.setText(df.format(time)+" min.");
                                                                 valid = true;
                                                                 revalidate();
                                                                 repaint();
                                                            }
                                                             else
                                                            {
                                                                 valid = false;
                                                                 totalTimeDisplay.setText(String.valueOf("IMPOSSIBLE"));
                                                                 revalidate();
                                                                 repaint();
                                                            }
                                                        }
                                                         catch(NumberFormatException ex)
                                                        {
                                                             valid = false;
                                                             totalTimeDisplay.setText(String.valueOf("Invalid Input"));
                                                             revalidate();
                                                             repaint();
                                                        }
                                                    }
                                                }
                                            }
                                        );
        
         totalTime = new JLabel("TIME REQUIRED :");
         totalTime.setFont(new Font("Bold", Font.PLAIN, 15));
         totalTime.setForeground(Color.WHITE);
         totalTime.setSize(185, 20);
         totalTime.setLocation(80,550);
     
         totalTimeDisplay = new JLabel("00 sec");
         totalTimeDisplay.setFont(new Font("Bold", Font.PLAIN, 15));
         totalTimeDisplay.setForeground(Color.WHITE);
         totalTimeDisplay.setSize(185, 20);
         totalTimeDisplay.setLocation(300,550);
         
         gm1 = new JLabel(" gm");
         gm1.setFont(new Font("Bold", Font.PLAIN, 15));
         gm1.setForeground(Color.WHITE);
         gm1.setSize(50, 20);
         gm1.setLocation(505,500);
         
         gm2 = new JLabel(" gm");
         gm2.setFont(new Font("Bold", Font.PLAIN, 15));
         gm2.setForeground(Color.WHITE);
         gm2.setSize(50, 20);
         gm2.setLocation(505,525);
         
         mainBkPanel.setLayout(null);
         mainBkPanel.add(userPic.thisImage);
         mainBkPanel.add(this.uName);
         mainBkPanel.add(totalAmount);
         mainBkPanel.add(fTotalAmount);
         mainBkPanel.add(amountToDeposit);
         mainBkPanel.add(gm1);
         mainBkPanel.add(gm2);
         
         mainBkPanel.add(fAmountToDeposit);
         mainBkPanel.add(totalTime);
         mainBkPanel.add(totalTimeDisplay);
         
         lowerBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
         lowerBtnPanel.setBackground(new Color(i,0,0));
         
         startWork = new JButton("START DEPOSITING");
         signOut = new JButton("SIGN OUT");
         startWork.setBackground(new Color(124,28,5));
         signOut.setBackground(new Color(124,28,5));
         startWork.setForeground(Color.white);
         signOut.setForeground(Color.white);

         signOut.addActionListener(new ActionListener()
                                        {
                                             @Override
                                             public void actionPerformed(ActionEvent e)
                                            {
                                                 remove(mainPanel);
                                                 add(firstLastPanel);
                                                 revalidate();
                                                 repaint();
                                            }
                                        }
                                    );
         
         startWork.addActionListener(new ActionListener()
                                        {
                                             @Override
                                             public void actionPerformed(ActionEvent e)
                                            {
                                                 if(valid == true && tamnt == true)
                                                {
                                                     setEnabled(false);
                                                     ee = new ElectroplateWindow(depositamount,time);
                                                }
                                                 else
                                                {
                                                     JOptionPane.showMessageDialog(null, "PLEASE CHECK YOUR INPUTS","ERROR!",ERROR_MESSAGE);
                                                }
                                            }
                                        }
                                    );
         
         lowerBtnPanel.add(startWork);
         lowerBtnPanel.add(signOut);

         mainPanel = new JPanel(new BorderLayout(10,10));
         mainPanel.setBackground(new Color(126, 58, 21));
         mainPanel.add(mainBkPanel);
         mainPanel.add(lowerBtnPanel,BorderLayout.SOUTH);

         add(mainPanel);
         setBackground(new Color(126,58,21));
         revalidate();
         repaint();
    }
     
     public void setHereEnabled()
    {
         this.setEnabled(true);
    }
     
     public  class ElectroplateWindow extends JFrame
    {    
         int j = 0;
         Thread t ;
         double amountdeposited = 0;
         double totalamount;
         double time;
         boolean pauseS = false;
         boolean stopS = false;
        
         JLabel lAmountDeposited;
         JLabel amountDeposited ;
         JLabel lAmountRemaning ;
         JLabel amountRemaning ;
         JLabel lTimeRemaning;
         JLabel timeRemaning ;
         JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
         ImagePanel bkPanel;
         JPanel lastPanel = new JPanel(new BorderLayout(10,10));
         JButton pause = new JButton("PAUSE");
         JButton stop = new JButton("STOP");
         JButton start = new JButton("START");
         JProgressBar bar ;
         
         public ElectroplateWindow(double TA, double T)
        {
             this.time = Double.valueOf(df.format(T));
             this.totalamount =Double.valueOf(df.format(TA));
       
             this.setTitle("ELECTROPLATING "+this.totalamount+" gm");
             this.setSize(400, 250);
             this.setResizable(false);
             this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
         
             lAmountDeposited = new JLabel("AMOUNT DEPOSITED");
             lAmountDeposited.setFont(new Font("Bold", Font.PLAIN, 15));
             lAmountDeposited.setForeground(Color.BLACK);
             lAmountDeposited.setSize(200, 20);
             lAmountDeposited.setLocation(20,20);
         
             amountDeposited = new JLabel("00 gm");
             amountDeposited.setFont(new Font("Bold", Font.PLAIN, 15));
             amountDeposited.setForeground(Color.BLACK);
             amountDeposited.setSize(200, 20);
             amountDeposited.setLocation(220,20);
         
             lAmountRemaning = new JLabel("AMOUNT REMANING");
             lAmountRemaning.setFont(new Font("Bold", Font.PLAIN, 15));
             lAmountRemaning.setForeground(Color.BLACK);
             lAmountRemaning.setSize(200, 20);
             lAmountRemaning.setLocation(20,40);

             amountRemaning = new JLabel(totalamount+" gm");
             amountRemaning.setFont(new Font("Bold", Font.PLAIN, 15));
             amountRemaning.setForeground(Color.BLACK);
             amountRemaning.setSize(200, 20);
             amountRemaning.setLocation(220,40);
                  
             lTimeRemaning = new JLabel("TIME REMANING");
             lTimeRemaning.setFont(new Font("Bold", Font.PLAIN, 15));
             lTimeRemaning.setForeground(Color.BLACK);
             lTimeRemaning.setSize(200, 20);
             lTimeRemaning.setLocation(20,60);
         
             timeRemaning = new JLabel(String.valueOf(this.time)+" sec");
             timeRemaning.setFont(new Font("Bold", Font.PLAIN, 15));
             timeRemaning.setForeground(Color.BLACK);
             timeRemaning.setSize(200, 20);
             timeRemaning.setLocation(220,60); 
           
             bar = new JProgressBar(0,(int) (time*60));
             bar.setBackground(Color.WHITE);
             bar.setForeground(new Color(126,58,21));
             bar.setSize(200, 20);
             bar.setLocation(100,140);
         
             bkPanel= new ImagePanel(windowBkPic.image);
             bkPanel.setBackground(new Color(126,58,21));
             bkPanel.setLayout(null);
             bkPanel.add(lAmountDeposited);
             bkPanel.add(amountDeposited);
             bkPanel.add(lAmountRemaning);
             bkPanel.add(amountRemaning);
             bkPanel.add(lTimeRemaning);
             bkPanel.add(timeRemaning);
             bkPanel.add(bar);
         
             buttonPanel.setBackground(new Color(100,0,0));
             
             pause.setBackground(new Color(124,28,5));
             pause.setForeground(Color.WHITE);
        
             stop.setBackground(new Color(124,28,5));
             stop.setForeground(Color.WHITE);
         
             start.setBackground(new Color(124,28,5));
             start.setForeground(Color.WHITE);
         
             buttonPanel.add(stop);
             buttonPanel.add(pause);
         
             pause.addActionListener(new ActionListener()
                                        {
                                             @Override
                                             public void actionPerformed(ActionEvent e)
                                           {
                                                 pauseS = false;
                                                 
                                                 arduino.digitalWrite(DigitalPin.PIN_13, DigitalState.LOW);
                                                 arduino.stopArduinoProcess();
                                                    
                                                 t.suspend();
                                                
                                                 buttonPanel.remove(pause);
                                                 buttonPanel.add(start);
                                                 
                                                 revalidate();
                                                 repaint();
                                           }
                                        }
                                    );
         
             start.addActionListener(new ActionListener()
                                        {
                                             @Override
                                             public void actionPerformed(ActionEvent e)
                                            {
                                                 pauseS = true;
                                             
                                                 arduino.digitalWrite(DigitalPin.PIN_13, DigitalState.HIGH);
                                                 arduino.runArduinoProcess();
                                                     
                                                 t.resume();
                                                
                                                 buttonPanel.remove(start);
                                                 buttonPanel.add(pause);
                                                  
                                                 revalidate();
                                                 repaint();
                                            }
                                        }
                                    );
             
             stop.addActionListener(new ActionListener()
                                        {
                                             @Override
                                             public void actionPerformed(ActionEvent e)
                                            {
                                                 arduino.digitalWrite(DigitalPin.PIN_13, DigitalState.LOW);
                                                 arduino.stopArduinoProcess();
                                               
                                                 t.suspend();
                                              
                                                 int a = JOptionPane.showConfirmDialog(null, "ARE YOU SURE TO STOP AND EXIT?","EXIT ?",YES_NO_OPTION,QUESTION_MESSAGE);
                                               
                                                 if(a==0)
                                                {
                                                     epBkSPlayer.stopSound();
                                                     setVisible(false);
                                                     setHereEnabled();
                                                }
                                                 else
                                                {
                                                     if(pauseS==false)
                                                    {
                                                         buttonPanel.remove(start);
                                                         buttonPanel.add(pause);
                                                         revalidate();
                                                         repaint();
                                                    }
                                                   
                                                     arduino.digitalWrite(DigitalPin.PIN_13, DigitalState.HIGH);
                                                     arduino.runArduinoProcess();
                                                   
                                                     t.resume();
                                                }
                                            }
                                        }
                                    );
         
             lastPanel.setBackground(new Color(126,58,21));
             lastPanel.add(bkPanel);
             lastPanel.add(buttonPanel,BorderLayout.SOUTH);
         
             this.add(lastPanel);
             this.addWindowListener(new WindowAdapter()
                                        {
                                             @Override
                                             public void windowClosing(WindowEvent e)
                                            {  
                                                 arduino.digitalWrite(DigitalPin.PIN_13, DigitalState.LOW);
                                                 arduino.stopArduinoProcess();  
                                            
                                                 t.suspend();
                
                                                 int a = JOptionPane.showConfirmDialog(null, "ARE YOU SURE TO EXIT ?","EXIT ?",YES_NO_OPTION,QUESTION_MESSAGE);
                                                 if (a==0)
                                                {   
                                                     epBkSPlayer.stopSound();
                                                     setVisible(false);
                                                     setHereEnabled();
                                                }
                                                 if(a==1)
                                                {
                                                     if(pauseS==false)
                                                    {
                                                         buttonPanel.remove(start);
                                                         buttonPanel.add(pause);
                                                         revalidate();
                                                         repaint();
                                                    }
                                            
                                                     t.resume();
                   
                                                     arduino.digitalWrite(DigitalPin.PIN_13, DigitalState.HIGH);
                                                     arduino.runArduinoProcess();
                                                }
                                            }
                                        }
                                    );
        
             j=0;
             epBkSPlayer.startSound();
             t = new Thread()
            {
                 @Override
                 public void run()
                {
                     for ( ; j <= ((time*60)); j++)
                    {
                        
                         SwingUtilities.invokeLater(new Runnable()
                                                         {
                                                             @Override
                                                             public void run()
                                                            {
                                                                 amountDeposited.setText(df.format(0.0003281*8*j)+" gm");
                                                                 amountRemaning.setText(df.format(totalamount-(0.0003281*8*j))+" gm");
                                                                 timeRemaning.setText(df.format(((time*60)-j)/60)+" min");
                                                                 bar.setValue(j);
                                                            }
                                                        }
                                                    );
                         try
                        {
                             Thread.sleep(1000);
                        }
                         catch(InterruptedException e)
                        {
                            System.out.println(e);
                        }
                    }
                
                     amountDeposited.setText(totalamount+" gm");
                     amountRemaning.setText("00 gm");
                     timeRemaning.setText("00 sec");
        
                     arduino.digitalWrite(DigitalPin.PIN_13, DigitalState.LOW);
                     arduino.stopArduinoProcess();
                     epBkSPlayer.stopSound();
                 
                     JOptionPane.showMessageDialog(null, "The process has been finished","Finished!",INFORMATION_MESSAGE);
                 
                     setVisible(false);
                     setHereEnabled();
                }
            };
           
             
             try
            {
                 arduino.digitalWrite(DigitalPin.PIN_13, DigitalState.HIGH);
                 arduino.runArduinoProcess();
                 this.setVisible(true);
                 t.start(); 
            }
             catch(Exception e)
            {
                 JOptionPane.showMessageDialog(null,"ERROR IN CIRCUIT","ERROR!",ERROR_MESSAGE);
                 System.exit(0);
            }  
        }    
    }
            
     public void SignIn()
    {
         remove(firstLastPanel); 
        
         sBkPanel = new ImagePanel(SignInBkPic.image);
         sBkPanel.setBackground(new Color(126,58,21));
         
         nameLabel = new JLabel("NAME :");
         nameLabel.setFont(new Font("Bold", Font.PLAIN, 15));
         nameLabel.setForeground(Color.BLACK);
         nameLabel.setSize(120, 20);
         nameLabel.setLocation(80,450);
         
         signInNameField = new JTextField();
         signInNameField.setFont(new Font("Bold", Font.PLAIN, 15));
         signInNameField.setForeground(Color.BLACK);
         signInNameField.setSize(200, 20);
         signInNameField.setLocation(200, 450);
         
         passwordLabel = new JLabel("PASSWORD : ");
         passwordLabel.setFont(new Font("Bold", Font.PLAIN, 15));
         passwordLabel.setForeground(Color.BLACK);
         passwordLabel.setSize(120, 20);
         passwordLabel.setLocation(80,475);
         
         passwordField = new JPasswordField();
         passwordField.setFont(new Font("Bold", Font.PLAIN, 15));
         passwordField.setForeground(Color.BLACK);
         passwordField.setEchoChar('*');
         passwordField.setSize(200, 20);
         passwordField.setLocation(200, 475);
         
         sBkPanel.setLayout(null);
         sBkPanel.add(nameLabel);
         sBkPanel.add(signInNameField);
         sBkPanel.add(passwordLabel);
         sBkPanel.add(passwordField);
         
         signInOk = new JButton("Sign In Ok");
         backScreenOne = new JButton("Back");
         signInOk.setBackground(new Color(124,28,5));
         backScreenOne.setBackground(new Color(124,28,5));
         signInOk.setForeground(Color.white);
         backScreenOne.setForeground(Color.white);
         
         backScreenOne.addActionListener(new ActionListener()
                                             {
                                                 @Override
                                                 public void actionPerformed(ActionEvent e)
                                                {
                                                     remove(signInPanel);
                                                     add(firstLastPanel);
                                                }
                                             }
                                        );
         
         signInOk.addActionListener(new ActionListener()
                                        {
                                             @Override
                                             public void actionPerformed(ActionEvent e)
                                            {
                                                 MyFile data;
                                                 String contain = null;
                                                
                                                 sInName = signInNameField.getText().trim();
                                                 sInPassword = String.valueOf(passwordField.getPassword());
                                                 System.out.println(sInName+" "+sInPassword);
                                                 if(sInName.compareTo("")==0)
                                                {
                                                     JOptionPane.showMessageDialog(null, "KINDLY WRITE YOUR USER NAME AND PASSWORD\nIT IS CASE SENSITIVE","ERROR!",ERROR_MESSAGE);
                                                }
                                                 else
                                                { 
                                                     try
                                                    {
                                                         data = new MyFile("E:\\DataOfP\\"+sInName+".txt");
                                                         contain  = data.fileReader();
                                                         
                                                         String[] parts  = contain.split("\\.");
                                                    
                                                         if(sInPassword.compareTo(parts[0].trim())==0)
                                                        {
                                                            System.out.println("Print " +contain);
                                                             System.out.println("can move further");
                                                             MainPage(signInPanel,sInName,parts[1].trim(),parts[2].trim());
                                                        }
                                                         else
                                                        {
                                                             JOptionPane.showMessageDialog(null, "YOUR PASSWORD DO NOT MATCH\nTRY AGAIN","ERROR!",ERROR_MESSAGE);
                                                        }
                                                    } 
                                                     catch (Exception ex)
                                                    {
                                                         JOptionPane.showMessageDialog(null, "YOU ARE NOT A USER YET\nCHECK TOUR PROVIDED INFORMATION IT IS CASE SENSITIVE\nOR SIGNUP TO ACCESS THE SOFTWARE","ERROR!",ERROR_MESSAGE); 
                                                    }                                     
                                                }
                                            }
                                        }
                                    );
         
         lowerBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
         lowerBtnPanel.setBackground(new Color(i,0,0));
         lowerBtnPanel.add(backScreenOne);
         lowerBtnPanel.add(signInOk);

         signInPanel = new JPanel(new BorderLayout(10,10));
         signInPanel.setBackground(new Color(126, 58, 21));
         signInPanel.add(sBkPanel);
         signInPanel.add(lowerBtnPanel,BorderLayout.SOUTH);

         add(signInPanel);
         setBackground(new Color(126,58,21));
         revalidate();
         repaint();
    }
     
     public void SignUp()
    {
         remove(firstLastPanel);
         sName = "";
         sPassword = "";
         sCPassword = "";
         chPassword = null;
         chCPassword = null;
         sDateOfBirth = "";
         sGender = "";
         
         bkPanel = new ImagePanel(sUpBkPic.image);
         bkPanel.setBackground(new Color(126,58,21));
         
         name = new JLabel("NAME : ");
         name.setFont(new Font("Bold", Font.PLAIN, 15));
         name.setForeground(Color.BLACK);
         name.setSize(120, 20);
         name.setLocation(80,450);
                 
         nField = new JTextField();
         nField.setFont(new Font("Bold", Font.PLAIN, 15));
         nField.setForeground(Color.BLACK);
         nField.setSize(200, 20);
         nField.setLocation(200, 450);
         
         password = new JLabel("PASSWORD : ");
         password.setFont(new Font("Bold", Font.PLAIN, 15));
         password.setForeground(Color.BLACK);
         password.setSize(120, 20);
         password.setLocation(80,475);
         
         pField = new JPasswordField();
         pField.setFont(new Font("Bold", Font.PLAIN, 15));
         pField.setForeground(Color.BLACK);
         pField.setEchoChar('*');
         pField.setSize(200, 20);
         pField.setLocation(200, 475);
         
         cPassword = new JLabel("C. PASSWORD : ");
         cPassword.setFont(new Font("Bold", Font.PLAIN, 15));
         cPassword.setForeground(Color.BLACK);
         cPassword.setSize(190, 20);
         cPassword.setLocation(80,500);
    
         cpField = new JPasswordField();
         cpField.setFont(new Font("Bold", Font.PLAIN, 15));
         cpField.setForeground(Color.BLACK);
         cpField.setEchoChar('*');
         cpField.setSize(200, 20);
         cpField.setLocation(200, 500);
         
         dateOfBirth = new JLabel("DATE OF BIRTH : ");
         dateOfBirth.setFont(new Font("Bold", Font.PLAIN, 15));
         dateOfBirth.setForeground(Color.BLACK);
         dateOfBirth.setSize(190, 20);
         dateOfBirth. setLocation(80,525);
         
         dField = new JTextField();
         dField.setFont(new Font("Bold", Font.PLAIN, 15));
         dField.setForeground(Color.BLACK);
         dField.setSize(200, 20);
         dField.setLocation(200, 525);
         
         gender = new JLabel("GENDER");
         gender.setFont(new Font("Bold", Font.PLAIN, 15));
         gender.setForeground(Color.BLACK);
         gender.setSize(120, 20);
         gender.setLocation(80,550);
               
         gMenu  = new JComboBox(gMenuStrings);
         gMenu.setForeground(Color.BLACK);
         gMenu.setSize(200, 20);
         gMenu.setLocation(200, 550);
         
         takePicSignUp.thisImage.setSize(120, 100);
         takePicSignUp.thisImage.setLocation(430, 450);
         
         takePic = new JLabel("TAKE");
         takePic.setFont(new Font("Bold", Font.PLAIN, 15));
         takePic.setForeground(Color.BLACK);
         takePic.setSize(50, 20);
         takePic.setLocation(430,555);
         
         takePic.addMouseListener(new MouseAdapter()
                                    {
                                         @Override
                                         public void mouseClicked(MouseEvent e)
                                        {
                                             takePic.setForeground(Color.red);
                                             revalidate();
                                             repaint();
                                             setEnabled(false);
     
                                             try
                                            {
                                                 TakePicture();
                                            }
                                             catch(Exception ex)
                                            {
                                                 JOptionPane.showMessageDialog(null,"FAILED TO LOAD CAMERA!\nCHECK THE CONNECTION.","ERROR",ERROR_MESSAGE);
                                                 takePic.setForeground(Color.black);
                                                 revalidate();
                                                 repaint();
                                                 setEnabled(true);
                                            }
                                        }
                                    }
                                );
           
         upload = new JLabel("UPLOAD");
         upload.setFont(new Font("Bold", Font.PLAIN, 15));
         upload.setForeground(Color.BLACK);
         upload.setSize(100, 20);
         upload.setLocation(490,555);
         
         upload.addMouseListener(new MouseAdapter() 
                                    {
                                         @Override
                                         public void mouseClicked(MouseEvent e)
                                        {
                                             upload.setForeground(Color.red);
                                             revalidate();
                                             repaint();
                                            
                                             File file = null;
                                             JFileChooser chooser = new JFileChooser();
         
                                             int a = chooser.showOpenDialog(null);
         
                                             if(a == JFileChooser.APPROVE_OPTION)
                                            {
                                                 file = chooser.getSelectedFile();
                                                 System.out.println(file);
                                                 try 
                                                {
                                                     picToSave = ImageIO.read(file);
                                                     picAdded  = true;
                                                     uploadedPicture = new Picture(120,100,file);
                                                     bkPanel.remove(takePicSignUp.thisImage);
                                                     takePicSignUp.changePic(uploadedPicture.thisImage);
                                                     takePicSignUp.thisImage.setSize(120,100);
                                                     takePicSignUp.thisImage.setLocation(430, 450);
                                                     bkPanel.add(takePicSignUp.thisImage);
                                                } 
                                                 catch (IOException ex)
                                                {
                                                     JOptionPane.showMessageDialog(null, "PLEASE UPLOAD SOME OTHER FILE!", "ERROR!", ERROR_MESSAGE);
                                                }
                                            }
                                             
                                             upload.setForeground(Color.black);
                                             revalidate();
                                             repaint();
                                        }
                                    }
                                );
         
         bkPanel.setLayout(null);
         bkPanel.add(name);
         bkPanel.add(nField);
         bkPanel.add(password);
         bkPanel.add(pField);
         bkPanel.add(cPassword);
         bkPanel.add(cpField);
         bkPanel.add(dateOfBirth);
         bkPanel.add(dField);
         bkPanel.add(gender);
         bkPanel.add(gMenu);
         bkPanel.add(takePicSignUp.thisImage);
         bkPanel.add(takePic);
         bkPanel.add(upload);
         
         signUpOk = new JButton("Sign Up Ok");
         backScreenOne = new JButton("Back");
         signUpOk.setBackground(new Color(124,28,5));
         backScreenOne.setBackground(new Color(124,28,5));
         signUpOk.setForeground(Color.white);
         backScreenOne.setForeground(Color.white);
         
         backScreenOne.addActionListener(new ActionListener()
                                            {
                                                 @Override
                                                 public void actionPerformed(ActionEvent e)
                                                {
                                                     remove(signUpPanel);
                                                     add(firstLastPanel);
                                                }
                                             }
                                        );
         
         signUpOk.addActionListener(new ActionListener() 
                                        {
                                             @Override
                                             public void actionPerformed(ActionEvent e)
                                            {
                                                 boolean namePass = false;
                                                 boolean passwordPass = false;
                                                 boolean dateOfBirthPass = false;
                                                
                                                 sName = nField.getText().trim();
                                                 if(sName.compareTo("")!=0)
                                                {
                                                     namePass = true;
                                                     System.out.println("namePass");
                                                }
                                                 
                                                 chPassword = pField.getPassword();
                                                 chCPassword = cpField.getPassword();
                                                 
                                                 sPassword = String.valueOf(chPassword);
                                                 sCPassword = String.valueOf(chCPassword);
                                                 
                                                 if(sPassword.compareTo(sCPassword)==0)
                                                {
                                                     passwordPass = true;
                                                     System.out.println("PasswordPass");
                                                }
                                                 else
                                                {
                                                     JOptionPane.showMessageDialog(null, "THE PASSWORS DO NOT MATCH", "PASSWORD ERROR", ERROR_MESSAGE);
                                                }
                                                 
                                                 sDateOfBirth = dField.getText();
                                                 
                                                 String[] ddmmyyyy = new String[3];
                                                 
                                                 ddmmyyyy = sDateOfBirth.split("-");
                                                 try
                                                {
                                                     if(Integer.valueOf(ddmmyyyy[0])>0&&Integer.valueOf(ddmmyyyy[0])<32)
                                                    {
                                                         if(Integer.valueOf(ddmmyyyy[1])>0&&Integer.valueOf(ddmmyyyy[1])<13)
                                                        {
                                                             if(Integer.valueOf(ddmmyyyy[2])>0)
                                                            {
                                                                 dateOfBirthPass = true;
                                                                 System.out.println("DateOfbirthPass");
                                                            }
                                                        }
                                                    }
                                                }
                                                 catch (NumberFormatException | HeadlessException n)
                                                {
                                                
                                                }
                                                 finally
                                                {
                                                     if(dateOfBirthPass==false)
                                                    {
                                                         JOptionPane.showMessageDialog(null, "THE DATE OF BIRTH IS NOT UNDERSTANDABLE","ERROR!",ERROR_MESSAGE);
                                                    }
                                                }
                                                 
                                                 int a = gMenu.getSelectedIndex();
                                                 if(a==0)
                                                {
                                                     sGender = "MALE";
                                                }
                                                 if(a==1)
                                                {
                                                     sGender = "FEMALE";
                                                }
                                                 
                                                 if(namePass==true && passwordPass==true && dateOfBirthPass==true && picAdded==true )
                                                {
                                                     File data = new File("E:\\DataOfP\\"+sName+".txt");
                                                     if(data.exists())
                                                    {
                                                         JOptionPane.showMessageDialog(null,"THE USER ALREADY EXISTS\nTRY DIFFERENT NAME\nOR SIGN IN INSTEAD","ERROR !",INFORMATION_MESSAGE);
                                                    }
                                                     else
                                                    {
                                                         SignUpFinal();
                                                    }
                                                     System.out.println(sName+" "+sPassword+" "+sCPassword+" "+sDateOfBirth+" "+sGender);
                                                }
                                                 else
                                                {
                                                     if(picAdded==false)
                                                    {
                                                         JOptionPane.showMessageDialog(null, "KINDLY UPLOAD A PIC OR TAKE A NEW ONE","ERROR!",ERROR_MESSAGE);
                                                    }
                                                     else
                                                    {
                                                         JOptionPane.showMessageDialog(null, "THE PROVIDED INFORMATION IS NOT VALID\nREVIEW AND EDIT IT","ERROR!",ERROR_MESSAGE);
                                                    }
                                                }
                                            }
                                        }
                                    );
         
         lowerBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
         lowerBtnPanel.setBackground(new Color(i,0,0));
         lowerBtnPanel.add(backScreenOne);
         lowerBtnPanel.add(signUpOk);

         signUpPanel = new JPanel(new BorderLayout(10,10));
         signUpPanel.setBackground(new Color(126, 58, 21));
         signUpPanel.add(bkPanel);
         signUpPanel.add(lowerBtnPanel,BorderLayout.SOUTH);

         add(signUpPanel);
         setBackground(new Color(126,58,21));
         revalidate();
         repaint();
    }
     
     public void SignUpFinal()
    {
         MyFile savedata = new MyFile("E:\\DataOfP\\"+sName+".txt",true);
         try
        {
             savedata.fileWriter(sPassword+".");
             savedata.fileWriter(sDateOfBirth);
             savedata.fileWriter("."+sGender);
            
             ImageIO.write((RenderedImage) picToSave,"jpg", new File("E:\\DataOfP\\"+sName+".jpg"));
        }
         catch(Exception ex)
        {
             System.out.println(ex);
        }
        
         try
        {
             String contain = savedata.fileReader();
             String[] parts = contain.split("\\.");
             MainPage(signUpPanel,sName,parts[1],parts[2]);
             
        } 
         catch (IOException ex)
        {
             System.out.println(ex);
        } 
    }
     
     public void TakePicture()
    {    
        
         JButton capture ;
         JPanel buttonPanel ; 
         WebcamPanel cameraPanel;
    
         JPanel lastPanel;
         JPanel finalPanel;
           
             myCamera = Webcam.getDefault();
       
             myCamera.setViewSize(WebcamResolution.VGA.getSize());
        
         cameraPanel = new WebcamPanel(myCamera);
         cameraPanel.setFPSDisplayed(true);
         cameraPanel.setDisplayDebugInfo(true);
         cameraPanel.setImageSizeDisplayed(true);
       
         capture = new JButton("TAKE PIC");
         capture.setBackground(new Color(124,28,5));
         capture.setForeground(Color.WHITE);
        
         buttonPanel = new JPanel(new FlowLayout());
         buttonPanel.add(capture);
        
         lastPanel = new JPanel();
         lastPanel.setLayout(new BorderLayout());
        
         lastPanel.add(cameraPanel,BorderLayout.NORTH);
         lastPanel.add(buttonPanel,BorderLayout.SOUTH);
        
         finalPanel = new JPanel(new FlowLayout());  
         finalPanel.add(lastPanel);
        
         JFrame TakePic = new JFrame("TAKE PIC");
         TakePic.setDefaultCloseOperation(HIDE_ON_CLOSE);
         TakePic.setSize(650, 550);
         TakePic.setResizable(false);
      
         TakePic.add(finalPanel);
         TakePic.setVisible(true);
     
         TakePic.addWindowListener(new WindowAdapter() 
                                        {
                                             @Override
                                             public void windowClosing(WindowEvent ec)
                                            {
                                                 takePic.setForeground(Color.black);
                                                 myCamera.close();
                                                 setEnabled(true);  
                                            }
                                        }
                                    );
     
         capture.addMouseListener(new MouseAdapter()
                                        {
                                             @Override
                                             public void mouseClicked(MouseEvent e)
                                            {
                                                 newImage = myCamera.getImage();
                                                 picToSave = newImage;
                                            
                                                 myCamera.close();
                                                 TakePic.setVisible(false);
                                            
                                                 newPicture = new Picture(120,100,newImage);
                                                 bkPanel.remove(takePicSignUp.thisImage);
                                                 takePicSignUp.changePic(newPicture.thisImage);
                                                 takePicSignUp.thisImage.setSize(120,100);
                                                 takePicSignUp.thisImage.setLocation(430, 450);
                                                 bkPanel.add(takePicSignUp.thisImage);
                                                 takePic.setForeground(Color.black);
                                                 setEnabled(true);
                                                 picAdded = true;
                                                 revalidate();
                                                 repaint();                       
                                            }
                                        }   
                                );                                      
    }
     
     public class Arduino extends JArduino
    {
         public Arduino(String port) throws NoSuchPortException
        {
             super(port);
        }

        @Override
        protected void setup()
        {
             pinMode(DigitalPin.PIN_13, PinMode.OUTPUT);
        }

        @Override
        protected void loop()
        {
             digitalWrite(DigitalPin.PIN_13, DigitalState.HIGH);        
             delay(10000);
        } 
    }
     
     public class ImagePanel extends JPanel
    {
         private Image bkPic = null;
         private final int iWidth2;
         private final int iHeight2;
         
         public ImagePanel(Image bkPic)
        {
             this.bkPic = bkPic;
             this.iWidth2 = bkPic.getWidth(this)/2;
             this.iHeight2 = bkPic.getHeight(this)/2;        
        }
   
         @Override
         public void paintComponent(Graphics g)
        {
             super.paintComponent(g);
             int x = this.getParent().getWidth()/2-iWidth2;
             int y = this.getParent().getHeight()/2-iHeight2;
             g.drawImage(bkPic, x, y, this);
        }
    }
     
     public class Picture
    {
         public JLabel thisImage;
         final public Image image;
         final public int width;
         final public int height;
         BufferedImage im;
       
         public Picture(int width, int height, String name) throws IOException, URISyntaxException
        {
             this.width = width;
             this.height = height;
             im = ImageIO.read(new File(getClass().getResource("/resources/"+name).toURI()));
             this.image = im.getScaledInstance(width, height, 1);
             thisImage = new JLabel(new ImageIcon(image));
        } 
         
         public Picture(int width, int height, File file) throws IOException
        {
             this.width = width;
             this.height = height;
             im = ImageIO.read(file);
             this.image = im.getScaledInstance(width, height, 1);
             thisImage = new JLabel(new ImageIcon(image));
        }
         
         public Picture(int width, int height, BufferedImage image)
        {
             this.width = width;
             this.height = height;
             im =  image;
             this.image = im.getScaledInstance(width, height, 1);
             thisImage = new JLabel(new ImageIcon(this.image));
        }
         public void changePic (JLabel newpic)
        {
             thisImage = newpic;
        }
    }
     
     public class MyFile
    {
         private File file = null;
         private String add;
    
         private BufferedReader bReader;
         private BufferedWriter bWriter;
         private FileReader     fReader;
         private FileWriter     fWriter;
     
         MyFile(String add)
        {
             this.add = add;
             file = new File(add);    
        }
    
         MyFile(String add, boolean newf)
        {
             try
            {
                 this.add = add;
                 file = new File(add);
                 try
                {
                     file.delete();
                }catch(Exception e)
                {
                     System.out.println(e);
                }
                 file.createNewFile();
            }
             catch(Exception e)
            {
                 System.out.println("ERROR CREATING NEW FILE : " + e);
            }
        }
         
         public void fileWriter(String input) throws FileNotFoundException, IOException
        {        
             fWriter = new FileWriter(file.toString(),true);
             bWriter = new BufferedWriter(fWriter); 
             try
            {
                 bWriter.write("\n"+input);
            }
             catch(Exception e)
            {
                 System.out.println("EROOR IN WRITING : " + e);
            }
             finally
            {
                 bWriter.close();
            } 
        }
      
         public String fileReader() throws FileNotFoundException, IOException
        {
             String output = " ";
        
             fReader = new FileReader(add);
             bReader = new BufferedReader(fReader);
        
             String temp = bReader.readLine();
        
             while(temp!=null)
            {
                 output = output + "\n" + temp;
                 temp = bReader.readLine();
            }
             return output.trim();
        }
    }
     
     static class SoundPlayer
    {
         private String adress;
         private URI adressURI;
         private Clip clip;
         Thread  play;
    
         public SoundPlayer(String name)
        {
             try
            {
                 adress    = getClass().getResource("/resources/"+name).toString();
                 adressURI = getClass().getResource("/resources/"+name).toURI();
            }
             catch (URISyntaxException ex)
            {
                 System.out.println(ex);
            }
         
             play = new Thread()
            {
                 @Override
                 public void run()
                {
                     try
                    {
                         clip = AudioSystem.getClip();
                         clip.open(AudioSystem.getAudioInputStream(new File(adressURI)));
                         clip.start();
                    }
                     catch (Exception exc)
                    {
                         System.out.println(exc);
                    }
                }
            };
        }
    
         public void startSound()
        {
             play.run();
        }
         public void stopSound()
        {   
             clip.stop();
             play.stop();
        }
    }
}