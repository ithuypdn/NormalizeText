/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import common.Algorithm;
import model.Library;
import view.Menu;

/**
 *
 * @author huypd
 */
public class NormalizeController extends Menu<String> {

    static String[] options = {"Normalize file text.", "Exit."};
    Library l;
    Algorithm ar;

    public NormalizeController() {
        super("PROGRAMING", options);
        l = new Library();
        ar = new Algorithm();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1 -> ar.normalizeText("input.txt", "output.txt");
            case 2 -> System.exit(0);
        }
    }
}
