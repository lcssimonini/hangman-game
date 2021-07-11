build_project() {
  echo "----- Building hangman application -----"
  sh ./gradlew bootJar
}

run_project() {
  echo "----- Running hangman application -----"
    docker build -t hangman .
    docker run hangman
    docker logs --tail=500 -f hangman
}

build_project && run_project