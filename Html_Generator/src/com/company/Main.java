package com.company;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main (String[] args) throws IOException {
        Table htmlTable = new Table();
        FileWriter table = new FileWriter("HtmlTable.html");
        table.write(htmlTable.html());
        table.close();
    }
}
