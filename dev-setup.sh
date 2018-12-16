#!/usr/bin/env bash

# publish module to the local yalc-"registry"
node_modules/.bin/yalc publish

# change working directory to Example
cd Example

# install dependencies
yarn install --pure-lockfile

# use local library from the yalc-"registry"
../node_modules/.bin/yalc link react-native-bluetooth-state

# link binaries in case they aren’t linked yet
react-native link react-native-bluetooth-state
