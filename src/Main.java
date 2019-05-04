import controller.*;
import model.*;

import java.io.*;

import com.google.gson.*;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.main();
    }
}
