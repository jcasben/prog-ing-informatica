/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejericicio7;

import java.util.logging.Level;
import java.util.logging.Logger;
import list.CursorOrderedList;
import list.List;
import list.ReferenceOrderedList;

/**
 * Main class of the program.
 *
 * @author jcasben
 * @author marc
 */
public class Main {
    /**
     * Main class of the program.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        System.out.println("---------------CURSOR LIST---------------");
        CursorOrderedList<Integer> cursorList = new CursorOrderedList<>(5,Integer.class);
        try {
            cursorList.add(10);
            cursorList.add(5);
        } catch (List.FullListException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Does the list contain 5? " + cursorList.contains(5));
        try {
            cursorList.remove(5);
            cursorList.remove(5);
        } catch (List.EmptyListException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(cursorList.contains(5));
        System.out.println(cursorList.isEmpty());
        cursorList.clear();
        System.out.println(cursorList.isEmpty());
        
        
        System.out.println("---------------REFERENCE LIST---------------");
        
        ReferenceOrderedList<Integer> refereceList = new ReferenceOrderedList<>();
        refereceList.add(10);
        refereceList.add(5);
        System.out.println("Does the list contain 5? " + refereceList.contains(5));
        try {
            refereceList.remove(5);
            refereceList.remove(5);
        } catch (List.EmptyListException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(refereceList.contains(5));
        System.out.println(refereceList.isEmpty());
        refereceList.clear();
        System.out.println(refereceList.isEmpty());
        
    }
    
}
