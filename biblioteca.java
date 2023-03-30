
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
/*O trabalho consiste em desenvolver um sistema em Java que permita a gestão de
uma biblioteca. O sistema deve permitir a adição e a remoção de livros, além de
possibilitar a busca por livros pelo título, area de interesse ou pelo nome do autor.
Além disso, o sistema deve ser capaz de gerar relatórios com informações sobre os
livros cadastrados.
O sistema deve armazenar os registros em um arquivo .csv com o seguinte padrão*/

public class biblioteca{
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
                case 5: break;
                default: System.out.println("Escolha uma opção válida!");
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
        adicionarLinhaNoArquivo("C:\\Users\\labunivas03\\SI\\Alysson - P3\\Java\\banco de dados.csv", title + "," + pageNumber + "," + authorsName + "," + areaOfInterest);
    }
    public static void removeBook() {
        Scanner scanner = new Scanner(System.in);
        String path = "C:\\Users\\labunivas03\\SI\\Alysson - P3\\Java\\banco de dados.csv";
        String pathw = "C:\\Users\\labunivas03\\SI\\Alysson - P3\\Java\\banco de dados1.csv";

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
        Scanner sc = new Scanner(System.in);
        String path = "C:\\Users\\labunivas03\\SI\\Alysson - P3\\Java\\banco de dados.csv";
        String[] search = obterConteudo(path);
        int option = 0;
            System.out.println("Por favor informe os dados para pesquisa: ");
            System.out.println("1 - Título do livro");
            System.out.println("2 - Nome do autor");
            System.out.println("3 - Área de interesse");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Informe o título do livro: ");
                    String title = sc.nextLine();
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
                    String authorsName = sc.nextLine();
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
                    String areaOfInterest = sc.nextLine();
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
        }
    }
    public static void regenerateReport() throws IOException {
    }
}
