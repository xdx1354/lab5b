import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;


public class MyWindow  {
    //GUI components
    private JButton abcButton;
    private JButton defButton;
    private JButton ghiButton;
    private JButton jklButton;
    private JButton mnoButton;
    private JButton pqrButton;
    private JButton stuvButton;
    private JButton wxyzButton;
    private JButton equalsButton;
    private JButton CEButton;
    private JButton cButton;
    private JButton subtractButton;
    private JButton addButton;
    private JButton dmButton;
    private JButton divButton;
    private JPanel mainPanel;
    private JLabel txtLabel;
    //other
    private String temp = "";
    private String part1;
    private String part2;
    private String operationSign;
    private String wholeText ="";
    private String result="";
    private int previousClicks =0;
    private boolean capitalLetters = false; //na start wpisywane beda male litery
    private ArrayList<JButton> buttonsList = new ArrayList<>();



    public MyWindow(){
        divButton.addMouseListener(new dividePressed());

        cButton.addMouseListener(new cPressed());
        CEButton.addMouseListener(new cePressed());
        addButton.addMouseListener(new addPressed());
        equalsButton.addMouseListener(new equalsPressed());
        subtractButton.addMouseListener(new subtractPressed());
        dmButton.addMouseListener(new DMPressed());
        abcButton.addMouseListener(new buttonsPressed());
        defButton.addMouseListener(new buttonsPressed());
        ghiButton.addMouseListener(new buttonsPressed());
        jklButton.addMouseListener(new buttonsPressed());
        mnoButton.addMouseListener(new buttonsPressed());
        pqrButton.addMouseListener(new buttonsPressed());
        stuvButton.addMouseListener(new buttonsPressed());
        wxyzButton.addMouseListener(new buttonsPressed());

        //wypelnianie listy przyciskow
        buttonsList.add(abcButton);  //0
        buttonsList.add(defButton);  //1
        buttonsList.add(ghiButton);  //2
        buttonsList.add(jklButton);  //3
        buttonsList.add(mnoButton);  //4
        buttonsList.add(pqrButton);  //5
        buttonsList.add(stuvButton); //6
        buttonsList.add(wxyzButton); //7

    }


    private String addStrings(String s1, String s2){
        return s1+s2;
    }

    private String subtractStrings(String s1, String s2){
        return s1.replace(s2,"");
    }

    private String commonPart(char[] str1, char[] str2) {           //jeśli istnieje wiecej niż jeden podciag o takiej samej najdluzeszej dlugości to zwroci pierwszy z nich
        int [][]tab = new int[str1.length][str2.length];
        int maxLength = 0;
        int iPos = 0;
        int jPos = 0;
        String longestCommon = "";

        if(str2.length==1){                     //jesli drugie slowo bedzie mialo dlugosc 1 to wykorzystywany pozniej algorytm wyrzuca bledy, wiec trzeba to obsłużyc osobno
            for(int i=0; i<str1.length; i++){
                if(str1[i]==str2[0]){
                    longestCommon+=str2[0];
                    return longestCommon;
                }
            }
        }


         for(int i=0; i<str1.length; i++ ){     // algorytm tworzacy tablice podcaigow
             for( int j = 0; j<str2.length; j++ ){

                 if(i==0 || j==0){
                     if(str1[i] == str2[j]){
                         tab[i][j] = 1;
                     }
                     else{
                         tab[i][j] = 0;
                     }
                 }
                 else if(str1[i] == str2[j]){
                    tab[i][j] = tab[i-1][j-1]+1;
                    if(tab[i][j]>maxLength){
                        iPos = i;                   //zapamietuje gdzie sie konczy najdluzszy podciag
                        jPos = j;
                        maxLength = tab[i][j];      //zwiekszam wartosc najdluzszego podciagu wspolnego słów
                    }
                 }
                 else {
                     tab[i][j] = 0;
                 }
             }
         }


        System.out.println("max len: " + maxLength);
        System.out.println("iPos: " + str1[iPos]);

        for (int k=0; k<maxLength; k++){
            System.out.println("iPos: " + str1[iPos-k]);
            longestCommon =str1[iPos-k]+longestCommon;
        }

        System.out.println(Arrays.deepToString(tab));
        return  longestCommon;
    }       //divideStrings



    public static void main(String[] args) {
        JFrame frame = new JFrame("MyWindow");
        MyWindow window = new MyWindow();
        frame.add(window.mainPanel);




        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    class equalsPressed extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            try{
                part2 = temp;

                switch (operationSign){
                    case "+":{
                        result = part1 + part2;
                        System.out.println(result);
                        break;
                    }
                    case "/":{
                        System.out.println(part1);
                        System.out.println(part2);
                        result = commonPart(part1.toCharArray(), part2.toCharArray());
                        if(result.isEmpty()){
                            JOptionPane.showMessageDialog(null, "Brak wspolnej czesci");
                        }
                        break;
                    }
                    case "-":{
                        result = subtractStrings(part1,part2);
                        break;
                    }
                }

                wholeText = wholeText + part2 + "=" + result;
                txtLabel.setText(wholeText);
                part1 = "";
                part2 = "";
                temp = "";
            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null, "Brak elementu do usuniecia");
            }
        }
    }

    class addPressed extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            try{

                part1 = temp;
                operationSign = "+";
                wholeText = wholeText + part1 + operationSign;
                temp = "";
                txtLabel.setText(wholeText);
            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null, "Brak elementu do dodania");
            }
        }
    }

    class dividePressed extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            try{
                part1 = temp;
                operationSign = "/";
                wholeText = wholeText + part1 + operationSign;
                temp = "";
                txtLabel.setText(wholeText);
            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null, "Brak elementu do dodania");
            }
        }
    }

    class subtractPressed extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            try{

                part1 = temp;
                operationSign = "-";
                wholeText = wholeText + part1 + operationSign;
                temp = "";
                txtLabel.setText(wholeText);
            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null, "Brak elementu do dodania");
            }
        }
    }

    class DMPressed extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getID());
            capitalLetters = !capitalLetters;
        }
    }

    class cPressed extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            try{
                temp = temp.substring(0,temp.length()-1);
                //wholeText = wholeText + temp;
                txtLabel.setText(wholeText + temp);
            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null, "Brak elementu do usuniecia");
            }
        }
    }

    class cePressed extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            try{
                temp = "";
                wholeText = "";
                txtLabel.setText(wholeText + temp);
            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null, "Brak elementu do usuniecia");
            }
        }
    }

    class buttonsPressed extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            Object src = e.getSource();
            int  id = 0;

            for(int i =0; i<8; i++){                // sprawdzam który przycisk został klikniety i zapisuje jego id
                if(src == buttonsList.get(i)){
                    id = i;
                    break;
                }
            }
            System.out.println(e.getClickCount());
            switch (id) {
                case 0 -> {                                     //abcButton

                        if (e.getClickCount()  == 1) {
                            temp += (capitalLetters) ? "A" : "a";
                        }
                        if (e.getClickCount() != 1 && e.getClickCount() % 3 == 1) {
                            temp = temp.substring(0, temp.length() - 1);
                            temp += (capitalLetters) ? "A" : "a";
                        }
                        if (e.getClickCount() % 3 == 2) {
                            temp = temp.substring(0, temp.length() - 1);
                            temp += (capitalLetters) ? "B" : "b";
                        }
                        if (e.getClickCount() % 3 == 0) {
                            temp = temp.substring(0, temp.length() - 1);
                            temp += (capitalLetters) ? "C" : "c";
                        }
                }
                case 1 -> {                                     //defButton
                    if (e.getClickCount() == 1) {
                        temp += (capitalLetters) ? "D" : "d";
                    }
                    if (e.getClickCount() != 1 && e.getClickCount() % 3 == 1) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "D" : "d";
                    }
                    if (e.getClickCount()%3 == 2) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "E" : "e";
                    } else if (e.getClickCount()%3 == 0) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "F" : "f";
                    }
                }
                case 2 -> {                                     //ghicButton

                    if (e.getClickCount()  == 1) {
                        temp += (capitalLetters) ? "G" : "g";
                    }
                    if (e.getClickCount() != 1 && e.getClickCount() % 3 == 1) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "G" : "g";
                    }
                    if (e.getClickCount() % 3 == 2) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "H" : "h";
                    }
                    if (e.getClickCount() % 3 == 0) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "I" : "i";
                    }
                }
                case 3 -> {                                     //jklButton

                    if (e.getClickCount()  == 1) {
                        temp += (capitalLetters) ? "J" : "j";
                    }
                    if (e.getClickCount() != 1 && e.getClickCount() % 3 == 1) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "J" : "j";
                    }
                    if (e.getClickCount() % 3 == 2) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "K" : "k";
                    }
                    if (e.getClickCount() % 3 == 0) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "L" : "l";
                    }
                }
                case 4 -> {                                     //mnoButton

                    if (e.getClickCount()  == 1) {
                        temp += (capitalLetters) ? "M" : "m";
                    }
                    if (e.getClickCount() != 1 && e.getClickCount() % 3 == 1) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "M" : "m";
                    }
                    if (e.getClickCount() % 3 == 2) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "N" : "n";
                    }
                    if (e.getClickCount() % 3 == 0) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "O" : "o";
                    }
                }
                case 5 -> {                                     //pqrButton

                    if (e.getClickCount()  == 1) {
                        temp += (capitalLetters) ? "P" : "p";
                    }
                    if (e.getClickCount() != 1 && e.getClickCount() % 3 == 1) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "P" : "p";
                    }
                    if (e.getClickCount() % 3 == 2) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "Q" : "q";
                    }
                    if (e.getClickCount() % 3 == 0) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "R" : "r";
                    }
                }
                case 6 -> {                                     //stuvButton

                    if (e.getClickCount()  == 1) {
                        temp += (capitalLetters) ? "S" : "s";
                    }
                    if (e.getClickCount() != 1 && e.getClickCount() % 4 == 1) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "S" : "s";
                    }
                    if (e.getClickCount() % 4 == 2) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "T" : "t";
                    }
                    if (e.getClickCount() % 4 == 3) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "U" : "u";
                    }
                    if (e.getClickCount() % 4 == 0) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "V" : "v";
                    }
                }
                case 7 -> {                                     //wxyzButton

                    if (e.getClickCount()  == 1) {
                        temp += (capitalLetters) ? "W" : "w";
                    }
                    if (e.getClickCount() != 1 && e.getClickCount() % 4 == 1) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "W" : "w";
                    }
                    if (e.getClickCount() % 4 == 2) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "X" : "x";
                    }
                    if (e.getClickCount() % 4 == 3) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "Y" : "y";
                    }
                    if (e.getClickCount() % 4 == 0) {
                        temp = temp.substring(0, temp.length() - 1);
                        temp += (capitalLetters) ? "Z" : "z";
                    }
                }
            }
            txtLabel.setText(wholeText + temp);
        }
    }

}
