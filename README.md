# react-native-bluetooth-state
Answering the question of "Is my bluetooth on?" in React Native.

This fork adds the functionality for Android, the README is adapted from the original one.

## Installation

React Native >=0.7.0 is needed.

Install it via npm:

```
$ npm install --save https://github.com/Artirigo/react-native-bluetooth-state.git
```

Follow the [React Native documentation for Linking Libraries](https://facebook.github.io/react-native/docs/linking-libraries-ios.html).

## Usage

```javascript
import BluetoothState from 'react-native-bluetooth-state';

const stateChangeHandler = bluetoothState => {
  // bluetoothState can either be "on", "off", "unknown", "unauthorized" or "unsupported", on iOS also "resetting"
};

// subscribe to changes
BluetoothState.subscribe(stateChangeHandler);

// Initialize needs to be called otherwise we don't get the initial state
BluetoothState.initialize();

// unsubscribe specific handler
BluetoothState.unsubscribe(stateChangeHandler);

// remove all change handlers
BluetoothState.clear();
```

## License
MIT
