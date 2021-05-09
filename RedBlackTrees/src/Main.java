import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.*;

public class Main {



    public static void main(String args[]) throws IOException {


        String read;
        int index = 0;
        String filename = "dictionary.txt";
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String> words = new ArrayList<String>();


        RBTree tree = new RBTree();
        while ((read = br.readLine()) != null) {
            words.add(index, read);
            index++;

        }
     /*  boolean loop = true;
        int i=0;
        do {
            System.out.println("Enter 1 for insert 2 for print 3 to stop");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if(choice == 1){
                String s=scanner.next();
                if(s.equals("c")){
                    System.out.println();
                }
                tree.insert(s);
            }
            else if(choice == 2){
                Queue<Node> q = new LinkedList<>();
                q.offer(tree.root);
                while(!q.isEmpty()){
                    int size = q.size();
                    while(size-->0){
                        Node cur = q.poll();
                        System.out.println(cur.data+"  "+cur.color);
                        if(cur.left != null)
                            q.offer(cur.left);
                        if(cur.right != null)
                            q.offer(cur.right);
                    }
                    System.out.println();
                }
            }
            else if(choice == 3)
                loop = false;
        }while(loop);
*/


        for (int i = 0; i < words.size(); i++) {
            tree.insert(words.get(i));
        }


        while(true){

            System.out.println("Choose 1-Insert Word or 2-Search Word or 3-Exit: ");
            Scanner spy = new Scanner(System.in).useDelimiter("\n");
            String data;
            int x=spy.nextInt();
            if(x==1){
                System.out.println("Insert the word: ");
                data= spy.next();
                boolean search=tree.searchWord(tree.root,data);
                if(search){

                    System.out.println("ERROR: Word already in the dictionary!");
                }
                else {
                    tree.insert(data);
                    System.out.println("Insertion of a Word is Done");
                }
            }else if(x==2){
                System.out.println("Insert the word to search: ");
                data= spy.next();
                boolean searchWord=tree.searchWord(tree.root,data);
                if(searchWord){
                    System.out.println("YES");
                }
                else System.out.println("NO");
            }else if(x==3){
                break;
            }

            System.out.println("The height of the RED-BLACK Tree: "+ tree.height(tree.root));
            System.out.println("The Size of the RED-BLACK Tree: "+ tree.size(tree.root)+"\n");
        }


    }
}





