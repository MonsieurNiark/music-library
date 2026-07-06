# Codex and GitHub Basics

Use this project to practice the full loop.

## 1. Create the repository

```bash
git init
git add .
git commit -m "Initial music library backend"
gh repo create music-library --private --source=. --remote=origin --push
```

## 2. Open a feature issue

Example issue:

```md
Feature: Add album ratings

## Goal

Allow users to rate albums from 1 to 5.

## API shape

- Add `rating` to album create/update requests.
- Return `rating` in album responses.

## Acceptance criteria

- Rating must be between 1 and 5.
- Existing seeded data has ratings.
- Tests cover valid and invalid ratings.
```

## 3. Ask Codex to implement it

Use this prompt in Codex:

```text
Implement the album rating issue.
Follow AGENTS.md.
Add validation and tests.
Run mvn test.
Create a concise PR summary when done.
```

## 4. Create a branch and PR

```bash
git switch -c feature/album-ratings
git add .
git commit -m "Add album ratings"
git push -u origin feature/album-ratings
gh pr create --fill --draft
```

## 5. Ask Codex to review

Comment on the PR:

```text
@codex review
```

You can also run the manual GitHub Action named `Codex manual review` after adding `OPENAI_API_KEY` in repository secrets.

For a focused review:

```text
@codex review for validation gaps and missing tests
```

## 6. Ask Codex to fix review findings

If Codex reports a high-priority issue, comment:

```text
@codex fix the P1 issue
```

## 7. Learn the pattern

For each change, practice this rhythm:

```text
Issue -> Codex plan -> implementation -> tests -> PR -> Codex review -> fix -> merge
```
