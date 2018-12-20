#!/usr/bin/env bash

# publish module to the local yalc-"registry"
node_modules/.bin/yalc publish

# change working directory to Example
cd Example

# use local library from the yalc-"registry"
../node_modules/.bin/yalc link react-native-bluetooth-state

# link binaries in case they aren’t linked yet
# react-native link react-native-bluetooth-state
node node_modules/react-native/local-cli/cli.js link react-native-bluetooth-state
