/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Message;
import model.Topic;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import view.ListFriendPanel;
import view.ListMessagePanel;
import view.MainView;
import view.ListTopicPanel;
import view.MessagePanel;

/**
 *
 * @author Ayoze Gil Sosa
 */
public class Controller implements ActionListener, KeyListener, ComponentListener {

    private SessionFactory sf;
    private Session s;
    private MainView mv;
    private ListMessagePanel mp; 
    private Transaction tr;
    private User user;
    private Topic topic;

    public Controller() {
        initProcesses();
    }
    
    //Inicia la comunicación con hibernate y la interfaz usuario 
    private void initProcesses(){
        sf = NewHibernateUtil.getSessionFactory();
        mv = new MainView();
        initLogin();
        mv.setVisible(true);
    }
    
    //Inicia la ventana de login
    private void initLogin(){
        s = sf.openSession();
        tr = new Transaction(this, s);
        user = null;
        topic = null;
        mv.initLogin();
        mv.getLoginPanel().getBtnLogin().addActionListener(this);
        mv.getLoginPanel().getBtnRegister().addActionListener(this);            
    }
    
    //Realiza el login o avisa si los datos no son correctos
    private void completeLogin() {
        User u;
        user = tr.getUser(mv.getLoginPanel().getTxtUsername().getText());
        if (user != null && user.getPassword().equals(
                String.valueOf(mv.getLoginPanel().getTxtPassword().getPassword()))){
            initSession();
        }
        else
            mv.getLoginPanel().getLblMessage().setText("Los datos no son correctos");     
    }
    
    //Inicia la ventana de registro de usuario
    private void initRegister(){
        mv.initRegister();
        mv.getRegisterPanel().getBtnRegister().setText("Registrar");
        mv.getRegisterPanel().getBtnRegister().addActionListener(this);
        mv.getRegisterPanel().getBtnCancel().addActionListener(this);  
        mv.getRegisterPanel().getBtnLoadPhoto().addActionListener(this);
        mv.getRegisterPanel().getTxtUsername().addKeyListener(this);
        mv.getRegisterPanel().getTxtPassword().addKeyListener(this);
        mv.getRegisterPanel().getTxtName().addKeyListener(this);
        mv.getRegisterPanel().getTxtLastName().addKeyListener(this);
        mv.getRegisterPanel().getTxtEmail().addKeyListener(this);
    }

    //Inicia la ventana de registro de usuario
    private void fillRegister(){
        mv.initRegister();
        mv.getRegisterPanel().getBtnRegister().setText("Modificar");
        mv.getRegisterPanel().getBtnRegister().addActionListener(this);
        mv.getRegisterPanel().getBtnCancel().addActionListener(this);  
        mv.getRegisterPanel().getBtnLoadPhoto().addActionListener(this);
        mv.getRegisterPanel().getTxtUsername().addKeyListener(this);
        mv.getRegisterPanel().getTxtPassword().addKeyListener(this);
        mv.getRegisterPanel().getTxtName().addKeyListener(this);
        mv.getRegisterPanel().getTxtLastName().addKeyListener(this);
        mv.getRegisterPanel().getTxtEmail().addKeyListener(this);
        mv.getRegisterPanel().getTxtUsername().setText(user.getUsername());
        mv.getRegisterPanel().getTxtPassword().setText(user.getPassword());
        mv.getRegisterPanel().getTxtName().setText(user.getName());
        mv.getRegisterPanel().getTxtLastName().setText(user.getLastname());
        mv.getRegisterPanel().getTxtEmail().setText(user.getMail());
        mv.getRegisterPanel().getTxtBirthdate().setDate(user.getBirthdate());
        mv.getRegisterPanel().getLblPhoto().setIcon(
                ImageOperator.loadImage(user.getPhotourl()));
    }
    
    //Realiza el registro del nuevo usuario e inicia sesión
    private void completeRegister() {
        try {
            String username = mv.getRegisterPanel().getTxtUsername().getText();
            String password = mv.getRegisterPanel().getTxtPassword().getText();
            String name = mv.getRegisterPanel().getTxtName().getText();
            String lastName = mv.getRegisterPanel().getTxtLastName().getText();
            Date birthdate = mv.getRegisterPanel().getTxtBirthdate().getDate();
            String email = mv.getRegisterPanel().getTxtEmail().getText();
            if (username.isEmpty() || password.isEmpty() || name.isEmpty()
                    || lastName.isEmpty() || email.isEmpty()){
                mv.getRegisterPanel().getLblMessage().setText("Rellena todos los campos");
            }
            else if (birthdate == null){
                mv.getRegisterPanel().getLblMessage().setText("Selecciona una fecha");
            }
            else if (tr.getUser(username) != null){
                mv.getRegisterPanel().getLblMessage().setText("El usuario ya existe");
            }
            else{
                user = new User(username,password,name,lastName,birthdate,email,
                        "");
                user = tr.createUser(user);
                String photourl = "./src/resources/"+user.getId()+".png";
                ImageOperator.savePhoto(
                        mv.getRegisterPanel().getLblPhoto().getIcon(),
                        photourl);
                user.setPhotourl(photourl);
                tr.updateUser(user);
                initSession();
            }             
        } catch (IOException e){
            mv.getRegisterPanel().getLblMessage().setText("Problema con la imagen");
        } catch (HibernateException e){
            mv.getRegisterPanel().getLblMessage().setText("Error: Revise todos los campos");
        }
    }

    //Realiza el registro del nuevo usuario e inicia sesión
    private void modifyRegister() {
        try {
            String username = mv.getRegisterPanel().getTxtUsername().getText();
            String password = mv.getRegisterPanel().getTxtPassword().getText();
            String name = mv.getRegisterPanel().getTxtName().getText();
            String lastName = mv.getRegisterPanel().getTxtLastName().getText();
            Date birthdate = mv.getRegisterPanel().getTxtBirthdate().getDate();
            String email = mv.getRegisterPanel().getTxtEmail().getText();
            if (username.isEmpty() || password.isEmpty() || name.isEmpty()
                    || lastName.isEmpty() || email.isEmpty()){
                mv.getRegisterPanel().getLblMessage().setText("Rellena todos los campos");
            }
            else if (birthdate == null){
                mv.getRegisterPanel().getLblMessage().setText("Selecciona una fecha");
            }
            else if (!user.getUsername().equals(username) &&  tr.getUser(username) != null){
                mv.getRegisterPanel().getLblMessage().setText("El usuario ya existe");
            }
            else{
                ImageOperator.savePhoto(
                        mv.getRegisterPanel().getLblPhoto().getIcon(),
                        user.getPhotourl());
                user.setUsername(mv.getRegisterPanel().getTxtUsername().getText());
                user.setPassword(mv.getRegisterPanel().getTxtPassword().getText());
                user.setName(mv.getRegisterPanel().getTxtName().getText());
                user.setLastname(mv.getRegisterPanel().getTxtLastName().getText());
                user.setMail(mv.getRegisterPanel().getTxtEmail().getText());
                user.setBirthdate(mv.getRegisterPanel().getTxtBirthdate().getDate());
                tr.updateUser(user);
                initSession();
            }          
        } catch (IOException e){
            mv.getRegisterPanel().getLblMessage().setText("Error: Problema con la imagen");
        } catch (HibernateException e){
            mv.getRegisterPanel().getLblMessage().setText("Error: Revise todos los campos");
        }
    }
    
    //Inicia la ventana personal de usuario.
    private void initSession(){
        //Activa listeners de la ventana de sesion
        mv.initSession();
        mv.setTitle("Hibernetwork - " + user.getUsername());
        mv.getSessionPanel().getBtnFriendRequest().addActionListener(this);
        mv.getSessionPanel().getBtnCreateTopic().addActionListener(this);
        mv.getSessionPanel().getBtnRefresh().addActionListener(this);
        mv.getSessionPanel().getBtnExitSession().addActionListener(this);
        mv.getSessionPanel().getBtnConfig().addActionListener(this);
        mv.getSessionPanel().getBtnAdmin().addActionListener(this);
        mv.getSessionPanel().getBtnUpdate().addActionListener(this);
        mv.getSessionPanel().getBtnDelete().addActionListener(this);
        mv.addComponentListener(this);

        //Se rellena la ficha personal del usuario
        mv.getSessionPanel().getLblUsername().setText(user.getUsername());
        mv.getSessionPanel().getLblPhoto().setIcon(
                ImageOperator.resizeImage(user.getPhotourl(), 60, 75));

        //Rellena lista de amigos
        tr.fillFriendsList(user, mv);
        //Rellena lista de temas
        tr.fillTopicsList(user, mv);
        //Comprobar solicitudes de amistad
        tr.checkFriendRequests(user, mv);
        //Comprobar invitaciones a temas
        tr.checkTopicRequests(user);
        
    }

    //Abre un nuevo tema en la ventana central
    protected void openTopic(Topic topic){  
        mp = new ListMessagePanel(topic);
        mp.getLblTitle().setText(topic.getTitle());
        mp.getBtnSendMessage().addActionListener(this);
        mv.getSessionPanel().getCenterPanel().removeAll();
        mv.getSessionPanel().getCenterPanel().add(mp);
        mp.setBounds(0, 0, mp.getParent().getSize().width, mp.getParent().getSize().height);
        tr.fillMessageList(user,topic,mp);
        tr.fillUsersList(topic,mp);
        mp.setVisible(true);
        mv.getSessionPanel().getCenterPanel().validate();
    }
    
    //Refresca la sesión para actualizar los datos
    protected void refresh(){
        s.close();
        s = sf.openSession();
        tr = new Transaction(this, s);
        user = tr.getUser(user.getId());
        if(topic != null){
            topic = tr.getTopic(topic.getId());
        }
        tr.checkFriendRequests(user, mv);
        tr.checkTopicRequests(user);
        if (topic!= null){
            openTopic(topic);
        }
    }

    //Manejadores de eventos
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (s.getTransaction().isActive()){
            s.getTransaction().commit();
        }
        switch(ae.getActionCommand()){
            /*
            Componentes de Login y Registro
            */
            case "Login":
                completeLogin();
                break;
            case "Register":
                initRegister();
                break;
            //En la ventana de registro comprobamos si es un nuevo usuario o uno registrado.
            case "Accept_Register":
                if (mv.getRegisterPanel().getBtnRegister().getText().equals("Registrar"))
                    completeRegister();
                else if (mv.getRegisterPanel().getBtnRegister().getText().equals("Modificar"))
                    modifyRegister();
                break;  
            case "Cancel_Register":
                if (mv.getRegisterPanel().getBtnRegister().getText().equals("Registrar"))
                    initLogin();
                else if (mv.getRegisterPanel().getBtnRegister().getText().equals("Modificar"))
                    initSession();
                break;
            case "Load_Photo":
                ImageOperator.loadPhoto(mv.getRegisterPanel().getLblPhoto());
                break;
                
            /*
            Componentes de Panel Superior de Sesión y Ventana de Ajustes
            */    
            case "Exit_Session":
                s.close();
                initLogin();
                break;
            case "Config_Options":
                mv.getSessionPanel().getMenuOption().setVisible(true);
                break;
            case "Delete_Account":
                mv.getSessionPanel().getMenuOption().setVisible(false);
                tr.deleteUser(user);
                initLogin();
                break;
            case "Update_Account":
                mv.getSessionPanel().getMenuOption().setVisible(false);
                fillRegister();
                break;
            case "Open_Admin":
                mv.getSessionPanel().getMenuOption().setVisible(false);
                break;
            case "Refresh":
                refresh();
                break;
                
            /*
            Componentes de amigo   
            */
            case "Friend_Request":
                User req_user = tr.getUser(
                        JOptionPane.showInputDialog(mv, "Nombre de Usuario", 
                                "Invitacion Amistad Tema", JOptionPane.QUESTION_MESSAGE));
                mv.getSessionPanel().getLblHelpMessage().setText(
                        tr.sendFriendRequest(user,req_user));
                break;
            case "Delete_Friend":
                mv.getSessionPanel().getLblHelpMessage().setText(tr.deleteFriend(user,
                        ((ListFriendPanel)(((JButton)(ae.getSource())).getParent())).getUser()));
                break;    
                
                
            /*
            Componentes de topic y mensajes
            */    
                 
            case "Create_Topic":
                topic = tr.createTopic(JOptionPane.showInputDialog(mv, 
                        "Título del Tema", "Nuevo Tema", JOptionPane.QUESTION_MESSAGE), user);
                openTopic(topic);
                break;
            case "Delete_Topic":
                mv.getSessionPanel().getLblHelpMessage().setText(
                        tr.deleteTopic(user,
                        ((ListTopicPanel)(((JButton)(ae.getSource())).getParent())).getTopic()));
                break;
            case "Open_Topic":
                topic = ((ListTopicPanel)(((JButton)(ae.getSource())).getParent())).getTopic();
                openTopic(topic);
                break;   
            case "Delete_Message":
                mv.getSessionPanel().getLblHelpMessage().setText(
                        tr.deleteMessage(user, topic,
                        ((MessagePanel)(((JButton)(ae.getSource())).getParent())).getMessage()));
                openTopic(topic);
                break;
            case "Modify_Message":
                Message oldmsg = ((MessagePanel)(((JButton)(ae.getSource())).getParent())).getMessage();
                String newmsg = JOptionPane.showInputDialog(mv, 
                        "Mensaje editado", "Editando Mensaje", JOptionPane.QUESTION_MESSAGE);
                mv.getSessionPanel().getLblHelpMessage().setText(
                        tr.updateMessage(oldmsg,newmsg));
                openTopic(topic);
                break; 
            case "Send_Message":
                String content = mp.getTxtNewMessage().getText();
                Date datetime = new Date();
                Message msg = new Message(topic, user, content, datetime);
                tr.createMessage(msg);
                openTopic(topic);
                break; 
                
            /*
            Invitaciones
            */    
                
            case "Auto_Topic_Request": //Invitación desde Lisa de Amigos al tema abierto
                User user_req = ((ListFriendPanel)(((JButton)(ae.getSource())).getParent())).getUser();
                mv.getSessionPanel().getLblHelpMessage().setText(
                        tr.sendTopicRequest(user,user_req,topic));
                break; 
            case "Invite_Friend": // Invitación desde lista de Temas
                Topic topic_req = ((ListTopicPanel)(((JButton)(ae.getSource())).getParent())).getTopic();
                User friend_req = tr.getUser(JOptionPane.showInputDialog(mv, 
                        "Nombre del usuario", "Invitar a tema", JOptionPane.QUESTION_MESSAGE));
                mv.getSessionPanel().getLblHelpMessage().setText(
                        tr.sendTopicRequest(user,friend_req,topic_req));
                break;

            default:
                System.out.print("Error: Evento no reconocido");
        }
    }

    @Override
    public void componentResized(ComponentEvent ce) {
        mv.validate();
        try{
        if (mv.getSessionPanel()!=null && topic != null);
            openTopic(topic);
        }
        catch(NullPointerException e){}
    }

    @Override
    public void componentMoved(ComponentEvent ce) {
    }

    @Override
    public void componentShown(ComponentEvent ce) {
    }

    @Override
    public void componentHidden(ComponentEvent ce) {
    }

    public MainView getMv() {
        return mv;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if(mv.getRegisterPanel().getTxtUsername().getText().length()>10){
            mv.getRegisterPanel().getTxtUsername().setText(
            mv.getRegisterPanel().getTxtUsername().getText().substring(0, 10));        
        }
        if(mv.getRegisterPanel().getTxtPassword().getText().length()>10){
            mv.getRegisterPanel().getTxtPassword().setText(
            mv.getRegisterPanel().getTxtPassword().getText().substring(0, 10));        
        }
        if(mv.getRegisterPanel().getTxtName().getText().length()>20){
            mv.getRegisterPanel().getTxtName().setText(
            mv.getRegisterPanel().getTxtName().getText().substring(0, 20));        
        }
        if(mv.getRegisterPanel().getTxtLastName().getText().length()>20){
            mv.getRegisterPanel().getTxtLastName().setText(
            mv.getRegisterPanel().getTxtLastName().getText().substring(0, 20));        
        }
        if(mv.getRegisterPanel().getTxtEmail().getText().length()>30){
            mv.getRegisterPanel().getTxtEmail().setText(
            mv.getRegisterPanel().getTxtEmail().getText().substring(0, 30));        
        }
    }
}
