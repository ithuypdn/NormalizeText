/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import model.Library;

/**
 *
 * @author huypd
 */
public abstract class Menu<T> {
    protected String title;
    protected ArrayList<T> options;
    Library l = new Library();

    public Menu() {
        options = new ArrayList<>();
    }

    public Menu(String tt, String[] op) {
        title = tt;
        options = new ArrayList<>();
        for (String s : op) {
            options.add((T) s);
        }
    }

    //-------------------------------------------
    public void display() {
        System.out.println(title);
        System.out.println("--------------------------------");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + "." + options.get(i));
        }
        System.out.println("--------------------------------");
    }
//-------------------------------------------

    public int getSelected() {
        display();
        return l.getInt("Enter your choice", 1, options.size() + 1);
    }
//-------------------------------------------

    public abstract void execute(int n);
//-------------------------------------------

    public void run() {
        while (true) {
            int n = getSelected();
            execute(n);
            if (n > options.size())
                break;
        }
    }
}
