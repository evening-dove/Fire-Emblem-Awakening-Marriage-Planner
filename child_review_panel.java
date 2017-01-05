
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;


import java.io.FileWriter;
import java.io.PrintWriter;


public class child_review_panel extends JPanel
{
    //A child will be different depedning on who thier flexable parrent is. This panel is a TextBox that lets the 
    //user type in their opinions on a child being paired up with different parent_units. If the user types in someting, 
    //exits the program, then re-launches it, what they wrote will be saved, and can be edited again in future.
    
    
    child based_on;
    
    JPanel other_parent_panel=new JPanel(new FlowLayout());
    
    //Initiates child_review_panel()
    //As a peramater, takes a child, refering to who this child_review_panel is made for
    public child_review_panel(child based_on)
    {
        super();
        
        setLayout(new GridLayout(0,1));
        
        add(new JLabel(based_on.name, SwingConstants.CENTER));
        
        this.based_on=based_on;
        
        //Creates one review_panel for each flexable parent option
        for (int i=0; based_on.constant_parent.marry_options.size()>i; i++){
            
            JFrame temp_frame=new JFrame();
            
            JPanel temp_frame_main_panel=new JPanel(new BorderLayout());
            
            JLabel temp_review_label=new JLabel("");
            JTextArea edit_review_area=new JTextArea("");
            JButton temp_edit_review_button=new JButton("Edit");
            
            
            String current_father_name=based_on.constant_parent.marry_options.get(i).name;
            
            //An anonymous action listener that allows the user to edit of save what is writen.
            temp_edit_review_button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    
                    //Lets the user edit what is writen
                    if (((JButton)event.getSource()).getText()=="Edit"){
                        temp_frame_main_panel.remove(temp_review_label);
                        temp_frame_main_panel.add(edit_review_area, BorderLayout.CENTER);
                        
                        ((JButton)event.getSource()).setText("Done");
                        
                    }
                    
                    //Saves what the user has writen to a text file.
                    else{
                        
                        temp_review_label.setText("<html><body>");
                        
                        String review_text=edit_review_area.getText();
                        
                        String current_line;
                        String[] text_for_file=new String[edit_review_area.getLineCount()];
                        
                        int c=0;
                        
                        //loops through all writen lines
                        while (review_text.contains("\n")==true){
                            
                            current_line=review_text.substring(0, review_text.indexOf("\n"));
                            
                            text_for_file[c]=current_line;
                            
                            temp_review_label.setText(temp_review_label.getText()+current_line+"<br>");
                            review_text=review_text.substring(review_text.indexOf("\n")+1, review_text.length());
                            c++;
                        }
                        
                        //Sets up the panel so that it can no longer be edited
                        current_line=review_text;
                            
                        text_for_file[c]=current_line;
                            
                        temp_review_label.setText(temp_review_label.getText()+current_line+"<br>");
                        review_text=review_text.substring(review_text.indexOf("\n")+1, review_text.length());
                        
                        temp_review_label.setText(temp_review_label.getText().substring(0, temp_review_label.getText().length()-4));
                        temp_review_label.setText(temp_review_label.getText()+"</body></html>");
                        
                        temp_frame_main_panel.remove(edit_review_area);
                        temp_frame_main_panel.add(temp_review_label, BorderLayout.NORTH);
                        
                        
                        ((JButton)event.getSource()).setText("Edit");
                        
                        Path file=Paths.get("C:/Users/Bryan/Documents/java/fire_emblem_awakening_child_planner/match_reviews/"+based_on.name+"/"+current_father_name+".txt");
                        Charset text_file_charset=Charset.forName("ISO-8859-1");
                        
                        //Writes to the .txt file, if it exists.
                        try{
                            List<String> new_file_lines=Arrays.asList(text_for_file);
                            Path new_file=Paths.get("C:/Users/Bryan/Documents/java/fire_emblem_awakening_child_planner/match_reviews/"+based_on.name+"/"+current_father_name+".txt");
                            Files.write(new_file, new_file_lines, text_file_charset);
                            
                        }
                        
                        catch (IOException e) {
                            System.out.println(e);
                        }
                        
                    }
                        
                    temp_frame.pack();
                    temp_frame.repaint();
            }
            }
            );
            
            
            //Creates the UI for the panel
            temp_frame_main_panel.add(temp_review_label, BorderLayout.NORTH);
            temp_frame_main_panel.add(temp_edit_review_button, BorderLayout.SOUTH);
            
            
            temp_frame.add(temp_frame_main_panel);
            
            temp_frame.setPreferredSize(new Dimension(500, 850));
            
            temp_frame.pack();
            
            
            JButton temp_father_button=new JButton(based_on.constant_parent.marry_options.get(i).name);
            
            //anonymous action listener for displaying a review_panel
            temp_father_button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    
                    //If the frame was closed but is now being opend, reads what is in the innitial .txt file and displays it.
                    if (temp_frame.isVisible()==false){
            
                        temp_review_label.setText("<html><body>");
                        edit_review_area.setText("");
                        
                        Path file=Paths.get("C:/Users/Bryan/Documents/java/fire_emblem_awakening_child_planner/match_reviews/"+based_on.name+"/"+((JButton)event.getSource()).getText()+".txt");
                        Charset text_file_charset=Charset.forName("ISO-8859-1");
                        
                        try{
                            
                            List<String> fileArray=Files.readAllLines(file, text_file_charset);
                            for (int ii=0; fileArray.size()>ii; ii++){
                                
                                temp_review_label.setText(temp_review_label.getText()+fileArray.get(ii)+"<br>");
                                edit_review_area.append(fileArray.get(ii)+"\n");
                            }
                            temp_review_label.setText(temp_review_label.getText()+"</body></html>");
                            edit_review_area.setText(edit_review_area.getText().substring(0,edit_review_area.getText().length()-1));
                        }
                        
                        catch (IOException e) {
                            System.out.println(e);
                        }
                                    
                        
                        temp_frame.setVisible(true);
                    }
            }
            }
            );
            
            other_parent_panel.add(temp_father_button);
            
            add(other_parent_panel);
            
        }
    }
}
