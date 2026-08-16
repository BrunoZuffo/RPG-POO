package persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import jogo.SaveGame;

public class GerenciadorSave {

    private static final Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    public static void salvar(SaveGame save) {
        try (FileWriter arquivo = new FileWriter("save.json")) {
            GSON.toJson(save, arquivo);
            System.out.println("Jogo salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo.");
        }
    }

    public static SaveGame carregar() {
        try (FileReader arquivo = new FileReader("save.json")) {
            return GSON.fromJson(arquivo, SaveGame.class);
        } catch (IOException e) {
            System.out.println("Nenhum save encontrado ou erro ao carregar o jogo.");
            return null;
        }
    }
}
