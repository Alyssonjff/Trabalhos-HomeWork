package br.edu.univas;

import java.util.Scanner;

//O trabalho consiste em desenvolver um sistema em Java que permita a gestão de
//uma biblioteca. O sistema deve permitir a adição e a remoção de livros, além de
//possibilitar a busca por livros pelo título, area de interesse ou pelo nome do autor.
//Além disso, o sistema deve ser capaz de gerar relatórios com informações sobre os
//livros cadastrados.
//O sistema deve armazenar os registros em um arquivo .csv com o seguinte padrão//

public class Biblioteca{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] booksTitle = new String[10000000];
        String[] booksArea = new String[booksTitle.length];
        String[] booksActorName = new String[booksTitle.length];
        Integer[] pageNumber = new Integer[booksTitle.length];
        Integer[] code = new Integer[booksTitle.length];
        int number = 0;
        int choose = 0;
        while (choose != 4){
            System.out.println("\t *** BEM VINDO ***");
            System.out.println("PRESSIONE '1' PARA ADICIONAR");
            System.out.println("PRESSIONE '2' PARA REMOVER");
            System.out.println("PRESSIONE '3' PARA BUSCAR");
            System.out.println("PRESSIONE '4' PARA SAIR");
            choose = sc.nextInt();
            if (choose == 1) {
                adictionBooks(booksTitle, booksArea, booksActorName, pageNumber, code, number);
                number++;
            } else if (choose == 2) {
                removeBooks(booksTitle, booksArea, booksActorName, pageNumber, code);
            } else if (choose == 3) {
                bookSearch(booksTitle, booksArea, booksActorName, pageNumber, code);
            }
        }
    }

    public static void adictionBooks(String[] booksTitle,String[] booksArea,String[] booksActorName,Integer[] pageNumber,Integer[] code, int number){
    Scanner sc = new Scanner(System.in);
        System.out.println("\t ADICIONANDO O LIVRO : " +number);
        System.out.println("Qual o titulo do livro?");
        booksTitle[number] = sc.nextLine();
        System.out.println("Qual o genero do livro?");
        booksArea[number] = sc.nextLine();
        System.out.println("Qual o nome do autor do livro do livro?");
        booksActorName[number] = sc.nextLine();
        System.out.println("Qual o numero de paginas do livro?");
        pageNumber[number] = sc.nextInt();
        code[number] = number;
        System.out.println("LIVRO SALVO");
        System.out.println();

    }

    public static void removeBooks(String[] booksTitle,String[] booksArea,String[] booksActorName,Integer[] pageNumber,Integer[] code){
        Scanner sc = new Scanner(System.in);
        String nameT;
        String nameA;
        System.out.println("\t REMOVENDO LIVRO");
        System.out.println("Qual o titulo do livro para remover?");
        nameT = sc.nextLine();
        System.out.println("Qual o ator do livro para remover?");
        nameA = sc.nextLine();
        for (int i = 0;i<booksTitle.length;i++){
            if (nameT.equalsIgnoreCase(booksTitle[i])){
                if (nameA.equalsIgnoreCase(booksActorName[i])){
                    System.out.println(String.format("%d - %s , %s , %s , %d  . Acabou de ser removido", code[i], booksTitle[i], booksArea[i], booksActorName[i], pageNumber[i]));
                    booksTitle[i] = null;
                    booksArea[i] = null;
                    booksActorName[i] = null;
                    pageNumber[i] = null;
                    code[i] = null;
                    System.out.println("LIVRO Excluido");
                    System.out.println();

                }
            }
        }
    }

    public static void bookSearch(String[] booksTitle,String[] booksArea,String[] booksActorName,Integer[] pageNumber,Integer[] code){
        Scanner sc = new Scanner(System.in);
        System.out.println("\t Qual metodo gostaria de ultilizar para procurar o livro?");
        System.out.println("1 - Nome do livro");
        System.out.println("2 - Genero do livro");
        System.out.println("3 - Autor do livro");
        System.out.println("4 - Codigo do livro");
        System.out.println("6 - Todos os livros cadastrados");
        String name;
        int choose = sc.nextInt();
        if (choose == 1){
            System.out.println("Qual o nome do livro preciso procurar?");
            sc.nextLine();
            name = sc.nextLine();
            for (int i = 0;i < booksTitle.length;i++){
                if(name.equalsIgnoreCase(booksTitle[i])) {
                    System.out.println(String.format("%d - %s , %s , %s , %d", code[i], booksTitle[i], booksArea[i], booksActorName[i], pageNumber[i]));
                }else if (i == booksTitle.length){
                    System.out.println("Livro nao encontrado por titulo");
                }
            }
            System.out.println();
        }else if (choose == 2){
            System.out.println("Qual o tipo de livro preciso procurar?");
            sc.nextLine();
            name = sc.nextLine();
            for (int i = 0;i < booksTitle.length;i++){
                if(name.equalsIgnoreCase(booksArea[i])) {
                    System.out.println(String.format("%d - %s , %s , %s , %d", code[i], booksTitle[i], booksArea[i], booksActorName[i], pageNumber[i]));
                }else if (i == booksTitle.length){
                    System.out.println("Livro nao encontrado por Genero");
                }
            }
            System.out.println();
        }else if (choose == 3){
            System.out.println("Qual o nome do autor que preciso procurar?");
            sc.nextLine();
            name = sc.nextLine();
            for (int i = 0;i < booksTitle.length;i++){
                if(name.equalsIgnoreCase(booksActorName[i])) {
                    System.out.println(String.format("%d - %s , %s , %s , %d", code[i], booksTitle[i], booksArea[i], booksActorName[i], pageNumber[i]));
                }else if (i == booksTitle.length){
                    System.out.println("Livro nao encontrado por nome de autor");
                }
            }
            System.out.println();
        }else if (choose == 4){
            System.out.println("Qual o codigo do livro?");
            int numero = sc.nextInt();
            if (code[numero] != null) {
                System.out.println(String.format("%d - %s , %s , %s , %d", code[numero], booksTitle[numero], booksArea[numero], booksActorName[numero], pageNumber[numero]));
            }else{
                System.out.println("Livro nao encontrado por Codigo");
            }
        }else if (choose == 6){
            for (int i = 0;i < booksTitle.length;i++){
                if (code[i] != null) {
                    System.out.println(String.format("%d - %s , %s , %s , %d", code[i], booksTitle[i], booksArea[i], booksActorName[i], pageNumber[i]));
                }
            }
            System.out.println();
        }
    }
}
