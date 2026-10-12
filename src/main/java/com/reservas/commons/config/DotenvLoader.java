package com.reservas.commons.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class DotenvLoader {

    private DotenvLoader() {
    }

    public static void load() {
        Path envFile = resolveEnvFile();
        if (envFile == null) {
            return;
        }

        Dotenv dotenv = Dotenv.configure()
                .directory(envFile.getParent().toString())
                .filename(envFile.getFileName().toString())
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        dotenv.entries().forEach(entry -> {
            boolean missingInEnv = System.getenv(entry.getKey()) == null;
            boolean missingInProps = System.getProperty(entry.getKey()) == null;
            if (missingInEnv && missingInProps) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });
    }

    private static Path resolveEnvFile() {
        String userDir = System.getProperty("user.dir");
        List<Path> candidates = List.of(
                Path.of(userDir, ".env"),
                Path.of(userDir, "commons", ".env"),
                Path.of(userDir).resolve("..").resolve(".env").normalize(),
                Path.of(userDir).resolve("..").resolve("commons").resolve("commons").resolve(".env").normalize(),
                Path.of(userDir).resolve("..").resolve("commons").resolve(".env").normalize()
        );

        return candidates.stream()
                .filter(Files::isRegularFile)
                .findFirst()
                .orElse(null);
    }
}
