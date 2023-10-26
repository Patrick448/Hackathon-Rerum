import com.javax0.sourcebuddy.Compiler;
import odlLoader.ODLLoader;
import orm.Entity;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class Terminal {

    static Compiler.Loaded loaded;
    static Connection connection;

    public static void setLoaded(Compiler.Loaded loaded) {
        Terminal.loaded = loaded;
    }

    public static Compiler.Loaded getLoaded() {
        return loaded;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        Terminal.connection = connection;
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

        Terminal.connection = baseConnection;


        Scanner scanner = new Scanner(System.in);
        Map<String, Command> comandos = new HashMap<>();

        comandos.put("load", new LoadCommand());
        comandos.put("insert", new InsertCommand());
        comandos.put("delete", new DeleteCommand());
        comandos.put("select", new SelectCommand());
        comandos.put("selectall", new SelectAllCommand());
        comandos.put("update", new UpdateCommand());
        comandos.put("create", new CreateCommand());

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

            try {
                Class<?> objClass = Terminal.getLoaded().get("generatedodl." + nomeClasse);
                Entity obj = (Entity)Terminal.getLoaded().newInstance("generatedodl." + nomeClasse);
                //obj.create(Terminal.getConnection());

                String[] atributosObjetos = objeto.split(",");
                obj.fromList(Arrays.asList(atributosObjetos), Terminal.getConnection());
                obj.insert(Terminal.getConnection());

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("Comando 'insert' requer dois argumentos. Exemplo: insert nomeDaClasse objeto");
        }
    }
}

class CreateCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            String nomeClasse = args[1];
            try {
                Class<?> objClass = Terminal.getLoaded().get("generatedodl." + nomeClasse);
                Entity obj = (Entity)Terminal.getLoaded().newInstance("generatedodl." + nomeClasse);
                obj.create(Terminal.getConnection());

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }


        } else {
            System.out.println("Comando 'create' requer um argumentos. Exemplo: insert nomeDaClasse");
        }
    }
}

class DeleteCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length == 3) {
            String nomeClasse = args[1];
            String id = args[2];
            long id_long = Long.parseLong(id);

            try {
                Class<?> objClass = Terminal.getLoaded().get("generatedodl." + nomeClasse);
                Entity obj = (Entity)Terminal.getLoaded().newInstance("generatedodl." + nomeClasse);
                obj.setAttr("o", id_long);

                try {
                    obj.delete(Terminal.getConnection());
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }

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
            long id_long = Long.parseLong(id);

            try {
                Class<?> objClass = Terminal.getLoaded().get("generatedodl." + nomeClasse);
                Entity obj = (Entity)Terminal.getLoaded().newInstance("generatedodl." + nomeClasse);
                obj.setAttr("o", id_long);

                try {
                    obj.select(Terminal.getConnection());
                    System.out.println(obj.getValuesListString(Terminal.getConnection()));

                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
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

            try {
                Class<?> objClass = Terminal.getLoaded().get("generatedodl." + nomeClasse);
                Entity obj = (Entity)Terminal.getLoaded().newInstance("generatedodl." + nomeClasse);

                try {
                    List<Entity> list = obj.selectAll(Terminal.getConnection());
                    for(Entity e : list) {
                        System.out.println(e.getValuesListString(Terminal.getConnection()));
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                try {
                    obj = (Entity)Terminal.getLoaded().newInstance("generatedodl." + nomeClasse);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                try {
                    obj.selectAll(Terminal.getConnection());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
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
            long id_long = Long.parseLong(id);
            String objeto = args[3];

            try {
                Class<?> objClass = Terminal.getLoaded().get("generatedodl." + nomeClasse);
                Entity obj = (Entity)Terminal.getLoaded().newInstance("generatedodl." + nomeClasse);
                obj.setAttr("o", id_long);

                String[] atributosObjetos = objeto.split(",");
                obj.fromList(Arrays.asList(atributosObjetos), Terminal.getConnection());
                obj.update(Terminal.getConnection());

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("Comando 'update' requer três argumentos. Exemplo: update nomeDaClasse id objeto");
        }
    }
}
