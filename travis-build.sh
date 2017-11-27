#!/bin/bash
set -e
cd 3.2.x
./gradlew clean check --stacktrace
cd ../3.3.x
./gradlew clean check --stacktrace

EXIT_STATUS=0

echo "Publishing Docs For Branch $TRAVIS_BRANCH"
if [[ $TRAVIS_BRANCH =~ ^master$ && $TRAVIS_PULL_REQUEST == 'false' ]]; then

  cd ../
  ./gradlew ascii || EXIT_STATUS=$?
  echo "Docs status: $EXIT_STATUS"

  git config --global user.name "$GIT_NAME"
  git config --global user.email "$GIT_EMAIL"
  git config --global credential.helper "store --file=~/.git-credentials"
  echo "https://$GH_TOKEN:@github.com" > ~/.git-credentials

  git clone https://${GH_TOKEN}@github.com/${TRAVIS_REPO_SLUG}.git -b gh-pages gh-pages --single-branch > /dev/null
  cd gh-pages

  # If this is the master branch then update the snapshot
  rm -rf *.html *.js images
  cp -R ../build/asciidoc/html5/* .
  git add .

  git commit -a -m "Updating docs for Travis build: https://travis-ci.org/$TRAVIS_REPO_SLUG/builds/$TRAVIS_BUILD_ID" && {
    git push origin HEAD || true
  }
  cd ..
  rm -rf gh-pages
fi

echo "Finished build with status: $EXIT_STATUS"

exit $EXIT_STATUS
