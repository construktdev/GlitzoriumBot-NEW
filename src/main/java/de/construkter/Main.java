package de.construkter;

import de.construkter.auto.faq.ButtonHandler;
import de.construkter.auto.faq.JoinFAQ;
import de.construkter.auto.mod.Checker;
import de.construkter.commands.slash.EmbedDesigner;
import de.construkter.commands.slash.HelpCommand;
import de.construkter.commands.slash.SendStatusEmbed;
import de.construkter.glitzoriumID.LoginHandler;
import de.construkter.glitzoriumID.RegisterHandler;
import de.construkter.gui.CustomOutputStream;
import de.construkter.gui.GlitzoriumBotControlPanel;
import de.construkter.listeners.OnReady;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Launching GUI...");
        GlitzoriumBotControlPanel.main(args);
        JFrame frame = new JFrame("Console Output");
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);

        // Redirect console output to the JTextArea
        CustomOutputStream customOutputStream = new CustomOutputStream(textArea);
        PrintStream printStream = new PrintStream(customOutputStream);
        System.setOut(printStream);
        System.setErr(printStream);
        JDA jda = JDABuilder.createDefault(Token.get(), GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                .setActivity(Activity.listening("/help"))
                .addEventListeners(new OnReady(), new HelpCommand(), new Checker(), new SendStatusEmbed(), new EmbedDesigner(), new JoinFAQ(), new ButtonHandler())
                .addEventListeners(new RegisterHandler(), new LoginHandler())
                .disableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOJI, CacheFlag.STICKER, CacheFlag.SCHEDULED_EVENTS)
                .build();
        jda.updateCommands().addCommands(
                Commands.slash("help", "Zeigt dir eine Liste mit allen Commands"),
                Commands.slash("send-status-embed", "[Admin] Sendet den Status embed"),
                Commands.slash("embed-builder", "[Admin] Embed designen und senden"),
                Commands.slash("register", "Registriere dich bei der GlitzoriumID").setGuildOnly(true),
                Commands.slash("login", "Melde dich bei der GlitzoriumID an").setGuildOnly(true)
                        .addOption(OptionType.STRING, "email", "Deine Email-Adresse")
                        .addOption(OptionType.STRING, "password", "Dein Passwort")
                        .addOption(OptionType.BOOLEAN, "stayloggedin", "True - Wenn du angemeldet bleiben möchtest")
        ).queue();
    }

}