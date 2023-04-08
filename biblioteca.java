
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
/*
 O trabalho consiste em desenvolver um sistema em Java que permita a gestão de
 uma biblioteca. O sistema deve permitir a adição e a remoção de livros, além de
 possibilitar a busca por livros pelo título, area de interesse ou pelo nome do autor.
 Além disso, o sistema deve ser capaz de gerar relatórios com informações sobre os
 livros cadastrados.
 O sistema deve armazenar os registros em um arquivo .csv com o seguinte padrão:
 nome_do_livro,numero_de_paginas,nome_do_autor,area_de_interesse
 Estrutura de dados,200,Michael T. Goodrich,Programação de computadores
 */

public class Biblioteca{
    public static String[] obterConteudo(String path) throws IOException {
    String[] conteudoDoArquivo;
    File arquivo = new File(path);
    if (arquivo.createNewFile()) {
        return new String[0];
    }
    long tamanhoDoArquivo = Files.lines(Paths.get(path)).count();

    conteudoDoArquivo = new String[(int) tamanhoDoArquivo];
    int count = 0;
    Scanner scan = new Scanner(arquivo);
    while (scan.hasNextLine()) {
        conteudoDoArquivo[count++] = scan.nextLine();
    }

    scan.close();

    return conteudoDoArquivo;
}
    public static String[] adicionarLinhaNoArquivo(String path, String linha) throws IOException {
        String[] novoConteudo;

        File arquivo = new File(path);
        arquivo.createNewFile();

        FileWriter writer = new FileWriter(arquivo, true);

        writer.write(linha.concat("\n"));
        writer.close();

        novoConteudo = obterConteudo(path);

        return novoConteudo;
    }

    public static void menu() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bem Vindo a Gestão de Biblioteca");
        int option = 0;
        while (option != 5) {
            System.out.println("Escolha uma opção: ");
            System.out.println("1 - Adicionar livro");
            System.out.println("2 - Remover livro");
            System.out.println("3 - Buscar livro");
            System.out.println("4 - Gerar Relatório");
            System.out.println("5 - Sair");

            option = scan.nextInt();

            switch (option) {
                case 1: addBook(); break;
                case 2: removeBook(); break;
                case 3: lookForBook(); break;
                case 4: regenerateReport(); break;
                case 5: System.out.println("Programa Encerrado!"); break;
                default: System.out.println("Por favor escolha uma opção válida!");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws IOException {
        menu();
    }
    public static void addBook() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor informe o título do livro: ");
        String title = scanner.nextLine();
        System.out.print("Por favor informe a quantidade de páginas: ");
        int pageNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Por favor informe o nome do autor: ");
        String authorsName = scanner.nextLine();
        System.out.print("Por favor informe a área de interesse: ");
        String areaOfInterest = scanner.nextLine();
        adicionarLinhaNoArquivo("C:\\Users\\Alysson\\Área de Trabalho\\Faculdade\\Banco de Dados.csv",
                title + "," + pageNumber + "," + authorsName + "," + areaOfInterest);
    }
    public static void removeBook() {
        Scanner scanner = new Scanner(System.in);
        String path = "C:\\Users\\Alysson\\Área de Trabalho\\Faculdade\\Banco de Dados.csv";
        String pathw = "C:\\Users\\Alysson\\Área de Trabalho\\Faculdade\\Banco de Dados1.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path));
             BufferedWriter bw = new BufferedWriter(new FileWriter(pathw))) {

            String line = br.readLine();
            boolean removed = false;
            System.out.println("Informe o título do livro que deseja remover:");
            String title = scanner.nextLine();
            while (line != null) {
                if (line.contains(title)) {
                    System.out.println("Livro removido");
                    removed = true;
                } else {
                    bw.write(line);
                    bw.newLine();
                }
                line = br.readLine();
            }
            if (!removed) {
                System.out.println("Livro não encontrado.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        File file = new File(path);
        file.delete();
        File tempFile = new File(pathw);
        tempFile.renameTo(file);
    }
    public static void lookForBook() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String path = "C:\\Users\\Alysson\\Área de Trabalho\\Faculdade\\Banco de Dados.csv";
        String[] search = obterConteudo(path);
        int option = 0;
        while (option != 4) {
            System.out.println("Por favor informe os dados para pesquisa: ");
            System.out.println("1 - Título do livro");
            System.out.println("2 - Nome do autor");
            System.out.println("3 - Área de interesse");
            System.out.println("4 - Voltar ao menu principal");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Informe o título do livro: ");
                    String title = scanner.nextLine();
                    boolean foundTitle = false;
                    for (String line : search) {
                        if (line.contains(title)) {
                            System.out.println(line);
                            foundTitle = true;
                        }
                    }
                    if (!foundTitle) {
                        System.out.println("Nenhum livro encontrado com o título informado.");
                    }
                    System.out.println();
                    break;

                case 2:
                    System.out.print("Informe o nome do autor: ");
                    String authorsName = scanner.nextLine();
                    boolean foundAuthorsName = false;
                    for (String line : search) {
                        if (line.contains(authorsName)) {
                            System.out.println(line);
                            foundAuthorsName = true;
                        }
                    }
                    if (!foundAuthorsName) {
                        System.out.println("Nenhum livro encontrado com o autor informado.");
                    }
                    System.out.println();
                    break;

                case 3:
                    System.out.print("Informe a área de interesse: ");
                    String areaOfInterest = scanner.nextLine();
                    boolean foundAreaOfInterest = false;
                    for (String line : search) {
                        if (line.contains(areaOfInterest)) {
                            System.out.println(line);
                            foundAreaOfInterest = true;
                        }
                    }

                    if (!foundAreaOfInterest) {
                        System.out.println("Nenhum livro encontrado com a área de interesse.");
                    }
                    System.out.println();
                    break;
                case 4:
                    menu();

            }
        }
    }

    public static void regenerateReport() throws IOException {
        String path = "C:\\Users\\Alysson\\Área de Trabalho\\Faculdade\\Banco de Dados.csv";
        String[] search = obterConteudo(path);
        String pathReport = "C:\\Users\\Alysson\\Área de Trabalho\\Faculdade\\Relatório.csv";
        for(String rel : search){
            adicionarLinhaNoArquivo(pathReport, rel);
        }
        System.out.println("Relatório gerado com sucesso!!");
    }

}
