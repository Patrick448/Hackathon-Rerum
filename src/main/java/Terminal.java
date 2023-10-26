import com.javax0.sourcebuddy.Compiler;
import odlLoader.ODLLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Terminal {

    static Compiler.Loaded loaded;

    public static void setLoaded(Compiler.Loaded loaded) {
        Terminal.loaded = loaded;
    }

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "1234");
        Connection baseConnection;

        try {
            baseConnection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Scanner scanner = new Scanner(System.in);
        Map<String, Command> comandos = new HashMap<>();

        comandos.put("load", new LoadCommand());
        comandos.put("insert", new InsertCommand());
        comandos.put("delete", new DeleteCommand());
        comandos.put("select", new SelectCommand());
        comandos.put("selectall", new SelectAllCommand());
        comandos.put("update", new UpdateCommand());

        while (true) {
            System.out.print("Digite um comando: ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("exit")) {
                System.out.println("Saindo do programa.");
                break;
            }

            String[] partes = entrada.split(" ");
            String comando = partes[0];

            if (comandos.containsKey(comando) && comandos.get(comando) != null) {
                Command cmd = comandos.get(comando);
                cmd.execute(partes);
            } else {
                System.out.println("Comando não reconhecido. Tente novamente.");
            }
        }
    }
}

interface Command {
    void execute(String[] args);
}

class LoadCommand implements Command {


    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            String nomeArquivo = args[1];
            Compiler.Loaded loaded;
            try {
                loaded = ODLLoader.loadClasses(nomeArquivo);
                Terminal.setLoaded(loaded);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("Comando 'load' requer um arquivo como argumento. Exemplo: load arquivo.odl");
        }
    }
}

class InsertCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length == 3) {
            String nomeClasse = args[1];
            String objeto = args[2];

            System.out.println("Executando o comando 'insert' com a classe: " + nomeClasse + " e objeto: " + objeto);

        } else {
            System.out.println("Comando 'insert' requer dois argumentos. Exemplo: insert nomeDaClasse objeto");
        }
    }
}

class DeleteCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length == 3) {
            String nomeClasse = args[1];
            String id = args[2];

            System.out.println("Executando o comando 'delete' com a classe: " + nomeClasse + " e ID: " + id);
        } else {
            System.out.println("Comando 'delete' requer dois argumentos. Exemplo: delete nomeDaClasse id");
        }
    }
}

class SelectCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length == 3) {
            String nomeClasse = args[1];
            String id = args[2];

            System.out.println("Executando o comando 'select' com a classe: " + nomeClasse + " e ID: " + id);
        } else {
            System.out.println("Comando 'select' requer dois argumentos. Exemplo: select nomeDaClasse id");
        }
    }
}

class SelectAllCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            String nomeClasse = args[1];

            System.out.println("Executando o comando 'selectall' com a classe: " + nomeClasse);
        } else {
            System.out.println("Comando 'selectall' requer um argumento. Exemplo: selectall nomeDaClasse");
        }
    }
}

class UpdateCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length == 4) {
            String nomeClasse = args[1];
            String id = args[2];
            String objeto = args[3];

            System.out.println("Executando o comando 'update' com a classe: " + nomeClasse + " e ID: " + id + " e objeto: " + objeto);
        } else {
            System.out.println("Comando 'update' requer três argumentos. Exemplo: update nomeDaClasse id objeto");
        }
    }
}
