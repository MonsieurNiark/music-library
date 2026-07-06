# AGENTS.md

## Project

This repository is a Java 21 Spring Boot backend for managing a music library.
It is intentionally small and used to learn Codex, GitHub issues, pull requests, reviews, and agent-driven feature work.

## Commands

- Build and test: `mvn test`
- Run locally: `mvn spring-boot:run`
- Package: `mvn package`

## Architecture

- Main package: `com.example.musiclibrary`
- Album feature: `src/main/java/com/example/musiclibrary/album`
- Cross-cutting REST errors: `src/main/java/com/example/musiclibrary/common`
- Sample data: `src/main/java/com/example/musiclibrary/bootstrap`
- Integration tests: `src/test/java`

## Engineering Guidelines

- Keep changes small and focused on the requested issue.
- Prefer domain methods and services over putting business logic in controllers.
- Validate request DTOs with Jakarta Bean Validation.
- Return stable response DTOs instead of exposing entities directly.
- Add or update tests for behavior changes.
- Do not add production dependencies without explaining why.
- Keep public API changes backwards-compatible unless the issue explicitly asks otherwise.

## Done Means

- Relevant tests were added or updated.
- `mvn test` passes, or the reason it could not be run is stated clearly.
- The final response explains the behavior change and verification.

## Review Guidelines

- Watch for broken REST status codes.
- Watch for missing validation and missing negative tests.
- Watch for accidental entity exposure through controllers.
- Watch for persistence changes that could lose child collections.
- Treat failing tests or compilation errors as blocking.
