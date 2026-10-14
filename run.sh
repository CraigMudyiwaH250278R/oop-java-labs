#!/usr/bin/env bash
# Build and run helper (Linux / macOS / Git Bash).
#   ./run.sh all        run every lab in order
#   ./run.sh lab05      run one lab
#   ./run.sh test       run the automated test-suite
#   ./run.sh library       Library Management System (Lab 11 mini-project), interactive menu
#   ./run.sh library-demo  scripted demo of the mini-project (persistence + overdue)
#   ./run.sh clean      delete compiled classes and generated output
# Add -i after a lab name for interactive input (labs 7 and 10), e.g.  ./run.sh lab07 -i
set -euo pipefail
cd "$(dirname "$0")"

compile() {
  mkdir -p out output
  javac -Xlint:all -d out $(find src tests -name '*.java')
}

run_lab() {
  local n="${1#lab}"; n="$(printf '%02d' "$((10#$n))")"
  echo; echo "################  LAB $n  ################"
  java -cp out "lab${n}.Lab${n}Runner" "${@:2}"
}

case "${1:-all}" in
  clean) rm -rf out output/*; echo "cleaned" ;;
  test)  compile; java -cp out AllTests ;;
  all)   compile; for i in 01 02 03 04 05 06 07 08 09 10 11; do run_lab "lab$i"; done; echo; echo "################  LAB 11 MINI-PROJECT DEMO  ################"; java -cp out lab11.library.LibraryDemo ;;
  library)      compile; java -cp out lab11.library.LibraryApp "${@:2}" ;;
  library-demo) compile; java -cp out lab11.library.LibraryDemo ;;
  lab*)  compile; run_lab "$@" ;;
  *)     echo "usage: ./run.sh [all|labNN|library|library-demo|test|clean]"; exit 1 ;;
esac
