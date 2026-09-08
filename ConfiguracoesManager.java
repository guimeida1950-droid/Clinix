import java.util.prefs.Preferences;

public class ConfiguracoesManager {

    private static final Preferences PREFS = Preferences.userNodeForPackage(
            ConfiguracoesManager.class);

    private static final String HOST = "host";

    private static final String PORTA = "porta";

    private static final String BANCO = "banco";

    private static final String USUARIO = "usuario";

    private static final String SENHA = "senha";

    private static final String MAXIMIZADO = "maximizado";

    // =========================================================
    // PADRÕES
    // =========================================================

    public static final String HOST_PADRAO = "localhost";

    public static final String PORTA_PADRAO = "3306";

    public static final String BANCO_PADRAO = "clinix";

    public static final String USUARIO_PADRAO = "root";

    public static final String SENHA_PADRAO = "root";

    public static final boolean MAXIMIZADO_PADRAO = true;

    // =========================================================
    // HOST
    // =========================================================

    public static String getHost() {

        return PREFS.get(
                HOST,
                HOST_PADRAO);
    }

    public static void setHost(
            String valor) {

        PREFS.put(
                HOST,
                valor);
    }

    // =========================================================
    // PORTA
    // =========================================================

    public static String getPorta() {

        return PREFS.get(
                PORTA,
                PORTA_PADRAO);
    }

    public static void setPorta(
            String valor) {

        PREFS.put(
                PORTA,
                valor);
    }

    // =========================================================
    // BANCO
    // =========================================================

    public static String getBanco() {

        return PREFS.get(
                BANCO,
                BANCO_PADRAO);
    }

    public static void setBanco(
            String valor) {

        PREFS.put(
                BANCO,
                valor);
    }

    // =========================================================
    // USUÁRIO
    // =========================================================

    public static String getUsuario() {

        return PREFS.get(
                USUARIO,
                USUARIO_PADRAO);
    }

    public static void setUsuario(
            String valor) {

        PREFS.put(
                USUARIO,
                valor);
    }

    // =========================================================
    // SENHA
    // =========================================================

    public static String getSenha() {

        return PREFS.get(
                SENHA,
                SENHA_PADRAO);
    }

    public static void setSenha(
            String valor) {

        PREFS.put(
                SENHA,
                valor);
    }

    // =========================================================
    // MAXIMIZADO
    // =========================================================

    public static boolean isMaximizado() {

        return PREFS.getBoolean(
                MAXIMIZADO,
                MAXIMIZADO_PADRAO);
    }

    public static void setMaximizado(
            boolean valor) {

        PREFS.putBoolean(
                MAXIMIZADO,
                valor);
    }

    // =========================================================
    // RESTAURAR PADRÃO
    // =========================================================

    public static void restaurarPadrao() {

        PREFS.put(
                HOST,
                HOST_PADRAO);

        PREFS.put(
                PORTA,
                PORTA_PADRAO);

        PREFS.put(
                BANCO,
                BANCO_PADRAO);

        PREFS.put(
                USUARIO,
                USUARIO_PADRAO);

        PREFS.put(
                SENHA,
                SENHA_PADRAO);

        PREFS.putBoolean(
                MAXIMIZADO,
                MAXIMIZADO_PADRAO);
    }
}