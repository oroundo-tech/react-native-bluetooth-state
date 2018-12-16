#!/usr/bin/env bash

node_modules/.bin/yalc publish

cd Example
../node_modules/.bin/yalc link react-native-bluetooth-state
react-native link react-native-bluetooth-state
