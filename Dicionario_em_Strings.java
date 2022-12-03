package br.edu.univas;

import java.util.Scanner;

public class Dicionario_em_Strings {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        String[] portugueseWord = new String[100];
        String[] englishWord = new String[100];

        for (int i = 0;;) {
            menu();
            int choose = sc.nextInt();
            sc.nextLine();
            if (i > 100){
                System.out.println("Nao e possivel adicionar mais palavras");
            }else if (choose == 1) {
                wordRegister(i,portugueseWord,englishWord);
                System.out.println(portugueseWord[i] + " = " + englishWord[i]);
                System.out.println();
                i++;
            }
            if(choose == 2){
                list(portugueseWord, englishWord);
                editList(portugueseWord, englishWord);
                System.out.println();
            }else if (choose == 3){
                list(portugueseWord, englishWord);
                deleteWordRegister(portugueseWord, englishWord);
                i--;
                System.out.println();
            }else if (choose == 4){
                foundWord(portugueseWord, englishWord);
            }
        }
    }

    public static void menu(){
        System.out.println("1. Cadastro");
        System.out.println("2. Edicao");
        System.out.println("3. Excluir");
        System.out.println("4. Encontrar");
    }

    public static void wordRegister(int i, String[] portugueseRegister, String[] englishRegister){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual Palavra a ser salva?");
        String word = sc.nextLine();
        if(analize(word, portugueseRegister)) {
            System.out.println("Palavra salva!");
            portugueseRegister[i] = word;
            System.out.println("Qual a traducao?");
            word = sc.nextLine();
            System.out.println("Palavra salva!");
            englishRegister[i] = word;
            System.out.println();
        }
    }

    public static void list(String[] portugueseWord, String[] englishWord){
        for (int i = 0;i < portugueseWord.length;i++){
            if (portugueseWord[i] != null && englishWord[i] != null){
                System.out.println((i + 1)+" . " + portugueseWord[i] + " = " +englishWord[i]);

            }else if (i < portugueseWord.length - 1 && portugueseWord[i] == null){
                portugueseWord[i] = portugueseWord[i+1];
                englishWord[i] = englishWord[i+1];
            }
        }
    }

    public static void editList(String[] portugueseWord, String[] englishWord){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual Linha sera editada?");
        int line = sc.nextInt() - 1;
        wordRegister(line, portugueseWord, englishWord);
    }

    public static void deleteWordRegister(String[] portugueseWord, String[] englishWord){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual Linha para excluir?");
        int line = sc.nextInt() - 1;
        portugueseWord[line] = null;
        englishWord[line] = null;
    }

    public static void foundWord(String[] portugueseWord, String[] englishWord){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual Palavra pra ser achada?");
        String word = sc.nextLine();
        for (int i = 0;i < portugueseWord.length;i++){
            if (word.equalsIgnoreCase(portugueseWord[i])){
                System.out.println((i+1) + " " + portugueseWord[i] + " = " + englishWord[i]);
                break;
            }else if(i == portugueseWord.length - 1){
                System.out.println("Palavra nao encontrada");
            }
        }
        System.out.println();
    }

    public static boolean analize(String toAnalize, String[] words){
        for (int i = 0;i < words.length;i++){
                if (toAnalize.equalsIgnoreCase(words[i])){
                    System.out.println("Palavra em uso");
                    return false;
                }
        }
        return true;
    }
}