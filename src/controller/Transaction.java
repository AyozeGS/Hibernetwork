/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import model.Frequest;
import model.Message;
import model.Topic;
import model.Trequest;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import view.ListFriendPanel;
import view.ListMessagePanel;
import view.MainView;
import view.ListTopicPanel;
import view.MessagePanel;
import view.UserPanel;

/**
 *
 * @author Ayoze Gil Sosa
 */
public class Transaction {
    
    Controller c;
    Session s;

    protected Transaction(Controller c, Session s) {
        this.c = c;
        this.s = s;
    }
    
    /*
    Transacciones de Usuario
    */

    //Devuelve una lista de usuarios
    protected List<User> getUsers(){
        s.beginTransaction();
        List<User> users = s.createQuery("From User").list();
        s.getTransaction().commit();  
        return users;
    }
    
    //Devuelve los datos de un usuario de la base de datos con la id indicada
    protected User getUser (int id){  
        User req_user;
        s.beginTransaction();
        req_user = (User)s.get(User.class, id);
        s.getTransaction().commit();
        return req_user;
    }
    
    //Devuelve los datos del usuario indicado de la base de datos
    protected User getUser (String username){
        User req_user;
        s.beginTransaction();
        Iterator it = s.createQuery("From User").list().iterator();
            while (it.hasNext()){
                req_user = (User)it.next();
                if (req_user.getUsername().equals(username)){
                    s.getTransaction().commit();
                    return req_user;
                }
            }
        s.getTransaction().commit();
        return null;
    }
    
    //Crea un usuario
    protected User createUser(User user) throws HibernateException {
        s.beginTransaction();
        s.save(user);
        s.getTransaction().commit();
        return user;
    }
    
    //Actualiza un usuario
    protected boolean updateUser(User user) throws HibernateException {
        try{
            s.beginTransaction();
            s.update(user);
            s.getTransaction().commit();
            return true;
        }
        catch (HibernateException ae){
            return false;
        }
    }
    
    //Actualiza un usuario
    protected boolean updateUser(int id){
        User req_user;
        s.beginTransaction();
        req_user = (User)s.get(User.class, id);
        if (req_user!=null){
            s.update(req_user);
            s.getTransaction().commit();
            return true;
        }
        else{
            s.getTransaction().commit();
            return false;
        }
    }
    
    //Actualiza un usuario
    protected boolean updateUser(String username){
        User req_user;
        s.beginTransaction();
        Iterator it = s.createQuery("From User").list().iterator();
            while (it.hasNext()){
                req_user = (User)it.next();
                if (req_user.getUsername().equals(username)){
                    s.update(req_user);
                    s.getTransaction().commit();
                    return true;
                }
            }
        s.getTransaction().commit();
        return false;
    }
    
    //Borra un usuario
    protected boolean deleteUser(User user){
        try{
            s.beginTransaction();
            s.delete(user);
            s.getTransaction().commit();
            return true;
        }
        catch (HibernateException ae){
            return false;
        }
    }
    
    //Actualiza un usuario
    protected boolean deleteUser(int id){
        User req_user;
        s.beginTransaction();
        req_user = (User)s.get(User.class, id);
        if (req_user!=null){
            s.delete(req_user);
            s.getTransaction().commit();
            return true;
        }
        else{
            s.getTransaction().commit();
            return false;
        }
    }
    
    //Actualiza un usuario
    protected boolean deleteUser(String username){
        User req_user;
        s.beginTransaction();
        Iterator it = s.createQuery("From User").list().iterator();
            while (it.hasNext()){
                req_user = (User)it.next();
                if (req_user.getUsername().equals(username)){
                    s.delete(req_user);
                    s.getTransaction().commit();
                    return true;
                }
            }
        s.getTransaction().commit();
        return false;
    }
    
    /*
    Transacciones de Topics y mensajes
    */
    
    //Devuelve la lista completa de topics
    protected List<Topic> getTopics(){
        s.beginTransaction();
        List<Topic> topics = s.createQuery("From Topic").list();
        s.getTransaction().commit();
        return topics;
    }
    
    //Devuelve los datos de un topic de la base de datos con la id indicada
    protected Topic getTopic(int id){  
        Topic req_topic;
        s.beginTransaction();
        req_topic = (Topic)s.get(Topic.class, id);
        s.getTransaction().commit();
        return req_topic;
    }
    
    //Crea un tema y lo añade a la lista del usuario
    protected Topic createTopic(String title, User user) {
        Topic new_topic;
        s.beginTransaction();
        new_topic = new Topic(title);
        s.save(new_topic);
        user.getTopics().add(new_topic);
        s.getTransaction().commit();
        fillTopicsList(user, c.getMv());
        return new_topic;
    }
    
    //Borra un topic de tu lista de topics
    protected String deleteTopic (User user, Topic topic){
        s.beginTransaction();
        user.getTopics().remove(topic);
        topic.getUsers().remove(user);
        
        if (topic.getUsers().isEmpty()){
            s.delete(topic);
            s.getTransaction().commit();
            fillTopicsList(user, c.getMv());
            return "Tema Borrado";
        }
        else {
            s.getTransaction().commit();
            fillTopicsList(user, c.getMv());
            return "Has Abandonado el tema";
        }
    }
    
    
    /*
    Transacciones de mensajes
    */
    
    //Devuelve la lista completa de topics
    protected List<Message> getMessages(){
        s.beginTransaction();
        List<Message> messages = s.createQuery("From Message").list();
        s.getTransaction().commit();
        return messages;
    }
    
    //Devuelve los datos de un mensaje de la base de datos con la id indicada
    protected Message getMessage(int id){  
        Message req_message;
        s.beginTransaction();
        req_message = (Message)s.get(Message.class, id);
        s.getTransaction().commit();
        return req_message;
    }
    
    //Crea un mensaje en un tema
    protected String createMessage (Message message){
        s.beginTransaction();
        s.save(message);
        message.getTopic().getMessages().add(message);
        s.getTransaction().commit();
        return "Mensaje Enviado";
    }            
          
    //Crea un mensaje en un tema
    protected String updateMessage (Message message, String string){
        s.beginTransaction();
        message.setContent(string);
        s.update(message);
        s.getTransaction().commit();
        return "Mensaje Editado";
    }      
    
    //Borra un topic de tu lista de topics
    protected String deleteMessage (User user, Topic topic, Message message){
        s.beginTransaction();
        topic.getMessages().remove(message);
        user.getMessages().remove(message);
        s.delete(message);
        s.getTransaction().commit();
        return "Mensaje Borrado";
    }

    /* 
    Transacciones de solicitudes
    */
    
    //Borra un usuario de tu lista de amigos
    protected String deleteFriend (User user, User friend){
        s.beginTransaction();
        user.getFriends().remove(friend);
        friend.getFriends().remove(user);
        s.getTransaction().commit();
        fillFriendsList(user, c.getMv());
        return "Amigo eliminado";
    }
    
    //Envía una invitación de amistad a otro usuario
    protected String sendFriendRequest(User user, User req_user) {
        Frequest freq;
        s.beginTransaction();
        if (req_user == user){
            s.getTransaction().commit();
            return "No puedes invitarte a ti mismo";
        }
        if (req_user == null){
            s.getTransaction().commit();
            return "El usuario no existe" ;
        }
        if (user.getFriends().contains(req_user)){
            s.getTransaction().commit();
            return "El usuario ya es amigo tuyo" ;
        }
        Iterator it = user.getFrequestsForOrigUser().iterator();
        while (it.hasNext()){
            Frequest aux = (Frequest)it.next();
            if (aux.getUserByDestUser() == req_user){
                s.getTransaction().commit();
                return "Ya has mandado esa invitación" ;
            }
        }
        it = user.getFrequestsForDestUser().iterator();
        while (it.hasNext()){
            Frequest aux = (Frequest)it.next();
            if (aux.getUserByOrigUser() == req_user){
                s.getTransaction().commit();
                return "El usuario ya te ha mandado invitación";
            }
        }
        freq = new Frequest(req_user, user);
        s.save(freq);
        user.getFrequestsForOrigUser().add(freq);
        req_user.getFrequestsForDestUser().add(freq);
        s.getTransaction().commit();
        return "Invitación enviada";
    }
    
    //Envía una invitación a un tema a otro usuario
    protected String sendTopicRequest(User user, User req_user, Topic req_topic) {
        
        Trequest treq;
        
        s.beginTransaction();
        
        if (req_user == user){
            s.getTransaction().commit();
            return "No puedes invitarte a ti mismo";
        }
        if (req_user == null){
            s.getTransaction().commit();
            return "El usuario no existe";
        }
        if (req_topic == null){
            s.getTransaction().commit();
            return "No hay ningún tema abierto";
        }   
        if (req_topic.getUsers().contains(req_user)){
            s.getTransaction().commit();
            return "El usuario ya pertenece al tema";
        }
        Iterator it = user.getTrequests().iterator();
        while (it.hasNext()){
            Trequest aux = (Trequest)it.next();
            if (aux.getUser() == req_user && aux.getTopic() == req_topic){
                s.getTransaction().commit();
                return "El usuario ya ha sido invitado al tema";
            }
        }
        treq = new Trequest(req_topic, req_user);
        s.save(treq);
        user.getTrequests().add(treq);
        req_topic.getTrequests().add(treq);
        s.getTransaction().commit();
        return("Invitación enviada");
    }
    
    //Actualiza la lista de amigos en la interfaz de usuario
    protected void fillFriendsList(User user, MainView mv){
        s.beginTransaction();
        TreeSet<User> friends = new TreeSet(user.getFriends());
        Iterator it = friends.iterator();
        mv.getSessionPanel().getFriendsPanel().removeAll();
        mv.getSessionPanel().getFriendsPanel().setSize(
                mv.getSessionPanel().getFriendsPanel().getSize().width,
                user.getFriends().size()*100);
        while(it.hasNext()){
            User u = (User)it.next();
                    ListFriendPanel fp = new ListFriendPanel(u);
                    mv.getSessionPanel().getFriendsPanel().add(fp);
                    fp.getBtnUnfriend().addActionListener(c);
                    fp.getBtnTopic().addActionListener(c);
        }
        mv.getSessionPanel().getFriendsPanel().revalidate();
        s.getTransaction().commit();
        mv.validate();
    }
    
    //Actualiza la lista de temas en la interfaz de usuario
    protected void fillTopicsList(User user, MainView mv){
        s.beginTransaction();
        TreeSet<Topic> topics = new TreeSet(user.getTopics());
        Iterator it = topics.iterator();
        mv.getSessionPanel().getTopicsPanel().removeAll();
        mv.getSessionPanel().getTopicsPanel().setSize(
                mv.getSessionPanel().getTopicsPanel().getSize().width,
                user.getFriends().size()*25);
        while(it.hasNext()){
            Topic t = (Topic)it.next();
            ListTopicPanel tp = new ListTopicPanel(t);
            mv.getSessionPanel().getTopicsPanel().add(tp);
            tp.getBtnAddFriend().addActionListener(c);
            tp.getBtnLeaveTopic().addActionListener(c);
            tp.getBtnTitle().addActionListener(c);
        }
        mv.getSessionPanel().getTopicsPanel().revalidate();
        s.getTransaction().commit();
        mv.validate();
    }
    
    //Actualiza la lista de temas en la interfaz de usuario
    protected void fillMessageList(User user, Topic topic, ListMessagePanel lmp){
        s.beginTransaction();
        TreeSet<Message> messages = new TreeSet(topic.getMessages());
        Iterator it = messages.iterator();
        lmp.getMessagesPanel().removeAll();
        lmp.revalidate();
        while(it.hasNext()){
            Message m = (Message)it.next();
            MessagePanel mp = new MessagePanel(m);
            lmp.getMessagesPanel().add(mp);
            if (user.equals(m.getUser())){
                mp.getBtnModify().addActionListener(c);
                mp.getBtnDelete().addActionListener(c);
            }else{
                mp.getBtnModify().setVisible(false);
                mp.getBtnDelete().setVisible(false);
            }      
        }
        lmp.getMessagesPanel().revalidate();
        s.getTransaction().commit();
        lmp.validate();
    }
    
    //Actualiza la lista de temas en la interfaz de usuario
    protected void fillUsersList(Topic topic, ListMessagePanel lmp){
        s.beginTransaction();
        TreeSet<User> users = new TreeSet(topic.getUsers());
        Iterator it = users.iterator();
        lmp.getUsersPanel().removeAll();
        while(it.hasNext()){
            User u = (User)it.next();
            UserPanel up = new UserPanel(u);
            lmp.getUsersPanel().add(up);
        }
        lmp.getUsersPanel().revalidate();
        s.getTransaction().commit();
        lmp.validate();
    }
    
    //Comprueba las invitaciones de amistad pendientes del usuario
    protected String checkFriendRequests(User user, MainView mv){
        String help = "";
        s.beginTransaction();
        Iterator it = user.getFrequestsForDestUser().iterator();
        while(it.hasNext()){
            it = user.getFrequestsForDestUser().iterator();
            Frequest temp_freq = (Frequest)it.next();
            User temp_user = temp_freq.getUserByOrigUser();
            int confirmado = JOptionPane.showConfirmDialog(mv,
                    temp_user.getUsername()+" te ha pedido amistad");
            switch (confirmado) {
                case JOptionPane.OK_OPTION:
                    temp_user.getFriends().add(user);
                    user.getFriends().add(temp_user);
                    user.getFrequestsForDestUser().remove(temp_freq);
                    s.delete(temp_freq);
                    help = "Amigo aceptado";
                    break;
                case JOptionPane.NO_OPTION:
                    user.getFrequestsForDestUser().remove(temp_freq);
                    s.delete(temp_freq);
                    help =  "Invitación rechazada";
                    break;
                default:
                    help =  "Invitación pendiente";
            }
            s.getTransaction().commit();
            s.beginTransaction();
        }
        s.getTransaction().commit();
        fillFriendsList(user, c.getMv());
        return help;
    }
    
    //Comprueba las invitaciones recibidas a nuevos temas pendientes
    protected String checkTopicRequests(User user){
        String help = "";
        s.beginTransaction();
        Iterator it = user.getTrequests().iterator();
        while(it.hasNext()){
            it = user.getTrequests().iterator();
            Trequest temp_treq = (Trequest)it.next();
            Topic temp_topic = temp_treq.getTopic();
            int confirmado = JOptionPane.showConfirmDialog(c.getMv(),
                    "Te han invitado al tema: "+temp_topic.getTitle());
            switch (confirmado) {
                case JOptionPane.OK_OPTION:
                    user.getTopics().add(temp_topic);
                    //temp_topic.getUsers().add(user);
                    user.getTrequests().remove(temp_treq);
                    s.delete(temp_treq);
                    help = "Tema añadido";
                    break;
                case JOptionPane.NO_OPTION:
                    user.getTrequests().remove(temp_treq);
                    s.delete(temp_treq);
                    help = "Invitación cancelada";
                    break;
                default:
                    help = "Invitación Pendiente";
            }
            s.getTransaction().commit();
            s.beginTransaction();
        }
        s.getTransaction().commit();
        fillTopicsList(user, c.getMv());
        return help;
    }

}

